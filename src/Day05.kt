import kotlin.math.min
import kotlin.math.pow

fun main() {
    fun getFromMap(map: List<String>, id: Long): Long {
        map.forEach { s ->
            val (destStart, sourceStart, length) = s.split(" ").map { it.toLong() }
            if (id in sourceStart..sourceStart + length) {
                return destStart + (id - sourceStart)
            }
        }

        return id
    }

    fun getLocForSeed(seed: Long, map: String): Long {
        val props = map.split("\n\n")
            .map { it.split("\n") }
            .map { it.filter { s -> s.isNotEmpty() } }
            .map { it.drop(1) }
        var curId = seed
        props.forEach {
            curId = getFromMap(it, curId)
        }

        return curId
    }

    fun part1(input: List<String>): Long {
        val seeds = input[0].split("seeds: ")[1]
            .split(" ")
            .map { it.trim().toLong() }

        val map = input.drop(1).joinToString("\n")

        return seeds.minOfOrNull { getLocForSeed(it, map) }!!
    }

    fun part2(input: List<String>): Long {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput("Day05")
    println(part1(testInput))

    val input = readInput("Day05")
    part1(input).println()
}
