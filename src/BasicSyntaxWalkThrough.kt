/**
 * Created by akatsuki174 on 2017/04/15.
 */

/**
 * `if`は式であり、値を返す。
 * ifは三項演算子と同じ役割を果たせるため、Kotlinでは三項演算子はない。
 * HelloWorld.ktのaMultiLanguageHello()でも同様の使い方をしていた。
 * 参考リンク：http://kotlinlang.org/docs/reference/control-flow.html#if-expression
 *
 * 関数は単一の式を返すだけなら中括弧なしで1行で書くことができる。
 * 参考リンク：http://kotlinlang.org/docs/reference/functions.html#single-expression-functions
 */
// Use a conditional expressions
fun max(a: Int, b: Int) = if (a > b) a else b

// 戻り値の型を指定することも可能
// 型推論できる場合はわざわざ書かなくても良い
//fun max(a: Int, b: Int):Int = if (a > b) a else b

// もちろんブロックを使って書くこともできる
//fun max(a: Int, b: Int): Int {
//    return if (a > b) a else b
//}

/**
 * nullを許可する場合はnullableであることを明示的にする必要があります。
 * nullの可能性がある場合は「?」を付与します。
 * 参考リンク：http://kotlinlang.org/docs/reference/null-safety.html#null-safety
 */
fun nullChecks(args: Array<String>) {
    if (args.size < 2) {
        println("No number supplied");
    } else {
        val x = parseInt(args[0])
        val y = parseInt(args[1])

        // x,yにnullが入っている可能性がある
        if (x != null && y != null) {
            print(x * y)
        } else {
            println("One of the arguments is null")
        }
    }
}

// もしstrが数字ではなかったらnullを返す
fun parseInt(str: String): Int? {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        println("One of the arguments isn't Int")
    }
    return null
}

/**
 * is演算子はオブジェクトが指定された型かどうかをチェックします。
 * immutableなローカル変数やプロパティをis演算子でチェックした場合は、明示的にキャストする必要はありません。
 * If we is-checked an immutable local variable or property, there's no need
 * 参考リンク
 * http://kotlinlang.org/docs/reference/classes.html#classes-and-inheritance
 * http://kotlinlang.org/docs/reference/typecasts.html#smart-casts
 */
fun isChecksAndSmartCasts(obj: Any): Int? {
    //このタイミングでこのコードを書いてもエラーになる
    //obj.length

    if (obj is String)
        return obj.length // Stringにキャストする必要はない
    return null
}

/**
 * `while`と`do..while`の使い方は以下の通り
 * 参考リンク：http://kotlinlang.org/docs/reference/control-flow.html#while-loops
 */
fun useAWhileLoop(args: Array<String>) {
    var i = 0
    while (i < args.size)
        println(args[i++])
    
    // do..whileで書くとこんなかんじ
    //do {
    //    println(args[i++])
    //} while (i < args.size)
}

/**
 * forループの使い方は以下の通り。
 * 参考リンク：http://kotlinlang.org/docs/reference/control-flow.html#for-loops
 */
fun useAForLoop(args: Array<String>) {
    for (arg in args)
        println(arg)
    println()
    // indexを使う例
    for (i in args.indices)
        println(args[i])
    println()
    // indexとvalueを使う例
    for ((index, value) in args.withIndex())
        println("$index: value is $value")
}

/**
 * ある数字がある範囲の中にあるか確認
 * ある数字がある範囲の外にあるか確認
 * あるコレクションがあるオブジェクトを含むか確認
 * 参考リンク：http://kotlinlang.org/docs/reference/ranges.html#ranges
 */
fun useRangesAndIn(args: Array<String>) {
    val x = args[0].toInt()
    // xがある範囲内に入っているか確認
    val y = 10
    if (x in 1..y - 1)
        println("OK")

    //範囲内で繰り返し
    for (a in 1..5)
        print("${a} ")

    println()
    val array = arrayListOf<String>()
    array.add("aaa")
    array.add("bbb")
    array.add("ccc")

    // ある数値が範囲外か確認
    if (x !in 0..array.size - 1)
        println("Out: array has only ${array.size} elements. x = ${x}")

    // コレクションがあるオブジェクトを含むか確認
    if ("aaa" in array) // collection.contains(obj) is called
        println("Yes: array contains aaa")

    if ("ddd" in array) // collection.contains(obj) is called
        println("Yes: array contains ddd")
    else
        println("No: array doesn't contains ddd")
}

