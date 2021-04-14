fun sumNumbers3(x: Int): Int = sumNumbers3(x,0)
fun sumNumbers3(x: Int, sum: Int ): Int = if (x < 10) (if (x % 10 % 3 == 0) sum + x % 10 else sum) else (if (x % 10 % 3 == 0) sumNumbers3(x/10,sum + x % 10)
    else sumNumbers3(x/10,sum))

fun main() {
    val kol: Int
    val x = readLine()!!.toInt()
    kol = sumNumbers3(x)
    println(kol)
}
