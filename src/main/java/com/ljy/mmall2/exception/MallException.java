package com.ljy.mmall2.exception;

import com.ljy.mmall2.enums.ResultEnum;

/**
 * unchecked 不用去处理异常，交给JVM去处理（继承RuntimeException）
 * checked 需要自己去处理（继承Exception）
 */

public class MallException extends RuntimeException {
    public MallException(String error) {
        super(error);
    }
    public MallException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}
