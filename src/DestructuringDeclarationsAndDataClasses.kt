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

/**
 *  Data class gets component functions, one for each property declared
 *  in the primary constructor, generated automatically, same for all the
 *  other goodies common for data: toString(), equals(), hashCode() and copy().
 *  参考リンク：http://kotlinlang.org/docs/reference/data-classes.html#data-classes
 */
data class User(val name: String, val id: Int)

fun getUser(): User {
    return User("Alex", 1)
}

fun dataClasses() {
    val user = getUser()
    println("name = ${user.name}, id = ${user.id}")

    // or

    val (name, id) = getUser()
    println("name = $name, id = $id")

    // or

    println("name = ${getUser().component1()}, id = ${getUser().component2()}")
}

