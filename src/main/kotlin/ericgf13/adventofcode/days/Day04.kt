package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day04 : Day(4) {
    private val input = getInputAsList().filter { it.isNotEmpty() }
    private val draw = input[0].split(",").map { Integer.parseInt(it) }
    private val boards = ArrayList<Board>()

    init {
        for (i in 1 until input.size step 5) {
            val board = Board(ArrayList())
            boards.add(board)

            for (y in 0..4) {
                val line = input[i + y].split(" ")
                    .filter { it.isNotBlank() }
                    .map { Number(Integer.parseInt(it), false) }
                board.lines.add(line)
            }
        }
    }

    override fun part1(): String {
        draw.forEach { num ->
            boards.forEach { board ->
                board.lines.forEach { line ->
                    line.forEach { number ->
                        if (number.value == num) number.marked = true
                    }
                }

                if (checkBoard(board)) {
                    val count = board.lines.flatten().filterNot { it.marked }.sumOf { it.value }
                    return (count * num).toString()
                }
            }
        }

        return ""
    }

    override fun part2(): String {
        val won = ArrayList<Board>()

        draw.forEach { num ->
            boards.forEach { board ->
                board.lines.forEach { line ->
                    line.forEach { number ->
                        if (number.value == num) number.marked = true
                    }
                }

                if (!won.contains(board) && checkBoard(board)) {
                    won.add(board)
                }

                if (won.size == boards.size) {
                    val count = board.lines.flatten().filterNot { it.marked }.sumOf { it.value }
                    return (count * num).toString()
                }
            }
        }

        return ""
    }

    private fun checkBoard(board: Board): Boolean {
        for (i in 0..4) {
            var markedRow = true
            var markedColumn = true

            for (j in 0..4) {
                markedRow = markedRow && board.lines[i][j].marked
                markedColumn = markedColumn && board.lines[j][i].marked
            }

            if (markedColumn || markedRow) return true
        }

        return false
    }

    data class Board(val lines: ArrayList<List<Number>>)

    data class Number(val value: Int, var marked: Boolean)
}
