import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalUnsignedTypes
class MathTests {
    @Test
    fun testFibonacciReturnsCorrectResults1(){
        // Arrange
        val input = 5uL
        val expectedResult = 5.0

        // Act
        val actualResult = Math.fibonacci(input)

        // Assert
        assertEquals(expected = expectedResult, actual = actualResult)
    }

    @Test
    fun testFibonacciReturnsCorrectResults2(){
        // Arrange
        val input = 8uL
        val expectedResult = 21.0

        // Act
        val actualResult = Math.fibonacci(input)

        // Assert
        assertEquals(expected = expectedResult, actual = actualResult)
    }

    @Test
    fun testFactorialReturnsCorrectResults(){
        // Arrange
        val input = 5uL
        val expectedResult = 120.0

        // Act
        val actualResult = Math.factorial(input)

        // Assert
        assertEquals(expected = expectedResult, actual = actualResult)
    }
}