package com.pingpongx.tcctransaction.capital.api;

import java.math.BigDecimal;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc
 */
public interface CapitalAccountService {

    BigDecimal getCapitalAccountByUserId(long userId);

}
