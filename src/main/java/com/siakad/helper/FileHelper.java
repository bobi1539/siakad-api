package com.siakad.helper;

import com.siakad.constant.GlobalMessage;
import com.siakad.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

public final class FileHelper {

    private FileHelper() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static boolean isEmpty(MultipartFile file) {
        return file == null || file.isEmpty();
    }

    public static boolean isPresent(MultipartFile file) {
        return !isEmpty(file);
    }
}
