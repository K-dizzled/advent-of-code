fun main() {
    fun isSymbol(c: Char): Boolean {
        return (!c.isDigit()) && (c != '.')
    }

    fun isGear(c: Char): Boolean {
        return c == '*'
    }

    fun getSymbolAround(field: List<String>, index: Pair<Int, Int>, condition: (Char) -> Boolean): Pair<Int, Int>? {
        val (row, col) = index
        val left = if (col == 0) null else Pair(row, col - 1)
        val right = if (col == field[row].length - 1) null else Pair(row, col + 1)
        val top = if (row == 0) null else Pair(row - 1, col)
        val bottom = if (row == field.size - 1) null else Pair(row + 1, col)
        val leftTop = if (row == 0 || col == 0) null else Pair(row - 1, col - 1)
        val rightTop = if (row == 0 || col == field[row].length - 1) null else Pair(row - 1, col + 1)
        val leftBottom = if (row == field.size - 1 || col == 0) null else Pair(row + 1, col - 1)
        val rightBottom = if (row == field.size - 1 || col == field[row].length - 1) null else Pair(row + 1, col + 1)

        return listOfNotNull(left, right, top, bottom, leftTop, rightTop, leftBottom, rightBottom).firstOrNull { condition(field[it.first][it.second]) }
    }

    fun part1(input: List<String>): Int {
        var result = 0
        var isNumberNearSymbol = false
        var number = ""
        for (i in input.indices) {
            for (j in input[i].indices) {
                val cur = input[i][j]
                if (cur.isDigit()) {
                    number += cur
                    if (getSymbolAround(input, Pair(i, j), ::isSymbol) != null) {
                        isNumberNearSymbol = true
                    }
                } else {
                    if (isNumberNearSymbol) {
                        result += number.toInt()
                    }
                    number = ""
                    isNumberNearSymbol = false
                }
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val gearsToNumbers: MutableMap<Pair<Int, Int>, MutableList<Int>> = mutableMapOf()
        var gearIndices: MutableList<Pair<Int, Int>> = mutableListOf()
        var number = ""
        for (i in input.indices) {
            for (j in input[i].indices) {
                val cur = input[i][j]
                if (cur.isDigit()) {
                    number += cur
                    getSymbolAround(input, Pair(i, j), ::isGear)?.let {
                        gearIndices.add(it)
                    }
                } else {
                    gearIndices.forEach {
                        gearsToNumbers.getOrPut(it) { mutableListOf() }.add(number.toInt())
                    }
                    number = ""
                    gearIndices = mutableListOf()
                }
            }
        }
        return gearsToNumbers.asSequence().map{ it.value.toSet() }
            .map{ it.toList() }
            .filter { it.size == 2 }
            .map { it.first() * it.last() }
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput("Day03")
    println(part2(testInput))
//    check(part1(testInput) == 142)

    val input = readInput("Day03")
    part2(input).println()
//    part2(input).println()
}
