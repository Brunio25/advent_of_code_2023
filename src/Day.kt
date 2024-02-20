import java.io.File

abstract class Day(
    private val inputPath: String? = null
) {
    val readFile by lazy { inputPath?.let { File(inputPath).useLines { it.toList() } } ?: emptyList() }

    abstract fun part1(): Any
    abstract fun part2(): Any
}