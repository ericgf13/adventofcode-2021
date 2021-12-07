package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day
import ericgf13.adventofcode.util.Coordinate
import kotlin.math.abs
import kotlin.math.max

class Day05 : Day(5) {
    private val input = getInputAsList()
    private val pattern = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")
    private val lines = ArrayList<Line>()

    init {
        input.forEach {
            val (startX, startY, endX, endY) = pattern.find(it)!!.destructured
            lines.add(Line(Coordinate(startX.toInt(), startY.toInt()), Coordinate(endX.toInt(), endY.toInt())))
        }
    }

    override fun part1(): String {
        val map = HashMap<Coordinate, Int>()
        lines.filter { it.start.x == it.end.x || it.start.y == it.end.y }.forEach { process(it, map) }
        return map.filterValues { it > 1 }.count().toString()
    }

    override fun part2(): String {
        val map = HashMap<Coordinate, Int>()
        lines.forEach { process(it, map) }
        return map.filterValues { it > 1 }.count().toString()
    }

    private fun process(line: Line, map: HashMap<Coordinate, Int>) {
        val stepX = if (line.start.x < line.end.x) 1 else if (line.start.x > line.end.x) -1 else 0
        val stepY = if (line.start.y < line.end.y) 1 else if (line.start.y > line.end.y) -1 else 0

        for (i in 0..max(abs(line.start.x - line.end.x), abs(line.start.y - line.end.y))) {
            val coord = Coordinate(line.start.x + i * stepX, line.start.y + i * stepY)
            map.putIfAbsent(coord, 0)
            map[coord] = map[coord]!! + 1
        }
    }

    data class Line(val start: Coordinate, val end: Coordinate)
}
