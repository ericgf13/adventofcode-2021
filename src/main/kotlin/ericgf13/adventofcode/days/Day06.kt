package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day06 : Day(6) {
    private val input = getInputAsString().split(",").map { Integer.parseInt(it) }
    private val newFishPerDays = HashMap<Int, Long>()

    // Brute force way
    override fun part1(): String {
        val mutableInput = input.toMutableList()

        for (day in 0 until 80) {
            for (i in 0 until mutableInput.size) {
                val value = mutableInput[i]
                if (value == 0) {
                    mutableInput.add(8)
                    mutableInput[i] = 6
                } else {
                    mutableInput[i] = value - 1
                }
            }
        }

        return mutableInput.size.toString()
    }

    // Used recursion. Could have used a map with the number of fish per age, evolving each day
    override fun part2(): String {
        var count = 0L

        for (value in input) {
            count += 1 + calcNewFish(value, 256)
        }

        return count.toString()
    }

    private fun calcNewFish(value: Int, days: Int): Long {
        var count = 0L
        var currentValue = value

        for (i in days - 1 downTo 0) {
            if (currentValue == 0) {
                count += 1 + newFishPerDays.getOrPut(i) { calcNewFish(8, i) }
                currentValue = 6
            } else {
                currentValue--
            }
        }

        return count
    }
}
