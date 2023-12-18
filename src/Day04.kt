import kotlin.math.min
import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        println(input)
        return input.foldRight(0) { s, acc ->
            val numbers = s.split(":")[1]
            val luckyNumbers = numbers.split("|")[0].split(" ").filter { it.isNotEmpty() }.map { it.trim().toInt() }
            val myNumbers = numbers.split("|")[1].split(" ").filter { it.isNotEmpty() }.map { it.trim().toInt() }
            val countLucky = luckyNumbers.count { myNumbers.contains(it) }
            acc + 2.0.pow(countLucky - 1).toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val multiplicationCoefficient = MutableList(input.size) { 1 }
        for (i in input.indices) {
            val s = input[i]
            val numbers = s.split(":")[1]
            val luckyNumbers = numbers.split("|")[0].split(" ").filter { it.isNotEmpty() }.map { it.trim().toInt() }
            val myNumbers = numbers.split("|")[1].split(" ").filter { it.isNotEmpty() }.map { it.trim().toInt() }
            val countLucky = luckyNumbers.count { myNumbers.contains(it) }
            for (j in i + 1..min(i + countLucky, multiplicationCoefficient.size)) {
                multiplicationCoefficient[j] += multiplicationCoefficient[i]
            }
        }

        return multiplicationCoefficient.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput("Day04")
    println(part2(testInput))
//    check(part1(testInput) == 142)

    val input = readInput("Day04")
    part2(input).println()
//    part2(input).println()
}
