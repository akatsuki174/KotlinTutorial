/**
 * Created by akatsuki174 on 2017/04/10.
 */

/**
 * 最もシンプルなHello World.
 * 文末にセミコロンを付けても付けなくてもいい
 */
internal fun SimplestVersion() {
    println("--------------SimplestVersion--------------")
    println("Hello, World!")
}

/**
 * 文字テンプレートや配列へのアクセスの仕方がわかる
 * IntelliJ上でargsを入力するには以下の手順を踏む
 * 1. ⌘ + alt + r
 * 2. eを入力
 * 3. Edit Configurationsが選択されているのでEnter
 * 4. ConfigurationタブのProgram argumentsに入力
 */
internal fun ReadingANameFromTheCommandLine(args: Array<String>) {
    println("--------------ReadingANameFromTheCommandLine--------------")
    if (args.size == 0) {
        println("Please provide a name as a command-line argument")
        return
    }
    println("Hello, ${args[0]}!")
}

/**
 * Line 2 demonstrates the for-loop, that would have been called "enhanced"
 * if there were any other for-loop in Kotlin.
 * See http://kotlinlang.org/docs/reference/basic-syntax.html#using-a-for-loop
 */
fun ReadingManyNamesFromTheCommandLine(args: Array<String>) {
    println("--------------ReadingManyNamesFromTheCommandLine--------------")
    for (name in args) {
        println("Hello, $name!")
    }
}
