package com.siakad.helper;

import com.siakad.constant.GlobalMessage;
import com.siakad.exception.BusinessException;

import java.security.SecureRandom;
import java.text.MessageFormat;

public final class StringHelper {

    private StringHelper() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isPresent(String string) {
        return !isEmpty(string);
    }

    public static String queryLike(String string) {
        return MessageFormat.format("%{0}%", string);
    }

    public static String random(int length) {
        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final SecureRandom random = new SecureRandom();

        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }
}
