package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day03 : Day(3) {
    private val input = getInputAsList()

    override fun part1(): String {
        var gammaRate = ""
        var epsilonRate = ""

        for (i in 0 until input[0].length) {
            val mostCommonBit = mostCommonBit(input, i)
            gammaRate += mostCommonBit
            epsilonRate += opposite(mostCommonBit)
        }

        return (Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2)).toString()
    }

    override fun part2(): String {
        val oxygen = calculateRating(input.toMutableList(), true)
        val co2 = calculateRating(input.toMutableList(), false)

        return (oxygen * co2).toString()
    }

    private fun mostCommonBit(input: List<String>, index: Int): Char {
        return if (input.map { it[index] }.joinToString("").replace("1", "").length > input.size / 2) '0' else '1'
    }

    private fun opposite(value: Char): Char {
        return if (value == '0') '1' else '0'
    }

    private fun calculateRating(input: MutableList<String>, mostCommon: Boolean): Int {
        var i = 0

        while (input.size > 1) {
            val mostCommonBit = mostCommonBit(input, i)
            val bit = if (mostCommon) mostCommonBit else opposite(mostCommonBit)
            input.removeIf { it[i] != bit }
            i++
        }

        return Integer.parseInt(input[0], 2)
    }
}
