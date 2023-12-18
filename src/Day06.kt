import kotlin.math.min
import kotlin.math.pow

fun main() {
    fun calculateWays(duration: Long, record: Long): Long {
        var ans = 0L
        for (i in 0..duration) {
            println("$i of $duration")
            val dist = i * (duration - i)
            if (dist > record) {
                ans++
            }
        }
        return ans
    }

    fun part1(input: List<String>): Long {
        val raceDurations = input[0].split(' ')
            .drop(1)
            .filter { it.isNotEmpty() }
            .map { it.trim().toLong() }
        val recordDistances = input[1].split(' ')
            .drop(1)
            .filter { it.isNotEmpty() }
            .map { it.trim().toLong() }

        var result = 1L
        for (i in raceDurations.indices) {
            result *= calculateWays(raceDurations[i], recordDistances[i])
        }
        return result
    }

    fun part2(input: List<String>): Long {
        val raceDuration = input[0].split(' ')
            .drop(1)
            .filter { it.isNotEmpty() }
            .joinToString("")
            .toLong()
        val recordDistance = input[1].split(' ')
            .drop(1)
            .filter { it.isNotEmpty() }
            .joinToString("")
            .toLong()

        return calculateWays(raceDuration, recordDistance)
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readTestInput("Day06")
//    println(part2(testInput))

    val input = readInput("Day06")
    part2(input).println()
}
