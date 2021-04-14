fun sumNumber(x: Int) : Int
{
    var n = x
    var sum = 0
    while (n != 0)
    {
        sum += n % 10
        n /= 10
    }
    return sum
}
fun prNumber(x: Int) : Int
{
    var n = x
    var composition = 0
    while (n != 0)
    {
        composition *= n % 10
        n /= 10
    }
    return composition
}

fun max(num:Int): Int = if (num < 10) num else if (num % 10 > max(num/10)) num % 10 else max(num/10)

fun min(num:Int): Int = if (num < 10) num else if (num % 10 < min(num/10)) num % 10 else min(num/10)

fun main() {
    val sum: Int = readLine()!!.toInt()
    val max: Int = max(sum)
    val min: Int = min(sum)
    println("max = $max")
    println("min = $min")
}
