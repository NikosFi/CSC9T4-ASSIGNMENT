package com.stir.cscu9t4assignment2021;

public class RefBookChapter extends Ref {

    private String book;
    private String editor;

    public RefBookChapter(String title, String[] authors, String doi, String publisher, int pubyear, String book, String editor) {
        super(title, authors, doi, publisher, pubyear);
        this.book = book;
        this.editor = editor;
    }

    public RefBookChapter(String title, String[] authors, String doi, String publisher, int pubyear, int day, int month, int year, String book, String editor) {
        super(title, authors, doi, publisher, pubyear, day, month, year);
        this.book = book;
        this.editor = editor;
    }

    @Override
    public String getCitation() {
        return super.getCitation();
    }
}
