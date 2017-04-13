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
}
