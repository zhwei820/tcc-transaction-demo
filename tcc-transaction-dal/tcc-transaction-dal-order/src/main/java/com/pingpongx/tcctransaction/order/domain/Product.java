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
public class Product implements Serializable{

    private long productId;

    private long shopId;

    private String productName;

    private BigDecimal price;

}
