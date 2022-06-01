package com.fabrika.tagtabazar.model

data class Product(
    val id: String?,
    val Company: String?,
    val Currency: String?,
    val Date: String?,
    val Img_detail: String?,
    val Img_icon: String?,
    val Information: String?,
    val Name: String?,
    val Price: Int?,
    val Size: String?,
    val Status: Boolean?,
){
    constructor() : this(
        null,null,null,null,null,null,null,null,null,null,null
    )
}
