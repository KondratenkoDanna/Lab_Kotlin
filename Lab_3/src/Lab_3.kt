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

// 7 8 18 20 32 35 38 44 56
//_________________7_________________ - циклический сдвиг вправо на 2 позиции
fun cyclicShift(a: List<Int>): List<Int>
{
    val ar1 = a.drop(2)
    val ar2 = a.dropLast(2)
    val aNew = ar1 + ar2
    return aNew
}
//_________________8_________________ - нахождение индексов двух минимальных чисел списка
fun index2minElements(a: MutableList<Int>) {
    val list = a.toCollection(mutableListOf<Int>())  // копирование необходимо, так как при простом приравнивании не будет создаваться новый список, а лишь другое имя будет
    val min1 = list.min()                            // указывать на тот же массив
    val index1 = a.indexOf(min1)
    while (list.contains(min1) == true)
        list.remove(min1)
    val min2 = list.min()
    val index2 = a.indexOf(min2)
    println("index1 = ${index1 + 1} ")
    println("index2 = ${index2 + 1}")
}
//_________________18_________________ - нахождение чисел до первого минимального
fun elementsBeforeMin(a: MutableList<Int>) {
    val min = a.min()
    val index = a.indexOf(min)
    val elementsBeforeMin = a.dropLast(a.size - index)
    for (el in elementsBeforeMin)
        print("$el  ")
}

fun main()
{
    val l = mutableListOf<Int>(3,45,7,8,1,14,5,32)
    elementsBeforeMin(l)

}





