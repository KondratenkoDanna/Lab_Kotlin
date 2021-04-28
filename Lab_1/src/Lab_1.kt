import kotlin.math.sqrt

//Найти делитель числа, являющийся взаимно простым с наибольшим количеством цифр данного числа.
fun vzSimpleNumbers(x: Int, y: Int): Boolean
{
    if (x == 1) return true
    if (y % x == 0) return false
    for (i in 2..(x/2 + 1))
        if (y % i == 0 && x % i == 0)
            return false
    return true
}

tailrec fun kolVpSimpleNumbers(x: Int, y: Int, counter: Int): Int = if (x == 0) counter else
    ( if (vzSimpleNumbers(x % 10,y))
                 { val counter1 = 1 + counter
                     kolVpSimpleNumbers(x/10, y, counter1)
                 }
                else kolVpSimpleNumbers(x/10, y, counter)
            )

fun foundDel(x: Int): Int {
    var maxKol = 0
    var del = 1
    for (i in 1..(x/2 + 1))
    {
        if (x % i == 0)
        {
            if (maxKol > kolVpSimpleNumbers(x, i,0))
                maxKol = kolVpSimpleNumbers(x, i,0)
            del = i
        }
    }
    return del
}

// ______________10______________ - Суммирование простых числе до 2 миллионов
fun sumTwoMillionSimpleNumbers(beforeNumber: Int): Long
{
    val a = Array(beforeNumber + 1, {true})
    val array = func(a, 1)
    return sumTwoMillionSimpleNumbers(a, 1, 0)
}

tailrec fun changeArray(a: Array<Boolean>, step: Int, size: Int, i: Int): Array<Boolean> = if (step * step + step * i > (size - 1)) a else {
    var ar = a
    ar[step * step + step * i] = false
    changeArray(ar, step, size, i + 1)
}

tailrec fun func(a: Array<Boolean>, step: Int): Array<Boolean> = if (existsSimpleNumbers(a) == false) a // если нет простых чисел
else {
    var newA = a
    var newStep = step
    loop@ for (i in 1 .. (a.size - 1))
        if (a[i] == true && i > step) {
            newStep = i
            newA = changeArray(a, i, a.size, 0)
            break@loop
        }
    func(newA, newStep)
}

fun existsSimpleNumbers(a: Array<Boolean>): Boolean {
    for (i in 0 .. a.size - 1)
        if (simpleNumber(i) == false && a[i] == true) return true
    return false
}
fun simpleNumber(x: Int): Boolean = simpleNumber(x, 2, true)
tailrec fun simpleNumber(x: Int, counter: Int, result: Boolean): Boolean = if (counter  > sqrt(x.toDouble())) result else {
    if (x % counter == 0) simpleNumber(x,counter + 1, false) else
        simpleNumber(x,counter + 1, result)
}

tailrec fun sumTwoMillionSimpleNumbers(a: Array<Boolean>, counter: Int, sum: Long): Long = if (counter + 1 > a.size) sum else
    if (a[counter] == true) sumTwoMillionSimpleNumbers(a, counter + 1, sum + counter) else
        sumTwoMillionSimpleNumbers(a, counter + 1, sum)

fun main()
{
       val x = readLine()!!.toInt()
    val del = foundDel(x)
    println(del)
}