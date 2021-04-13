package com.stir.cscu9t4assignment2021;

import com.stir.cscu9t4assignment2021.GuiComponents.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * @author 2836012
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
    private JTextField title = new JTextField(60);
    private JTextField authors = new JTextField(60);
    private JTextField pubYear = new JTextField(60);
    private JTextField publisher = new JTextField(60);
    private JTextField doi = new JTextField(60);
    //Text Area
//    JScrollPane outputScrollPane = new JScrollPane();
//    JTextArea output = new JTextArea(20, 98);
    //ComboBoxes
    private final String publicationType[] = new String[]{"Journal", "Conference Paper", "Book Chapters"};
    private JComboBox<String> typeList = new JComboBox<>(publicationType);
    private final String findAllList[] = new String[]{"Journal", "Conference Venue", "Publisher"};
    private JComboBox findAll = new JComboBox(findAllList);
    //Button
    private JButton editAuthors = new JButton("Add Authors");
    ArrayList<String> authHolderArray = new ArrayList<>();
    private JButton btnInsert = new JButton("Add");
    //create Instance to the class that provides the functionality
    private RefCollection bibliography = new RefCollection();

    //Journal GUI attributes
    private JLabel labJournal = new JLabel("Journal Name:");
    private JLabel labVolume = new JLabel("Volume:");
    private JLabel labIssue = new JLabel("Issue:");
    //TextFields
    private JTextField journalName = new JTextField(60);
    private JTextField volume = new JTextField(60);
    private JTextField issue = new JTextField(60);

    //Conference GUI attributes
    //Labels
    private JLabel labConferenceName = new JLabel("Conference:");
    private JLabel labLocation = new JLabel("Location:");
    //TextFields
    private JTextField conference = new JTextField(60);
    private JTextField location = new JTextField(60);

    //Book GUI attributes
    //Labels
    private JLabel labBookTitle = new JLabel("Book title:");
    private JLabel labEditor = new JLabel("Editor Name:");
    //TextFields
    private JTextField bookTitle = new JTextField(60);
    private JTextField editor = new JTextField(60);

    // Date GUI attributes
    //Labels
    private JLabel lday = new JLabel("Day");
    private JLabel lmonth = new JLabel("Month");
    private JLabel lyear = new JLabel("Year");
    //TextFields
    private JTextField day = new JTextField();
    private JTextField month = new JTextField(60);
    private JTextField year = new JTextField(60);
    // findAll search field && labels
    JLabel lselectType = new JLabel("Select Type");
    JLabel lquery = new JLabel("Search for:");
    JTextField searchField = new JTextField(20);
    JButton submitSearchBtn = new JButton("Search");
    //JMenuItemWithEventInitialization
    JMenu file;
    JMenu tools;
    JMenu export_to;
    JMenuItem Open;
    JMenuItem Save;
    JMenuItem importCsv ;
    JMenuItem exportXml;
    JMenuItem exportText;


    BottomPanel btp = new BottomPanel();
    MenuTopBarPanel mtbp = new MenuTopBarPanel();
    FunctionalityPanel fnp = new FunctionalityPanel();
    TextAreaPanel txtAreaPanel = new TextAreaPanel();
    //main
    public static void main(String[] args) {
        RefSystemGUI applic = new RefSystemGUI();
    }

    //constructor
    public RefSystemGUI() {

        super("Bibliography");
        txtAreaPanel.setText("Tips: Use the Tools in the menu bar to import or export \n" +
                            "While exporting make sure that the content you want to export is displayed in the text Area");

        Border blackline = BorderFactory.createLineBorder(Color.black);


        JTable dbTable = new JTable();
        // dbTable second half of central panel
        Object[] columns = new Object[]{"Title", "Authors", "DOI", "Publisher", "Year", "Date","Journal", "Issue", "Volume", "Venue", "Location","Book","Editor"};
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tableModel.setColumnIdentifiers(columns);
        dbTable.setModel(tableModel);
        dbTable.setSize(new Dimension(500, 500));


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
        lday.setBounds(5, 240, 30, 20);
        day.setBounds(30, 240, 30, 20);
        lmonth.setBounds(65, 240, 60, 20);
        month.setBounds(105, 240, 30, 20);
        lyear.setBounds(140, 240, 30, 20);
        year.setBounds(170, 240, 50, 20);

        //edit Authors
        editAuthors.addActionListener(e -> {
            JTextField textField = new JTextField(40);
            textField.setBounds(20, 20, 250, 20);
            JTextArea textArea = new JTextArea(40,10);
            textArea.setBounds(5, 50, 360, 300);

            JButton xbtn = new JButton("Add");
            xbtn.setBounds(270, 20, 100, 20);
            xbtn.addActionListener(event -> {
                if(!textField.getText().isEmpty()) {
                    authHolderArray.add(textField.getText());
                }
                textArea.setText(String.join("\n", authHolderArray));
                textField.setText("");
            });
            JPanel myPanel = new JPanel();

            myPanel.add(textField);
            myPanel.add(xbtn);

            myPanel.add(textArea);
            myPanel.setLayout(null);
            UIManager.put("OptionPane.minimumSize", new Dimension(400, 400));
            int op  =JOptionPane.showConfirmDialog(null, myPanel,
                    "Add Authors ",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if(op == JOptionPane.OK_OPTION ) {
                authors.setText((String.join(";",authHolderArray)));
            }
            else{
                authors.setText("");
                authHolderArray.removeAll(authHolderArray);
            }
        });
        // edit Authors end

        //fix tab key (due to incorrect configuration of the layouts)
        typeList.setNextFocusableComponent(title);
        title.setNextFocusableComponent(authors);
        authors.setNextFocusableComponent(editAuthors);

        fnp.add(typeList);

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
                    fnp.add(labJournal);
                    fnp.add(journalName);
                    fnp.add(labIssue);
                    fnp.add(issue);
                    fnp.add(labVolume);
                    fnp.add(volume);
                    fnp.removeFields(labConferenceName, labLocation, conference, location);
                    fnp.removeFields(labBookTitle, labEditor, bookTitle, editor);


                    // fnp add:
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
                    fnp.add(btnInsert);
                    repaint();
                    break;
                case 1:
                    fnp.removeFields(labJournal, labIssue, journalName, issue);
                    fnp.remove(labVolume);
                    fnp.remove(volume);
                    fnp.removeFields(labBookTitle, labEditor, bookTitle, editor);
                    labConferenceName.setBounds(5, 300, 115, 20);
                    conference.setBounds(80, 300, 140, 20);
                    fnp.add(labConferenceName);
                    labConferenceName.setVisible(true);
                    fnp.add(conference);
                    labLocation.setBounds(5, 330, 115, 20);
                    location.setBounds(80, 330, 140, 20);
                    fnp.add(labLocation);
                    fnp.add(location);

                    // fnp add:
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
                    fnp.add(btnInsert);
                    repaint();
                    break;
                case 2:
                    fnp.removeFields(labJournal, labIssue, journalName, issue);
                    fnp.remove(labVolume);
                    fnp.remove(volume);
                    fnp.removeFields(labConferenceName, labLocation, conference, location);
                    labBookTitle.setBounds(5, 300, 150, 20);
                    bookTitle.setBounds(80, 300, 140, 20);
                    fnp.add(labBookTitle);
                    fnp.add(bookTitle);
                    labEditor.setBounds(5, 330, 150, 20);
                    editor.setBounds(85, 330, 135, 20);
                    fnp.add(labEditor);
                    fnp.add(editor);

                    // fnp add:
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
                    fnp.add(btnInsert);
                    repaint();
                    break;
            }
        });


        findAll.setRenderer(new DropDown.MyComboBoxRenderer("Find All By", 255, 102, 102));
        findAll.setPreferredSize(new Dimension(200, 30));
        findAll.setSelectedIndex(-1);
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setEditable(false);
        btp.add(lselectType);
        btp.add(findAll);
        btp.add(lquery);
        btp.add(searchField);
        btp.add(submitSearchBtn);

        findAll.addActionListener(e -> {
            switch (findAll.getSelectedIndex()) {
                case 0:
                    searchField.setText("Search for Journal...");
                    searchField.setForeground(Color.lightGray);
                    searchField.setEditable(true);
                    break;
                case 1:
                    searchField.setText("Search for Conference Venue...");
                    searchField.setForeground(Color.lightGray);
                    searchField.setEditable(true);
                    break;
                case 2:
                    searchField.setText("Search for Publisher...");
                    searchField.setForeground(Color.lightGray);
                    searchField.setEditable(true);
                    break;
            }
        });

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                searchField.setText("");
                searchField.setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });


        submitSearchBtn.addActionListener(e -> {
            for(Ref ref : bibliography.allEntries()){
                if(ref instanceof RefJournal){
                    tableModel.addRow(new Object[]{ref.getTitle(),String.join(",",ref.getAuthors()),ref.getPubyear(),ref.getPublisher(),ref.getDoi(),ref.getDateAdded(),
                            ((RefJournal) ref).getJournal(),((RefJournal) ref).getVolume(),((RefJournal) ref).getIssue(),null,null,null,null});

                }
                if(ref instanceof RefConference) {
                    tableModel.addRow(new Object[]{ref.getTitle(), String.join(",", ref.getAuthors()), ref.getPubyear(), ref.getPublisher(), ref.getDoi(), ref.getDateAdded(),
                            null, null, null, ((RefConference) ref).getVenue(), ((RefConference) ref).getLocation(), null, null});
                }
                if(ref instanceof RefBookChapter) {
                    tableModel.addRow(new Object[]{ref.getTitle(), String.join(",", ref.getAuthors()), ref.getPubyear(), ref.getPublisher(), ref.getDoi(), ref.getDateAdded(),
                            null, null, null, null, null, ((RefBookChapter) ref).getBook(),((RefBookChapter) ref).getEditor()});
                }
            }


            switch (findAll.getSelectedIndex()) {
                case 0:
                    txtAreaPanel.setText(bibliography.lookUpByJournal(searchField.getText()));
                    findAll.setSelectedIndex(-1);
                    searchField.setText("");
                    searchField.setEditable(false);
                    break;
                case 1:
                    txtAreaPanel.setText(bibliography.lookUpByVenue(searchField.getText()));
                    searchField.setText("");
                    searchField.setEditable(false);
                    break;
                case 2:
                    txtAreaPanel.setText(bibliography.lookUpByPublisher(searchField.getText()));
                    searchField.setText("");
                    searchField.setEditable(false);
                    break;
            }
        });


        btnInsert.setBounds(20, 420, 250, 30);
        btnInsert.addActionListener(e -> {
            String message = "";
            message = addCitation("generic");
            txtAreaPanel.setText(message);
        });

        tools = new JMenu("Tools");
        file =new JMenu("File");
        importCsv = new JMenuItem("Import Csv");
        export_to = new JMenu("Export To");
        exportXml = new JMenuItem("Export To .xml");
        exportText = new JMenuItem("Export To .txt");

        mtbp.add(file);
        mtbp.add(tools);
        tools.add(importCsv);
        tools.add(export_to);
        export_to.add(exportXml);
        export_to.add(exportText);

        importCsv.addActionListener(e -> {
            FileDialog fd = new FileDialog(new JFrame());
            fd.setVisible(true);
            File[] f = fd.getFiles();
            if (f.length > 0) {
                try {
                    bibliography.setFilePath(fd.getFiles()[0].getAbsolutePath());
                    bibliography.importMany();
                    displayMessage();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


        exportText.addActionListener(e -> {
            JOptionPane.showMessageDialog(new JFrame(), "Navigate to the directory that you want to create your exported file. \n" +
                    "ATTENTION : After naming the file you should type in the end of the name \".txt\"\n" +
                    "E.g ---> \"export\" produces incorrect file but ----> \"export.txt\" correct");
            FileDialog fd = new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
            fd.setVisible(true);
            String path = fd.getDirectory() + fd.getFile();

            //fix only to get text from search option
            if(!txtAreaPanel.getText().isEmpty()) {
                txtAreaPanel.setText(bibliography.exportToText(txtAreaPanel.getText(),path));
            }
        });

        exportXml.addActionListener(e -> {
            JOptionPane.showMessageDialog(new JFrame(), "Navigate to the directory that you want to create your exported file. \n" +
                    "ATTENTION : After naming the file you should type in the end of the name \".xml\"\n" +
                    "E.g ---> \"export\" produces incorrect file but ----> \"export.xml\" correct");
            FileDialog fd = new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
            fd.setVisible(true);
            String path = fd.getDirectory() + fd.getFile();
            if(!txtAreaPanel.getText().isEmpty() ){
                txtAreaPanel.setText(bibliography.exportXML(path));
            }
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

    /**
     *  user adds citation based on the combobox value
     * @param what
     * @return
     */
    public String addCitation(String what) {
        String message = "Citation added\n";

        System.out.println("Adding " + what + " citation to the Bibliography");

        // global attribute declaration
        String t= title.getText();
        if(t.isBlank()){
            return message = "The title cannot be blank. Please fill the Title field with an appropriate title";
        }
        String[] a = authors.getText().split(";");
        if(a.length < 1 || a[0].isBlank()){
            return message = "The authors cannot be blank. Please fill the Authors field with the authors of the citation";
        }

        String d = doi.getText();
        if(d.isBlank()) {
            return message = "The doi cannot be blank. Please fill the Doi field with an appropriate doi";
        }
        String p = publisher.getText();


        int dayAdded = 0;
        int monthAdded = 0;
        int yearAdded = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String newDate = sdf.format(new Date());
        try {
            dayAdded = Integer.parseInt(day.getText());
            monthAdded = Integer.parseInt(month.getText());
            yearAdded = Integer.parseInt(year.getText());
            String date = (year.getText() + "-" + month.getText() + "-" + day.getText());
            if (!bibliography.validateDate(date)) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            if (day.getText().isEmpty() && month.getText().isEmpty() && year.getText().isEmpty()) {
                System.out.println("Date has been formatted automatically to : " + newDate);
                dayAdded = 0;
                monthAdded = 0;
                yearAdded = 0;
                message = "Date has been formatted automatically to : " + newDate + "\n"
                        + "Citation added successfully";
            } else {
                System.out.println("Interrupt invalid date");
                return message = "The date should contain only valid date characters (e.g " + newDate + " )";
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Your date is invalid");
            return message = "The date that you inserted is incorrect. \n" +
                    "Please check the date fields and try again.";
        }

        int py;
        try {
             py = Integer.parseInt(pubYear.getText());
        }
        catch (NumberFormatException numberFormatException) {
            return message = "The year should contain only numerical values and it cannot be blank.";
        }
        switch (typeList.getSelectedIndex()) {
            case 0:
                String jn = journalName.getText();
                if(jn.isBlank()) {
                    return message = "The Journal name cannot be blank. Please fill the JournalName field with the journal name of your citation";
                }
                int i,v;
                try {
                    i = Integer.parseInt(issue.getText());
                    v = Integer.parseInt(volume.getText());
                } catch (NumberFormatException nfe) {
                    return message = "The volume and issue field can contain only numerical values. Please try again";
                }
                RefJournal refJournal = new RefJournal(t, a, py, p, d, dayAdded, monthAdded, yearAdded, jn, i, v);
                bibliography.addCite(refJournal); //after beta implement with generics
                break;
            case 1:
                String cf = conference.getText();
                if(cf.isBlank()) {
                    return message = "The Conference name cannot be blank. Please fill the Conference Name field with the conference name of your citation.";
                }
                String l = location.getText();
                if(l.isBlank()) {
                    return message = "The Location of a Conference cannot be blank. Please fill the Location field with the location of your citation.";
                }
                RefConference refConference = new RefConference(t, a, py, p, d, dayAdded, monthAdded, yearAdded, cf, l);
                bibliography.addCite(refConference);
                break;
            case 2:
                String bk = bookTitle.getText();
                if(bk.isBlank()) {
                    return message = "The Book Chapter cannot be blank. Please fill the Book Chapter field with the book chapter of your citation.";
                }
                String e = editor.getText();
                RefBookChapter refBK = new RefBookChapter(t, a, py, p, d, dayAdded, monthAdded, yearAdded, bk, e);
                bibliography.addCite(refBK);
                break;
        }

        a = new String[]{};
        return message;
    }

    /**
     * receives text to be displayed by the refcollectoin
     *
     * @return
     */
    private String receiveMessageFromCollection() {
        return bibliography.getMessage();
    }

    private void displayMessage() {
        txtAreaPanel.setText(receiveMessageFromCollection());
    }
}

/*************************************************** END OF RefSystemGUI **********************************************/


