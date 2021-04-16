fun sumNumberUp(x: Int): Int = if (x == 0) x else sumNumberUp(x/10) + x%10

fun main() {
    val num: Int = readLine()!!.toInt()
    println(sumNumberUp(num))
}