package com.stir.cscu9t4assignment2021;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.function.ToDoubleBiFunction;

public class RefJournal extends Ref {
    private String journal;
    private int volume;
    private int issue;

    private String dateAdded;

    public RefJournal(String title, String[] authors,int pubyear, String publisher, String doi,String journal, int volume, int issue) {
        super(title, authors,pubyear, publisher, doi);
        this.journal = journal;
        this.volume = volume;
        this.issue = issue;
    }

    public RefJournal(String title, String[] authors,int pubyear, String publisher, String doi,  int day, int month, int year, String journal, int volume, int issue) {
        super(title, authors,pubyear, publisher, doi, day, month, year);
        this.journal = journal;
        this.volume = volume;
        this.issue = issue;
    }



    public String getJournal(){
        return journal;
    }

    public int getVolume() {
        return volume;
    }

    public int getIssue() {
        return issue;
    }

    @Override
    public String getCitation() {
        String result =  String.join(",  ",getAuthors()) + ", "  + "\"" + getTitle() + ",\" " +
                getJournal() + ", vol." + getVolume() + ", no." + getIssue() + ", \n" + getPublisher() + ", " + getPubyear() + ". doi:"
                + getDoi() + ", " + getDateAdded() + "\n";
        return result;
    }


}
