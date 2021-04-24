package com.gymbuddy.backgymbuddy.admin.base;


import java.io.File;

public class DirMake {
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static File testdir(String dir) {
        File newFile = null;
        String test2 = dir.replaceAll("/home/www/resources","");
        String [] dir2 = test2.split("/");
        String tt = "/home/www/resources";
        String str = "/";
        if (isWindows()) {
            tt ="C:\\Users\\user";
            str = "\\";
        } else if (isMac()) {
            tt ="/home/";
        } else if (isUnix()) {
            tt ="/home/www/resources";
        }

        for(int i=0;i<dir2.length;i++){
            if(!dir2[i].equals("")){
                tt += str+dir2[i];
                newFile = new File(tt);
                //폴더 없으면 경로 생성하기
                if (!newFile.exists()) {
                    try{
                        newFile.mkdir(); //폴더 생성합니다.
                    }
                    catch(Exception e){
                        e.getStackTrace();
                    }
                }
            }
        }
        return newFile;
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }
}
