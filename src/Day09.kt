import kotlin.math.min
import kotlin.math.pow

fun main() {
    fun extrapolate(seq: List<Long>, backwards: Boolean): Long {
        var value = 0L
        var mult = 1
        var curSeq = seq
        while(curSeq.any{ it != 0L }) {
            if (backwards) {
                value += curSeq.first() * mult
                mult *= -1
            } else {
                value += curSeq.last()
            }
            curSeq = curSeq.windowed(2).map { (a, b) -> b - a }
        }
        return value
    }

    fun part1(input: List<String>): Long {
        return input.foldRight(0L) { s, acc ->
            acc + extrapolate(s.split(" ").map { it.trim().toLong() }, false)
        }
    }

    fun part2(input: List<String>): Long {
        return input.foldRight(0L) { s, acc ->
            acc + extrapolate(s.split(" ").map { it.trim().toLong() }, true)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput("Day09")
    println(part2(testInput))

    val input = readInput("Day09")
    part2(input).println()
}
