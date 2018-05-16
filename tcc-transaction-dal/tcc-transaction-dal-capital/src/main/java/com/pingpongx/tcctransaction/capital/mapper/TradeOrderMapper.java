package com.pingpongx.tcctransaction.capital.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.pingpongx.tcctransaction.capital.domain.TradeOrder;

/**
 * @author liuwei
 * @date 2018/5/9
 * @desc
 */
public interface TradeOrderMapper extends BaseMapper<TradeOrder> {

    TradeOrder findByMechantOrderNo(String merchantOrderNo);

    void updateStatusByOrderNo(TradeOrder tradeOrder);

    void insertBySelective(TradeOrder tradeOrder);
}
