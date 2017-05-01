/**
 * Created by akatsuki174 on 2017/04/30.
 */

/**
 * 新しい文法として `val <プロパティ名> : <タイプ> by <式>` という形のものが出てくる
 * byの後の式はdelegateである。get()やset()メソッドは委譲されプロパティに対応します。
 * プロパティdelegatesはinterfaceで実装されている必要はありませんが、
 * getValue()とsetValue()を提供する必要があります。
 */

import kotlin.reflect.KProperty

class Example {
    // プロパティがvarの場合はsetValueも実装している必要がある
    var p: String by Delegate() // プロパティの委譲

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
}

