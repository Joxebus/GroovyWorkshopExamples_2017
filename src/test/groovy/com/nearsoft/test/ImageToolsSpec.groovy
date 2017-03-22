package com.nearsoft.test

import com.nearsoft.util.ImageTool
import spock.lang.Specification

/**
 * Tests the ImageTool operations
 */
class ImageToolsSpec extends Specification {

    def "Test thumbnail for image"(){
        setup:
        ImageTool imageTool = new ImageTool()
        File thumbnail = new File('src/test/resources/wallpaper1_thumnail.jpg')
        byte[] originalBytes = null

        when:
        imageTool.load('src/test/resources/wallpaper1.jpg')
        imageTool.saveOriginal()
        originalBytes = imageTool.getOriginalBytes("JPEG")
        imageTool.thumbnail(640)
        thumbnail.bytes = imageTool.getResultBytes("JPEG")
        imageTool.restoreOriginal()
        byte[] backupBytes = imageTool.getResultBytes("JPEG")

        then:
        thumbnail.bytes
        backupBytes == originalBytes

    }
}
