package day_2

import Day

class DayTwo(inputPath: String) : Day(inputPath) {
    private enum class CUBES(val amount: Int) {
        RED(12),
        GREEN(13),
        BLUE(14);

        companion object {
            fun findCube(str: String) = entries.first { it.name.lowercase() == str }.amount
        }
    }

    override fun part1(): Int = readFile
        .map { line ->
            val splitLine = line.split(":").map { it.trim() }
            val gameId = splitLine[0].split(" ")[1].toInt()

            val map = splitLine[1].split(";").map { hand ->
                hand.trim().split(",").map { cube ->
                    cube.trim().split(" ").map { it.trim() }.let { it[0].toInt() to CUBES.findCube(it[1]) }
                }
            }
            Pair(gameId, map)
        }
        .filterNot { game -> game.second.any { hand -> hand.any { it.first > it.second } } }
        .sumOf { it.first }


    override fun part2(): Int = readFile
        .map { line ->
            val splitLine = line.split(":").map { it.trim() }
            val gameId = splitLine[0].split(" ")[1].toInt()

            val map = splitLine[1].split(";").map { hand ->
                hand.trim().split(",").map { cube ->
                    cube.trim().split(" ").map { it.trim() }.let { it[0].toInt() to it[1] }
                }
            }.flatten()
            Pair(gameId, map)
        }
        .map { game -> game.second.sortedBy { -it.first }.distinctBy { it.second } }
        .sumOf { it.fold(1) { acc, (fst, _) -> acc * fst }.toInt() }

}

fun main() {
    val part2 = DayTwo("src/day_2/input.txt").part2()
    println(part2)
}
