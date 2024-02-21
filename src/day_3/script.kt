package day_3

import Day
import kotlin.math.max
import kotlin.math.min

class DayThree(inputPath: String) : Day(inputPath) {
    private val specialCharRegex = "[^\\d.]".toRegex()
    private val numbersRegex = "\\d+".toRegex()

    override fun part1(): Any = readFileList.mapIndexed { row, line ->
        "\\d+".toRegex().findAll(line).sumOf { match ->
            val start = max(match.range.first - 1, 0)
            val end = min(match.range.last + 1, line.length - 1)

            if (isSpecialMatch(row, start..end)) match.value.toInt()
            else 0
        }
    }.sum()

    private fun isSpecialMatch(row: Int, range: IntRange): Boolean {
        val previousRow = readFileList.getOrNull(row - 1)?.slice(range)?.contains(specialCharRegex)
        val nextRow = readFileList.getOrNull(row + 1)?.slice(range)?.contains(specialCharRegex)
        val rowLeft = readFileList[row].getOrNull(range.first)?.toString()?.matches(specialCharRegex)
        val rowRight = readFileList[row].getOrNull(range.last)?.toString()?.matches(specialCharRegex)
        return listOf(previousRow, nextRow, rowLeft, rowRight).contains(true)
    }

    override fun part2(): Int = readFileList.mapIndexed { row, line ->
        "\\*".toRegex().findAll(line).sumOf { match ->
            val rangeStart = max(match.range.first - 1, 0)
            val rangeEnd = min(match.range.last + 1, line.length - 1)

            val numbers = findMatchingNumbers(row, rangeStart .. rangeEnd)

            if (numbers.size == 2) numbers.first() * numbers.last() else 0
        }
    }.sum()


    private fun findMatchingNumbers(rowIndex: Int, range: IntRange): List<Int> {
        val previousRow = numbersRegex.findAll(readFileList[rowIndex - 1]).filter { it.range.intersect(range).isNotEmpty() }
        val currentRow = numbersRegex.findAll(readFileList[rowIndex]).filter { it.range.intersect(range).isNotEmpty() }
        val nextRow = numbersRegex.findAll(readFileList[rowIndex + 1]).filter { it.range.intersect(range).isNotEmpty() }

        return (previousRow + currentRow + nextRow).map { it.value.toInt() }.toList()
    }
}

fun main() {
    val part = DayThree("src/day_3/input.txt").part2()
    println(part)
}
