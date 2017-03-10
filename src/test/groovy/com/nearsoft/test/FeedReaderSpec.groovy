package com.nearsoft.test

import com.nearsoft.categories.FeedReaderGroovy
import com.nearsoft.categories.FeedReaderJava
import spock.lang.Specification

class FeedReaderSpec extends Specification {
    List feeds = ["http://www.booklender.com/rss/NewReleasesBooks.xml",
                  "http://www.booklender.com/blog/feed/"]


    def "Test Java Book Reader"() {
        when:
        String url = feeds[0]
        def books = FeedReaderJava.readBookFeed(url)

        then:
        !books.isEmpty()
        books.size() == 40
    }

    def "Test Groovy Book Reader"() {
        when:
        String url = feeds[0]
        def books = FeedReaderGroovy.readBookFeed(url)

        then:
        !books.isEmpty()
        books.size() == 40
    }

    def "Test Java Blog Reader"() {
        when:
        String url = feeds[1]
        def blogs = FeedReaderJava.readBlogFeed(url)

        then:
        !blogs.isEmpty()
        blogs.size() == 10
    }

    def "Test Groovy Bolg Reader"() {
        when:
        String url = feeds[1]
        def blogs = FeedReaderGroovy.readBlogFeed(url)

        then:
        !blogs.isEmpty()
        blogs.size() == 10
    }

}
