// Define a new type for the Express Router
type ExpressRouter = any;
// Define a new class to wrap the Express Router
class Router {
    expressRouter: ExpressRouter;
    constructor(expressRouter: ExpressRouter) {
        this.expressRouter = expressRouter;
    }
}