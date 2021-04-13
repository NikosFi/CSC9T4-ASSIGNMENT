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

    private String path;

    public void writeXmlFile(List<Ref> list) {

        try {

            dFact = DocumentBuilderFactory.newInstance();
            build = dFact.newDocumentBuilder();
            doc = build.newDocument();

            Element root = doc.createElement("bibliography");
            doc.appendChild(root);


            for (Ref ref : list) {
                Element item = doc.createElement("ref");
                root.appendChild(item);
                commonRefElements(item, ref);


                if (ref instanceof RefJournal) {
                    item.setAttribute("type",RefJournal.class.getSimpleName());
                    Element journal = doc.createElement("journal");
                    journal.appendChild(doc.createTextNode(String.valueOf(((RefJournal) ref)
                            .getJournal())));
                    item.appendChild(journal);

                    Element volume = doc.createElement("volume");
                    volume.appendChild(doc.createTextNode(String.valueOf(((RefJournal) ref).getVolume())));
                    item.appendChild(volume);

                    Element issue = doc.createElement("issue");
                    issue.appendChild(doc.createTextNode(String.valueOf(((RefJournal) ref).getIssue())));
                    item.appendChild(issue);
                }

                if (ref instanceof RefConference) {
                    item.setAttribute("type",RefConference.class.getSimpleName());

                    Element venue = doc.createElement("venue");

                    venue.appendChild(doc.createTextNode(String.valueOf(((RefConference) ref).getVenue())));
                    item.appendChild(venue);

                    Element location = doc.createElement("location");
                    location.appendChild(doc.createTextNode(String.valueOf(((RefConference) ref).getLocation())));
                    item.appendChild(location);

                }
                if (ref instanceof RefBookChapter) {
                    item.setAttribute("type",RefBookChapter.class.getSimpleName());


                    Element book = doc.createElement("book");
                    book.appendChild(doc.createTextNode(String.valueOf(((RefBookChapter) ref).getBook())));
                    item.appendChild(book);

                    Element editor = doc.createElement("editor");
                    editor.appendChild(doc.createTextNode(String.valueOf(((RefBookChapter) ref).getEditor())));
                    item.appendChild(editor);
                }

            }

            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            aTransformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            try {
                // location and name of XML file you can change as per need
                FileWriter fos = new FileWriter(getXmlPath());
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

    public String getXmlPath(){
        return path;
    }

    public void setXmlPath(String path){
        this.path = path;
    }


}
