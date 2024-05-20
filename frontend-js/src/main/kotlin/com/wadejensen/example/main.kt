import { DOMConsole } from './DOMConsole';
document.addEventListener('DOMContentLoaded', () => {
    console.log('DOMContentLoaded');
    document.getElementById('startButton')?.addEventListener('click', start);
});
console.log('main() web called');
function start(ev: Event): void {
    const shared = new SharedClass(new DOMConsole(), Math);
    shared.platform = 'JavaScript Browser';
    shared.printMe();
    shared.printPrimes(1000);
}
class SharedClass {
    platform: string;
    console: DOMConsole;
    math: Math;
    constructor(console: DOMConsole, math: Math) {
        this.console = console;
        this.math = math;
    }
    printMe(): void {
        this.console.log(`Platform: ${this.platform}`);
    }
    printPrimes(limit: number): void {
        const primes = this.generatePrimes(limit);
        this.console.log(`Primes up to ${limit}: ${primes.join(', ')}`);
    }
    private generatePrimes(limit: number): number[] {
        const sieve = Array(limit).fill(true);
        sieve[0] = sieve[1] = false;
        for (let i = 2; i * i <= limit; i++) {
            if (sieve[i]) {
                for (let j = i * i; j < limit; j += i) {
                    sieve[j] = false;
                }
            }
        }
        return sieve
            .map((isPrime, index) => isPrime ? index : -1)
            .filter((num) => num !== -1);
    }
}