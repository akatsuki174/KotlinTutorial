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
    // 以下の書き方も可能
    for (i in args.indices)
        println(args[i])
}
