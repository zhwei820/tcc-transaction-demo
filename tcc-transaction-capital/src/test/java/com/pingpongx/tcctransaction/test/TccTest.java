package com.pingpongx.tcctransaction.test;

import com.pingpongx.tcctransaction.capital.api.CapitalTradeOrderService;
import com.pingpongx.tcctransaction.capital.api.dto.CapitalTradeOrderDto;
import com.pingpongx.tcctransaction.capital.service.LaunchApplication;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liuwei
 * @date 2018/5/9
 * @desc
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LaunchApplication.class)
public class TccTest {

    @Autowired
    private CapitalTradeOrderService capitalTradeOrderService;

    @Test
    public void test(){
        CapitalTradeOrderDto capitalTradeOrderDto = new CapitalTradeOrderDto();
        capitalTradeOrderDto.setSelfUserId(1000);
        capitalTradeOrderDto.setOppositeUserId(2000);
        capitalTradeOrderDto.setOrderTitle("转账1元");
        capitalTradeOrderDto.setMerchantOrderNo("ABC123456");
        capitalTradeOrderDto.setAmount(BigDecimal.ONE);

        capitalTradeOrderService.record(null,capitalTradeOrderDto);

        System.out.println("tcc-transaction");
    }



}
