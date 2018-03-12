package com.nearsoft.test

import com.nearsoft.beans.SmartPhone
import spock.lang.Specification

/**
 * Created by obautista on 10/3/17.
 */
class SmartPhoneSpec extends Specification{

    def "Test a phone can be cloned" (){
        given: "2 phones are defined"
        SmartPhone phone1 = new SmartPhone()
        SmartPhone phone2
        when: "When assign values to the first one"
        phone1.with {
            brand = "SAMSUNG"
            megapixels = 20.0
            ram = 3
            rom = 32
            color = "WHITE"
        }

        and: "Call clone method on first to second"
        phone2 = phone1.clone()
        then: "Phone1 and Phone2 has the same values"
        phone1.hashCode() == phone2.hashCode()
        phone1.equals(phone2)

    }
}
