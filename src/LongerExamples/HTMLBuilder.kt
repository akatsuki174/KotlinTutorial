package LongerExamples

/**
 * Created by akatsuki174 on 2017/06/01.
 */

/**
 * これはGroobyスタイルの型安全ビルダーの例です。
 *
 * ビルダーは、コード内のデータを宣言的に記述するのに適しています。
 * この例の中ではKotlinでHTMLページをどのように記述するかを紹介します。
 *
 * 詳しくはこちらのページを御覧ください。
 * http://kotlinlang.org/docs/reference/type-safe-builders.html
 *
 * ※Builderパターンはbuild.gradleの定義にも使われている書き方。
 *  独自のブロックを定義してその中で初期化する。
 *  XMLの生成やUIコンポーネントのレイアウト、3Dシーンの描画などに有効
 */

fun htmlBuilder(args: Array<String>) {
    // ビルドしたいモデルを定義
    val result =
            html {
                head {
                    title { +"XML encoding with Kotlin" }
                }
                body {
                    h1 { +"XML encoding with Kotlin" }
                    p { +"this format can be used as an alternative markup to XML" }

                    // 属性やテキストコンテンツを持つ要素
                    a(href = "http://jetbrains.com/kotlin") { +"Kotlin" }

                    // 要素が入れ子になっている
                    p {
                        +"This is some"
                        b { +"mixed" }
                        +"text. For more see the"
                        a(href = "http://jetbrains.com/kotlin") { +"Kotlin" }
                        +"project"
                    }
                    p { +"some text" }

                    // コマンドライン引数から生成したコンテンツ
                    p {
                        +"Command line arguments were:"
                        ul {
                            for (arg in args)
                                li { +arg }
                        }
                    }
                }
            }
    println(result)
}

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String? {
        val builder = StringBuilder()
        for (a in attributes.keys) {
            builder.append(" $a=\"${attributes[a]}\"")
        }
        return builder.toString()
    }


    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

abstract class TagWithText(name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class HTML() : TagWithText("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(), init)

    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

class Head() : TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title() : TagWithText("title")

abstract class BodyTag(name: String) : TagWithText(name) {
    fun b(init: B.() -> Unit) = initTag(B(), init)
    fun p(init: P.() -> Unit) = initTag(P(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun ul(init: UL.() -> Unit) = initTag(UL(), init)
    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }
}

class Body() : BodyTag("body")
class UL() : BodyTag("ul") {
    fun li(init: LI.() -> Unit) = initTag(LI(), init)
}

class B() : BodyTag("b")
class LI() : BodyTag("li")
class P() : BodyTag("p")
class H1() : BodyTag("h1")

class A() : BodyTag("a") {
    public var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}

// `クラス名().->`と書くことで当該クラスをレシーバーとして
// そのクラスインスタンスに従属する引数なしの関数という定義になります。
// htmlに引数として関数リテラルを渡したとき、それは拡張関数として型付けされます
fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}

