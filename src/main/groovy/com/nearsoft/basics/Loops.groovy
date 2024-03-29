package com.nearsoft.basics
/**
 * This is an example of different ways to write or use a loop
 * with Groovy.
 */

// Closure with no arguments
def separator = {  println "\n ${'*'*30}" }
def separatorName = {name ->  println "\n\n ${'*'*15} ${name} ${'*'*15} \n" }

separatorName("for")
for(i in 1..5){ print "$i," }

separatorName("each")
('a'..'d').each{ print "$it," }

separatorName("step")
1.step(100,3){ print "$it," }

separatorName("step down")
100.step(50,-5){ print "$it," }

separatorName("step decimal")
1.3.step(3,0.3){ print "$it," }

separatorName("upto")
1.upto(5){ print "$it," }

separatorName("downto")
100.downto(80){ print "$it," }
