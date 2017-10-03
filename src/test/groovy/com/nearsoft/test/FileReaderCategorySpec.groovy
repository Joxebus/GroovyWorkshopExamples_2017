package com.nearsoft.test

import com.nearsoft.categories.FileReaderCategory
import groovy.json.JsonSlurper
import spock.lang.Specification

/**
 * This is an example of how to modify the behavior of
 * a String by adding a method which read a file with
 * the name contained in the string.
 */
class FileReaderCategorySpec extends Specification {

    String SRC = 'src/test/resources/'

    def "Test category can read"(){
        when:
        def content
        use(FileReaderCategory){
            content = 'lorem.txt'.getContent(SRC)
        }
        then:
        content.size() == 0
        content.contains('pharetra elit')
        content.startsWith('Lorem')
    }

    def "Test read XML file using category to read the file"(){
        setup:
        def books
        when:
        use(FileReaderCategory){
            def response = new XmlSlurper().parseText('books.xml'.getContent(SRC))
            books = response.value.books.book
        }
        then:
        books.size()       ==  4
        books[0].author    ==  'Manuel De Cervantes'
        books[2].title     ==  'Alice in Wonderland'
        books.getClass().name == 'groovy.util.slurpersupport.NodeChildren'
    }

    def "Test read JSON file using category to read the file"(){
        setup:
        def books = []
        when:
        use(FileReaderCategory){
            def response = new JsonSlurper().parseText('books.json'.getContent(SRC))
            books = response.books
        }

        then:
        books.size()       ==  4
        books[0].author    ==  'Manuel De Cervantes'
        books[3].title     ==  'El cuaderno de Maya'
        books       instanceof List
        books[0]    instanceof Map
    }
}
