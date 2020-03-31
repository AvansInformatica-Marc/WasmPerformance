class WasmMath {
    /**
     * @param { bigint } n
     * @param { bigint } a
     * @param { bigint } b
     * @returns { bigint }
     */
    static fibonacci(n, a = 0n, b = 1n) {
        return n === 0n ? a : WasmMath.fibonacci(n - 1n, b, a + b)
    }

    /**
     * @param { bigint } n
     * @param { bigint } run
     * @returns { bigint }
     */
    static factorial(n, run = 1n) {
        return n === 1n ? run : WasmMath.factorial(n - 1n, run * n)
    }
}

class Duration {
    #value

    /**
     * @param {bigint} value
     */
    constructor(value) {
        this.#value = value
    }

    toString() {
        const valueMs = this.#value / 1_000_000n
        if(valueMs > 1_000n) {
            const valueS = Number(valueMs) / 1_000
            return (Math.round(valueS * 100) / 100) + "s"
        }
        return (Math.round(Number(valueMs) * 100) / 100) + "ms"
    }
}

function getTime() {
    return BigInt(Date.now()) * 1_000_000n
}

function main(){
    const startTime = getTime()

    const totalElement = document.getElementById("js-total")
    totalElement.innerText = "Running..."

    const fibElement = document.getElementById("js-fib")
    fibElement.innerText = "Running..."
    const [fibResult, fibDuration] = runTimeTest(1_000n, () => WasmMath.fibonacci(1000n))
    fibElement.innerText = fibDuration.toString()
    console.log(`JavaScript: fibonacci(1000) = ${fibResult}`)

    const factElement = document.getElementById("js-fact")
    factElement.innerText = "Running..."
    const [factResult, factDuration] = runTimeTest(10_000n, () => WasmMath.factorial(150n))
    factElement.innerText = factDuration.toString()
    console.log(`JavaScript: factorial(150) = ${factResult}`)

    const endTime = getTime()

    totalElement.innerText = new Duration(endTime - startTime).toString()
}

/**
 * @param { bigint } repeatOperation
 * @param { () => any } block
 */
function runTimeTest(repeatOperation, block) {
    const startTime = getTime()

    for(let i = 0n; i < repeatOperation - 1n; i++) {
        block()
    }

    const value = block()

    const endTime = getTime()

    return [value, new Duration(endTime - startTime)]
}

main()
