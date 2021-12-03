package ericgf13.adventofcode

import ericgf13.adventofcode.days.Day01
import ericgf13.adventofcode.days.Day02
import ericgf13.adventofcode.days.Day03
import kotlin.system.measureTimeMillis

fun main() {
    val days = listOf(
        Day01(),
        Day02(),
        Day03()
    )

    days.forEach {
        val sb = StringBuilder()
        sb.appendLine("===== DAY ${it.dayNum} =====")
        sb.appendLine(" - " + measureTimeMillis { sb.append("Part 1: ${it.part1()}") } + " ms")
        sb.appendLine(" - " + measureTimeMillis { sb.append("Part 2: ${it.part2()}") } + " ms")
        print(sb)
    }
}
