package com.pingpongx.tcctransaction.test;

import com.pingpongx.tcctransaction.order.api.PaymentService;
import com.pingpongx.tcctransaction.order.service.LaunchApplication;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
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
    private PaymentService paymentService;

    @Test
    public void test(){
        Pair<Long, Integer> pair = Pair.of(1l,1);
        List<Pair<Long, Integer>> pairList = new ArrayList<>();
        pairList.add(pair);
        paymentService.makePayment( 2000,1, pairList, BigDecimal.ONE);



    }



}
