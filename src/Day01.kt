fun main() {
    fun part1(input: List<String>): Int {
        return input.foldRight(0) { s, acc ->
            val firstDigit = s.find { it.isDigit() } ?: return@foldRight acc
            val lastDigit = s.findLast { it.isDigit() } ?: return@foldRight acc
            acc + "$firstDigit$lastDigit".toInt()
        }
    }

    val digitsWords = listOf(
        "one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine",
        "1", "2", "3", "4", "5", "6", "7", "8", "9"
    )

    val mapDigits = mapOf(
        "one" to 1, "two" to 2, "three" to 3, "four" to 4,
        "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9,
        "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6, "7" to 7, "8" to 8, "9" to 9
    )

    fun findWord(startIndex: Int, s: String): String? {
        for (digit in digitsWords) {
            val endIndex = startIndex + digit.length
            if (endIndex > s.length) continue
            val sub = s.substring(startIndex, endIndex)
            if (sub == digit) {
                return digit
            }
        }
        return null
    }

    fun getFirstWord(s: String): String {
        for (startIndex in s.indices) {
            findWord(startIndex, s)?.let { return it }
        }
        throw Exception("No digit found in $s")
    }

    fun getLastWord(s: String): String {
        for (startIndex in s.indices.reversed()) {
            findWord(startIndex, s)?.let { return it }
        }
        throw Exception("No digit found in $s")
    }

    fun part2(input: List<String>): Int {
        return input.foldRight(0) { s, acc ->
            val firstDigit = mapDigits[getFirstWord(s)] ?: return@foldRight acc
            val lastDigit = mapDigits[getLastWord(s)] ?: return@foldRight acc
            acc + "$firstDigit$lastDigit".toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput("Day01")
    println(part2(testInput))
//    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part2(input).println()
//    part2(input).println()
}
