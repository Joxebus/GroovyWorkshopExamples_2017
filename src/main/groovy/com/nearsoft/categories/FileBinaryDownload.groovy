package com.nearsoft.categories

/**
 *
 * User: Joxebus

 * This class is used by the to download a file from web
 */

class FileBinaryDownload {

    def static leftShift(File file, URL url){
        url.withInputStream {is->
            file.withOutputStream {os->
                def bs = new BufferedOutputStream( os )
                bs << is
            }
        }
    }

}
