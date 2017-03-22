package com.nearsoft.basics

def camelCase = /[A-Z]{0,1}[a-z]*([A-Z]{1}[a-z]*)*/

// the return type of ==~ is therefore a boolean
assert 'A' ==~ camelCase
assert 'aA' ==~ camelCase
assert 'thisIsMyVariableInCamelCase' ==~ camelCase

// the return type of =~ is a Matcher
println (('thisIsMyVariableInCamelCase' =~ /[A-Z]{0,1}[a-z]*/).collect{
    it.toUpperCase()
}.join(' ').trim().replaceAll(' ', '_' ))


def urlRegex = '\\(?\\bhttp://[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]'

def text = '&lt;img src="http://www.nybooks.com/wp-content/uploads/2017/03/greenhouse_1-040617.jpg" /&gt;Nancy Weiss Malkiel’s &lt;i&gt;“Keep the Damned Women Out”&lt;/i&gt;,'

( (text =~ urlRegex ).collect().each{ println it } )
