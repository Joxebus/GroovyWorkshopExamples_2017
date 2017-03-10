package com.nearsoft.basics

import com.nearsoft.categories.FeedReaderGroovy
import groovy.json.JsonSlurper



def jsonSlurper = new JsonSlurper()
use(FeedReaderGroovy){
    def url = "https://www.theguardian.com/international/rss"
    def response = url.newsFeed()
    //def json = jsonSlurper.parseText(response)
    println response

}

