package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day14 : Day(14) {
    private val input = getInputAsList().drop(2).associate { it.substring(0, 2) to it.substring(6) }
    private val template = getInputAsList()[0]

    override fun part1(): String {
        var polymer = template

        for (step in 1..10) {
            var newPolymer = ""

            for (i in 0 until polymer.length - 1) {
                val pair = polymer[i] + "" + polymer[i + 1]
                newPolymer += polymer[i] + input[pair]!!
            }

            polymer = newPolymer + polymer[polymer.length - 1]
        }

        val eachCount = polymer.groupingBy { it }.eachCount()
        return (eachCount.maxOf { it.value } - eachCount.minOf { it.value }).toString()
    }

    override fun part2(): String {
        var globalMap = mutableMapOf<String, Long>()
        for (i in 0 until template.length - 1) {
            val pair = template[i] + "" + template[i + 1]
            globalMap.putIfAbsent(pair, 0)
            globalMap[pair] = globalMap[pair]!! + 1
        }

        for (step in 1..40) {
            val stepMap = mutableMapOf<String, Long>()

            input.forEach { (key, value) ->
                if (globalMap.containsKey(key)) {
                    val pair1 = key[0] + value
                    val pair2 = value + key[1]

                    stepMap.putIfAbsent(pair1, 0)
                    stepMap.putIfAbsent(pair2, 0)
                    stepMap[pair1] = stepMap[pair1]!! + globalMap[key]!!
                    stepMap[pair2] = stepMap[pair2]!! + globalMap[key]!!
                }
            }

            globalMap = stepMap
        }

        val countMap = mutableMapOf<Char, Long>()
        globalMap.forEach { (key, value) ->
            countMap.putIfAbsent(key[0], 0)
            countMap.putIfAbsent(key[1], 0)
            countMap[key[0]] = countMap[key[0]]!! + value
            countMap[key[1]] = countMap[key[1]]!! + value
        }

        val maxEntry = countMap.maxByOrNull { it.value }
        var max = maxEntry!!.value / 2
        if (template.startsWith(maxEntry.key) || template.endsWith(maxEntry.key)) {
            max++
        }

        val minEntry = countMap.minByOrNull { it.value }
        var min = minEntry!!.value / 2
        if (template.startsWith(minEntry.key) || template.endsWith(minEntry.key)) {
            min++
        }

        return (max - min).toString()
    }
}
