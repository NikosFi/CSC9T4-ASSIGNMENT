package com.stir.cscu9t4assignment2021;

public class RefConference extends Ref {

    private String venue;
    private String location;

    public RefConference(String title, String[] authors,int pubyear, String publisher, String doi, String venue, String location) {
        super(title, authors,pubyear, publisher, doi);
        this.venue = venue;
        this.location = location;
    }

    public RefConference(String title, String[] authors,int pubyear, String publisher, String doi, int day, int month, int year, String venue, String location) {
        super(title, authors,pubyear, publisher, doi, day, month, year);
        this.venue = venue;
        this.location = location;
    }

    @Override
    public String getCitation() {
        return super.getCitation();
    }
}
