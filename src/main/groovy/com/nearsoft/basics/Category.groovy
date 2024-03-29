package com.nearsoft.basics

/**
 * This example shows how to use categories on specific pieces of code
 * a category needs the word 'use' following by the class that showld
 * be used on the block that overrides or add certain behavior.
 */

import com.nearsoft.categories.FeedReaderGroovy
import com.nearsoft.categories.FeedReaderJava
import groovy.time.TimeCategory

List feeds = ["http://www.booklender.com/rss/NewReleasesBooks.xml",
              "http://www.booklender.com/blog/feed/"]

def printEntries = { list ->
    list.each{ println it }
}

use(FeedReaderJava){
    def books = feeds[0].readBookFeed()
    def blogs = feeds[1].readBlogFeed()
    printEntries(books)
    printEntries(blogs)
}

use(TimeCategory)  {
    println 1.minute.from.now
    println 10.hours.ago

    def someDate = new Date()
    println someDate - 3.months
}


