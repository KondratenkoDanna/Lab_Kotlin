import java.lang.IllegalArgumentException

// ______________1______________
fun sumNumberUp(x: Int): Int = if (x == 0) x else sumNumberUp(x/10) + x%10
// ______________2______________
fun sumDown(num: Int): Int = sumDown(num, 0)
tailrec fun sumDown(num: Int, sum: Int): Int = if(num == 0) sum else sumDown(num / 10,sum + num % 10)
// ______________3______________ - 5
fun proizUp(num: Int): Int = if (num < 10) num else proizUp(num / 10) * (num % 10)

fun proizDown(num: Int): Int = proizDown(num, 1)
tailrec fun proizDown(num: Int, p: Int): Int = if (num <10) p * num else proizDown(num / 10,p * (num % 10))

fun maxUp(num:Int): Int = if (num < 10) num else if (num % 10 > maxUp(num / 10)) num % 10 else maxUp(num / 10)
fun minUp(num:Int): Int = if (num < 10) num else if (num % 10 < minUp(num / 10)) num % 10 else minUp(num / 10)

tailrec fun digitsDown(num : Int, acum : Int, f : (Int, Int) -> Int, pr : (Int) -> Boolean) : Int =
    if ( num == 0) acum else
        digitsDown(num/10, if (pr(num % 10)) f(num % 10, acum) else acum, f, pr)

fun maxDown(num: Int) : Int = digitsDown(num, 0, {a,b -> if (a>b) a else b}, {x -> true})
fun minDown(num: Int) : Int = digitsDown(num, 9, {a,b -> if (a<b) a else b}, {x -> true})

// ______________4______________
tailrec fun digitsDown(num : Int, acum : Int, f : (Int, Int) -> Int) : Int =
    if ( num == 0) acum else
        digitsDown(num / 10, f(num % 10, acum), f)

fun sum(num: Int) : Int = digitsDown(num, 0, {a,b -> a+b})
fun proiz(num: Int) : Int = digitsDown(num, 1, {a,b -> a*b})
fun max(num: Int) : Int = digitsDown(num, 0, {a,b -> if (a>b) a else b})
fun min(num: Int) : Int = digitsDown(num, 9, {a,b -> if (a<b) a else b})
// ______________5______________ - 3
// ______________6______________
fun maxChet(num: Int): Int = digitsDown(num, 0, {a, b -> if (a > b) a else b}, {x -> x % 2 == 0})
fun del3(num: Int): Int = digitsDown(num, 1, {a, b -> a * b}, {x -> x % 3 == 0 && x > 4})
fun pr(num: Int): Int = digitsDown(num, 0, {a, b -> a + b}, {x -> x % 2 == 0 && x <= 4})
// ______________7______________
//Mетод 1. Найти количество чисел, взаимно простых с заданным.
fun vzSimpleNumbers(x: Int, y: Int): Boolean = if (y == 1) true else (if (x % y == 0) false else vzSimpleNumbers(x, y, x / 2 + 1, true))
tailrec fun vzSimpleNumbers(x: Int, y: Int, counter: Int, result: Boolean): Boolean = if (counter  == 1) result else {
    if (y % counter == 0 && x % counter == 0) {
        val result1 = false
        vzSimpleNumbers(x, y, counter - 1, result1)
    }
    else vzSimpleNumbers(x, y, counter - 1, result)
}

fun kolNumbersVzSimple(x: Int, y: Int) : Int = kolNumbersVzSimple(x, y, 0)
fun kolNumbersVzSimple(x: Int, y: Int, counter: Int) : Int = if (y == 0) counter else {
    if (vzSimpleNumbers(x, y))
        kolNumbersVzSimple(x, y - 1, counter + 1) else
            kolNumbersVzSimple(x, y - 1, counter)
    }

//Метод 2. Найти сумму цифр числа, делящихся на 3.
fun sumNumbers3(x: Int): Int = sumNumbers3(x,0)
tailrec fun sumNumbers3(x: Int, sum: Int ): Int = if (x < 10) (if (x % 10 % 3 == 0) sum + x % 10 else sum)
        else (if (x % 10 % 3 == 0) sumNumbers3(x/10,sum + x % 10)
else sumNumbers3(x/10,sum))

//Метод 3. Найти делитель числа, являющийся взаимно простым с наибольшим количеством цифр данного числа
fun kolVzN(x: Int, y: Int): Int = kolVzN(x, y, 0)
tailrec fun kolVzN(x: Int, y: Int, counter: Int): Int = if (x == 0) counter else                        //количество взаимно простых цифр числа с заданным
        (if (vzSimpleNumbers(x % 10, y)) kolVzN(x / 10, y, counter + 1) else
            kolVzN(x / 10, y, counter))

fun delNumders(x: Int): Int = delNumders(x, x / 2 + 1, x, kolVzN(x, x))
tailrec fun delNumders(x: Int, counter: Int, del: Int, kolNumb: Int): Int = if (counter == 1) del else {    // 1 не включительно, потому что тогда везде будет 1
    if (x % counter == 0)
    {
        if (kolVzN(x, counter, 0) > kolNumb)
        delNumders(x, counter - 1, counter, kolVzN(x, counter)) else
            delNumders(x, counter - 1, counter, kolNumb)
    }
    else
        delNumders(x, counter - 1, del, kolNumb)
}
// ______________8______________
fun or(s: Char): (Int, Int) -> Int = when (s) {
    '+' -> {a: Int, b: Int -> a + b}
    '-' -> {a: Int, b: Int -> a - b}
    '*' -> {a: Int, b: Int -> a * b}
    else -> throw IllegalArgumentException("Unknown operation")
}

// ______________78______________ - Монетные перегородки
fun splitN(n: Int): Int = splitN(n, n) // расщепление n
fun splitN(n: Int, counter: Int): Int =
    if (counter > n) splitN(n, n) else {

        if (counter == 0) {
            if (n == 0) 1 else 0
        } else splitN(n, counter - 1) + splitN(n - counter, counter)
    }

fun delMillion(n: Int): Int = if (splitN(n) % 1000000 >= 1) n else delMillion(n + 1)

fun main() {
    //val num: Int = readLine()!!.toInt()
    //val num2: Int = readLine()!!.toInt()
    //val n = kolFoundelay(5,5,0)
    println(splitN(20))
    //println(kolFoundelay( 3))
    //println(roundelay(readLine()!!.toInt(), readLine()!!.toInt()))
}