import express, { Request, Response } from 'express';
import fetch from 'node-fetch';
import path from 'path';
const app = express();
const __dirname = path.resolve();
interface Address {
    streetNum: number;
    streetName: string;
    suburb: string;
    postcode: number;
}
interface Person {
    name: string;
    age: number;
    address: Address;
}
enum Method {
    GET = 'GET',
    POST = 'POST'
}
interface RequestInit {
    method: Method;
    headers: { [key: string]: string };
    body: Person;
}
function main(...args: string[]) {
    // nothing here
}
function start() {
    const shared = new SharedClass(console, Math);
    const app = express();
    app.get('/primes', (req: Request, res: Response) => {
        shared.platform = 'Node.js';
        shared.printMe();
        console.log(shared.givePrimes(100));
    });
    app.get('/async-get', async (req: Request, res: Response) => {
        console.log('async-get route pinged!');
        const resp = await fetch('https://jsonplaceholder.typicode.com/todos/1');
        const data = await resp.json();
        if (data) {
            console.dir(data);
        }
        res.send(JSON.stringify(data));
    });
    app.get('/async-post', async (req: Request, res: Response) => {
        console.log('async-post route pinged');
        const wade = '{"name":"Wade Jensen", "age": 22, "address": {"streetNum": 123, "streetName": "Fake street", "suburb": "Surry Hills", "postcode": 2010}}';
        const person: Person = JSON.parse(wade);
        const request: RequestInit = {
            method: Method.POST,
            headers: { username: 'wjensen', password: '1234567' },
            body: person
        };
        console.log('Request object:');
        console.dir(request);
        const resp = await fetch('https://jsonplaceholder.typicode.com/posts', {
            method: request.method,
            headers: request.headers,
            body: JSON.stringify(request.body)
        });
        const data = await resp.json();
        if (data) {
            console.log('Response object:');
            console.dir(data);
        }
        res.send(JSON.stringify(data));
    });
    app.get('/parse-json', (req: Request, res: Response) => {
        console.log('parse-json route pinged');
        const data = '{"name":"Wade Jensen", "age": 22, "address": {"streetNum": 123, "streetName": "Fake street", "suburb": "Surry Hills", "postcode": 2010}}';
        console.log(data);
        const person: Person = JSON.parse(data);
        res.send(`
            name    = ${person.name},
            age     = ${person.age},
            address.streetNum  = ${person.address.streetNum},
            address.streetName = ${person.address.streetName},
            address.suburb     = ${person.address.suburb},
            address.postcode   = ${person.address.postcode}
        `.trim());
    });
    app.get('/promise-get', (req: Request, res: Response) => {
        console.log('promise-get route pinged!');
        fetch('https://jsonplaceholder.typicode.com/todos/1')
            .then((result) => result.json())
            .then((json) => JSON.stringify(json))
            .then((strResult) => {
                console.log(strResult);
                res.send(strResult);
            });
    });
    app.listen(3000, () => {
        console.log('Server is running on port 3000');
    });
    const staticWebContentPath = path.join(__dirname, '../../frontend-js/src/main/web');
    console.log(`Serving content from: ${staticWebContentPath}`);
    app.use(express.static(staticWebContentPath));
    console.log('TypeScript - Node.js webserver ready.');
}
start();