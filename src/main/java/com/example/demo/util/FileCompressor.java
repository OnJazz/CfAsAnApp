package com.example.demo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

public class FileCompressor {

    public static byte[] zip(byte[] data) throws IOException {
        var deflater = new Deflater();
        deflater.setLevel(deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        var outputStream = new ByteArrayOutputStream(data.length);
        var tmp = new byte[4*1024];
        while(!deflater.finished()){
            var size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try{
            outputStream.close();
        }catch(Exception exception){
        }
        return outputStream.toByteArray();
    }

    public static byte[] unzip(byte[] data){
        var inflater = new Inflater();
        inflater.setInput(data);
        var outputStream = new ByteArrayOutputStream(data.length);
        var tmp = new byte[4*1024];
        try{
            while(!inflater.finished()){
                var count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        }catch (Exception exception){
        }
        return outputStream.toByteArray();
    }
}
