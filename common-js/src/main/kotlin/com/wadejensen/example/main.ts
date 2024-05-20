// Define the Console class
class Console {
    log(message: string): void {
        console.log(message);
    }
}
// Define the Math class
class Math {
    // Add methods as needed
}
// Define the SharedClass
class SharedClass {
    private console: Console;
    private math: Math;
    public platform: string;
    constructor(console: Console, math: Math) {
        this.console = console;
        this.math = math;
        this.platform = '';
    }
    printMe(): void {
        this.console.log(`Platform: ${this.platform}`);
    }
    printPrimes(limit: number): void {
        // Implementation for printing primes up to the limit
    }
}
/**
 * main function for JavaScript
 */
function main(...args: string[]): void {
    //nothing here, it's executed before DOM is ready
}
/**
 * We start this function from <button onClick="
 */
function start(): void {
    const shared = new SharedClass(new Console(), new Math());
    shared.platform = "JavaScript";
    shared.printMe();
    shared.printPrimes(1000);
}