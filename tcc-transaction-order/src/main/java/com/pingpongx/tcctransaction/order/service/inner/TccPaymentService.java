package com.pingpongx.tcctransaction.order.service.inner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pingpongx.tcctransaction.capital.api.CapitalTradeOrderService;
import com.pingpongx.tcctransaction.capital.api.dto.CapitalTradeOrderDto;
import com.pingpongx.tcctransaction.order.domain.Order;
import com.pingpongx.tcctransaction.order.domain.OrderLine;
import com.pingpongx.tcctransaction.order.mapper.OrderLineMapper;
import com.pingpongx.tcctransaction.order.mapper.OrderMapper;
import java.math.BigDecimal;
import org.mengyun.tcctransaction.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuwei
 * @date 2018/5/9
 * @desc 由于原业务较为复杂，所以把tcc业务抽离出来
 */
@Service
public class TccPaymentService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderLineMapper orderLineMapper;

    @Reference(version = "1.0.0")
    private CapitalTradeOrderService capitalTradeOrderService;


    @Compensable(confirmMethod = "confirmMakeTccPayment",cancelMethod = "cancelMakeTccPayment")
    public void makeTccPayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount){
        order.pay(order,redPacketPayAmount,capitalPayAmount);
        //插入order
        orderMapper.insertBySelective(order);
        //插入orderLine
        for (OrderLine orderLine : order.getOrderLines()) {
            orderLineMapper.insertBySelective(orderLine);
        }
        //调用资金和红包服务
        String result = capitalTradeOrderService.record(null,buildCapitalTradeOrderDto(order));

        System.out.println("=======================>"+result);

        if (1==1){
            throw new IllegalArgumentException("故意抛出异常，测试从服务cancel方法");
        }
        System.out.println("====================>主服务try调用成功");

    }

    public void confirmMakeTccPayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount){
        order.confirm();
        orderMapper.updateStatusByOrderNo(order);
        System.out.println("====================>主服务confirm调用成功");
    }

    public void cancelMakeTccPayment(Order order,BigDecimal redPacketPayAmount,BigDecimal capitalPayAmount){
        order.cancel();
        orderMapper.updateStatusByOrderNo(order);
        System.out.println("====================>主服务cancel调用成功");
    }

    private CapitalTradeOrderDto buildCapitalTradeOrderDto(Order order){
        CapitalTradeOrderDto capitalTradeOrderDto = new CapitalTradeOrderDto();
        capitalTradeOrderDto.setAmount(order.getCapitalPayAmount());
        capitalTradeOrderDto.setMerchantOrderNo(order.getMerchantOrderNo());
        capitalTradeOrderDto.setSelfUserId(order.getPayUserId());
        capitalTradeOrderDto.setOppositeUserId(order.getPayeeUserId());
        capitalTradeOrderDto.setOrderTitle(String.format("order no:%s",order.getMerchantOrderNo()));
        return capitalTradeOrderDto;
    }




}
