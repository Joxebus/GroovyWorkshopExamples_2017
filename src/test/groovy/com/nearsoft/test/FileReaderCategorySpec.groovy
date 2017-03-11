package com.nearsoft.test

import com.nearsoft.categories.FileReaderCategory
import spock.lang.Specification

/**
 * This is an example of how to modify the behavior of
 * a String by adding a method which read a file with
 * the name contained in the string.
 */
class FileReaderCategorySpec extends Specification {

    def "Test category can read"(){
        when:
        def content
        use(FileReaderCategory){
            content = 'lorem.txt'.getContent('src/test/resources/')
        }
        then:
        content.size() > 0
        content.contains('pharetra elit')
        content.startsWith('Lorem')
    }
}
