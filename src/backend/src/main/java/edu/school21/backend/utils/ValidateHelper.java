package edu.school21.backend.utils;

import org.springframework.stereotype.Component;

@Component
public class ValidateHelper {

    private static final int LIMIT = 1000;
    private static final int ZERO = 0;

    public void validatePaging(
            final Integer limit,
            final Integer offset
    ) {
        if (limit != null && limit > LIMIT) {
            throw ExceptionUtil.createBadRequestException("Allowable number of records for the sample is exceeded. The limit is " + LIMIT);
        }
        if (limit != null && limit <= ZERO) {
            throw ExceptionUtil.createBadRequestException("Limit must be more then 0");
        }
        if (offset != null && offset < ZERO) {
            throw ExceptionUtil.createBadRequestException("Offset must be positive number");
        }
    }

    public void validatePositiveNumber(final Integer number) {
        if (number != null && number < ZERO) {
            throw ExceptionUtil.createBadRequestException("Number must be more then 0");
        }
    }
}
