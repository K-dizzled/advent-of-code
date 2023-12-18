import kotlin.math.min
import kotlin.math.pow

fun main() {
    fun hash(s: String) = s.fold(0L) { hash, c ->
        ((hash + c.code) * 17) % 256
    }

    fun part1(input: List<String>): Long {
        return input[0].split(',').sumOf { hash(it) }
    }

    fun part2(input: List<String>): Long {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput("Day15")
    println(part1(testInput))

    val input = readInput("Day15")
    part1(input).println()
}
