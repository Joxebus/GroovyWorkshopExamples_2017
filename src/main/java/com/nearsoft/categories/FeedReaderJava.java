package com.nearsoft.categories;

import com.nearsoft.beans.Blog;
import com.nearsoft.beans.Book;
import com.nearsoft.beans.Feed;
import com.nearsoft.beans.FeedEntry;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an example of Java Mixin with groovy.
 */
public class FeedReaderJava {

    static DateFormat pubDateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");

    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";


    public static List<Book> readBookFeed(String feedUrl){
        List<Book> bookList = new ArrayList<>();
        for(FeedEntry entry : readFeed(feedUrl).getEntries()){
            try {
                bookList.add(feedEntryToBook(entry));
            } catch (ParseException e) {
                System.out.printf("The entry doesn't contain a valid date: %s", entry.getPubDate());
            }
        }
        return bookList;
    }

    public static List<Blog> readBlogFeed(String feedUrl){
        List<Blog> blogList = new ArrayList<>();
        for(FeedEntry entry : readFeed(feedUrl).getEntries()){
            try {
                blogList.add(feedEntryToBlog(entry));
            } catch (ParseException e) {
                System.out.printf("The entry doesn't contain a valid date: %s", entry.getPubDate());
            }
        }
        return blogList;
    }




    private static Feed readFeed(String feedUrl) {
        Feed feed = null;
        URL url;
        try {
            url = new URL(feedUrl);
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = null;
            String title = null;
            String link = null;
            String author = null;
            String pubdate = null;

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read(url);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed();
                                feed.setTitle(title);
                                feed.setAuthor(author);
                                feed.setLink(link);
                                feed.setPubDate(pubdate);
                                feed.setDescription(description);

                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        FeedEntry feedEntry = new FeedEntry();
                        feedEntry.setTitle(title);
                        feedEntry.setAuthor(author);
                        feedEntry.setLink(link);
                        feedEntry.setPubDate(pubdate);
                        feedEntry.setDescription(description);
                        feed.getEntries().add(feedEntry);
                        continue;
                    }
                }
            }
        } catch (XMLStreamException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    private static String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private static InputStream read(final URL url) {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Book feedEntryToBook(FeedEntry entry) throws ParseException {
        Book book = new Book();
        book.setTitle(entry.getTitle());
        book.setAuthor(entry.getAuthor() != null ? entry.getAuthor() : entry.getTitle().split(" - by ")[1]);
        book.setLink(entry.getLink());
        book.setPubDate(pubDateFormatter.parse(entry.getPubDate()));
        return book;
    }

    private static Blog feedEntryToBlog(FeedEntry entry) throws ParseException {
        Blog blog = new Blog();
        blog.setTitle(entry.getTitle());
        blog.setAuthor(entry.getAuthor());
        blog.setLink(entry.getLink());
        blog.setDescription(entry.getDescription());
        blog.setPubDate(pubDateFormatter.parse(entry.getPubDate()));
        return blog;
    }
}
