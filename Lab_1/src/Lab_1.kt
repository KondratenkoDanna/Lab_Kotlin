    fun main() {
        var x: Int = readLine()!!.toInt()
        var sum = 0
        while (x != 0)
        {
            sum += x % 10
            x /= 10
        }
        println(sum)
    }
