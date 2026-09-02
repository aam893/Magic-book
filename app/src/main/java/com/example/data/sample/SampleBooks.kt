package com.example.data.sample

import com.example.data.model.Book

object SampleBooks {
    val list: List<Book> = (
        SampleBooksPart1.list +
        SampleBooksPart2.list +
        SampleBooksPart3.list +
        SampleBooksPart4.list +
        SampleBooksPart5.list
    )
}
