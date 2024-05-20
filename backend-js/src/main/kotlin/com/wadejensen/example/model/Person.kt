// Define the Address type
interface Address {
    street: string;
    city: string;
    state: string;
    zipCode: string;
}
// Define the Person type
interface Person {
    name: string;
    age: number;
    address: Address;
}