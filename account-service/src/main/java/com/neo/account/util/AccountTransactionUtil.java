package com.neo.account.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * @Author ABODE
 * @Date 2025/03/08 6:08 PM
 */
@Component
public class AccountTransactionUtil {

    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final SecureRandom random = new SecureRandom();
    public String generateAccountNumber() {
        int min = 100_000_000; // Smallest 9-digit number
        int max = 999_999_999; // Largest 9-digit number
        int randomNumber = random.nextInt(max - min) + min;
        return "1" + randomNumber; // Ensure it's always 10 digits by prefixing with "1"
    }

    public String generateReference() {
        int length = 15;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
