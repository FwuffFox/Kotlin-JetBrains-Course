package tictactoe

import kotlin.math.abs

fun main() {
    val game = Game(" ".repeat(9))
    game.printField()
    var mark = 'X'

    while (true) {
        print("Enter the coordinates: ")
        val inputCoords = readln().split(' ')
        val row = inputCoords[0]
        val col = inputCoords[1]

        //Bad Inputs
        when {
            !row[0].isDigit() || !col[0].isDigit() -> {
                println("You should enter numbers!")
                continue
            }
            row.length != 1 || col.length != 1 -> {
                println("You should enter numbers!")
                continue
            }
            row.toInt() !in 1..3 || col.toInt() !in 1..3 -> {
                println("Coordinates should be from 1 to 3!")
                continue
            }
            game.isFilled(row.toInt(), col.toInt()) -> {
                println("This cell is occupied! Choose another one!")
                continue
            }
        }

        game.fillCell(mark, row.toInt(), col.toInt())
        game.printField()

        mark = if (mark == 'X') 'O' else 'X'

        when (val state = game.checkGameState()) {
            "Game not finished" -> continue
            else -> {
                println(state)
                break
            }
        }

    }
}

class Game(string: String) {

    var field: MutableList<MutableList<Char>>

    init {
        val row1 = string[0..2].toCharArray().toMutableList()
        val row2 = string[3..5].toCharArray().toMutableList()
        val row3 = string[6..8].toCharArray().toMutableList()
        field = mutableListOf(row1, row2, row3)
    }

    fun checkGameState(): String {
        //Rows
        return if (field[0].all { it == field[0][0] } && field[0][0] != ' ') "${field[0][0]} wins"
        else if (field[1].all { it == field[1][0] } && field[1][0] != ' ') "${field[1][0]} wins"
        else if (field[2].all { it == field[2][0] } && field[2][0] != ' ') "${field[2][0]} wins"

        //Columns
        else if (listOf(field[0][0], field[1][0], field[2][0]).all { it == field[0][0] } && field[0][0] != ' ') "${field[0][0]} wins"
        else if (listOf(field[0][1], field[1][1], field[2][1]).all { it == field[0][1] } && field[0][1] != ' ') "${field[0][1]} wins"
        else if (listOf(field[0][2], field[1][2], field[2][2]).all { it == field[0][2] } && field[0][2] != ' ') "${field[0][2]} wins"

        //Diagonals
        else if (listOf(field[0][0], field[1][1], field[2][2]).all { it == field[0][0] } && field[0][0] != ' ') "${field[0][0]} wins"
        else if (listOf(field[0][2], field[1][1], field[2][0]).all { it == field[0][2] } && field[0][2] != ' ') "${field[0][2]} wins"
        else if (field.any { list -> list.any { it == ' ' } }) "Game not finished"

        else "Draw"
    }

    fun printField() = println(this.toString())

    fun fillCell(mark: Char, row: Int, column: Int) {
        if (isFilled(row, column)) return
        field[row-1][column-1] = mark
    }

    fun isFilled(row: Int, column: Int): Boolean = field[row - 1][column - 1] != ' '


    override fun toString(): String {
        return "---------\n" +
                "| ${field[0].joinToString(" ")} |\n" +
                "| ${field[1].joinToString(" ")} |\n" +
                "| ${field[2].joinToString(" ")} |\n" +
                "---------"
    }
}

private operator fun String.get(intRange: IntRange): String {
    return this.substring(intRange)
}
