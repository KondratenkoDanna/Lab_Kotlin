fun main()
{
    val n = readLine()!!.toInt()
    val ar: Array<Int?> = arrayOfNulls<Int>(n)

    val array: Array<Int> = emptyArray()
    arrayInput(array,n)
    ar.forEach{
        print("$it ")
    }
    println(min(ar as Array<Int>, 9, n))
}

fun arrayInput(array : Array<Int>, size : Int) : Array<Int> =
    arrayInput(array, 0, size)

tailrec fun arrayInput(array : Array<Int>, counter : Int, size : Int) : Array<Int> =
    if (counter == size) array else {
        array[counter] = readLine()!!.toInt()
        arrayInput(array, counter + 1, size)
    }

fun min(ar : Array<Int>, min: Int, count: Int) : Int =
    if (ar.size == count) min else
        if (ar[count] < min) min(ar, ar[count], count + 1) else
            min(ar, min, count + 1)

