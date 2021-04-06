package com.stir.cscu9t4assignment2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
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
        String message = "";
        String publisherLookUp = ct.stream()
                .filter(ref ->  ref.getPublisher().equals(publisher))
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

    public String importMany(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] cols = line.split(",");

        }
        return line;
    }

}
