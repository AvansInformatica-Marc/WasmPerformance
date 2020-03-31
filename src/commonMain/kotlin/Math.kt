@ExperimentalUnsignedTypes
object Math {
    tailrec fun fibonacci(n: ULong, a: Double = 0.0, b: Double = 1.0): Double
            = if (n == 0uL) a else fibonacci(n - 1u, b, a + b)

    tailrec fun factorial(n: ULong, run: Double = 1.0): Double
            = if (n == 1uL) run else factorial(n - 1u, run * n.toLong())
}
