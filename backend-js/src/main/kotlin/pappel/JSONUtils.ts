// JSON utilities for easier conversion of types between JavaScript and TypeScript.
//
// Most of the time these functions will be directly incorporated into the framework
// methods and won't be used in any application code.

type Json = { [key: string]: any };

class JSONUtils {

    /**
     * Converts a map of [data] into JSON.
     * @return Json
     */
    static toJSON(data: Map<string, any>): Json {
        const arrayOfPairs: [string, any][] = Array.from(data).map(
            ([key, value]) => {
                if (value instanceof Map) {
                    return [key, JSONUtils.toJSON(value as Map<string, any>)];
                } else if (Array.isArray(value)) {
                    return [key, JSONUtils.toJSON(value as any[])];
                } else {
                    return [key, value];
                }
            }
        );

        return Object.fromEntries(arrayOfPairs);
    }

    /**
     * Converts any iterable [data] into JSON.
     * @return Json
     */
    static toJSON(data: Iterable<any>): any[] {
        const array: any[] = Array.from(data).map(
            (entry) => {
                if (entry instanceof Map) {
                    return JSONUtils.toJSON(entry as Map<string, any>);
                } else {
                    return entry;
                }
            }
        );

        return array;
    }

    /**
     * Converts any JSON input data to a map.
     * @return Map<string, any>
     */
    static retrieveMap(json: any): Map<string, any> | null {
        if (typeof json !== "object") {
            return null;
        }

        const map: Map<string, any> = new Map();

        const keys: string[] = Object.keys(json);

        for (const key of keys) {
            if (typeof json[key] === "object") {
                map.set(key, JSONUtils.retrieveMap(json[key]));
            } else {
                map.set(key, json[key]);
            }
        }

        return map;
    }

}