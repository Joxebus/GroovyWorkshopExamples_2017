package com.nearsoft.test

import com.nearsoft.beans.Person
import spock.lang.Specification

/**
 * This test is designed to validate the creation of a Person bean
 */
class PersonSpec extends Specification{

    def "Test valid telephones"(){
        when:
        println "Testing with name:$name and phone:$phone"
        Person person = new Person()
        person.setName(name)
        person.setPhone(phone)

        then:
        person.phone ==~ /(\d{3})-(\d{3})-(\d{4})/

        where:
        name    | phone
        'Amaia' | "442-123-4560"
        'Jorge' | "662-653-4567"
        'David' | "415-345-4568"
        'Diego' | "442-123-4569"

    }

    def "Test invalid telephones"(){
        when:
        println "Testing with name:$name and phone:$phone"
        Person person = new Person()
        person.setName(name)
        person.setPhone(phone)

        then:
        thrown RuntimeException

        where:
        name    | phone
        'Amaia' | "442-1234560"
        'Jorge' | "662653-4567"
        'David' | "415-345--4568"
        'Diego' | "(442)-123-4569"

    }
}
