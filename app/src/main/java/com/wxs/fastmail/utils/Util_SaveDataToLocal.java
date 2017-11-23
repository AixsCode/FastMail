package com.wxs.fastmail.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Util_SaveDataToLocal {

    public static boolean saveDataToLocal(String data, String filePath, String fileName) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String fileDir = Environment.getExternalStorageDirectory().getPath() + filePath;
            File folders = new File(fileDir);
            if (!folders.exists()) {
                folders.mkdirs();
            }

            File file = new File(fileDir, fileName);
            FileOutputStream fileOutputStream;
            try {
                // 根据设置的文件路径以及文件名写入数据
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            // 外存储器不可用
            return false;
        }
    }
}
