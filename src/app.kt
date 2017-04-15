/**
 * Created by akatsuki174 on 2017/04/08.
 */
fun main(args: Array<String>) {
    // Hello World
    simplestVersion()
    readingANameFromTheCommandLine(args)
    readingManyNamesFromTheCommandLine(args)
    aMultiLanguageHello(args)
    AnObjectOrientedHello(args[0]).greet()
    // Basic syntax walk-through

    // Use a conditional expressions
    println(max(args[0].toInt(), args[1].toInt()))
    println(max(a = args[0].toInt(), b = args[1].toInt())) // 名前付き引数を使っても書ける
    // Null-checks
    nullChecks(args)
}
