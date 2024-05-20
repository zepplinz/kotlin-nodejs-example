import { RequestInit as FetchRequestInit, RequestMode, RequestCredentials, RequestCache, RequestRedirect } from 'node-fetch';
enum Method {
    GET = 'GET',
    POST = 'POST',
    PUT = 'PUT',
    DELETE = 'DELETE',
    PATCH = 'PATCH',
    OPTIONS = 'OPTIONS',
    HEAD = 'HEAD'
}
interface RequestInit extends FetchRequestInit {
    method?: Method;
    headers?: { [key: string]: string | null };
    body?: any;
    referrer?: string;
    referrerPolicy?: any;
    mode?: RequestMode;
    credentials?: RequestCredentials;
    cache?: RequestCache;
    redirect?: RequestRedirect;
    integrity?: string;
    keepalive?: boolean;
    window?: any;
}
function RequestInit(
    method: Method = Method.GET,
    headers: { [key: string]: string | null } | null = null,
    body: any = null,
    referrer: string | null = null,
    referrerPolicy: any = null,
    mode: RequestMode | null = null,
    credentials: RequestCredentials | null = null,
    cache: RequestCache | null = null,
    redirect: RequestRedirect | null = null,
    integrity: string | null = null,
    keepalive: boolean | null = null,
    window: any | null = null): RequestInit {
    const o: RequestInit = {};
    o.method = method;
    o.headers = headers ? Object.fromEntries(Object.entries(headers)) : undefined;
    o.body = JSON.stringify(body);
    o.referrer = referrer || undefined;
    o.referrerPolicy = referrerPolicy || undefined;
    o.mode = mode || undefined;
    o.credentials = credentials || undefined;
    o.cache = cache || undefined;
    o.redirect = redirect || undefined;
    o.integrity = integrity || undefined;
    o.keepalive = keepalive || undefined;
    o.window = window || undefined;
    return o;
}