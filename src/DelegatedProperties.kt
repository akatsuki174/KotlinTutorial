/**
 * Created by akatsuki174 on 2017/04/30.
 */

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 新しい文法として `val <プロパティ名> : <タイプ> by <式>` という形のものが出てくる
 * byの後の式はdelegateである。get()やset()メソッドは委譲されプロパティに対応します。
 * プロパティdelegatesはinterfaceで実装されている必要はありませんが、
 * getValue()とsetValue()を提供する必要があります。
 */
class Example {
    // プロパティがvarの場合はsetValueも実装している必要がある
    // valの場合はgetValueだけでも良い
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

/**
 * observable()関数は2つの引数（初期値と修正のための値）を取る。
 * ハンドラはnameを呼び出すたびに呼ばれ、3つのパラメータ（割り当てられている値、古い値、新しい値）を持っている。
 * もし割り当てを拒否したい場合はobservable()の代わりにvetoable()を使用する。
 * vetoableはプロパティへの代入前にラムダ式が実行され、代入を行うかどうかを値として返す。
 * これにより、代入を拒否することができる。
 */
class Person {
    var name: String by Delegates.observable("no name") {
        d, old, new ->
        println("$old - $new")
    }
}

fun observableProperty() {
    val person = Person()
    println("最初 = ${person.name}")
    person.name = "Carl"
    println("代入後 = ${person.name}")
}

/**
 * Kotlinでは初期化されていない非抽象プロパティを持つことはできません。
 * nullで初期化することもできますが、アクセスするたびに確認する必要があります。
 * Kotlinにはこれを解決するdelegateがあり、あるプロパティに書き込みする前に読み込みをしようとすると
 * 例外が発生します。最初の割り当て後は期待通りに動きます。
 */
class Animal {
    var name: String by Delegates.notNull()

    fun init(name: String) {
        this.name = name
    }
}

fun notNullProperty() {
    val animal = Animal()
    // animal.name -> IllegalStateException
    animal.init("Carl")
    println(animal.name)
}
