package com.pingpongx.tcctransaction.redpacket.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
@Data
public class TradeOrder implements Serializable{
    private long id;

    private long selfUserId;

    private long oppositeUserId;

    private String merchantOrderNo;

    private BigDecimal amount;

    private String status = "DRAFT";

    private long version = 1l;

    public void confirm(){
        this.status = "CONFIRM";
    }

    public void cancel(){
        this.status = "CANCEL";
    }
}
