package com.lanshiqin.pigeon.server.Interceptor;

import com.lanshiqin.pigeon.server.exception.UnAuthException;
import com.lanshiqin.pigeon.server.util.JwtUserInfo;
import com.lanshiqin.pigeon.server.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT 拦截器
 *
 * @author 蓝士钦
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) throw new UnAuthException("令牌不存在");
        try {
            JwtUserInfo jwtUserInfo = jwtUtil.getJwtUserInfo(token);
            if (jwtUserInfo==null) throw new UnAuthException("令牌已过期");
            UserInfo.setUserInfo(jwtUserInfo);
        } catch (Exception e) {
            throw new UnAuthException("令牌已过期");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfo.remove();
    }

    private static final class UserInfo {

        private static ThreadLocal<JwtUserInfo> threadLocal = new ThreadLocal<>();
        public static void setUserInfo(JwtUserInfo jwtUserInfo){
            threadLocal.set(jwtUserInfo);
        }
        public static JwtUserInfo getUserInfo(){
            return threadLocal.get();
        }
        public static void remove(){
            threadLocal.remove();
        }
    }

    public JwtUserInfo getUserInfo(){
        return UserInfo.getUserInfo();
    }


}
