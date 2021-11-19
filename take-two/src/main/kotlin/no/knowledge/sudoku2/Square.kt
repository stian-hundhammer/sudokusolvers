package no.knowledge.sudoku2

const val NOT_SET = 0

data class Square(
    var value: Int,
    val preset: Boolean = false,
    val blocks: List<Block>
)