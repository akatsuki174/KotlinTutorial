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

}

/**
 * Delegates.lazy()は遅延プロパティを返す。
 * 最初にgetが呼ばれた時はlazy()が渡されたラムダ式が実行され、結果を保持する。
 * それ以降getが呼ばれた時は単に保持していた結果を返す。
 * スレッドセーフにしたい場合は、代わりにblockingLazy()を使います。
 * この場合、1つのスレッドだけで値が計算され、全てのスレッドは同じ値を参照します。
 */

class LazySample {
    val lazy: String by lazy {
        println("computed!")
        "my lazy"
    }
}

fun lazyProperty() {
    val sample = LazySample()
    // 1回目の呼び出し時にはクロージャ内の処理が行われるが
    // 2回目以降の呼び出し時には単に初回の評価結果が返される
    println("lazy = ${sample.lazy}")    // クロージャ内のprintln()文も実行される
    println("lazy = ${sample.lazy}")    // 初回評価結果である"lazy String"しかprintされない
}

