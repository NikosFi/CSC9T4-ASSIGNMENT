package com.stir.cscu9t4assignment2021;

import com.stir.cscu9t4assignment2021.GuiComponents.*;

import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

/**
 * @author null
 */
public class RefSystemGUI extends JFrame {

    // Global GUI attributes
    //Labels
    private JLabel labTitle = new JLabel("Title:");
    private JLabel labAuthors = new JLabel("Authors");
    private JLabel labPubYear = new JLabel("Publication Year:");
    private JLabel labPublisher = new JLabel("Publisher");
    private JLabel labDOI = new JLabel("DOI");
    //TextFields
    private JTextField title = new JTextField(35);
    private JTextField authors = new JTextField(25);
    private JTextField pubYear = new JTextField(25);
    private JTextField publisher = new JTextField(12);
    private JTextField doi = new JTextField(10);
    //ComboBoxes
    private final String publicationType[] = new String[]{"Journal", "Conference Paper", "Book Chapters"};
    private JComboBox<String> typeList = new JComboBox<>(publicationType);
    private final String findAllList[] = new String[]{"Journal", "Conference Venue", "Publisher"};
    private JComboBox findAll = new JComboBox(findAllList);
    //Button
    private JButton editAuthors = new JButton("Add Authors");
    private JButton btnInsert = new JButton("Insert");
    //create Instance to the class that provides the functionality
    private RefCollection bibliography = new RefCollection();

    //Journal GUI attributes
    private JLabel labJournal = new JLabel("Journal Name:");
    private JLabel labVolume = new JLabel("Volume:");
    private JLabel labIssue = new JLabel("Issue:");
    //TextFields
    private JTextField journalName = new JTextField(7);
    private JTextField volume = new JTextField(7);
    private JTextField issue = new JTextField(7);

    //Conference GUI attributes
    //Labels
    private JLabel labConferenceName = new JLabel("Conference:");
    private JLabel labLocation = new JLabel("Location:");
    //TextFields
    private JTextField conference = new JTextField(7);
    private JTextField location = new JTextField(7);

    //Book GUI attributes
    //Labels
    private JLabel labBookTitle = new JLabel("Book title:");
    private JLabel labEditor = new JLabel("Editor Name:");
    //TextFields
    private JTextField bookTitle = new JTextField(7);
    private JTextField editor = new JTextField(7);

    // Date GUI attributes
    //Labels
    private JLabel lday = new JLabel("Day");
    private JLabel lmonth = new JLabel("Month");
    private JLabel lyear = new JLabel("Year");
    //TextFields
    private JTextField day = new JTextField();
    private JTextField month = new JTextField(3);
    private JTextField year = new JTextField(3);


    BottomPanel btp = new BottomPanel();
    MenuTopBarPanel mtbp = new MenuTopBarPanel();
    FunctionalityPanel fnp = new FunctionalityPanel();

    //main
    public static void main(String[] args) {
        RefSystemGUI applic = new RefSystemGUI();
    }

    //constructor
    public RefSystemGUI() {

        super("Bibliography");

        Border blackline = BorderFactory.createLineBorder(Color.black);
        TextAreaPanel txtAreaPanel = new TextAreaPanel();
        // dbTable second half of central panel
        JTable dbTable = new JTable();
        dbTable.setSize(new Dimension(500, 500));
        final Object[] JOURNAL_HEADER = {"Title", "Authors", "DOI", "Publisher", "PubYear", "Journal", "Volume", "Issue", "Date Added"};
        final Object[][] JOURNAL_ENTRIES = {{"title", new String[]{"ASDF"}, "DSAF", "String", 231, "ADSF", 213, 123},
                {"title", new String[]{"ASDF"}, "DSAF", "String", 231, "ADSF", 213, 123}};

        DefaultTableModel tableModel = new DefaultTableModel(JOURNAL_ENTRIES, JOURNAL_HEADER) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        dbTable.setModel(tableModel);
        tableModel.addRow(new Object[]{"Column 2", "Column 3"});
        tableModel.addRow(new Object[]{});
        tableModel.setNumRows(15);
        dbTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
        dbTable.getTableHeader().setReorderingAllowed(false); // MAKE COLUMNS NOT ABLE TO MOVE

        JScrollPane tableScrollPane = new JScrollPane(dbTable);
        tableScrollPane.setPreferredSize(new Dimension(500, 300));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Citation Entries",
                TitledBorder.CENTER, TitledBorder.TOP));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, txtAreaPanel, tableScrollPane); //central panel/output areas
        splitPane.setBackground(Color.LIGHT_GRAY);
        splitPane.setBorder(blackline);
        splitPane.setDividerLocation(400);
        splitPane.setEnabled(false);


        //set bounds
        typeList.setBounds(5, 20, 200, 20);
        typeList.setRenderer(new DropDown.MyComboBoxRenderer("Type Of Publication", 102, 255, 102));
        typeList.setSelectedIndex(-1);
        labTitle.setBounds(5, 80, 150, 20);
        title.setBounds(70, 80, 150, 20);
        labAuthors.setBounds(5, 110, 150, 20);
        authors.setBounds(70, 110, 150, 20);
        editAuthors.setBounds(230, 110, 104, 20);
        labPubYear.setBounds(5, 140, 150, 20);
        pubYear.setBounds(110, 140, 110, 20);
        labPublisher.setBounds(5, 170, 150, 20);
        publisher.setBounds(70, 170, 150, 20);
        labDOI.setBounds(5, 200, 150, 20);
        doi.setBounds(70, 200, 150, 20);
        lday.setBounds(5,240,30,20);
        day.setBounds(30,240,30,20);
        lmonth.setBounds(65,240,60,20);
        month.setBounds(105,240,30,20);
        lyear.setBounds(140,240,30,20);
        year.setBounds(170,240,50,20);

        //edit Authors
        editAuthors.addActionListener(e -> {
            JTextField xField = new JTextField(40);
            xField.setBounds(20, 20, 250, 20);
            JTextArea yField = new JTextArea();
            yField.setBounds(5, 50, 360, 300);

            JLabel xlab = new JLabel("Add authors");
            xlab.setBounds(100, 5, 150, 15);
            JPanel myPanel = new JPanel();
            myPanel.setLayout(null);
            myPanel.add(xField);
            myPanel.add(xlab);

            myPanel.add(yField);
            UIManager.put("OptionPane.minimumSize", new Dimension(400, 400));
            JOptionPane.showConfirmDialog(null, myPanel,
                    "Add Authors ",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
        });
        // edit Authors end

        //fix tab key (due to incorrect configuration of the layouts)
        typeList.setNextFocusableComponent(title);
        title.setNextFocusableComponent(authors);
        authors.setNextFocusableComponent(editAuthors);

        // fnp add:
        fnp.add(typeList);
        fnp.add(labTitle);
        fnp.add(title);
        fnp.add(editAuthors);
        fnp.add(labAuthors);
        fnp.add(authors);
        fnp.add(labPubYear);
        fnp.add(pubYear);
        fnp.add(labPublisher);
        fnp.add(publisher);
        fnp.add(labDOI);
        fnp.add(doi);
        fnp.add(lday);
        fnp.add(day);
        fnp.add(lmonth);
        fnp.add(month);
        fnp.add(lyear);
        fnp.add(year);

        typeList.addActionListener(e -> {
            switch (typeList.getSelectedIndex()) {
                // TODO set them in a function
                case 0:
                    labJournal.setBounds(5, 300, 150, 20);
                    journalName.setBounds(95, 300, 130, 20);
                    labIssue.setBounds(5, 330, 150, 20);
                    issue.setBounds(95, 330, 130, 20);
                    labVolume.setBounds(5, 360, 150, 20);
                    volume.setBounds(95, 360, 130, 20);
                    fnp.add(labJournal); fnp.add(journalName); fnp.add(labIssue); fnp.add(issue); fnp.add(labVolume); fnp.add(volume);
                    fnp.removeFields(labConferenceName,labLocation,conference,location);
                    fnp.removeFields(labBookTitle,labEditor,bookTitle,editor);
                    String message = "Journal is pressed";
                    txtAreaPanel.setText(message);
                    repaint();
                    break;
                case 1:
                    fnp.removeFields(labJournal, labIssue, journalName, issue);
                    fnp.remove(labVolume); fnp.remove(volume);
                    fnp.removeFields(labBookTitle,labEditor,bookTitle,editor);
                    labConferenceName.setBounds(5, 300, 115, 20);
                    conference.setBounds(80, 300, 140, 20);
                    fnp.add(labConferenceName);
                    labConferenceName.setVisible(true);
                    fnp.add(conference);
                    labLocation.setBounds(5, 330, 115, 20);
                    location.setBounds(80, 330, 140, 20);
                    fnp.add(labLocation);
                    fnp.add(location);
                    repaint();
                    break;
                case 2:
                    fnp.removeFields(labJournal, labIssue, journalName, issue);
                    fnp.remove(labVolume); fnp.remove(volume);
                    fnp.removeFields(labConferenceName,labLocation,conference,location);
                    labBookTitle.setBounds(5, 300, 150, 20);
                    bookTitle.setBounds(80, 300, 140, 20);
                    fnp.add(labBookTitle);
                    fnp.add(bookTitle);
                    labEditor.setBounds(5, 330, 150, 20);
                    editor.setBounds(85, 330, 135, 20);
                    fnp.add(labEditor);
                    fnp.add(editor);
                    repaint();
                    break;
            }
        });


        findAll.setRenderer(new DropDown.MyComboBoxRenderer("Find All By", 255, 102, 102));
        findAll.setSelectedIndex(-1);
        findAll.setBounds(500, 320, 150, 20);
//        add(findAll);
        repaint();
        findAll.addActionListener(e -> {

            switch (findAll.getSelectedIndex()) {
                case 0:
                    txtAreaPanel.setText(
                            bibliography.lookUpByJournal(journalName.getText()));
                    break;
                case 1:
                    txtAreaPanel.setText(
                            bibliography.lookUpByPublisher(publisher.getText()));
                    break;
            }
        });

        btnInsert.setBounds(20, 420, 250, 30);
        fnp.add(btnInsert);
        btnInsert.addActionListener(e -> {
            String message = "";
            message = addCitation("generic");
            txtAreaPanel.setText(message);
        });


        getContentPane().add(BorderLayout.SOUTH, btp);
        getContentPane().add(BorderLayout.NORTH, mtbp);
        getContentPane().add(BorderLayout.CENTER, splitPane);
        getContentPane().add(BorderLayout.WEST, fnp);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1450, 800));
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(screenSize.width, screenSize.height);


    }

    public String addCitation(String what) {
        String message = "Citation added\n";
        System.out.println("Adding " + what + " citation to the Bibliography");

        // global attribute declaration
        String t = title.getText();
        String[] a = authors.getText().split(";");// need to pass elements from JOptionPane;
        String d = doi.getText();
        String p = publisher.getText();


        int dayAdded = 0;
        int monthAdded = 0;
        int yearAdded = 0;
        try {
            dayAdded = Integer.parseInt(day.getText());
            monthAdded = Integer.parseInt(month.getText());
            yearAdded = Integer.parseInt(year.getText());
            String date = (year.getText() + "-" + month.getText() + "-" + day.getText());
            if (!validateDate(date)) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            Date newDate = new Date();
            if (day.getText().isEmpty() && month.getText().isEmpty() && year.getText().isEmpty()) {
                System.out.println("Date has been formatted automatically to : " + newDate.toString());
                dayAdded = 0;
                monthAdded = 0;
                yearAdded = 0;
            } else {
                System.out.println("Your date is invalid");
                return message = "Your date should contain only valid date characters , range from 1970 to " + (newDate.getYear() + 1900) + " and the input fields should not be empty";
//                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Date");
            return message = "Your date input contains incorrect values for a date ";
        }


        int py = Integer.parseInt(pubYear.getText());

        switch (typeList.getSelectedIndex()) {
            case 0:
                String jn = journalName.getText();
                int i = Integer.parseInt(issue.getText());
                int v = Integer.parseInt(volume.getText());
                RefJournal refJournal = new RefJournal(t,a,py,p,d, dayAdded, monthAdded, yearAdded, jn, i, v);
                bibliography.addCite(refJournal); //remember to try with Gens
                break;
            case 1:
                String cf = conference.getText();
                String l = location.getText();
                RefConference refConference = new RefConference(t,a,py,p,d, cf, l);
                bibliography.addCite(refConference);
                break;
            case 2:
                String bk = bookTitle.getText();
                String e = editor.getText();
                RefBookChapter refBK = new RefBookChapter(t,a,py,p,d, bk, e);
                bibliography.addCite(refBK);
                break;
        }
        return message;
    }

    public static boolean validateDate(String date) {
        try {
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );

            return true;
        } catch (DateTimeException e) {
            System.out.println(e);
            return false;
        }
    }


}

/*************************************************** END OF RefSystemGUI **********************************************/


