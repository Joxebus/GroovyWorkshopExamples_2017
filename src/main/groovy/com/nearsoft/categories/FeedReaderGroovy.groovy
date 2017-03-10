package com.nearsoft.categories

import com.nearsoft.beans.Blog
import com.nearsoft.beans.Book

import java.text.DateFormat
import java.text.SimpleDateFormat

class FeedReaderGroovy {

    static DateFormat pubDateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");

    static List readBookFeed(String url){
        def rss = new XmlSlurper().parseText(url.toURL().text)
        def list = []
        rss.channel.item.each{ entry ->
            def titleAuthor = entry.title.toString().split(" - by ")
            def book = new Book([title:titleAuthor[0],
                        author:titleAuthor[1]?:'Anonymous',
                        pubDate:pubDateFormatter.parse(entry.pubDate.toString()),
                        link:entry.link.toString()])
            list.add(book)
        }
        list
    }

    static List readBlogFeed(String url){
        def rss = new XmlSlurper().parseText(url.toURL().text)
        def list = []
        rss.channel.item.each{ entry ->
            def titleAuthor = entry.title.toString().split(", by ")
            def blogEntry = new Blog ([title:titleAuthor[0],
                             author:titleAuthor[1]?:'Anonymous',
                             pubDate:pubDateFormatter.parse(entry.pubDate.toString()),
                             link:entry.link.toString(),
                             description:entry.description])
            list.add(blogEntry)
        }
        list
    }

    static Map newsFeed(String url){
        def rss = new XmlSlurper().parseText(url.toURL().text)
        def channel = [:]
        def items = []
        channel.title = rss.channel.title
        channel.link = rss.channel.link
        channel.description = rss.channel.description
        channel.language = rss.channel.language
        channel.pubDate = pubDateFormatter.parse(rss.channel.pubDate.toString())
        rss.channel.item.each{ entry ->
            println entry.properties
            def item = [title:entry.title,
                        description: entry.description,
                                 pubDate:pubDateFormatter.parse(entry.pubDate.toString()),
                                 link:entry.link]
            items.add(item)
        }
        channel
    }

}
