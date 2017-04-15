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
