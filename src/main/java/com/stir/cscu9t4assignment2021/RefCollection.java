package com.stir.cscu9t4assignment2021;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RefCollection {
    private String filePath;
    private String message;

    private int errorCounter = 0;
    private List<Ref> ct;
    private List<Ref> falseImports;


    public RefCollection() {
        ct = new ArrayList<Ref>();
        falseImports = new ArrayList<Ref>();
    } //constructor

    // add a record to the list


    public void addCite(Ref ref) {
        ct.add(ref);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<Ref> allEntries(){
        return ct;
    }

    public String lookUpByJournal(String journal) {

        String message = "";
        String journalLookup = ct.stream()
                .filter(ref -> ref instanceof RefJournal && ((RefJournal) ref).getJournal().equals(journal))
                .sorted(Comparator.comparing(refAuthors -> refAuthors.getAuthors()[0]))
                .map(Ref::getCitation)
                .collect(Collectors.joining("\n"));

        return message = (journalLookup.isEmpty()) ? "There are no Journals with this name." : journalLookup;
    }


    public String lookUpByVenue(String venue) {
        String message = "";
        String venueLookUp = ct.stream()
                .filter(ref -> ref instanceof RefConference && ((RefConference) ref).getVenue().equals(venue))
                .sorted(Comparator.comparing(refAuthors -> refAuthors.getAuthors()[0]))
                .map(Ref::getCitation)
                .collect(Collectors.joining("\n"));


        return message = (venueLookUp.isEmpty()) ? "There are no Conferences with this name." : venueLookUp;
    }


    public String lookUpByPublisher(String publisher) {

        if(publisher.isEmpty()){
            publisher = "N/A";
        }

        String message = "";
        String finalPublisher = publisher;
        String publisherLookUp = ct.stream()
                .filter(ref -> ref.getPublisher().equals(finalPublisher))
                .sorted(Comparator.comparing(refAuthors -> refAuthors.getAuthors()[0]))
                .map(Ref::getCitation)
                .collect(Collectors.joining("\n"));

        return message = (publisherLookUp.isEmpty()) ? "There are no Publishers with this name." : publisherLookUp;
    }


    public int getNumberOfRefs(String type) {
        return ct.size();
    }

    public String exportAll() {
        return null;
    }

    public String exportXML(String savePath) {
        XmlWriter xmlWriter = new XmlWriter();

        xmlWriter.setXmlPath(savePath);
        xmlWriter.writeXmlFile(ct);

        return "Your file is located to: " + savePath;
    }


    public String exportToText(String source,String savePath) {

        File newExportedText = new File(savePath);
        try {
            FileWriter fw = new FileWriter(newExportedText, false);
            fw.write(source);
            fw.close();
        } catch (IOException ioe) {
            System.out.println("");
        }
        return "Your file is located to: " + savePath;
    }

    public String importMany() throws IOException {

        AtomicInteger countInvDates = new AtomicInteger();

        if(importType() == 0) {
            setMessage("Invalid CSV Header. Please follow the instructions");
            return getMessage();
        }



        Files.lines(Path.of(filePath))
                .skip(1)
                .map(objectToAdd)
                .filter(Objects::nonNull)
                .forEach(ct -> {
                    //could be used for validation in any field (csv will be checked in advance though for not optional fields)
                    if (!validateDate(ct.getDateAdded())) {
                        falseImports.add(ct);
                        countInvDates.getAndIncrement();
                    }
                    else {
                        addCite(ct);
                    }
                });
        if(errorCounter > 0 && countInvDates.intValue() > 0) {
            setMessage("The file has been imported but " + errorCounter + " entries that included empty fields on some of the compulsory fields and \n"
                    + countInvDates + " entries were having invalid dates couldn't be imported to the system");
            return getMessage();
        }

        if (countInvDates.intValue() > 0) {
            setMessage("The file has been imported successfully \n" +
            "BUT " + countInvDates + " entries couldn't be imported due to invalid date");
            return getMessage();
        }
        if(errorCounter > 0 ) {
            setMessage("The file has been imported successfully \n" + errorCounter +" entries included empty fields for the compulsory fields and therefore they couldn't be added into the database");
            errorCounter = 0;
        }
        else {
            setMessage("The file has been imported successfully \n" + " New entries has been imported to the database");
        }
        return getMessage();
    }



    private Function<String, Ref> objectToAdd = (line) -> {

        String[] cols = line.split(",", -1);

        if(isNull(cols[0],cols[1])){
            errorCounter++;
            return null;
        }
        try {
            Integer.parseInt(cols[2]);
        } catch(Exception e) {
            errorCounter++;
            return null;
        }

        int dayAdded = 0;
        int monthAdded = 0;
        int yearAdded = 0;

        String[] dateSplit = cols[5].split("/");
        try {
            dayAdded = Integer.parseInt(dateSplit[0]);
            monthAdded = Integer.parseInt(dateSplit[1]);
            yearAdded = Integer.parseInt(dateSplit[2]);

        } catch (NumberFormatException exception) {
            if (dateSplit[0].equals("") || cols[5].isEmpty()) {
                dayAdded = 0;
                monthAdded = 0;
                yearAdded = 0;
            }
        }


        if (importType() == 1) {
            if (notNull(cols[6], cols[7], cols[8])) {
                return importJournal(cols, dayAdded, monthAdded, yearAdded);
            }
        }
        if (importType() == 2) {
            if (notNull(cols[6], cols[7])) {
                return importConference(cols, dayAdded, monthAdded, yearAdded);
            }
        }
        if (importType() == 3) {
            return importBook(cols, dayAdded, monthAdded, yearAdded);
        }
        if (importType() == 4) {
            if (notNull(cols[6], cols[7], cols[8])) {
                return importJournal(cols, dayAdded, monthAdded, yearAdded);
            }
            if (notNull(cols[9], cols[10])) {
                return importConference(cols, dayAdded, monthAdded, yearAdded);
            }
            if(!cols[11].isEmpty()) {
                return  importBook(cols, dayAdded, monthAdded, yearAdded);
            }
            else{
                errorCounter++;
                return null;
            }

            //redundant else in order to execute the function (caught by importType == 0 validation)
            //null will be inserted (and immediately omitted if user doesn't follow the instructions
        }
        else{
            errorCounter++;
            return null;
        }

    };


    private boolean notNull(String... args) {
        for (String arg : args) {
            if (arg.equals("")) {
                return false;
            }
        }
        return true;
    }

    private boolean isNull(String... args) {
        for (String arg : args) {
            if (arg.equals("")) {
                return true;
            }
        }
        return false;
    }


    public boolean validateDate(String date) {
        try {
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );
            return true;
        } catch (DateTimeException e) {
            System.out.println(e);
            return false;
        }
    }


    public int importType() {

        String[] checkFirstLine = {};

        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(getFilePath()));
            line = br.readLine();
            System.out.println(line);
            checkFirstLine = line.split(",");
        } catch (IOException e) {
            System.out.println("Invalid File");
        }

        if (isJournal(checkFirstLine)) {
            return 1;
        }
        if (isConference(checkFirstLine)) {
            return 2;
        }
        if (isBook(checkFirstLine)) {
            return 3;
        }
        if (containsAll(checkFirstLine)) {
            return 4;
        } else {
            return 0;
        }
    }


    private void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    private boolean isJournal(String[] firstLine) {
        return firstLine.length == 9 && firstLine[6].equals("journal");  //for the unique ones replaceAll and split and if empty for the ones that i can have replace with N/A if any of the others apply notNull
    }                                                                    // store the whole entry to another array

    private boolean isConference(String[] firstLine) {
        return firstLine.length == 8 && firstLine[6].equals("venue");
    }

    private boolean isBook(String[] firstLine) {
        return firstLine.length == 8 && firstLine[6].equals("bookTitle");
    }

    private boolean containsAll(String[] firstLine) {
        return (firstLine.length == 13);
    }


    //make the method to trim and add in any case
    private RefJournal importJournal(String[] lineCols, int dayAdded, int monthAdded, int yearAdded) {

        return new RefJournal(
                lineCols[0],
                lineCols[1].split(";"),
                Integer.parseInt(lineCols[2]),
                lineCols[3],
                lineCols[4],
                dayAdded,
                monthAdded,
                yearAdded,
                lineCols[6],
                Integer.parseInt(lineCols[7]),
                Integer.parseInt(lineCols[8])
        );
    }

    private RefConference importConference(String[] lineCols, int dayAdded, int monthAdded, int yearAdded) {

        return new RefConference(
                lineCols[0],
                lineCols[1].split(";"),
                Integer.parseInt(lineCols[2]),
                lineCols[3],
                lineCols[4],
                dayAdded,
                monthAdded,
                yearAdded,
                (lineCols.length == 8) ? lineCols[6] : lineCols[9], //if type is conf set to 6 else this will be called only for type 4
                (lineCols.length == 8) ? lineCols[7] : lineCols[10]
        );
    }

    private RefBookChapter importBook(String[] lineCols, int dayAdded, int monthAdded, int yearAdded ) {

        return new RefBookChapter(
                lineCols[0],
                lineCols[1].split(";"),
                Integer.parseInt(lineCols[2]),
                lineCols[3],
                lineCols[4],
                dayAdded,
                monthAdded,
                yearAdded,
                (lineCols.length == 8) ? lineCols[6] : lineCols[11], //if type is conf set to 6 else this will be called only for type 4
                (lineCols.length == 8) ? lineCols[7] : lineCols[12]
        );
    }


}
