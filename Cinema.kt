package cinema

import java.util.*

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()

    println("Enter the number of seats in each row:")
    val columns = readln().toInt()

    val cinema = Cinema(rows, columns)

    while (true) {
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )

        when (readln().toInt()) {
            0 -> break
            1 -> cinema.print()
            2 -> {
                while (true) {
                    println("Enter a row number:")
                    val row = readln().toInt()

                    println("Enter a seat number in that row:")
                    val column = readln().toInt()

                    if (row > cinema.rows || column > cinema.columns) println("Wrong input!")
                    else if (cinema.placeIsBought(row, column)) println("That ticket has already been purchased!")
                    else {
                        println("Ticket price: \$${cinema.buyTicket(row, column)}")
                        break
                    }
                }
            }
            3 -> println(cinema.cinemaStatistics())
        }
    }
}

/**
 * Creates a cinema
 * @param rows Amount of rows in Cinema
 * @param columns Amount of seats in each row
 */
class Cinema(rows: Int, columns: Int) {
    private val cinema: MutableList<MutableList<String>> = mutableListOf()
    public var rows: Int = 0
    public var columns: Int = 0
    private var income: Int = 0
    private var curIncome: Int = 0
    private var boughtSeats: Int = 0

    init {
        repeat(rows) {
            val row = mutableListOf<String>()
            repeat(columns) {
                row.add("S")
            }
            cinema.add(row)
            this.rows = rows
            this.columns = columns
        }
    }

    /**
     * Prints a cinema
     */
    fun print() {
        println("Cinema:")
        print("  ")
        for (i in 1..columns) {
            print("$i ")
        }
        println()
        for (i in 1..rows) {
            print("$i ")
            for (elem in cinema[i - 1]) {
                print("$elem ")
            }
            println()
        }
    }

    /**
     * Checks if place is bought
     */
    fun placeIsBought(row: Int, column: Int): Boolean = cinema[row - 1][column - 1] == "B"

    /**
     * Buys a ticket
     */
    fun buyTicket(row: Int, column: Int): Int {
        cinema[row - 1][column - 1] = "B"
        val a = if (rows * columns <= 60) {
            10
        } else if (rows % 2 == 0) {
                if (row <= rows / 2) 10
                else 8

        } else {
                if (row < rows / 2 + 1) 10
                else 8
        }
        curIncome += a
        boughtSeats++
        return a
    }

    fun getTotalIncome(): Int {
        var res: Int = 0
        for (i in 1..rows) {
            for (j in 1..columns) {
                val a = if (rows * columns <= 60) {
                    10
                } else if (rows % 2 == 0) {
                    if (i <= rows / 2) 10
                    else 8

                } else {
                    if (i < rows / 2 + 1) 10
                    else 8
                }
                res += a
            }
        }
        return res
    }

    fun cinemaStatistics(): String {
        val percentage: Double = if (boughtSeats != 0) (boughtSeats.toDouble() / (rows * columns) * 100)
                        else 0.0
        val formatPercentage = String.format(locale = Locale.ENGLISH, format="%.2f", percentage)
        return "Number of purchased tickets: $boughtSeats\n" +
                "Percentage: $formatPercentage%\n" +
                "Current income: \$$curIncome\n" +
                "Total income: \$${getTotalIncome()}"
    }
}

