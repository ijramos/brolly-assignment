package com.brolly.assignment.service.util;

import java.util.UUID;

public class ServiceHelperUtil {

    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

}
