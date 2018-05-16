package com.pingpongx.tcctransaction.redpacket.api;

import com.pingpongx.tcctransaction.redpacket.api.dto.RedPacketTradeOrderDto;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
public interface RedPacketTradeOrderService {

    String record(TransactionContext transactionContext,RedPacketTradeOrderDto redPacketTradeOrderDto);

}
