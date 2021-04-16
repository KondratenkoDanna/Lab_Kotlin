fun sumNumberUp(x: Int): Int = if (x == 0) x else sumNumberUp(x/10) + x%10

fun sumDown(num: Int): Int = sumDown(num, 0)
tailrec fun sumDown(num: Int, sum: Int): Int = if(num == 0) sum else sumDown(num/10,sum+num%10)

fun main() {
    val num: Int = readLine()!!.toInt()
    println(sumDown(num))
}
