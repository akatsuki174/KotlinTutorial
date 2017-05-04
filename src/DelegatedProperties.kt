/**
 * Created by akatsuki174 on 2017/04/30.
 */

/**
 * 新しい文法として `val <プロパティ名> : <タイプ> by <式>` という形のものが出てくる
 * byの後の式はdelegateである。get()やset()メソッドは委譲されプロパティに対応します。
 * プロパティdelegatesはinterfaceで実装されている必要はありませんが、
 * getValue()とsetValue()を提供する必要があります。
 */

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Example {
    // プロパティがvarの場合はsetValueも実装している必要がある
    // valの場合はgetValueだけでも良い
    var p: String by Delegate() // プロパティの委譲

    var obs: String by Delegates.observable("default") {
        prop, old, new ->
        println("$old -> $new")
    }

    val lStr: String by lazy {
        println("in lazy value")
        "lazy String"
    }

    override fun toString() = "Example Class"
}

class Delegate() {
    // KPropertyにはプロパティ名や型の情報が入っている
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "getter: $thisRef, thank you for delegating '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("setter: $value has been assigned to ${prop.name} in $thisRef")
    }
}

fun customDelegate() {
    val example = Example()
    println(example.p)  // getterが呼ばれる
    example.p = "NEW"   // setterが呼ばれる
    println(example.p) // getterが呼ばれる

    // Standard Delegates

    // observable delegate
    // 値の変更を検知することができる
    println("最初 = ${example.obs}")
    example.obs = "値の代入"
    println("代入後 = ${example.obs}")

    // lazy
    // 1回目の呼び出し時にはクロージャ内の処理が行われるが
    // 2回目以降の呼び出し時には単に初回の評価結果が返される
    println(example.lStr)   // クロージャ内のprintln()文も実行される
    println(example.lStr)   // 初回評価結果である"lazy String"しかprintされない
}

