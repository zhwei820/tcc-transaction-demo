package com.pingpongx.tcctransaction.redpacket.api;

import java.math.BigDecimal;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
public interface RedPacketAccountService {

    BigDecimal getRedPacketAccountByUserId(long userId);

}
