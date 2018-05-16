package com.pingpongx.tcctransaction.capital.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author liuwei
 * @date 2018/5/7
 * @desc
 */
@Data
public class CapitalAccount implements Serializable{

    private long id;

    private long userId;

    private BigDecimal balanceAmount;

    private BigDecimal transferAmount = BigDecimal.ZERO;

    public void transferFrom(BigDecimal amount) {
        this.balanceAmount = this.balanceAmount.subtract(amount);
        if (BigDecimal.ZERO.compareTo(this.balanceAmount)>0){
            throw new RuntimeException("no enough balance");
        }
    }

    public void transferTo(BigDecimal amount) {
        this.balanceAmount = this.balanceAmount.add(amount);
    }

    public void cancelTransfer(BigDecimal amount) {
        transferTo(amount);
    }
}
