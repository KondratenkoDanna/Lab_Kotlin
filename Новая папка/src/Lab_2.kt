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

fun main() {
    val num: Int = readLine()!!.toInt()
    println("max = ${del3(num)}")
    println("max = ${pr(num)}")

}
