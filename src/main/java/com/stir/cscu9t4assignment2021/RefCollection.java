package com.stir.cscu9t4assignment2021;

import com.stir.cscu9t4assignment2021.GuiComponents.TextAreaPanel;
import com.sun.jdi.IntegerType;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.XMLFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RefCollection {
    private List<Ref> ct;


    public RefCollection() {
        ct = new ArrayList<Ref>();
    } //constructor

    // add a record to the list


    public void addCite(Ref ref) {
        ct.add(ref);
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
        for (Ref ref: ct) {
            System.out.println(ref);
        }
        String message = "";
        String publisherLookUp = ct.stream()
                .filter(ref -> ref.getPublisher().equals(publisher))
                .sorted(Comparator.comparing(refAuthors -> refAuthors.getAuthors()[0]))
                .map(Ref::getCitation)
                .collect(Collectors.joining("\n"));


        return message = (publisherLookUp.isEmpty()) ? "There are no Publishers with this name." : publisherLookUp;
    }


    public int getNumberOfRefs(String type) {
        return ct.size();
    }

    public String exportAll() {
       return null ;
    }

    public String exportXML() {
        XmlWriter xmlWriter = new XmlWriter();

        xmlWriter.writeXmlFile(ct);

        return "hola";
    }

    public String exportToText(String source) {


        File newExportedText = new File("exportedToText.txt");
        try {
            FileWriter fw = new FileWriter(newExportedText,false);
            fw.write(source);
            fw.close();
        }
        catch (IOException ioe) {
            System.out.println("");
        }
        return "Your file has been created \n Good luck finding it";
    }

    public String importMany(String filePath) throws IOException {

            Files.lines(Path.of(filePath))
                    .skip(1)
                    .map(objectToAdd)
                    .forEach(ct::add);
        return "The file has been imported successfully \n" + " New entries has been imported to the database";
    }


//    private void objectToAdd(String line) {
//
//        RefJournal refJournal;
//
//        TextAreaPanel output = new TextAreaPanel();
//
//        String[] cols = line.split(",");
//
//        String t, p, doi, date, jn, c, l, bt, e;
//        String[] a;
//        int py, v, i;
//
//
//        int dayAdded = 0;
//        int monthAdded = 0;
//        int yearAdded = 0;
//        String[] dateSplit = cols[5].split("/");
//        try {
//            dayAdded = Integer.parseInt(dateSplit[0]);
//            monthAdded = Integer.parseInt(dateSplit[1]);
//            yearAdded = Integer.parseInt(dateSplit[2]);
//            String dateToValidate = (dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0]);
//
//            if (!validateDate(dateToValidate)) {
//                throw new IllegalArgumentException();
//            }
//        } catch (NumberFormatException exception) {
//            if (dateSplit[0].equals("")) {
//                dayAdded = 0;
//                monthAdded = 0;
//                yearAdded = 0;
//            } else {
//                output.setText("The CSV contains dates that include invalid characters");
//            }
//        } catch (IllegalArgumentException exception) {
//            output.setText("The CSV that you provided includes dates that are not valid \n" +
//                    exception.getMessage());
//        }
//
//        py = Integer.parseInt(cols[2]);
//
//        if (notNull(cols[6], cols[7], cols[8])) {
//            refJournal = new RefJournal(
//                    t = cols[0],
//                    a = cols[1].split(";"),
//                    py,
//                    p = cols[3],
//                    doi = cols[4],
//                    dayAdded,
//                    monthAdded,
//                    yearAdded,
//                    jn = cols[6],
//                    v = Integer.parseInt(cols[7]),
//                    i = Integer.parseInt(cols[8])
//            );
//            ct.add(refJournal);
//        }
//    }

    private Function<String, Ref> objectToAdd = (line) -> {


        TextAreaPanel output = new TextAreaPanel();

        String[] cols = line.split(",", -1);
//        List<String> cols = Arrays.asList(cols1);

        String t, p, doi, date, jn, c, l, bt, e;
        String[] a;
        int py, v, i;


        int dayAdded = 0;
        int monthAdded = 0;
        int yearAdded = 0;

        String[] dateSplit = cols[5].split("/");
        try {
            dayAdded = Integer.parseInt(dateSplit[0]);
            monthAdded = Integer.parseInt(dateSplit[1]);
            yearAdded = Integer.parseInt(dateSplit[2]);
            String dateToValidate = (dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0]);

            if (!validateDate(dateToValidate)) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException exception) {
            if (dateSplit[0].equals("")) {
                dayAdded = 0;
                monthAdded = 0;
                yearAdded = 0;
            } else {
                output.setText("The CSV contains dates that include invalid characters");
            }
        } catch (IllegalArgumentException exception) {
            output.setText("The CSV that you provided includes dates that are not valid \n" +
                    exception.getMessage());
        }

        py = Integer.parseInt(cols[2]);
        if (notNull(cols[6], cols[7], cols[8])) {
            RefJournal refJournal;
            return refJournal = new RefJournal(
                    t = cols[0],
                    a = cols[1].split(";"),
                    py,
                    p = cols[3],
                    doi = cols[4],
                    dayAdded,
                    monthAdded,
                    yearAdded,
                    jn = cols[6],
                    v = Integer.parseInt(cols[7]),
                    i = Integer.parseInt(cols[8])
            );

        }

        if (notNull(cols[9], cols[10])) {
            Ref refConference;
            return refConference = new RefConference(
                    t = cols[0],
                    a = cols[1].split(";"),
                    py,
                    p = cols[3],
                    doi = cols[4],
                    dayAdded,
                    monthAdded,
                    yearAdded,
                    cols[9],
                    cols[10]
            );
        }

        if (!cols[11].equals("") || !cols[12].equals("")) {
            RefBookChapter refBookChapter;
            return refBookChapter = new RefBookChapter(
                    t = cols[0],
                    a = cols[1].split(";"),
                    py,
                    p = cols[3],
                    doi = cols[4],
                    dayAdded,
                    monthAdded,
                    yearAdded,
                    cols[11],
                    cols[12]
            );

        } else {

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


}
