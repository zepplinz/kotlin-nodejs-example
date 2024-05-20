// Define the IMath interface
interface IMath {
    sqrt(x: number): number;
}
// Define the Math class that implements the IMath interface
class Math implements IMath {
    sqrt(x: number): number {
        return Math.sqrt(x);
    }
}