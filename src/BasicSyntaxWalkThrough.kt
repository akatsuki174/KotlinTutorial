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

// Return null if str does not hold a number
fun parseInt(str: String): Int? {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        println("One of the arguments isn't Int")
    }
    return null
}
