fun main() {
    fun parseBag(s: String): Triple<Int, Int, Int> {
        var result = Triple(0, 0, 0)
        val colors = s.split(",")
        colors.forEach { color ->
            val trimmed = color.trim()
            val (count, colorName) = trimmed.split(" ")
            when (colorName) {
                "red" -> result = result.copy(first = result.first + count.toInt())
                "green" -> result = result.copy(second = result.second + count.toInt())
                "blue" -> result = result.copy(third = result.third + count.toInt())
            }
        }
        return result
    }

    fun part1(input: List<String>): Int {
        return input.foldRight(0) { s, acc ->
            val gameIndex = s.split(":")[0].split(" ")[1].toInt()
            val bags =  s.split(":")[1].split(";")
            val available = Triple(12, 13, 14)
            val isPossible = bags.all { bag ->
                val (red, green, blue) = parseBag(bag)
                red <= available.first && green <= available.second && blue <= available.third
            }
            if (isPossible) {
                acc + gameIndex
            } else {
                acc
            }
        }
    }

    fun parseBags(bags: List<String>): Int {
        val parsed = bags.map { parseBag(it) }
        val maxRed = parsed.maxOf { it.first }
        val maxGreen = parsed.maxOf { it.second }
        val maxBlue = parsed.maxOf { it.third }
        return maxRed * maxGreen * maxBlue
    }

    fun part2(input: List<String>): Int {
        return input.foldRight(0) { s, acc ->
            val bags =  s.split(":")[1].split(";")
            val magicNumber = parseBags(bags)
            acc + magicNumber
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput("Day02")
    println(part2(testInput))
//    check(part1(testInput) == 142)

    val input = readInput("Day02")
    part2(input).println()
//    part2(input).println()
}
