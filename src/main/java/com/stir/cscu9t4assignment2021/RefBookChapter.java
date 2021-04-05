package com.stir.cscu9t4assignment2021;

public class RefBookChapter extends Ref {

    private String book;
    private String editor;

    public RefBookChapter(String title, String[] authors,int pubyear, String publisher, String doi, String book, String editor) {
        super(title, authors,pubyear, publisher, doi);
        this.book = book;
        this.editor = editor;
    }

    public RefBookChapter(String title, String[] authors,int pubyear, String publisher, String doi, int day, int month, int year, String book, String editor) {
        super(title, authors,pubyear, publisher, doi, day, month, year);
        this.book = book;
        this.editor = editor;
    }

    @Override
    public String getCitation() {
        return super.getCitation();
    }
}
