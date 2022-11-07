package cinema

fun showSeats(rows: Int, seats: Int, layout: MutableList<MutableList<String>>) {
    println()
    println("Cinema:")
    for (i in 0..rows) {
        for (j in 0..seats) print("${layout[i][j]} ")
        println()
    }
}
fun buyTicket(rows: Int, seats: Int, layout: MutableList<MutableList<String>>): Int {
    var row = 0
    var seat = 0
    var price = 0
    try {
        do {
            println()
            println("Enter a row number:")
            row = readln().toInt()
            println("Enter a seat number in that row:")
            seat = readln().toInt()
            if (layout[row][seat] == "B") {
                println("That ticket has already been purchased!")
            } else {
                price = if (rows * seats <= 60 || row <= rows / 2) {
                    10
                } else {
                    8
                }
                println("Ticket price: $$price")
            }
        } while (layout[row][seat] == "B")
        layout[row][seat] = "B"
        showSeats(rows, seats, layout)
        return price
    } catch(e: Exception) {
        println("Wrong input!")
        buyTicket(rows, seats, layout)
    }
    return price
}

fun statistics(rows: Int, seats: Int, tickets: Int, sales: Int, total: Int) {
    println("Number of purchased tickets: $tickets")
    val percentage = 100.0 * tickets.toDouble() / (rows * seats).toDouble()
    val formatPercentage = "%.2f".format(percentage)
    println("Percentage: $formatPercentage%")
    println("Current income: $$sales")
    println("Total income: $$total")
}

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    val layout = MutableList(rows + 1) {
        MutableList(seats + 1) { "0" }
    }
    for (i in 0..rows) {
        for (j in 0..seats) {
            if (i == 0) {
                if (j == 0) layout[i][j] = " "
                else layout[i][j] = j.toString()
            } else {
                if (j == 0) layout[i][j] = i.toString()
                else layout[i][j] = "S"
            }
        }
    }
    var tickets = 0
    var sales = 0
    val total = if (rows * seats <= 60) {
        rows * seats * 10
    } else {
        10 * seats * (rows / 2) + 8 * seats * (rows - rows / 2)
    }

    do {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        val userInput = readln()
        when (userInput) {
            "1" -> showSeats(rows, seats, layout)
            "2" -> {
                sales += buyTicket(rows, seats, layout)
                tickets++
            }
            "3" -> statistics(rows, seats, tickets, sales, total)
        }
    } while (userInput != "0")

}