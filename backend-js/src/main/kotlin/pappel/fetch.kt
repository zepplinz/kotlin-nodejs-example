import fetch, { RequestInit, Response } from 'node-fetch';
function fetch(input: any): Promise<Response> {
    return fetch(input) as Promise<Response>;
}
function fetch(input: any, init: RequestInit): Promise<Response> {
    return fetch(input, init);
}