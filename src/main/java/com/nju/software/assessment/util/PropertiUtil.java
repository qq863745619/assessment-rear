package com.nju.software.assessment.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class PropertiUtil {
    public static String  getProperty(String filepath,String property){
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream(filepath);
        String result=null;
        try {
            prop.load(in);
            result=prop.getProperty(property);
//          System.out.println(result);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String getProp(String filePath,String name) {
        ClassPathResource resource = new ClassPathResource(filePath);
        Properties pros = new Properties();
        try {
            pros.load(resource.getInputStream());
        } catch (IOException e) {
//             log.error(e.getMessage(), e);
        }
        return pros.getProperty(name);
    }
    public static void main(String[] args) {
        getProperty("/fybh.properties","fybh");
    }
}
