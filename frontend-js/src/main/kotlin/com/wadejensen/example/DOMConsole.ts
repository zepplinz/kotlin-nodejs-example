// Define the IConsole interface
interface IConsole {
    println(s: string): void;
}
// Implement the DOMConsole class
class DOMConsole implements IConsole {
    println(s: string): void {
        // prints text by adding it to the 'console' element
        const consoleElement = document.getElementById("console");
        if (consoleElement) {
            consoleElement.appendChild(document.createTextNode(`${s}\n`));
        }
    }
}