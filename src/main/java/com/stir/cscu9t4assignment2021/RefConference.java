package com.stir.cscu9t4assignment2021;

public class RefConference extends Ref {

    private String venue;
    private String location;

    public RefConference(String title, String[] authors, String doi, String publisher, int pubyear, String venue, String location) {
        super(title, authors, doi, publisher, pubyear);
        this.venue = venue;
        this.location = location;
    }

    public RefConference(String title, String[] authors, String doi, String publisher, int pubyear, int day, int month, int year, String venue, String location) {
        super(title, authors, doi, publisher, pubyear, day, month, year);
        this.venue = venue;
        this.location = location;
    }

    @Override
    public String getCitation() {
        return super.getCitation();
    }
}
