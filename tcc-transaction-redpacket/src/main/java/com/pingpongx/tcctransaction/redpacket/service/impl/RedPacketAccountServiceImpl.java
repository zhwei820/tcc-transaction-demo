package com.pingpongx.tcctransaction.redpacket.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pingpongx.tcctransaction.redpacket.api.RedPacketAccountService;
import java.math.BigDecimal;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
@Service
public class RedPacketAccountServiceImpl implements RedPacketAccountService{

    @Override
    public BigDecimal getRedPacketAccountByUserId(long userId) {
        return BigDecimal.ZERO;
    }
}
