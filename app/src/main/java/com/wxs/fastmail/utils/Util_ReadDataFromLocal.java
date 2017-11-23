package com.wxs.fastmail.utils;


import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Util_ReadDataFromLocal {

    public static String readDataFromLocal(String filePath, String fileName) {

        String result = null;

        // 首先判断外存储器是否可用
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String fileDir = Environment.getExternalStorageDirectory() + filePath;
            File file = new File(fileDir);

            // 判断文件是否存在
            if (!file.exists()) {
                result = null;
            } else {

                File file1 = new File(fileDir, fileName);
                try {
                    InputStream inputStream = new FileInputStream(file1);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    try {
                        while ((len = inputStream.read(bytes)) != -1) {
                            byteArrayOutputStream.write(bytes, 0, len);
                        }
                        inputStream.close();
                        byteArrayOutputStream.close();
                        byte[] data = byteArrayOutputStream.toByteArray();
                        result = new String(data);

                        return result;
                    } catch (IOException e) {
                        e.printStackTrace();

                        return result;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                    return result;
                }
            }
        }
        return result;
    }
}
