package com.pingpongx.tcctransaction.order.api;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
public interface PaymentService {

    void makePayment(long payerUserId,long shopId,
        List<Pair<Long,Integer>> productQuantities,BigDecimal redPacketPayAmount);




}
