package com.nju.software.assessment.util;


import java.util.Random;

public class GetVerifyCode {
    private final static int codeLength =6;

    /**
     * 产生随机验证码
     * @return 验证码字符串
     */
    public static String getCode(){

        Random rand = new  Random();
        int  a ;
        String  result ="";
        for( int j =0; j<codeLength; j++ ){
            a = Math.abs( rand.nextInt()%9 );
            result += String.valueOf(a);
        }
        return  result;
    }

}
