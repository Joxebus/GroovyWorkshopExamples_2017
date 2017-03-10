package com.nearsoft.test

import com.nearsoft.util.GoogleExchangeRates
import spock.lang.Specification


class GoogleExchangeRatesSpec extends Specification{

    def "Test exchange rate service"(){
        setup:
        def googleExchangeRates = new GoogleExchangeRates()

        when:
        def currencies = [from, to]
        def exchageRate = googleExchangeRates.findRate(*currencies) // Spread dot example
        println exchageRate

        then:
        exchageRate.collect{ k, v -> k } == ['from', 'to', 'rate']
        exchageRate.from    ==  from
        exchageRate.to      ==  to
        exchageRate.rate    instanceof  BigDecimal

        where:
        from | to
        'USD'| 'EUR'
        'USD'| 'MXN'
        'MXN'| 'EUR'

    }

    def "Test exhange rest sevice with a list of rates"(){
        setup:
        List currencyList = []
        currencyList << [from:'USD', to:'EUR']
        currencyList << [from:'USD', to:'MXN']
        currencyList << [from:'MXN', to:'EUR']
        currencyList << [from:'EUR', to:'USD']

        def googleExchangeRates = new GoogleExchangeRates()

        when:
        List response = googleExchangeRates.findAllExchangeRatesFromList(currencyList)
        println response

        then:
        response         instanceof List
        response.size()  == 4


    }
}
