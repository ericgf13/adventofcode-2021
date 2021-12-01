package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day01 : Day(1) {
    private val input = getInputAsList().map { it.toInt() }

    override fun part1(): String {
        var result = 0
        input.forEachIndexed { index, i ->
            if (index != 0 && i > input[index - 1]) {
                result++
            }
        }
        return result.toString()
    }

    override fun part2(): String {
        var result = 0
        input.forEachIndexed { index, i ->
            if (index != 0 && index <= input.size - 3) {
                val window = i + input[index + 1] + input[index + 2]
                val previousWindow = input[index - 1] + i + input[index + 1]

                if (window > previousWindow) {
                    result++
                }
            }
        }
        return result.toString()
    }
}
