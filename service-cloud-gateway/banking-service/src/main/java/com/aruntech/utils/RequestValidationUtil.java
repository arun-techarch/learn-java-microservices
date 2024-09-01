package com.aruntech.utils;

import com.aruntech.model.AccountCreateRequest;

public final class RequestValidationUtil {

    public static boolean validateAccountRequest(AccountCreateRequest request) {
        if(!request.getName().isEmpty() && request.getName().length() > 0 &&
            request.getAmount() > 0.0 &&
            !request.getPhone().isEmpty() && request.getPhone().length() == 10 &&
            !request.getEmail().isEmpty() && request.getEmail().length() > 0 &&
            request.getEmail().contains("@") && request.getEmail().contains(".")) {
            //return true if all validation pass
            return true;
        }
        return false;
    }
}
