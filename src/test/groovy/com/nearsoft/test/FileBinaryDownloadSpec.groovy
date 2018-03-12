package com.nearsoft.test

import com.nearsoft.categories.FileBinaryDownload
import spock.lang.Specification;

/**
 * This test use as category the FileBinaryDownload
 */
public class FileBinaryDownloadSpec extends Specification{

    def setupSpec(){
        def src = new File('src/test/resources/tmp')
        if(src.exists()){
            src.eachFileRecurse {
                it.delete()
            }
        }else{
            src.mkdir()
        }

    }

    def "Test if a file can be downloaded"(){
        when:
        def fullName = "${filename}${Math.abs(new Random().nextInt() % 600 + 1)}.jpg"
        def file = new File("src/test/resources/tmp/${fullName}")
        use(FileBinaryDownload){
            file << url.toURL()
        }

        def fileSpec = new File("src/test/resources/tmp/${fullName}")
        then:
        file.name == fullName
        fileSpec.bytes instanceof byte[]

        where:
        filename    |   url
        'example1'  |   'http://i.imgur.com/HTWuqmi.jpg'
        'example2'  |   'http://i.imgur.com/iCHMcMd.jpg'
        'example3'  |   'http://i.imgur.com/kPkvetw.jpg'

    }

    def cleanupSpec(){
        def src = new File('src/test/resources/tmp')
        if(src.exists()){
            src.deleteDir()
        }
    }
}
