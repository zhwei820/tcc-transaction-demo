package com.pingpongx.tcctransaction.capital.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.pingpongx.tcctransaction.capital.domain.CapitalAccount;

/**
 * @author liuwei
 * @date 2018/5/9
 * @desc
 */
public interface CapitalAccountMapper extends BaseMapper<CapitalAccount>{

    CapitalAccount findByUserId(long selfUserId);

    void updateAmountByUserId(CapitalAccount transferFromAccount);
}
