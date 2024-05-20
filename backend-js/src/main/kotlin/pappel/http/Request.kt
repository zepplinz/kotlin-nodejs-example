/**
 * Adapted from Raphael Stäbler's Pappel Node.js framework for Kotlin
 * https://github.com/blazer82/pappel-framework
 */
import { JSONUtils } from './JSONUtils';
enum Method {
    GET = 'GET',
    POST = 'POST',
    PUT = 'PUT',
    DELETE = 'DELETE',
    PATCH = 'PATCH',
    OPTIONS = 'OPTIONS',
    HEAD = 'HEAD'
}
enum Protocol {
    HTTP = 'HTTP',
    HTTPS = 'HTTPS'
}
interface Request {
    baseURL: string;
    body: { [key: string]: any } | null;
    cookies: { [key: string]: string } | null;
    hostname: string;
    ip: string;
    ips: string[] | null;
    method: Method;
    parameters: { [key: string]: string } | null;
    path: string;
    protocol: Protocol;
    query: { [key: string]: any } | null;
}
class RequestImpl implements Request {
    baseURL: string;
    body: { [key: string]: any } | null;
    cookies: { [key: string]: string } | null;
    hostname: string;
    ip: string;
    ips: string[] | null;
    method: Method;
    parameters: { [key: string]: string } | null;
    path: string;
    protocol: Protocol;
    query: { [key: string]: any } | null;
    constructor(private req: any) {
        this.baseURL = req.baseUrl as string;
        this.body = null;
        this.cookies = null;
        this.hostname = req.hostname as string;
        this.ip = req.ip as string;
        this.ips = req.ips as string[] | null;
        this.method = Method[req.method as keyof typeof Method];
        this.parameters = JSONUtils.retrieveMap(req.params) as { [key: string]: string } | null;
        this.path = req.path as string;
        this.protocol = Protocol[(req.protocol as string).toUpperCase() as keyof typeof Protocol];
        this.query = JSONUtils.retrieveMap(req.query) as { [key: string]: any } | null;
    }
}
export { Request, RequestImpl, Method, Protocol };