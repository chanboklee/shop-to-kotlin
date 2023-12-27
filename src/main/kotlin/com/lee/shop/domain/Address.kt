package com.lee.shop.domain

import jakarta.persistence.Embeddable

@Embeddable
class Address(
    var city: String,
    var zipcode: String,
    var street: String,
){

}