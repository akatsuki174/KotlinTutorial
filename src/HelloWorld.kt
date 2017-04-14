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
 *
 * 参考リンク
 * http://kotlinlang.org/docs/reference/basic-types.html#strings
 * http://kotlinlang.org/docs/reference/basic-types.html#arrays
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
 * 参考リンク：http://kotlinlang.org/docs/reference/basic-syntax.html#using-a-for-loop
 */
internal fun readingManyNamesFromTheCommandLine(args: Array<String>) {
    for (name in args) {
        println("Hello, $name!")
    }

    // このような書き方もできる
    //for (i in args.indices)
    //    println("Hello, ${args[i]}!")

    //for ((index, value) in args.withIndex())
    //println("$index: Hello, $value!")
}

/**
 * `val`は1度だけ代入できるローカル変数
 * if文はこのように1行で書くこともできる
 * whenによるパターンマッチングも行っている。
 * 参考リンク：http://kotlinlang.org/docs/reference/control-flow.html#when-expression
 */
internal fun aMultiLanguageHello(args: Array<String>) {
    val language = if (args.size == 0) "EN" else args[0]
    // 分岐をブロックにすることもできる
    //    val language = if (args.size == 0) {
    //        "EN"
    //    } else {
    //        args[0]
    //    }

    // もちろん他の言語で見るような書き方もできる
    //    var language = ""
    //    if (args.size == 0) {
    //        language = "EN"
    //    } else {
    //        args[0]
    //    }

    println(when (language) {
        "EN" -> "Hello!"
        "FR" -> "Salut!"
        "IT" -> "Ciao!"
        else -> "Sorry, I can't greet you in $language yet"
    })

    // 複数条件に当てはまる場合は最初に当てはまった条件の処理が実行される
    // val x = 1
    // when (x) {
    //     in 1..10 -> println("1 <= x <= 10")
    //     in 0..5 -> println("0 <= x <= 5")
    //     else -> println("other")
    // }
    // 出力 ： 1 <= x <= 10

    // 分岐条件を「,」でまとめることもできる
    //    val x = 1
    //    when (x) {
    //        0,1 -> println("x = 0 or 1")
    //        else -> println("x != 0 or 1")
    //    }
}

/**
 * 初期コンストラクタを持ったクラスとメンバー関数がある
 * このオブジェクトを生成する時にnewというキーワードは不要
 * 参考リンク：http://kotlinlang.org/docs/reference/classes.html#classes
 */
class AnObjectOrientedHello(val name: String) {
    // このように書くこともできるがプライマリコンストラクタがアノテーションや可視性修飾子を
    // 持っていない場合は↑の書き方でも良い
    // class AnObjectOrientedHello constructor(val name: String) {
    // }

    fun greet() {
        println("Hello, $name")
    }
}
