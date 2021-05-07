fun main()
{
    val n = readLine()!!.toInt()
    var ar: Array<Int> = Array<Int>(n,{0})

    arrayInput(ar,n)
    ar.forEach{
        print("$it ")
    }
    /*println(ar.min())
    println(ar.max())
    println(ar.sum())
    println(ar.fold(1, {currentResult, element -> currentResult * element}))*/
    println("умножение = ${arrayOp(ar.iterator(), {a,b -> a*b}, ar[0])}")

}

fun arrayInput(array : Array<Int>, size : Int) : Array<Int> =   // ввод массива с клавиатуры
    arrayInput(array, 0, size)
tailrec fun arrayInput(array : Array<Int>, counter : Int, size : Int) : Array<Int> =
    if (counter == size) array else {
        array[counter] = readLine()!!.toInt()
        arrayInput(array, counter + 1, size)
    }

tailrec fun arrayOp(a: Iterator<Int>, f: (Int, Int) -> Int, result: Int): Int =
    if (a.iterator().hasNext() == false) result else
        arrayOp(a, f, f(a.iterator().next(),result))


