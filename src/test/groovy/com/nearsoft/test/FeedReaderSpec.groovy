package com.nearsoft.test

import com.nearsoft.categories.FeedReaderGroovy
import com.nearsoft.categories.FeedReaderJava
import spock.lang.Specification

class FeedReaderSpec extends Specification {
    List feeds = ["http://www.booklender.com/rss/NewReleasesBooks.xml",
                  "http://www.booklender.com/blog/feed/"]


    def "Test Java Book/Blog Reader"() {
        when:
        String url1 = feeds[0]
        String url2 = feeds[1]

        def books = FeedReaderJava.readBookFeed(url1)
        def blogEntries = FeedReaderJava.readBlogFeed(url2)

        then:
        !books.isEmpty()
        books.size() == 41
        !blogEntries.isEmpty()
        blogEntries.size() == 10

    }

    def "Test Groovy Book/Blog Reader"() {
        when:
        String url1 = feeds[0]
        String url2 = feeds[1]

        def books = FeedReaderGroovy.readBookFeed(url1)
        def blogEntries = FeedReaderGroovy.readBlogFeed(url2)

        then:
        !books.isEmpty()
        books.size() == 41
        !blogEntries.isEmpty()
        blogEntries.size() == 10
    }

}
