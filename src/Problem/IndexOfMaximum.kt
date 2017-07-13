package Problem

/**
 * Created by akatsuki174 on 2017/07/13.
 */

/*
 * arraｙの中で一番大きな要素のindexを返す、nullだったら空を返す
 */

fun indexOfMax(a: IntArray): Int? {

    if (a.isEmpty())
        return  null

    val max = a.max()
    var maxIndex = 0
    for (i in a.indices)
        if (max == a[i]) {
            maxIndex = i
        }

    return maxIndex
}
