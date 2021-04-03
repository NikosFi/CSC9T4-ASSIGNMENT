package com.stir.cscu9t4assignment2021;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.function.ToDoubleBiFunction;

public class RefJournal extends Ref {
    private String journal;
    private int volume;
    private int issue;

    private Date dateAdded;

    public RefJournal(String title, String[] authors, String doi, String publisher, int pubyear, String journal, int volume, int issue) {
        super(title, authors, doi, publisher, pubyear);
        this.journal = journal;
        this.volume = volume;
        this.issue = issue;
    }

    public RefJournal(String title, String[] authors, String doi, String publisher, int pubyear, int day, int month, int year, String journal, int volume, int issue) {
        super(title, authors, doi, publisher, pubyear, day, month, year);
        this.journal = journal;
        this.volume = volume;
        this.issue = issue;

       this.dateAdded = (new Date(year-1900,month-1,day));




    }

    @Override
    public String getCitation() {
        String result = getTitle() + " " + getDoi() + " "  + getPublisher()  +  " " +
                Arrays.toString(getAuthors()) + " " + getPubyear() + " " + getDateAdded();
        return result;
    }

    public String getJournal(){
        return journal;
    }


}
