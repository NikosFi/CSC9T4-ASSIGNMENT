package com.stir.cscu9t4assignment2021;

import java.util.Arrays;
import java.util.Date;

public class RefConference extends Ref {

    private String venue;
    private String location;
    private Date dateAdded;

    public RefConference(String title, String[] authors,int pubyear, String publisher, String doi, String venue, String location) {
        super(title, authors,pubyear, publisher, doi);
        this.venue = venue;
        this.location = location;
    }

    public RefConference(String title, String[] authors,int pubyear, String publisher, String doi, int day, int month, int year, String venue, String location) {
        super(title, authors,pubyear, publisher, doi, day, month, year);
        this.venue = venue;
        this.location = location;


        this.dateAdded = (new Date(year-1900,month-1,day));

    }

    public String getVenue(){
        return venue;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String getCitation() {
        String result = String.join(",  ",getAuthors()) + ", "  + "\"" + getTitle() + ",\" " +
               getVenue() + ", " + getLocation() + ", \n" + getPublisher() + ", " + getPubyear() + ". doi:"
                + getDoi() + ", " + getDateAdded() + "\n";
        return result;
    }
}
