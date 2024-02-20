package day_1

import Day

class DayOne(inputPath: String) : Day(inputPath) {
    private val dict = mapOf(
        "one"   to 1,
        "two"   to 2,
        "three" to 3,
        "four"  to 4,
        "five"  to 5,
        "six"   to 6,
        "seven" to 7,
        "eight" to 8,
        "nine"  to 9
    )

    override fun part1(): Int = readFile
        .map { line -> line.filter { it.isDigit() } }
        .sumOf { line -> (line.first() + "" + line.last()).toInt() }

    override fun part2(): Int = readFile
        .map { line ->
            buildString {
                line.forEachIndexed { index, character ->
                    if (character.isDigit()) {
                        append(character)
                    } else {
                        dict.forEach { (numberString, numberValue) ->
                            if (line.substring(index).startsWith(numberString)) {
                                append(numberValue)
                            }
                        }
                    }
                }
            }
        }
        .map { "${it.first()}${it.last()}" }
        .sumOf { it.toInt() }

}

fun main() {
    val part2 = DayOne("src/day_1/input.txt").part2()
    println(part2)
}
