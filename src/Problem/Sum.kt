package Problem

/**
 * Created by akatsuki174    on 2017/07/12.
 */

/*
 * sum()関数が、引数のarray aの全ての要素の和を計算するように実装する
 */

fun sum(a: Array<Int>): Int {
    // 標準で用意されているsum()関数もある
    //a.sum()
    var sum = 0
    for (num in a) {
        sum += num
    }
    return sum
}
