package com.flyingstudio.fscore.utils;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.RawRes;

import com.flyingstudio.fscore.app.Flying;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by guopu on 2017/10/15.
 */

public class FileUtil {
    public static final File writeToDisk(InputStream is,String path,String name){
        File file = new File(path+name);
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(file);
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(fos);
            byte []bytes = new byte[1024];
            int length;
            while ((length =bis.read(bytes))!= -1){
                bos.write(bytes,0,length);
            }
            bos.flush();
            fos.flush();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (is!=null){
                    is.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void autoInstallApk(File file) {
        String name = file.getName();
        if (name.contains(".")) {
            String extension = name.substring(name.lastIndexOf(".")).toString().toLowerCase();
            if (extension.equals("apk")) {
                final Intent install = new Intent();
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                install.setAction(Intent.ACTION_VIEW);
                install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                Flying.getAppContext().startActivity(install);
            }
        }
    }
    public static String getRawRes(@RawRes int rawId) {
        final InputStream is = Flying.getAppContext().getResources().openRawResource(rawId);
        final BufferedInputStream bis = new BufferedInputStream(is);
        final InputStreamReader isr = new InputStreamReader(bis);
        final BufferedReader br = new BufferedReader(isr);
        final StringBuilder stringBuilder = new StringBuilder();
        String str;
        try {
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
