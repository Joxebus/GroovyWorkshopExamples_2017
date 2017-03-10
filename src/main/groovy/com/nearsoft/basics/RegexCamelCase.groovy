package com.nearsoft.basics

def camelCase = /[A-Z]{0,1}[a-z]*([A-Z]{1}[a-z]*)*/

assert 'A' ==~ camelCase
assert 'aA' ==~ camelCase

assert 'estaEsUnaVariableEnCamelCase' ==~ camelCase

println (('estaEsUnaVariableEnCamelCase' =~ /[A-Z]{0,1}[a-z]*/).collect{
    it.toUpperCase()
}.join(' ').trim().replaceAll(' ', '_' ))


