package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day
import ericgf13.adventofcode.util.Coordinate

class Day11 : Day(11) {
    private val input = getInputAsList().map { it.toList().map(Char::digitToInt).toMutableList() }
    private var part2Result = 0;

    override fun part1(): String {
        var flashCount = 0
        var step = 0

        do {
            step++
            var stepFlashCount = 0

            input.forEachIndexed { y, row ->
                row.forEachIndexed { x, _ ->
                    input[y][x]++
                }
            }

            do {
                var flash = false

                input.forEachIndexed { y, row ->
                    row.forEachIndexed { x, energy ->
                        if (energy > 9) {
                            flash = true
                            row[x] = 0
                            stepFlashCount++
                            if (step <= 100) {
                                flashCount++
                            }

                            val adjacentCoords = listOf(
                                Coordinate(x + 1, y),
                                Coordinate(x - 1, y),
                                Coordinate(x, y + 1),
                                Coordinate(x, y - 1),
                                Coordinate(x + 1, y - 1),
                                Coordinate(x + 1, y + 1),
                                Coordinate(x - 1, y - 1),
                                Coordinate(x - 1, y + 1),
                            )

                            adjacentCoords.forEach { coord ->
                                if (coord.x >= 0 && coord.x < row.size && coord.y >= 0 && coord.y < input.size && input[coord.y][coord.x] != 0) {
                                    input[coord.y][coord.x]++
                                }
                            }
                        }
                    }
                }
            } while (flash)
        } while (stepFlashCount < 100)

        part2Result = step
        return flashCount.toString()
    }

    override fun part2(): String {
        return part2Result.toString()
    }
}
