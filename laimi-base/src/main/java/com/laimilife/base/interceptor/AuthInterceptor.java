package com.laimilife.base.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.laimilife.base.util.AssertUtil;


public class AuthInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取Session  
        HttpSession session = request.getSession();  
        Object username = (Object)session.getAttribute("user");  
          
        if(!AssertUtil.isNull(username)) {  
            return true;  
        }
        //不符合条件的，跳转到登录界面  
        request.getRequestDispatcher("/public/login.jsp").forward(request, response);
        response.setCharacterEncoding("UTF-8");    
        response.setContentType("application/json; charset=utf-8");  
        try{  
            JSONObject res = new JSONObject();  
            res.put("code","400001");  
            res.put("desc","登陆已过期，请重新登陆");  
            PrintWriter out = response.getWriter();  
            out.append(res.toString());
            return false;  
        }  
        catch (Exception e){  
            e.printStackTrace();  
            response.sendError(500);  
            return false;  
        }
	}

}
