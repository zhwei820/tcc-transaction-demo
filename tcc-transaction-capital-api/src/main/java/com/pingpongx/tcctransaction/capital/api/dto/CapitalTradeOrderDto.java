package com.pingpongx.tcctransaction.capital.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
@Data
public class CapitalTradeOrderDto implements Serializable{

    private long selfUserId;

    //对方账号ID
    private long oppositeUserId;

    private String orderTitle;

    private String merchantOrderNo;

    private BigDecimal amount;

}
