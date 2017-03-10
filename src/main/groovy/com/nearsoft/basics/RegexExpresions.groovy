package com.nearsoft.basics

/**
 * Simple regex to validate a phone number format
 */

def regexValidPhone = /(\d{3})-(\d{3})-(\d{4})/
def phoneNumbers = ["442-123-4567","442-123-4568","442-123-4569","442-1234560"]

phoneNumbers.each{ number ->
    assert number ==~ regexValidPhone
}



