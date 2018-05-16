package com.pingpongx.tcctransaction.capital.api;


import com.pingpongx.tcctransaction.capital.api.dto.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
public interface CapitalTradeOrderService {

    /**
     * 创建资金账户变更记录
     * @param transactionContext
     * @param capitalTradeOrderDto
     * @return
     */
    String record(TransactionContext transactionContext,CapitalTradeOrderDto capitalTradeOrderDto);

}
