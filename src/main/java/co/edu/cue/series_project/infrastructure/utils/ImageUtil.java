package co.edu.cue.series_project.infrastructure.utils;


import java.io.*;

public class ImageUtil {

    public static byte[] convertFileToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return data;
    }
}
