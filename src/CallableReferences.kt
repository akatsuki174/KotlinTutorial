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
