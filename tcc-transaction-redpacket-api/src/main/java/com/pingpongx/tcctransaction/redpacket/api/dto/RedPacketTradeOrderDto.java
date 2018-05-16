package com.pingpongx.tcctransaction.redpacket.api.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
@Data
public class RedPacketTradeOrderDto {

    private long selfUserId;

    private long oppositeUserId;

    private String orderTitle;

    private String merchantOrderNo;

    private BigDecimal amount;

}
