package com.fabrika.tagtabazar.model

data class Category(
    val id: String?,
    val Name: String?,
    val Order: Int?
){
    constructor() : this(
        null,null,null
    )
}
