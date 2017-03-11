package com.nearsoft.categories


class FileReaderCategory {

    static String getContent(String filename, String src){
        new File("${src?:'src/main/resources/'}$filename").text
    }
}
