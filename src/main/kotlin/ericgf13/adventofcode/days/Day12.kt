package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day12 : Day(12) {
    private val input = getInputAsList().map { it.split("-").toList() }

    override fun part1(): String {
        val paths = mutableListOf<List<String>>()
        findPath("start", mutableListOf(), paths, true)
        return paths.count { it[it.size - 1] == "end" }.toString()
    }

    override fun part2(): String {
        val paths = mutableListOf<List<String>>()
        findPath("start", mutableListOf(), paths, false)
        return paths.count { it[it.size - 1] == "end" }.toString()
    }

    private fun findPath(location: String, path: MutableList<String>, paths: MutableList<List<String>>, twiceUsed: Boolean) {
        if (location != "end") {
            input.forEach { connection ->
                var next = ""
                if (connection[0] == location && connection[1] != "start" && (connection[1][0].isUpperCase() || !path.contains(connection[1]) || !twiceUsed)) {
                    next = connection[1]
                } else if (connection[1] == location && connection[0] != "start" && (connection[0][0].isUpperCase() || !path.contains(connection[0]) || !twiceUsed)) {
                    next = connection[0]
                }

                if (next != "") {
                    val newTwiceUsed = twiceUsed || (next[0].isLowerCase() && path.contains(next))
                    val newPath = path.toMutableList()
                    newPath.add(next)
                    paths.add(newPath)
                    findPath(next, newPath, paths, newTwiceUsed)
                }
            }
        }
    }
}
