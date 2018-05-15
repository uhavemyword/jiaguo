class Error {
    code: string;
    message: string;
    target: string;
    details: Error[];
}

export class ErrorResponse {
    error: Error;

    static match(err: ErrorResponse | any): err is ErrorResponse {
        return (<ErrorResponse>err).error !== undefined && (<ErrorResponse>err).error.message !== undefined;
    }
}