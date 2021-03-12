package com.stir.cscu9t4assignment2021;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

//  ***************************************************      ___________________________________________________________
//  *                                                 *      |                                                         |
//  ***************************************************      -----------------------------------------------------------
/**
 * @author saemundur
 */
public class RefSystemGUI extends JFrame {

//  ***************************************************
//  *     attribute declaration                       *                                                               |
//  ***************************************************

    private JLabel labTitle = new JLabel("Title:");
    private JLabel labDate = new JLabel("Date:");
    private JLabel labPublisher = new JLabel("Publisher");
    private JLabel labDOI = new JLabel("DOI");
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * For the j combobox Journal selection
     */
    private JLabel labJournal = new JLabel("Journal Name:");
    private JLabel labVolumeIssue = new JLabel("Volume Issue:");

    /**
     * For the j combobox Conference selection
     */
    private JLabel labConferenceName = new JLabel("Conference:");
    private JLabel labLocation = new JLabel("Location:");

    /**
     * For the j combobox Book selection
     */
    private JLabel labBookTitle = new JLabel("Book title:");
    private JLabel labEditor = new JLabel("Editor Name:");

    /**
     * need to add the authors somehow still didn't decide
     */
    private JTextField title = new JTextField(35);
    private final String publicationType[] = new String[]{"Journal", "Conference Paper", "Book Chapters"};
    private JComboBox<String> typeList = new JComboBox<>(publicationType);
    private JFormattedTextField date = new JFormattedTextField(format);
    private JTextField publisher = new JTextField(12);
    private JTextField doi = new JTextField(10);
    private final String findAllList[] = new String[]{"Journal", "Conference Venue", "Publisher"};
    private JComboBox findAll = new JComboBox(findAllList);
    private JButton btnInsert = new JButton("Insert");
    private JTextArea outputArea = new JTextArea(20, 50);


    /**
     * For the j combobox index 1
     */
    private JTextField journalName = new JTextField(7);
    private JTextField volumeIssue = new JTextField(7);

    /**
     * For the j combobox index 1
     */
    private JTextField conference = new JTextField(7);
    private JTextField location = new JTextField(7);

    /**
     * For the j combobox index 1
     */
    private JTextField bookTitle = new JTextField(7);
    private JTextField editor = new JTextField(7);

//    private RefCollection bibliography = new RefCollection();

    public static void main(String[] args) {
        RefSystemGUI applic = new RefSystemGUI();

    }


    public RefSystemGUI() {
        super("Bibliography");
        setLayout(null);
        setSize(1200, 500);

        typeList.setRenderer(new DropDown.MyComboBoxRenderer("Type Of Publication"));
        typeList.setSelectedIndex(-1);
        typeList.setBounds(5,5,200,20);
        add(typeList);
        labTitle.setBounds(5,40,150,20);
        title.setBounds(120,40,150,20);
        add(labTitle);
        add(title);

        typeList.addActionListener(e -> {



            switch (typeList.getSelectedIndex()) {

                case 0:
                    addJournalFields();
                    removeBookFields();
                    removeConferenceFields();

                    String message="";

                    message = "I pressed the Journal +5 wisdom";
                    outputArea.setText(message);
                    repaint();
                    break;
                case 1:
                    removeJournalFields();
                    removeBookFields();
                    labConferenceName.setBounds(5,200,150,20);
                    conference.setBounds(120,200,150,20);
                    add(labConferenceName);
                    labConferenceName.setVisible(true);
                    add(conference);
                    labLocation.setBounds(5,240,150,20);
                    location.setBounds(120,240,150,20);
                    add(labLocation);
                    add(location);
                    repaint();

                    break;
                case 2:
                    removeJournalFields();
                    removeConferenceFields();
                    labBookTitle.setBounds(5,200,150,20);
                    bookTitle.setBounds(120,200,150,20);
                    add(labBookTitle);
                    add(bookTitle);
                    labEditor.setBounds(5,240,150,20);
                    editor.setBounds(120,240,150,20);
                    add(labEditor);
                    add(editor);

                    repaint();
                    break;
            }
        });
        labDate.setBounds(5,80,150,20);
        date.setBounds(120,80,150,20);
        add(labDate);
        add(date);

        labPublisher.setBounds(5,120,150,20);
        publisher.setBounds(120,120,150,20);
        add(labPublisher);
        add(publisher);

        labDOI.setBounds(5,160,150,20);
        doi.setBounds(120,160,150,20);
        add(labDOI);
        add(doi);

        findAll.setBounds(500,320,150,20);
        add(findAll);
        findAll.addActionListener(e -> {

        });
        outputArea.setBounds(300 , 10 ,390  , 300);
        add(outputArea);

        btnInsert.setBounds(300,320,150,20);
        add(btnInsert);
        btnInsert.addActionListener(e -> {
            String message = "";
                message = "nothing to insert";
            outputArea.setText(message);
        });



        outputArea.setVisible(true);


        setVisible(true);


    }

    public void addJournalFields(){
        labJournal.setBounds(5,200,150,20);
        journalName.setBounds(120,200,150,20);
        add(labJournal);
        add(journalName);

        labVolumeIssue.setBounds(5,240,150,20);
        volumeIssue.setBounds(120,240,150,20);
        add(labVolumeIssue);
        add(volumeIssue);
    }
    /**
     * method to remove the Journal Fields
     */

    public void removeJournalFields() {

        remove(labJournal);
        remove(journalName);

        remove(labVolumeIssue);
        remove(volumeIssue);

    }

    /**
     * method to remove the Conference Fields
     */
    public void removeConferenceFields() {

        remove(labConferenceName);
        remove(conference);

        remove(labLocation);
        remove(location);

    }

    /**
     * method to remove the Book Fields
     */
    public void removeBookFields() {
        remove(labBookTitle);
        remove(bookTitle);

        remove(labEditor);
        remove(editor);
    }


}