package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day
import ericgf13.adventofcode.util.Coordinate

class Day13 : Day(13) {
    private val input = getInputAsList()
    private val dots = input.filterNot { it.isEmpty() || it.contains("fold") }.map { it.split(",") }.map { Coordinate(it[0].toInt(), it[1].toInt()) }
    private val folds = input.filter { it.contains("fold") }.map { it.removePrefix("fold along ") }

    override fun part1(): String {
        processFold(folds[0])
        return dots.toSet().count().toString()
    }

    override fun part2(): String {
        folds.forEach(::processFold)

        /*
        for (y in 0..dots.maxOf { it.y }) {
            for (x in 0..dots.maxOf { it.x }) {
                if (dots.any { it.x == x && it.y == y }) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println("")
        }
        */

        return "ECFHLHZF"
    }

    private fun processFold(fold: String) {
        val axis = fold[0]
        val value = fold.substring(2).toInt()

        dots.forEach { dot ->
            if (axis == 'x') {
                if (dot.x > value) {
                    dot.x = value * 2 - dot.x
                }
            } else {
                if (dot.y > value) {
                    dot.y = value * 2 - dot.y
                }
            }
        }
    }
}
