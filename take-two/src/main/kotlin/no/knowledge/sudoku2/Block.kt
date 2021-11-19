package no.knowledge.sudoku2

class Block(
    val name: String
) {

    val squares: MutableList<Square> = mutableListOf<Square>()

    fun check(): Boolean {
        if (squares.size != 9) {
            throw IllegalArgumentException("Can not check a block that does not have nine squares")
        }

        val mapOfMultipleValues = squares.filter { it.value != NOT_SET }
            .groupBy { it.value }
            .filter { it.value.size > 1 }

        return mapOfMultipleValues.isEmpty()
    }

    override fun toString(): String {
        return this.name
    }
}