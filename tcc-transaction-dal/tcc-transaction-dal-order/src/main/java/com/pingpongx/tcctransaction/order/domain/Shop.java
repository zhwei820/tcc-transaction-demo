package com.pingpongx.tcctransaction.order.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
@Data
public class Shop implements Serializable{
    private long id;

    private long ownerUserId;
}
