package com.cngc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class TransformFolder {
	public static String strKey = "LmMGStGtOpF4xNyvYt54EQ==";
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		String inPath = args[0];
		File file = new File(inPath);
        
		if(file.exists()) {
			
            File[] files = file.listFiles();
            //输出文件夹
            String outFilePath = inPath+"0";
            File file3 = new File(outFilePath);
        	if(!file3.exists())
        		file3.mkdirs();
        	
        	KeyGenerator _generator = KeyGenerator.getInstance("DES"); 
            _generator.init(new SecureRandom(strKey.getBytes())); 
            Key key = _generator.generateKey(); 
            _generator = null;
        	
            for(File file2:files) {
            	String fileName = file2.getName();
            	//加密保存
            	Cipher cipher = Cipher.getInstance("DES"); 
        	    cipher.init(Cipher.ENCRYPT_MODE, key); 
        	    InputStream is = new FileInputStream(file2.getPath()); 
        	    OutputStream out = new FileOutputStream(outFilePath+File.separator+fileName); 
        	    CipherInputStream cis = new CipherInputStream(is, cipher); 
        	    byte[] buffer = new byte[1024]; 
        	    int r; 
        	    while ((r = cis.read(buffer)) > 0) { 
        	        out.write(buffer, 0, r); 
        	    } 
        	    cis.close(); 
        	    is.close(); 
        	    out.close(); 
            }
		}
	}
}
