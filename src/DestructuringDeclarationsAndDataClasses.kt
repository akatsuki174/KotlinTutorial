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
    // プライマリコンストラクタで宣言した順番で
    // component関数を使って呼ぶことができる
    println("name = ${getUser().component1()}, id = ${getUser().component2()}")

    // 特に定義しなくてもtoString関数が使える
    println(user.toString())

    // 特に定義しなくてもhashCode関数が使える
    println(user.hashCode())

    // 特に定義しなくてもequals関数が使える
    val otherUser = User("Tom", 2)
    println(user.equals(otherUser))

    // 特に定義しなくてもcopy関数が使える
    val copyUser = user.copy("Jim", 3)
    println("name = ${copyUser.name}, id = ${copyUser.id}")
}

/**
 *  mapについての用例
 *  参考リンク：https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html
 */
fun traversingAMap() {
    val map = hashMapOf<String, Int>()
    map.put("one", 1)
    map.put("two", 2)
    // このように初期化することもできる
    //val map2 = hashMapOf("一" to 1, "二" to 2)
    //println("value = ${map2.get("一")}")

    // JavaにもあったようにLinkedHashMap,SortedMapが使える
    //val linkedMap = linkedMapOf("mikan" to "🍊", "apple" to "🍎")
    //val sortedMapOf = sortedMapOf("mikan" to "🍊", "apple" to "🍎")

    for ((key, value) in map) {
        println("key = $key, value = $value")
    }

    // 別の方法でvalueを取り出すこともできる
    println("value = ${map.get("one")}")
    println("value = ${map["two"]}")
}

/**
 * データクラスはtoString(), equals(), hashCode(), copy()が自動で作られます。
 * 参考リンク：http://kotlinlang.org/docs/reference/data-classes.html#data-classes
 */
fun autogeneratedFunctions() {
    val user = User("Alex", 1)
    println(user) // toString()

    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)

    println("user == secondUser: ${user == secondUser}")
    println("user == thirdUser: ${user == thirdUser}")

    // copy() function
    println(user.copy())            // User(name=Alex, id=1)
    println(user.copy("Max"))       // User(name=Max, id=1)  user.copy(name = "Max")と同義
    println(user.copy(id = 2))      // User(name=Alex, id=2)
    println(user.copy("Max", 2))    // User(name=Max, id=2)
}

