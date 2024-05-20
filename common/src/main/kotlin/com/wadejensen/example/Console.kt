// Define the IConsole interface
interface IConsole {
    println(s: string): void;
}
// Define the Console class that implements IConsole
class Console implements IConsole {
    println(s: string): void {
        console.log(s);
    }
}