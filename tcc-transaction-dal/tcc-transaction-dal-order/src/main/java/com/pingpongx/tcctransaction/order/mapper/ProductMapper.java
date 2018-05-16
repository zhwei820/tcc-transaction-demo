package com.pingpongx.tcctransaction.order.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.pingpongx.tcctransaction.order.domain.Product;

/**
 * @author liuwei
 * @date 2018/5/9
 * @desc
 */
public interface ProductMapper extends BaseMapper<Product> {

    Product getById(long productId);
}
