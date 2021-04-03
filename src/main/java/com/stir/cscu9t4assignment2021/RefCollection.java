package com.stir.cscu9t4assignment2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        String result = "";
        for (Ref ref : ct) {
            if (ref instanceof RefJournal && ((RefJournal) ref).getJournal().equals(journal)) { //if object contains an instance of a child  instance of can find it for you
                result = (ref.getCitation());
            } else {
                result = "-1";
            }
        }
        return result;
    }


    public String lookUpByVenue(String venue) {
        return null;
    }

    public String lookUpByPublisher(String publisher) {
        return null;
    }

    public int getNumberOfRefs(String type) {
        return 0;
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
