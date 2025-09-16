package hellocucumber;

import okhttp3.internal.http2.ErrorCode;

public class NotFoundException extends Exception {

    ErrorCode code;

    public NotFoundException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }
}
