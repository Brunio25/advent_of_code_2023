package day_4

import Day

class DayFour(inputPath: String) : Day(inputPath) {
    override fun part1(): Int = readFileList
        .map { line ->
            line.trim().split(":")[1].trim().split("|").map { numList ->
                numList.trim().split("\\s+".toRegex()).map { it.trim().toInt() }
            }.let { numLists ->
                numLists[1].filter { numLists[0].contains(it) }
            }
        }
        .filter { it.isNotEmpty() }
        .sumOf { it.drop(1).fold(1) { acc, _ -> acc * 2 }.toInt() }


    private val captureGame = "Card\\s+(\\d+):".toRegex()

    override fun part2(): Any {
        val games = readFileList.mapNotNull { captureGame.matchAt(it, 0)?.groupValues?.get(1)?.toInt() }
        val copiesWon: Map<Int, List<Int>> = readFileList.mapIndexed { index, line ->
            line.trim().split(":")[1].trim().split("|").map { numList ->
                numList.trim().split("\\s+".toRegex()).map { it.trim().toInt() }
            }.let { numLists ->
                val range =
                    games[index] + 1..games[index] + numLists[1].filter { numLists[0].contains(it) }.size

                games[index] to range.toList()
            }
        }.toMap()

        val stack = copiesWon.map { it.key }.toMutableList()
        var index = 0
        while (index < stack.size) {
            copiesWon[stack[index]]?.toList()?.let { stack.addAll(it) }
            index++
        }

        return stack.size
    }
}

fun main() {
    val part = DayFour("src/day_4/input.txt").part2()
    println(part)
}
