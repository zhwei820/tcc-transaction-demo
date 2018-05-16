package com.pingpongx.tcctransaction.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pingpongx.tcctransaction.order.api.PaymentService;
import com.pingpongx.tcctransaction.order.domain.Order;
import com.pingpongx.tcctransaction.order.domain.OrderLine;
import com.pingpongx.tcctransaction.order.domain.Shop;
import com.pingpongx.tcctransaction.order.mapper.ProductMapper;
import com.pingpongx.tcctransaction.order.mapper.ShopMapper;
import com.pingpongx.tcctransaction.order.service.inner.TccPaymentService;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc 如果事务日志没有成功提交，那么整个TCC事务将会需要恢复，
 * 如果是在CONFIRMING阶段出异常，则恢复Job将继续启动事务的Confirm操作过程，
 * 如果是在TRYING阶段出异常，则恢复Job将启动事务的Cancel操作过程。
 */
@Service(version = "1.0.0")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private TccPaymentService tccPaymentService;

    @Override
    @Transactional
    public void makePayment(long payerUserId, long shopId, List<Pair<Long, Integer>> productQuantities,
        BigDecimal redPacketPayAmount) {

        //根据shopId去查找店铺信息，主要目的用于获取收款者ID
        Shop shop = shopMapper.findById(shopId);

        //创建支付订单,状态为未支付成功
        Order order = new Order();
        order.setPayUserId(payerUserId);
        order.setPayeeUserId(shop.getOwnerUserId());
        order.setMerchantOrderNo(UUID.randomUUID().toString());
        for (Pair<Long, Integer> pair : productQuantities) {
            long productId = pair.getLeft();
            //根据productId查询单价
            BigDecimal price = productMapper.getById(productId).getPrice();

            order.addOrderLine(new OrderLine(productId,pair.getRight(),price));
        }
        tccPaymentService.makeTccPayment(order,redPacketPayAmount, order.getTotalAmount().subtract(redPacketPayAmount));
    }


}
