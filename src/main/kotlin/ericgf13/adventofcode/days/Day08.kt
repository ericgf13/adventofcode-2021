package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day08 : Day(8) {
    private val input = getInputAsList()
    private val digitsBySignals = mutableMapOf<List<String>, List<String>>()

    init {
        input.forEach { line ->
            val signals = line.substring(0, line.indexOf("|") - 1).split(" ")
            val digits = line.substring(line.indexOf("|") + 2).split(" ")
            digitsBySignals[signals] = digits
        }
    }

    override fun part1(): String {
        return digitsBySignals.values.flatten().count { setOf(2, 3, 4, 7).contains(it.length) }.toString()
    }

    override fun part2(): String {
        var result = 0

        for (entry in digitsBySignals) {
            val signals = entry.key.map { it.toList() }
            val digits = entry.value.map { it.toList() }

            val segments = mutableMapOf<String, List<Char>>()

            segments["1"] = signals.single { it.size == 2 }
            segments["7"] = signals.single { it.size == 3 }
            segments["4"] = signals.single { it.size == 4 }
            segments["8"] = signals.single { it.size == 7 }
            segments["9"] = signals.single { it.containsAll(segments["4"]!!) && !segments.values.contains(it) }
            segments["6"] = signals.single { it.size == 6 && !it.containsAll(segments["7"]!!) && !segments.values.contains(it) }
            segments["0"] = signals.single { it.size == 6 && !segments.values.contains(it) }
            segments["3"] = signals.single { it.containsAll(segments["7"]!!) && !segments.values.contains(it) }
            segments["5"] = signals.single { segments["9"]!!.containsAll(it) && !segments.values.contains(it) }
            segments["2"] = signals.single { !segments.values.contains(it) }

            var output = ""
            digits.forEach { digit ->
                segments.forEach { segment ->
                    if (segment.value.sorted() == digit.sorted()) {
                        output += segment.key
                    }
                }
            }

            result += output.toInt()
        }

        return result.toString()
    }
}
