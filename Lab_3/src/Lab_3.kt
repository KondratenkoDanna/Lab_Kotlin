import java.io.File
import java.io.BufferedReader
import kotlin.math.abs
import kotlin.math.sqrt

// ________________1________________
fun listOp(): List<Int> {
    print("введите размер массива:  ")
    val size = readLine()!!.toInt()
    val l = mutableListOf<Int>()
    return arrayInput(l, size)
}

fun arrayInput(l : List<Int>, size: Int) : List<Int> =   // ввод массива с клавиатуры
    arrayInput(l, 0, size)
tailrec fun arrayInput(l : List<Int>, counter : Int, size : Int) : List<Int> =
    if (counter == size) l else arrayInput(l.plus(readLine()!!.toInt()), counter + 1, size)


tailrec fun listOp(a: Iterator<Int>, f: (Int, Int) -> Int, result: Int): Int =
    if (a.iterator().hasNext() == false) result else
        listOp(a, f, f(a.iterator().next(),result))

// ________________3________________
fun readFromFile(path: String): List<Int> {
    val bufferedReader: BufferedReader = File(path).bufferedReader()
    val inputString = bufferedReader.use {
        it.readText()
    }
    val st = inputString + " "
    var i = 0
    var indexStart = 0
    var indexEnd = 0
    var flag = 0
    var l = mutableListOf<Int>()
    for(el in st)
        if (el.toInt() == 32) {
            val str = st.subSequence(indexStart, indexEnd)

            l.plus(str.toString().toInt())
            indexEnd++
            indexStart = indexEnd
        }
        else indexEnd++
    return l
}
fun getList(): () -> List<Int> =
    when (readLine()!!.toString()) {
        "file" -> { {readFromFile("source.txt")} }
        "keyboard" -> { {listOp()} }
        else -> { {listOp()} }
    }

//_________________7_________________ - циклический сдвиг вправо на 2 позиции
fun cyclicShift(a: List<Int>): List<Int>
{
    val l1 = a.drop(2)
    val l2 = a.dropLast(2)
    val lNew = l1 + l2
    return lNew
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

//_________________35_________________ - Дано вещественное число R и массив вещественных чисел. Найти элемент массива, который наиболее близок к данному числу
fun nearestNumber(l: List<Double>, r: Double) = nearestNumber(l, l[0],r,0)
fun nearestNumber(l: List<Double>, nearestNumber: Double, r: Double, counter: Int): Double = if (l.size - 1 < counter) nearestNumber else
    if (abs(l[counter] - r) < abs(nearestNumber - r)) nearestNumber(l, l[counter], r, counter + 1)
        else nearestNumber(l,nearestNumber,r,counter + 1)

//_________________38_________________ - Дан целочисленный массив и отрезок a..b. Необходимо найти количество элементов, значение которых принадлежит этому отрезку.
fun kolElSegment(l: List<Int>, a: Int, b: Int) = kolElSegment(l, a, b, 0, 0)
fun kolElSegment(l: List<Int>, a: Int, b: Int, counter: Int, kol: Int): Int = if (counter > l.size - 1) kol else
    if (l[counter] >= a && l[counter] <= b) kolElSegment(l,a,b,counter + 1, kol + 1)
        else kolElSegment(l,a,b,counter + 1, kol)

//_________________44_________________ - Дан массив чисел. Необходимо проверить, чередуются ли в нем целые и вещественные числа.
fun series(l: List<Double>): Boolean = series(l, 0, true)
fun series(l: List<Double>, counter: Int, result: Boolean): Boolean = if (counter + 1 > l.size - 1) result else
    if (l[counter] == l[counter].toInt().toDouble() && l[counter + 1] == l[counter + 1].toInt().toDouble()
        || l[counter] != l[counter].toInt().toDouble() && l[counter + 1] != l[counter + 1].toInt().toDouble()) series(l, counter + 1, false)
            else series(l, counter + 1, result)

//_________________56_________________ - Для введенного списка посчитать среднее арифметическое непростых элементов, которые больше, чем среднее арифметическое простых.
fun simpleNumber(x: Int): Boolean = simpleNumber(x, 2, true)
tailrec fun simpleNumber(x: Int, counter: Int, result: Boolean): Boolean = if (counter  > sqrt(x.toDouble())) result else {
    if (x % counter == 0) simpleNumber(x, counter + 1, false) else
        simpleNumber(x, counter + 1, result)
}

fun arithmeticMeanSimple(l: List<Int>, sum: Double, kol: Double, counter: Int): Double = if (counter == l.size - 1) sum/kol else
    if (simpleNumber(l[counter]) == true) arithmeticMeanSimple(l, sum + l[counter], kol + 1, counter + 1)
        else arithmeticMeanSimple(l, sum, kol, counter + 1)

fun arithmeticMeanNoSimple(l: List<Int>, sum: Double, kol: Double, counter: Int, arithmeticMeanSimple: Double): Double = if (counter == l.size) sum/kol else
    if (simpleNumber(l[counter]) == false && l[counter] > arithmeticMeanSimple) {
        arithmeticMeanNoSimple (l, sum+l[counter], kol+1, counter+1, arithmeticMeanSimple)
    }  else arithmeticMeanNoSimple(l, sum, kol, counter + 1, arithmeticMeanSimple)

fun task44(): Double {
    val l = listOp()
    val arithmeticMeanSimple = arithmeticMeanSimple(l,0.0,0.0,0)
    return arithmeticMeanNoSimple(l, 0.0,0.0,0,arithmeticMeanSimple(l,0.0,0.0,0))
}

fun main() {
    print(task44())

    //print(arithmeticMeanNoSimple(l,0.0,0.0,0,arithmeticMeanSimple(l,0.0,0.0,0)))
}







