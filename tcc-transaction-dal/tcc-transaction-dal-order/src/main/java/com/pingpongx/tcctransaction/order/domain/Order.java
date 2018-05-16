package com.pingpongx.tcctransaction.order.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */

@Data
public class Order implements Serializable{

    private long id;

    private long payUserId;

    private long payeeUserId;

    private BigDecimal redPacketPayAmount;

    private BigDecimal capitalPayAmount;

    private String status = "DRAFT";

    private String merchantOrderNo;

    private long version = 1l;

    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    public BigDecimal getTotalAmount(){
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderLine orderLine : orderLines) {
            totalAmount = totalAmount.add(orderLine.getTotalAmount());
        }
        return totalAmount;
    }

    public void confirm(){
        this.status = "CONFIRM";
    }

    public void cancel(){
        this.status = "CANCEL";
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public void pay(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {
        order.setRedPacketPayAmount(redPacketPayAmount);
        order.setCapitalPayAmount(capitalPayAmount);
    }
}
