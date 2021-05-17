import java.io.File
import java.io.BufferedReader
import kotlin.math.abs

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
//_________________20_________________ - найти все пропущенные цифры
fun missingNumbers(max: Int, l: List<Int>, counter: Int, lNew: List<Int>): List<Int> = if (counter == max) lNew else
    if (l.contains(counter) == false){
        missingNumbers(max, l, counter + 1,lNew.plus(counter))
    }
        else missingNumbers(max, l, counter + 1, lNew)

fun missingNumbers(l: List<Int>, missingNumders: List<Int>): List<Int> {
    val min = l.min()!!.toInt()
    val max = l.max()!!.toInt()

    return missingNumbers(max,l,min + 1,missingNumders)
}

fun printList(l: List<Int>) = printList(l, 0)
fun printList(l: List<Int>, counter: Int): List<Int> = if (counter + 1 > l.size) l else
    if (l.size > counter) { print("${l[counter]}  ")
    printList(l, counter + 1) } else
        printList(l, counter + 1)
//_________________32_________________ - найти количество локальных максимумов
fun kolLocMax(l: List<Int>): Int = kolLocMax(l,1,0)
fun kolLocMax(l: List<Int>, counter: Int, kol: Int): Int = if (counter + 1 > l.size - 1) kol else
    if (l[counter - 1] < l[counter] && l[counter + 1] < l[counter]) kolLocMax(l,counter + 2, kol + 1)
        else kolLocMax(l,counter + 1, kol)

//_________________32_________________ - Дано вещественное число R и массив вещественных чисел. Найти элемент массива, который наиболее близок к данному числу
fun nearestNumber(l: List<Double>, r: Double) = nearestNumber(l, l[0],r,0)
fun nearestNumber(l: List<Double>, nearestNumber: Double, r: Double, counter: Int): Double = if (l.size - 1 < counter) nearestNumber else
    if (abs(l[counter] - r) < abs(nearestNumber - r)) nearestNumber(l, l[counter], r, counter + 1)
        else nearestNumber(l,nearestNumber,r,counter + 1)

fun main()
{// 7 8 18 20 32 35 38 44 56
    val l = mutableListOf<Double>(1.1,2.1,3.5,3.66,2.45,1.01,5.05,5.45,5.001,5.015)
    print(nearestNumber(l,5.1))
}





