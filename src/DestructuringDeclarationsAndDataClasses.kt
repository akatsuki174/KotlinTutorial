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
 *  データクラスにはコンポーネント関数があります。
 *  プライマリコンストラクタで宣言したプロパティを取得できたり、
 *  toString(), equals(), hashCode(), copy()が容易されていたりします。
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
    val (name, id) = getUser() // 分解宣言
    println("name = $name, id = $id")
    // or
    println("name = ${getUser().component1()}, id = ${getUser().component2()}")

    // 特に定義しなくてもtoString関数が使える
    println(user.toString())

    // 特に定義しなくてもhashCode関数が使える
    println(user.hashCode())

    // 特に定義しなくてもequals関数が使える
    val otherUser = User("Tom", 2)
    println(user.equals(otherUser))

    println(user.component1())
    println(user.component2())
}

