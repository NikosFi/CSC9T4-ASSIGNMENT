package com.stir.cscu9t4assignment2021;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Ref {

    private String title;
    private String[] authors;
    private String doi;
    private String publisher;
    private int pubyear;

    private Date dateAdded;
    private DateTimeFormatter df;


    public Ref(String title, String[] authors,int pubyear, String publisher, String doi) {
        this.title = title;
        this.authors = authors;
        this.doi = doi;
        this.publisher = publisher;
        this.pubyear = pubyear;
    }

    //12122012


    public Ref(String title, String[] authors,int pubyear, String publisher, String doi, int day, int month, int year) {
        this.title = title;
        this.authors = authors;
        this.doi = doi;
        this.publisher = publisher;
        this.pubyear = pubyear;


        if (day == 0 && month == 0 && year == 0) {
            dateAdded = new Date();
        } else {
            dateAdded = new Date(year - 1900, month - 1, day);
            //find what to use for dd/mm/year
        }

    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDateAdded() {

        SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yyyy ");

        return sdf.format(dateAdded);


    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public int getPubyear() {
        return pubyear;
    }

    public void setPubyear(int pubyear) {
        this.pubyear = pubyear;
    }



    public String getCitation() {
        String result = getTitle();
        return result;
    }


}
