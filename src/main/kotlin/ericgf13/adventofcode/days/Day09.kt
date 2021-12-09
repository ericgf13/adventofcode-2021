package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day
import ericgf13.adventofcode.util.Coordinate

class Day09 : Day(9) {
    private val input = getInputAsList().map { it.map(Char::digitToInt) }

    override fun part1(): String {
        var result = 0

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, point ->
                if (isLowPoint(x, y, point)) {
                    result += point + 1
                }
            }
        }

        return result.toString()
    }

    override fun part2(): String {
        val basins = mutableListOf<List<Coordinate>>()

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, point ->
                if (isLowPoint(x, y, point)) {
                    val lowPoint = Coordinate(x, y)
                    val basinLocations = mutableListOf<Coordinate>()

                    basinLocations.add(lowPoint)
                    checkAdjacent(lowPoint, basinLocations)

                    basins.add(basinLocations)
                }
            }
        }

        return basins.map { it.size }.sortedDescending().take(3).reduce(Int::times).toString()
    }

    private fun isLowPoint(x: Int, y: Int, point: Int): Boolean {
        return (y - 1 < 0 || input[y - 1][x] > point)
                && (y + 1 >= input.size || input[y + 1][x] > point)
                && (x - 1 < 0 || input[y][x - 1] > point)
                && (x + 1 >= input[0].size || input[y][x + 1] > point)
    }

    private fun checkAdjacent(point: Coordinate, basinLocations: MutableList<Coordinate>) {
        val value = input[point.y][point.x]
        val adjacentCoords = listOf(
            Coordinate(point.x, point.y - 1),
            Coordinate(point.x, point.y + 1),
            Coordinate(point.x - 1, point.y),
            Coordinate(point.x + 1, point.y)
        )

        adjacentCoords.forEach { coord ->
            if (!basinLocations.contains(coord) && coord.y >= 0 && coord.y < input.size && coord.x >= 0 && coord.x < input[0].size) {
                val coordValue = input[coord.y][coord.x]
                if (coordValue in (value + 1)..8) {
                    basinLocations.add(coord)
                    checkAdjacent(coord, basinLocations)
                }
            }
        }
    }
}
