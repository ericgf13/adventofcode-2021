package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day
import ericgf13.adventofcode.util.Coordinate

class Day02 : Day(2) {
    private val input = getInputAsList()
    private val pattern = Regex("(.+) (.+)")

    override fun part1(): String {
        val coord = Coordinate(0, 0)

        for (command in input) {
            val (direction, unitStr) = pattern.find(command)!!.destructured
            val unit = unitStr.toInt()

            when (direction) {
                "forward" -> coord.offsetX(unit)
                "down" -> coord.offsetY(unit)
                "up" -> coord.offsetY(-unit)
            }
        }

        return (coord.x * coord.y).toString()
    }

    override fun part2(): String {
        val coord = Coordinate(0, 0)
        var aim = 0

        for (command in input) {
            val (direction, unitStr) = pattern.find(command)!!.destructured
            val unit = unitStr.toInt()

            when (direction) {
                "forward" -> {
                    coord.offsetX(unit)
                    coord.offsetY(aim * unit)
                }
                "down" -> aim += unit
                "up" -> aim -= unit
            }
        }

        return (coord.x * coord.y).toString()
    }
}
