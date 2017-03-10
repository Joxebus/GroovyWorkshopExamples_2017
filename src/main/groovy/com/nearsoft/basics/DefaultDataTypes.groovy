package com.nearsoft.basics

/**
 * This are some of the examples of the data types that
 * groovy uses by default
 */


def name = '"value inserted"'
def map = [:]                               // java.util.LinkedHashMap

def elements = [1,                          // java.lang.Integer
            11111111111,                    // java.lang.Long
            11111111111111111111,           // java.math.BigInteger
            0.1,                            // java.math.BigDecimal
            "Normal String",                // java.lang.String
            'Another normal String',        // java.lang.String
            "GString with value $name",     // groovy.lang.GString
            [],                             // java.util.ArrayList

]

elements.each{ println it.class.name }
println map.getClass().name
