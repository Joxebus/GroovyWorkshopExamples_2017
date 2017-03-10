package com.nearsoft.basics

/**
 * Here are some example with what can we do
 * with the lists
 */

import com.nearsoft.beans.Person

Person.metaClass.nameAndAge = { ->
    name +' has '+age
}

def separator = { name ->
    println """
${'-'*30} $name ${'-'*30}"
"""
}

def numberList = [1,2,2,6,4,8,8,6,1,3]

def sqr = { it*it }
separator('ORIGINAL NUMBER LIST')
println numberList
separator('Natural sort')
println numberList.sort()
separator('Unique elements')
println numberList.unique()
separator('Sqr Numbers')
println numberList.collect(sqr)


def personList = []
personList << new Person([name:'Omar', age:25])
personList << new Person([name:'Diego', age:24])
personList << new Person([name:'Saul', age:15])
personList << new Person([name:'Maria', age:23])
separator('ORIGINAL PERSON LIST')
println personList
separator('sort by name')
println personList.sort{ it.name }*.nameAndAge()
separator('sort by age')
println personList.sort{ it.age }*.nameAndAge()
separator('sort by age reverse')
println personList.sort{ it.age }.reverse()*.nameAndAge()

def letterQuantity = { it?.name?.size() }

separator('list of names')
println personList*.name
separator('list of ages')
println personList*.age
separator('list name sizes')
println personList.collect(letterQuantity)

separator(" runtime metaprogramming ")
personList.metaClass.toTable = {->
    def table = "\tName \t|\t Age"
    delegate.each{
        table += "\n$it.name \t\t|\t $it.age"
    }
    table
}

println personList.toTable()
