package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day10 : Day(10) {
    private val input = getInputAsList()

    override fun part1(): String {
        var result = 0
        val points = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )

        input.forEach { line ->
            val expected = ArrayDeque<Char>()

            line.forEach { char ->
                when (char) {
                    '(' -> expected.addFirst(')')
                    '[' -> expected.addFirst(']')
                    '{' -> expected.addFirst('}')
                    '<' -> expected.addFirst('>')
                    else -> {
                        if (expected.removeFirst() != char) {
                            result += points[char]!!
                        }
                    }
                }
            }
        }

        return result.toString()
    }

    override fun part2(): String {
        val scores = mutableListOf<Long>()
        val points = mapOf(
            ')' to 1,
            ']' to 2,
            '}' to 3,
            '>' to 4
        )

        input.forEach { line ->
            val expected = ArrayDeque<Char>()
            var corrupted = false

            line.forEach { char ->
                when (char) {
                    '(' -> expected.addFirst(')')
                    '[' -> expected.addFirst(']')
                    '{' -> expected.addFirst('}')
                    '<' -> expected.addFirst('>')
                    else -> {
                        if (expected.removeFirst() != char) {
                            corrupted = true
                        }
                    }
                }
            }

            if (!corrupted) {
                scores.add(expected.fold(0) { score, char -> score * 5 + points[char]!! })
            }
        }

        return scores.sorted()[scores.size / 2].toString()
    }
}
