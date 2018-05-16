package com.pingpongx.tcctransaction.redpacket.domain;

import com.pingpongx.tcctransaction.exception.InsufficientBalanceException;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc 红包账户
 */
@Data
public class RedPacketAccount implements Serializable{

    private long id;

    private long userId;

    private BigDecimal balanceAmount;

    public void transferFrom(BigDecimal amount){
        this.balanceAmount = this.balanceAmount.subtract(amount);
        if (BigDecimal.ZERO.compareTo(this.balanceAmount)>0){
            throw new InsufficientBalanceException();
        }
    }

    public void transferTo(BigDecimal amount){
        this.balanceAmount = this.balanceAmount.add(amount);
    }

    public void cancelTransfer(BigDecimal amount){
        transferTo(amount);
    }



}
