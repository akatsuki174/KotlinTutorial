package LongerExamples

/**
 * Created by akatsuki174 on 2017/06/10.
 */

/**
 * これは、The Game of Lifeの簡単な実装です。
 * 参考リンク：http://en.wikipedia.org/wiki/Conway's_Game_of_Life
 */

/*
 * セルが生息するところ。不変。
 */
class Field(
        val width: Int,
        val height: Int,
        // この関数はinit(i, j)がtrueかつ生きていたらコンストラクタに伝える役目がある
        init: (Int, Int) -> Boolean
) {
    private val live: Array<Array<Boolean>> = Array(height) { i -> Array(width) { j -> init(i, j) } }

    private fun liveCount(i: Int, j: Int)
            = if (i in 0..height - 1 &&
            j in 0..width - 1 &&
            live[i][j]) 1 else 0

    // 近隣のセルがどれくらい生きているか
    fun liveNeighbors(i: Int, j: Int) =
            liveCount(i - 1, j - 1) +
                    liveCount(i - 1, j) +
                    liveCount(i - 1, j + 1) +
                    liveCount(i, j - 1) +
                    liveCount(i, j + 1) +
                    liveCount(i + 1, j - 1) +
                    liveCount(i + 1, j) +
                    liveCount(i + 1, j + 1)

    // field[i, j]を指定したらこの関数が呼び出される
    operator fun get(i: Int, j: Int) = live[i][j]
}

/**
 * この関数はフィールドの現在の状況を取得し、次のタイミングでの新しいフィールドを返す
 */
fun next(field: Field): Field {
    return Field(field.width, field.height) { i, j ->
        val n = field.liveNeighbors(i, j)
        if (field[i, j])
        // (i, j)は生きている
            n in 2..3 // 2か3の隣接セルが生きていたらまたこのセルも生きている
        else
        // (i, j)が死んでいる
            n == 3 // 3つの隣接セルが生きていたら新しいセルが生まれる
    }
}

/** A few colony examples here */
fun life() {
    // Simplistic demo
    runGameOfLife("***", 3)
    // "Star burst"
    runGameOfLife("""
        _______
        ___*___
        __***__
        ___*___
        _______
    """, 10)
    // Stable colony
    runGameOfLife("""
        _____
        __*__
        _*_*_
        __*__
        _____
    """, 3)
    // Stable from the step 2
    runGameOfLife("""
        __**__
        __**__
        __**__
    """, 3)
    // Oscillating colony
    runGameOfLife("""
        __**____
        __**____
        ____**__
        ____**__
    """, 6)
    // A fancier oscillating colony
    runGameOfLife("""
        -------------------
        -------***---***---
        -------------------
        -----*----*-*----*-
        -----*----*-*----*-
        -----*----*-*----*-
        -------***---***---
        -------------------
        -------***---***---
        -----*----*-*----*-
        -----*----*-*----*-
        -----*----*-*----*-
        -------------------
        -------***---***---
        -------------------
    """, 10)
}

// UTILITIES

fun runGameOfLife(fieldText: String, steps: Int) {
    var field = makeField(fieldText)
    for (step in 1..steps) {
        println("Step: $step")
        for (i in 0..field.height - 1) {
            for (j in 0..field.width - 1) {
                print(if (field[i, j]) "*" else " ")
            }
            println("")
        }
        field = next(field)
    }
}

fun makeField(s: String): Field {
    val lines = s.replace(" ", "").split('\n').filter({ it.isNotEmpty() })
    val longestLine = lines.toList().maxBy { it.length } ?: ""

    return Field(longestLine.length, lines.size) { i, j -> lines[i][j] == '*' }
}
