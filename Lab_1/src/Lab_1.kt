fun vzSimpleNumbers(x: Int, y: Int): Boolean
{
    if (y % x == 0) return false
    for (i in 2..(x/2 + 1))
        if (y % i == 0 && x % i == 0)
            return false
    return true
}

fun kolVzSimpleNumbers(x: Int): Int
{
    var counter = 0
    for (i in 1..100)
        if (vzSimpleNumbers(x,i)) ++counter
    return counter
}
fun main() {
    val kol: Int
    val x = readLine()!!.toInt()
    kol = kolVzSimpleNumbers(x)
    println(kol)
}
