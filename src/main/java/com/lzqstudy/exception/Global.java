package com.lzqstudy.exception;

import com.lzqstudy.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 李正强
 * @version 1.0
 */
@RestControllerAdvice
public class Global {
//    @ExceptionHandler
    public Result handException(Exception e){
        return Result.error("400",e.getMessage());
    }
}
