package no.knowledge.sudoku2

class Board {

    val squares: List<Square>

    constructor (data: Array<Int>) {

        val blocks: MutableMap<String, Block> = mutableMapOf()

        squares = blockSetupData().split("|")
            .mapIndexed { index, it ->
                val blocksForSquare = it.split(",")
                    .map { blockName ->
                        blocks.computeIfAbsent(blockName) {
                            Block(it)
                        }
                    }

                val value = data[index]
                val square = Square(value, value != NOT_SET, blocksForSquare)
                blocksForSquare.forEach { block -> block.squares.add(square) }

                square

            }
    }

    fun isNextOk(index: Int): Boolean {
        println("at index $index")
        if (index == squares.size) {
            return true
        }

        val current = squares[index]

        return if (current.preset) {
            return isNextOk(index + 1)
        } else {
            for (x in 1..9) {
                current.value = x

                val isOk = current.blocks.all { it.check() } && isNextOk(index + 1)

                if (isOk) return true
            }

            // none was ok - unset it again
            current.value = NOT_SET
            // and return to previous, saying that this was not ok

            println("index $index with groups ${current.blocks} was not found")
            false
        }

    }

    fun printBoard() {
        var index = 0
        for (row in 0..8) {
            print("|")
            for (col in 0..8) {
                val value = squares[index].value

                val toPrint = if (value != NOT_SET) value.toString() else " "

                print(" $toPrint ")

                if (col % 3 == 2) print(" | ")
                index++
            }
            println()
            if (row % 3 == 2) {
                (0..35).forEach { _ -> print("-") }
                println()
            }
        }
    }

    private fun blockSetupData(): String =
        "h1,v1,b1|h1,v2,b1|h1,v3,b1|h1,v4,b2|h1,v5,b2|h1,v6,b2|h1,v7,b3|h1,v8,b3|h1,v9,b3" +
                "|h2,v1,b1|h2,v2,b1|h2,v3,b1|h2,v4,b2|h2,v5,b2|h2,v6,b2|h2,v7,b3|h2,v8,b3|h2,v9,b3" +
                "|h3,v1,b1|h3,v2,b1|h3,v3,b1|h3,v4,b2|h3,v5,b2|h3,v6,b2|h3,v7,b3|h3,v8,b3|h3,v9,b3" +
                "|h4,v1,b4|h4,v2,b4|h4,v3,b4|h4,v4,b5|h4,v5,b5|h4,v6,b5|h4,v7,b6|h4,v8,b6|h4,v9,b6" +
                "|h5,v1,b4|h5,v2,b4|h5,v3,b4|h5,v4,b5|h5,v5,b5|h5,v6,b5|h5,v7,b6|h5,v8,b6|h5,v9,b6" +
                "|h6,v1,b4|h6,v2,b4|h6,v3,b4|h6,v4,b5|h6,v5,b5|h6,v6,b5|h6,v7,b6|h6,v8,b6|h6,v9,b6" +
                "|h7,v1,b7|h7,v2,b7|h7,v3,b7|h7,v4,b8|h7,v5,b8|h7,v6,b8|h7,v7,b9|h7,v8,b9|h7,v9,b9" +
                "|h8,v1,b7|h8,v2,b7|h8,v3,b7|h8,v4,b8|h8,v5,b8|h8,v6,b8|h8,v7,b9|h8,v8,b9|h8,v9,b9" +
                "|h9,v1,b7|h9,v2,b7|h9,v3,b7|h9,v4,b8|h9,v5,b8|h9,v6,b8|h9,v7,b9|h9,v8,b9|h9,v9,b9"

}