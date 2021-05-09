fun main()
{
    val ar = arrayOp()
    /*println(ar.min())
    println(ar.max())
    println(ar.sum())
    println(ar.fold(1, {currentResult, element -> currentResult * element}))*/
    println("умножение = ${arrayOp(ar.iterator(), {a,b -> a*b}, ar[0])}")

}
fun arrayOp(): Array<Int> {
    print("введите размер массива: ")
    var ar: Array<Int> = Array<Int>(readLine()!!.toInt(),{0})
    return arrayInput(ar)
}

fun arrayInput(array : Array<Int>) : Array<Int> =   // ввод массива с клавиатуры
    arrayInput(array, 0, array.size)
tailrec fun arrayInput(array : Array<Int>, counter : Int, size : Int) : Array<Int> =
    if (counter == size) array else {
        array[counter] = readLine()!!.toInt()
        arrayInput(array, counter + 1, size)
    }

tailrec fun arrayOp(a: Iterator<Int>, f: (Int, Int) -> Int, result: Int): Int =
    if (a.iterator().hasNext() == false) result else
        arrayOp(a, f, f(a.iterator().next(),result))


