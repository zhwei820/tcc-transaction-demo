package com.pingpongx.tcctransaction.redpacket.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pingpongx.tcctransaction.redpacket.api.RedPacketTradeOrderService;
import com.pingpongx.tcctransaction.redpacket.api.dto.RedPacketTradeOrderDto;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc 和资金服务一样处理方式，此处省略。。。
 */
@Service
public class RedPacketTradeOrderServiceImpl implements RedPacketTradeOrderService {

    @Override
    @Compensable(confirmMethod = "confirmRecord",cancelMethod = "cancelRecord")
    @Transactional
    public String record(TransactionContext transactionContext, RedPacketTradeOrderDto redPacketTradeOrderDto) {
        return "SUCCESS";
    }

    public void confirmRecord(){

    }

    public void cancelRecord(){

    }


}
