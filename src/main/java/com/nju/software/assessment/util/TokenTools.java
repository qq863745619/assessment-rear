package com.nju.software.assessment.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class TokenTools {
    public final static String tokenServerkey = "Token";

    /**
     * 生成token放入session
     * @param request
     * @param tokenServerkey
     */
    public static void createToken(HttpServletRequest request,String tokenServerkey){
        String token = TokenProccessor.getInstance().makeToken();
        request.getSession().setAttribute(tokenServerkey, token);
    }

    /**
     * 移除token
     * @param request
     * @param tokenServerkey
     */
    public static void removeToken(HttpServletRequest request,String tokenServerkey){
        request.getSession().removeAttribute(tokenServerkey);
    }

    /**
     * 判断请求参数中的token是否和session中一致
     * @param request
     * @return
     */
    public static boolean judgeTokenIsEqual(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String tokenClientkey="";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("Token")){
                tokenClientkey = cookie.getValue();
                break;
            }
        }
        if(StringUtils.isEmpty(tokenClientkey)){
            return false;
        }
        String token_server = (String) request.getSession()
                .getAttribute(tokenServerkey);
        if(StringUtils.isEmpty(token_server)){
            return false;
        }

        return token_server.equals(tokenClientkey);
    }

}

