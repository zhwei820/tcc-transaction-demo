package com.pingpongx.tcctransaction.order.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.pingpongx.tcctransaction.order.domain.Order;

/**
 * @author liuwei
 * @date 2018/5/9
 * @desc
 */
public interface OrderMapper extends BaseMapper<Order> {

    void updateStatusByOrderNo(Order order);

    void insertBySelective(Order order);
}
