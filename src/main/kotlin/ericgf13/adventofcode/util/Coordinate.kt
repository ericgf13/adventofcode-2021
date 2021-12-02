package ericgf13.adventofcode.util

data class Coordinate(var x: Int, var y: Int) {

    fun offsetX(offset: Int): Int {
        x += offset
        return x
    }

    fun offsetY(offset: Int): Int {
        y += offset
        return y
    }
}
