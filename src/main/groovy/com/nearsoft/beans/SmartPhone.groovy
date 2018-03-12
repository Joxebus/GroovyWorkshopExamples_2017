package com.nearsoft.beans

import groovy.transform.AutoClone
import groovy.transform.Canonical

@Canonical
@AutoClone
class SmartPhone{

    String brand
    BigDecimal megapixels
    int ram
    int rom
    String color

}
