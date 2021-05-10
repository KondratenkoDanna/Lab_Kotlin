import java.io.File
import java.io.BufferedReader

// ________________1________________
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

// ________________3________________
fun readFromFile(path: String): Array<Int> {
    val bufferedReader: BufferedReader = File(path).bufferedReader()
    val inputString = bufferedReader.use {
        it.readText()
    }
    val ar = inputString.toString() + " "
    var i = 0
    var indexStart = 0
    var indexEnd = 0
    var flag = 0
    var array: Array<Int> = arrayOf()
    for(el in ar)
        if (el.toInt() == 32) {
            val str = ar.subSequence(indexStart, indexEnd)
            if (flag == 0) {
                println("size = $str")
                array= Array(str.toString().toInt(),{0})
                flag = 1
            } else {
                array[i] = str.toString().toInt()
                i++
            }
            indexEnd++
            indexStart = indexEnd
        }
        else indexEnd++
    return array
}
fun getArray(): () -> Array<Int> =
    when (readLine()!!.toString()) {
        "file" -> { {readFromFile("source.txt")} }
        "keyboard" -> { {arrayOp()} }
        else -> { {arrayOp()} }
    }


fun main()
{
    println("Input from a file or from the keyboard?")
    val a = getArray()()
    for (el in a) print("$el  ")
}




