package com.pingpongx.tcctransaction.order.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
@Data
public class OrderLine implements Serializable {

    private long id;

    private long productId;

    //数量
    private int quantity;

    //单价
    private BigDecimal unitPrice;

    public OrderLine(long productId, Integer right, BigDecimal price) {
        this.productId = productId;
        this.quantity = right;
        this.unitPrice = price;

    }

    public BigDecimal getTotalAmount(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }


}
