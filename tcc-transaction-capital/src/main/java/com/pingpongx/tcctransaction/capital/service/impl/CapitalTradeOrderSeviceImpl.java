package com.pingpongx.tcctransaction.capital.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pingpongx.tcctransaction.capital.api.CapitalTradeOrderService;
import com.pingpongx.tcctransaction.capital.api.dto.CapitalTradeOrderDto;
import com.pingpongx.tcctransaction.capital.domain.CapitalAccount;
import com.pingpongx.tcctransaction.capital.domain.TradeOrder;
import com.pingpongx.tcctransaction.capital.mapper.CapitalAccountMapper;
import com.pingpongx.tcctransaction.capital.mapper.TradeOrderMapper;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc 本地事务的控制与分布式事务控制互不影响
 */
@Service(version = "1.0.0")
public class CapitalTradeOrderSeviceImpl implements CapitalTradeOrderService {

    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    @Autowired
    private CapitalAccountMapper capitalAccountMapper;

    @Override
    @Compensable(confirmMethod = "confirmRecord",cancelMethod = "cancelRecord")
    @Transactional
    public String record(TransactionContext transactionContext,CapitalTradeOrderDto capitalTradeOrderDto) {

        //插入交易记录
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setSelfUserId(capitalTradeOrderDto.getSelfUserId());
        tradeOrder.setOppositeUserId(capitalTradeOrderDto.getOppositeUserId());
        tradeOrder.setMerchantOrderNo(capitalTradeOrderDto.getMerchantOrderNo());
        tradeOrder.setAmount(capitalTradeOrderDto.getAmount());
        tradeOrderMapper.insertBySelective(tradeOrder);

        //先扣减付款方资金账户资金，更新
        CapitalAccount transferFromAccount = capitalAccountMapper.findByUserId(capitalTradeOrderDto.getSelfUserId());

        transferFromAccount.transferFrom(capitalTradeOrderDto.getAmount());

        capitalAccountMapper.updateAmountByUserId(transferFromAccount);

        /*if (1==1){
            throw new IllegalArgumentException("故意抛出异常，测试cancel方法");
        }*/
        System.out.println("========================>从服务try阶段成功");
        return "SUCCESS";
    }

    /**
     * 幂等判断
     * @param transactionContext
     * @param capitalTradeOrderDto
     */
    @Transactional
    public void confirmRecord(TransactionContext transactionContext,CapitalTradeOrderDto capitalTradeOrderDto){

        //处理try预留资源，更新交易记录状态
        TradeOrder tradeOrder = tradeOrderMapper.findByMechantOrderNo(capitalTradeOrderDto.getMerchantOrderNo());

        /*if (1==1){
            throw new IllegalArgumentException("故意抛出异常，测试job恢复任务方法");
        }*/

        //增加幂等判断
        if (null != tradeOrder && "DRAFT".equals(tradeOrder.getStatus())){
            tradeOrder.confirm();

            tradeOrderMapper.updateStatusByOrderNo(tradeOrder);

            //增加收款方资金账户资金，更新
            CapitalAccount transferToAccount = capitalAccountMapper.findByUserId(capitalTradeOrderDto.getOppositeUserId());

            transferToAccount.transferTo(capitalTradeOrderDto.getAmount());

            capitalAccountMapper.updateAmountByUserId(transferToAccount);
        }

        System.out.println("=====================>从服务confirm调用成功");

    }

    /**
     * 幂等判断
     * @param transactionContext
     * @param capitalTradeOrderDto
     */
    public void cancelRecord(TransactionContext transactionContext,CapitalTradeOrderDto capitalTradeOrderDto){

        //处理try预留资源，更新交易记录状态
        TradeOrder tradeOrder = tradeOrderMapper.findByMechantOrderNo(capitalTradeOrderDto.getMerchantOrderNo());

        //增加幂等判断
        if (null != tradeOrder && "DRAFT".equals(tradeOrder.getStatus())){
            tradeOrder.cancel();

            tradeOrderMapper.updateStatusByOrderNo(tradeOrder);

            //对扣减付款资金账户资金进行反操作
            CapitalAccount transferFromAccount = capitalAccountMapper.findByUserId(capitalTradeOrderDto.getSelfUserId());

            transferFromAccount.cancelTransfer(capitalTradeOrderDto.getAmount());

            capitalAccountMapper.updateAmountByUserId(transferFromAccount);

        }
        System.out.println("==================>从服务cancel调用成功");

    }








}
