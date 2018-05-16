package com.pingpongx.tcctransaction.order.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.pingpongx.tcctransaction.order.domain.OrderLine;

/**
 * @author liuwei
 * @date 2018/5/9
 * @desc
 */
public interface OrderLineMapper extends BaseMapper<OrderLine> {

    void insertBySelective(OrderLine orderLine);
}
