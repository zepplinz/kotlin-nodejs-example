/**
 * Adapted from Raphael Stäbler's Pappel Node.js framework for Kotlin
 * https://github.com/blazer82/pappel-framework
 */

import { JSONUtils } from './JSONUtils';

enum Status {
    OK = 200,
    CREATED = 201,
    ACCEPTED = 202,
    NO_CONTENT = 204,
    BAD_REQUEST = 400,
    UNAUTHORIZED = 401,
    FORBIDDEN = 403,
    NOT_FOUND = 404,
    INTERNAL_SERVER_ERROR = 500,
    NOT_IMPLEMENTED = 501,
    BAD_GATEWAY = 502,
    SERVICE_UNAVAILABLE = 503,
    GATEWAY_TIMEOUT = 504
}

interface Response {
    end(): void;
    render(view: string, callback?: (html: string | null) => void): void;
    render(view: string, parameters: Record<string, any>, callback?: (html: string | null) => void): void;
    send(string: string): void;
    json(data: any): void;
    type(type: string): void;
    append(field: string, value: string): void;
    status(code: number): void;
}

class Response {
    private res: Response;

    constructor(res: Response) {
        this.res = res;
    }

    end() {
        this.res.end();
    }

    render(view: string): void;
    render(view: string, callback: (html: string | null) => void): void;
    render(view: string, parameters: Record<string, any>): void;
    render(view: string, parameters: Record<string, any>, callback: (html: string | null) => void): void;
    render(view: string, parametersOrCallback?: Record<string, any> | ((html: string | null) => void), callback?: (html: string | null) => void) {
        if (typeof parametersOrCallback === 'function') {
            this.res.render(`${view}.html`, parametersOrCallback);
        } else if (parametersOrCallback && typeof parametersOrCallback === 'object') {
            if (callback) {
                this.res.render(`${view}.html`, JSONUtils.toJSON(parametersOrCallback), callback);
            } else {
                this.res.render(`${view}.html`, JSONUtils.toJSON(parametersOrCallback));
            }
        } else {
            this.res.render(`${view}.html`);
        }
    }

    send(string: string) {
        this.res.send(string);
    }

    sendJSON(data: Record<string, any> | any[]) {
        this.res.json(JSONUtils.toJSON(data));
    }

    setContentType(type: string) {
        this.res.type(type);
    }

    setHeader(field: string, value: string) {
        this.res.append(field, value);
    }

    setHeaders(fields: Record<string, string>) {
        Object.entries(fields).forEach(([key, value]) => {
            this.res.append(key, value);
        });
    }

    setStatus(status: Status) {
        this.res.status(status);
    }
}