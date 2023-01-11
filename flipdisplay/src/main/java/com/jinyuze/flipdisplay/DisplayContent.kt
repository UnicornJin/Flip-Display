package com.jinyuze.flipdisplay

class DisplayContent(width: Int, height: Int) {
    val content = mutableListOf<MutableList<Char>>()

    init {
        var i = 0
        while(i < height) {
            var j = 0
            val temp = mutableListOf<Char>()
            while(j < width) {
                temp.add(' ')
                j++
            }
            content.add(temp)
            i++
        }
    }

    fun setRow(row: Int, str: String) {
        for (i in str.indices) {
            if (i < content[row].size) {
                content[row][i] = str[i]
            }
        }
    }

    fun getCharAt(row: Int, column: Int): Char{
        return content[row][column]
    }
}