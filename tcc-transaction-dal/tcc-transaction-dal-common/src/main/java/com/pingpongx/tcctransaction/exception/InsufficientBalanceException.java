package com.pingpongx.tcctransaction.exception;

/**
 * @author liuwei
 * @date 2018/5/7
 * @desc 余额不足异常
 */
public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(){

    }

    public InsufficientBalanceException(String message){
        super(message);
    }
}
