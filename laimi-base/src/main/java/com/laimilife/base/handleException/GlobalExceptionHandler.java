package com.laimilife.base.handleException;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laimilife.base.exception.BaseException;
import com.laimilife.base.vo.Result;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static transient Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获BaseException异常
     * @author: zhangweiqiang
     * @date: 2018年3月26日 下午6:26:59
     */
    @ExceptionHandler(BaseException.class)  
    public @ResponseBody Result handleBaseExcepion(HttpServletRequest request, BaseException ex){

        StringWriter sw = new StringWriter();  
        PrintWriter pw = new PrintWriter(sw);  
        ex.printStackTrace(pw);  
        
        logger.error(sw.toString());
        return new Result(ex.getCode(), ex.getDesc());  
    }
    
    /**
     * 捕获Exception异常
     * @author: zhangweiqiang
     * @date: 2018年3月26日 下午6:26:59
     */
    @ExceptionHandler(Exception.class)  
    public @ResponseBody Result handleExcepion(HttpServletRequest request, Exception ex){

        StringWriter sw = new StringWriter();  
        PrintWriter pw = new PrintWriter(sw);  
        ex.printStackTrace(pw);  
        
        logger.error(sw.toString());
        return new Result("88888", "操作失败了，请稍后重试");
    }
}
