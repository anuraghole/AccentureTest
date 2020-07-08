package com.example.accenturetest.data

data class ResponseData(
    var rows: List<Row>? = mutableListOf(),
    var title: String? = ""
)