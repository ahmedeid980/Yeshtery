package com.ahmedeid.yeshtery.Yeshtery.ExceptionHandling;

import com.ahmedeid.yeshtery.Yeshtery.util.ResponseCodeDescription;
import lombok.Getter;

/**
 * prepared this class for exception handling
 */
public class YeshteryExceptionHandler extends RuntimeException {

    public YeshteryExceptionHandler(ResponseCodeDescription responseCodeDescription, Throwable cause) {
        super(responseCodeDescription.getErrorMessage(), cause);
        this.code = responseCodeDescription.getStatusCode();
    }

    @Getter
    private final long code;
    public YeshteryExceptionHandler(ResponseCodeDescription responseCodeDescription) {
        super(responseCodeDescription.getErrorMessage());
        this.code = responseCodeDescription.getStatusCode();
    }
}
