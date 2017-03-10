package com.nearsoft.util

/*
 * This is an example to get the exchange rates from Google
*/

public class GoogleExchangeRates{

    /**
     * Receive a list of maps with 2 keys 'from' and 'to'
     * this is used to get the currency rate
     * @param currencyList
     * @return List with all the currencies requested
     */
    public List findAllExchangeRatesFromList(List currencyList){
        ArrayList<HashMap> response = []
        currencyList.each{
            response << findRate(it.from, it.to)
        }
        response
    }

    /**
     * Receive to params to request de convertion from google.
     * @param from
     * @param to
     * @return a Map wih from, to and rate values.
     */
    public Map findRate (String from, String to){
        Map response = [:]
        response.from = from
        response.to = to

        def urlString = 'https://www.google.com/finance/converter?'
        def queryString = "a=1&from=${from}&to=${to}"

        def url = new URL(urlString+queryString)
        def connection = url.openConnection()
        connection.setRequestMethod("POST")
        connection.doOutput = true

        def writer = new OutputStreamWriter(connection.outputStream)
        writer.flush()
        writer.close()
        connection.connect()


        def out  = connection.content.text
        out = (out =~  /<span class=bld>(.+)$to/)[0][1]
        response.rate = out.trim().toBigDecimal()
        response
    }


}
