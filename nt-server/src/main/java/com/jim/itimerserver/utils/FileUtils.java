package com.jim.itimerserver.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileUtils {

    public static String save(MultipartFile file, String filePath) throws IOException {
        // 创建目标文件对象
        File targetFile = new File(filePath);

        // 确保父目录存在
        File parentDir = targetFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("无法创建目录: " + parentDir.getAbsolutePath());
            }
        }

        // 将文件保存到指定位置
        try (InputStream inputStream = file.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        return targetFile.getAbsolutePath();
    }

    /**
     * 获取文件名的后缀（包括.），如果没有后缀则返回空字符串
     */
    public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1 || fileName.lastIndexOf('.') == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.')); // 包含 .
    }

    /**
     * 获取文件名的后缀（不包括.），如果没有后缀则返回空字符串
     */
    public static String getFileExtensionWithoutDot(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1 || fileName.lastIndexOf('.') == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1); // 不包含 .
    }

}
