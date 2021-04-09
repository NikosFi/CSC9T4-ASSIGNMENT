package com.stir.cscu9t4assignment2021;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XmlWriter {
    private DocumentBuilderFactory dFact;
    private DocumentBuilder build;
    private Document doc;

    public void writeXmlFile(List<Ref> list) {

        try {

            dFact = DocumentBuilderFactory.newInstance();
            build = dFact.newDocumentBuilder();
            doc = build.newDocument();

            Element root = doc.createElement("Studentinfo");
            doc.appendChild(root);


            for (Ref ref : list) {

                if (ref instanceof RefJournal) {
                    Element refJournal = doc.createElement("RefJournal");
                    root.appendChild(refJournal);

                    commonRefElements(refJournal, ref);

                    Element journal = doc.createElement("journal");
                    journal.appendChild(doc.createTextNode(String.valueOf(((RefJournal) ref)
                            .getJournal())));
                    refJournal.appendChild(journal);

                    Element volume = doc.createElement("volume");
                    volume.appendChild(doc.createTextNode(String.valueOf(((RefJournal) ref).getVolume())));
                    refJournal.appendChild(volume);

                    Element issue = doc.createElement("issue");
                    issue.appendChild(doc.createTextNode(String.valueOf(((RefJournal) ref).getIssue())));
                    refJournal.appendChild(issue);
                }

                if (ref instanceof RefConference) {
                    Element refConference = doc.createElement("RefConference");
                    root.appendChild(refConference);


                    commonRefElements(refConference, ref);

                    Element venue = doc.createElement("venue");
                    venue.appendChild(doc.createTextNode(String.valueOf(((RefConference) ref).getVenue())));
                    refConference.appendChild(venue);

                    Element location = doc.createElement("location");
                    location.appendChild(doc.createTextNode(String.valueOf(((RefConference) ref).getLocation())));
                    refConference.appendChild(location);

                }
                if (ref instanceof RefBookChapter) {
                    Element refBook = doc.createElement("RefBook");
                    root.appendChild(refBook);
                    commonRefElements(refBook, ref);

                    commonRefElements(refBook, ref);

                    Element book = doc.createElement("book");
                    book.appendChild(doc.createTextNode(String.valueOf(((RefBookChapter) ref).getBook())));
                    refBook.appendChild(book);

                    Element editor = doc.createElement("editor");
                    editor.appendChild(doc.createTextNode(String.valueOf(((RefBookChapter) ref).getEditor())));
                    refBook.appendChild(editor);
                }

            }

            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            aTransformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            try {
                // location and name of XML file you can change as per need
                FileWriter fos = new FileWriter("./ros.xml");
                StreamResult result = new StreamResult(fos);
                aTransformer.transform(source, result);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (TransformerException ex) {
            System.out.println("Error outputting document");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        }
    }


    public void commonRefElements(Element cors, Ref ref) {
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(String.valueOf(ref
                .getTitle())));
        cors.appendChild(title);

        Element authors = doc.createElement("authors");
        authors.appendChild(doc.createTextNode(String.join(";",ref.getAuthors())));
        cors.appendChild(authors);

        Element doi = doc.createElement("doi");
        doi.appendChild(doc.createTextNode(String.valueOf(ref.getDoi())));
        cors.appendChild(doi);

        Element publisher = doc.createElement("publisher");
        publisher.appendChild(doc.createTextNode(String.valueOf(ref
                .getPublisher())));
        cors.appendChild(publisher);

        Element pubyear = doc.createElement("pubyear");
        pubyear.appendChild(doc.createTextNode(String.valueOf(ref.getPubyear())));
        cors.appendChild(pubyear);

        Element dateAdded = doc.createElement("dateAdded");
        dateAdded.appendChild(doc.createTextNode(String.valueOf(ref.getDateAdded())));
        cors.appendChild(dateAdded);
    }
}
