// Define the IMath interface
interface IMath {
    sqrt(x: number): number;
}
// Implement the Math class
class Math implements IMath {
    // Access the JavaScript Math object
    private mathJs: any = Math;
    // Implement the sqrt function
    sqrt(x: number): number {
        return this.mathJs.sqrt(x);
    }
}