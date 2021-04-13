package com.stir.cscu9t4assignment2021;
/**
 * @author 2836012
 */
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

        if(editor.isEmpty()) {
            this.editor = "N/A";
        }else {
            this.editor = editor;
        }
    }

    public String getBook() {
        return book;
    }

    public String getEditor() {
        return editor;
    }

    @Override
    public String getCitation() {
        String result = String.join(",  ",getAuthors()) + ", "  + "\"" + getTitle() + ",\" " +
                getBook() + ", " + getEditor() + ", \n" + getPublisher() + ", " + getPubyear() + ". doi:"
                + getDoi() + ", " + getDateAdded() + "\n";
        return result;
    }
}
