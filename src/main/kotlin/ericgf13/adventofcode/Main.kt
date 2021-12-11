package ericgf13.adventofcode

import ericgf13.adventofcode.days.*
import kotlin.system.measureTimeMillis

fun main() {
    val days = listOf(
        Day01(),
        Day02(),
        Day03(),
        Day04(),
        Day05(),
        Day06(),
        Day07(),
        Day08(),
        Day09(),
        Day10(),
        Day11()
    )

    days.forEach {
        val sb = StringBuilder()
        sb.appendLine("===== DAY ${it.dayNum} =====")
        sb.appendLine(" - " + measureTimeMillis { sb.append("Part 1: ${it.part1()}") } + " ms")
        sb.appendLine(" - " + measureTimeMillis { sb.append("Part 2: ${it.part2()}") } + " ms")
        print(sb)
    }
}
