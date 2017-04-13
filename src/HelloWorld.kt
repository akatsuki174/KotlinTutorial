/**
 * Created by akatsuki174 on 2017/04/10.
 */

/**
 * 最もシンプルなHello World.
 * 文末にセミコロンを付けても付けなくてもいい
 */
internal fun simplestVersion() {
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
internal fun readingANameFromTheCommandLine(args: Array<String>) {
    if (args.size == 0) {
        println("Please provide a name as a command-line argument")
        return
    }
    println("Hello, ${args[0]}!")
}

/**
 * ループはこのようにして書くことができるが、他の書き方もある。
 */
internal fun readingManyNamesFromTheCommandLine(args: Array<String>) {
    for (name in args) {
        println("Hello, $name!")
    }
}

/**
 * `val`は1度だけ代入できるローカル変数
 * if文はこのように1行で書くこともできる
 * whenによるパターンマッチングも行っている。
 */
internal fun aMultiLanguageHello(args: Array<String>) {
    val language = if (args.size == 0) "EN" else args[0]
    println(when (language) {
        "EN" -> "Hello!"
        "FR" -> "Salut!"
        "IT" -> "Ciao!"
        else -> "Sorry, I can't greet you in $language yet"
    })
}
