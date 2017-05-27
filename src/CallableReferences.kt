/**®
 * Created by akatsuki174 on 2017/05/22.
 */

/**
 * "Callable References"もしくは"Feature Literals"は
 * 名前付きの関数やプロパティを値として渡すことができます。
 * 関数を渡す場合はプレフィックスとして`::`を付けます。
 * ::を付けることでその関数の関数オブジェクトを取得することができます。
 */

fun referenceToAFunction() {
    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))

    println(::square) // 「fun square(kotlin.Int): kotlin.Int」と表示される
    // println(::square(2)) <- これはエラーになる
    val funcObj = ::square
    println(funcObj(2)) // <- これは4が出力される
}

fun isOdd(x: Int) = x % 2 != 0

fun square(i: Int): Int = i * i

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

