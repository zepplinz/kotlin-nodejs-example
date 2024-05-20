
/**
 * Adapted from Raphael Stäbler's Pappel Node.js framework for Kotlin
 * https://github.com/blazer82/pappel-framework
 */

import express, { Request, Response, NextFunction } from 'express';
import http from 'http';
import bodyParser from 'body-parser';

class Application {
    private app = express();
    private router = express.Router();

    startHttpServer(port: number): void {
        console.log(`Starting server on port ${port}.`);
        this.app.use(bodyParser.raw());
        http.createServer(this.app).listen(port, () => {
            console.log("Server started successfully");
        });
    }

    /**
     * Starts listening on [port].
     * @param port TCP port to listen on.
     * @return void
     */
    listen(port: number): void {
        this.app.listen(port, () => {
            console.log(`Listening on port ${port}`);
        });
    }

    /**
     * Handles all requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    all(path: string, callback: (request: Request, response: Response) => void): void {
        this.app.all(path, (req: Request, res: Response) => {
            callback(req, res);
        });
    }

    /**
     * Handles DELETE requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    delete(path: string, callback: (request: Request, response: Response) => void): void {
        this.app.delete(path, (req: Request, res: Response) => {
            callback(req, res);
        });
    }

    /**
     * Handles GET requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    get(path: string, callback: (request: Request, response: Response) => void): void {
        this.app.get(path, (req: Request, res: Response) => {
            callback(req, res);
        });
    }

    /**
     * Registers a global request [callback].
     * @param callback Callback to handle requests
     */
    onRequest(callback: (request: Request, response: Response, next: NextFunction) => void): void {
        this.app.use((req: Request, res: Response, next: NextFunction) => {
            callback(req, res, next);
        });
    }

    /**
     * Handles POST requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    post(path: string, callback: (request: Request, response: Response) => void): void {
        this.app.post(path, (req: Request, res: Response) => {
            callback(req, res);
        });
    }

    /**
     * Handles PUT requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    put(path: string, callback: (request: Request, response: Response) => void): void {
        this.app.put(path, (req: Request, res: Response) => {
            callback(req, res);
        });
    }

    /**
     * Uses [router] for [path].
     * @param path Path relative to the router's base path
     * @param router Instance of another router to use for [path]
     */
    use(path: string, router: express.Router): void {
        this.app.use(path, router);
    }

    /**
     * Enables serving of static content beneath the specified filepath
     */
    serveStaticContent(path: string): void {
        this.app.use(express.static(path));
    }
}