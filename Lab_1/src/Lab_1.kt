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

fun main()
{
       val x = readLine()!!.toInt()
    val del = foundDel(x)
    println(del)
}