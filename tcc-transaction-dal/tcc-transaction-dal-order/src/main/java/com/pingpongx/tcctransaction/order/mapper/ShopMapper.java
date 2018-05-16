package com.pingpongx.tcctransaction.order.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.pingpongx.tcctransaction.order.domain.Shop;

/**
 * @author liuwei
 * @date 2018/5/9
 * @desc
 */
public interface ShopMapper extends BaseMapper<Shop> {

    Shop findById(long shopId);
}
