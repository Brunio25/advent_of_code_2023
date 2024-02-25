import java.io.File

abstract class Day(
    private val inputPath: String
) {
    val readFileList by lazy { File(inputPath).useLines { it.toList() } }

    abstract fun part1(): Any
    abstract fun part2(): Any
}