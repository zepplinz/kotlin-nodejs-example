interface IConsole {
    println(message: string): void;
}
interface IMath {
    sqrt(value: number): number;
}
class SharedClass {
    private console: IConsole;
    private math: IMath;
    private platform: string = "";
    constructor(console: IConsole, math: IMath) {
        this.console = console;
        this.math = math;
    }
    printMe(): void {
        this.console.println(`Hello Kotlin!\n\nThis is a shared code between multiple runtimes. Current platform: [${this.platform}]:`);
    }
    printPrimes(n: number): void {
        this.calcPrimes(n).forEach(prime => this.console.println(prime.toString()));
    }
    givePrimes(n: number): number[] {
        return this.calcPrimes(n);
    }
    private calcPrimes(n: number): number[] {
        const primes: number[] = [];
        if (n > 0) primes.push(2);
        let c = 1;
        let p = 3;
        while (n > 1) {
            let prime = true;
            const sqrt = this.math.sqrt(p);
            for (let i = 1; i <= c; i++) {
                if (primes[i - 1] < sqrt) {
                    if (p % primes[i] === 0) {
                        prime = false;
                        break;
                    }
                }
            }
            if (prime) {
                primes.push(p);
                c++;
                if (c === n) {
                    break;
                }
            }
            p += 2;
        }
        return primes;
    }
}