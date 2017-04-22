/**
 * Created by akatsuki174 on 2017/04/23.
 */

/**
 * この例では分解宣言というコンセプトを紹介する。
 * 分解宣言をすると一度に複数の変数を作成できる。
 * 必要な数のコンポーネント関数を呼び出せれば、分解宣言の右側には何でも置ける。
 * 参考リンク：http://kotlinlang.org/docs/reference/multi-declarations.html#multi-declarations
 */
fun destructuringDeclarations() {
    val pair = Pair(1, "one")
    val (num, name) = pair

    println("num = $num, name = $name")
}

class Pair<K, V>(val first: K, val second: V) {
    operator fun component1(): K {
        return first
    }

    operator fun component2(): V {
        return second
    }
}

