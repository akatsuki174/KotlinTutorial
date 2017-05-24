/**®
 * Created by akatsuki174 on 2017/05/22.
 */

/**
 * "Callable References"もしくは"Feature Literals"は
 * 名前付きの関数やプロパティを値として渡すことができます。
 * 関数を渡す場合はプレフィックスとして`::`を付けます。
 */

fun referenceToAFunction() {
    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))
}

fun isOdd(x: Int) = x % 2 != 0

/**
 * 高階関数はcompose(f, g) = f(g(*))のように渡された2つの関数を返します。
 * これはcallable referencesにも適用することができます。
 */

fun compositionOfFunctions() {
    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")
    println(strings.filter(oddLength))
}

fun length(s: String) = s.length

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

