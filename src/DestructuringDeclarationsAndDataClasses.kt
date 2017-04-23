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
    // このようにして受け取ることもできる
    //val num = pair.component1()
    //val name = pair.component2()
    // デフォルトでtoString()が使える
    //println(pair.toString())

    // それぞれ独立で使うことができる
    println("num = $num, name = $name")
}

// 2つのイミュータブルな値を表すデータクラス
class Pair<K, V>(val first: K, val second: V) {
    // 多重宣言で複数の値を返すようにするためにcomponent*()を用意している
    // （*の部分は任意の整数）
    // operator : 演算子をオーバーロードをしたいときに使う
    operator fun component1(): K {
        return first
    }

    operator fun component2(): V {
        return second
    }
}

