package no.knowledge.sudoku2

class App {

    fun run() {
        val board = Board(intBoard)
        board.printBoard()
        board.isNextOk(0)
        println()
        board.printBoard()
    }

}

fun main() {
    App().run()
}


val intBoard = arrayOf(
        9, 4, 0,    0, 5, 1,    7, 0, 3,
        3, 0, 0,    2, 8, 0,    1, 0, 0,
        0, 0, 1,    0, 7, 0,    2, 0, 4,

        0, 3, 9,    0, 0, 0,    4, 0, 5,
        5, 0, 7,    0, 0, 0,    0, 0, 0,
        4, 0, 8,    0, 0, 5,    9, 6, 0,

        0, 9, 3,    0, 2, 0,    8, 0, 0,
        0, 0, 0,    7, 1, 0,    0, 0, 0,
        8, 0, 6,    0, 4, 9,    5, 0, 0
    )