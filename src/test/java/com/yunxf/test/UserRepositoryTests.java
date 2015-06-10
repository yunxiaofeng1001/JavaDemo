package com.yunxf.test;

import com.yunxf.hello.Application;
import com.yunxf.hello.entity.Trade;
import com.yunxf.hello.entity.User;
import com.yunxf.hello.repository.TradeRepository;
import com.yunxf.hello.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * Created by yunxiaofeng on 2015/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeRepository tradeRepository;

    RestTemplate restTemplate = new TestRestTemplate();



//    使用用户张三创建一条86元5角人民币的交易记录，验证交易状态为等待付款，并且交易的金额是正确的。
    @Test
    public void testOne(){
        User u = new User();
        u.setName("张三");
        u = userRepository.save(u);
        Trade trade = new Trade();
        trade.setType("pending");
        trade.setState("wait");
        trade.setUser(u);
        trade.setCreateTime(new Date());
        trade.setMoney(new BigDecimal(86.5));
        trade.setMoneyType("rmb");
        Trade testTrade = tradeRepository.save(trade);
        Assert.assertEquals(testTrade.getMoney(), new BigDecimal(86.5));
        Assert.assertEquals(testTrade.getState(),"wait");
    }




//    使用你所习惯的测试框架，初始化3条交易成功信息，2条交易失败信息。验证查询接口按全部状态查询共5条交易，只查询成功的交易是3条，只查询失败的交易是2条。
    @Test
    public void testTwo1(){
        User u = new User();
        u.setName("张三");
        u = userRepository.save(u);
        Trade t1 = new Trade("success",u);
        Trade t2 = new Trade("success",u);
        Trade t3 = new Trade("success",u);
        Trade t4 = new Trade("failed",u);
        Trade t5 = new Trade("failed",u);
        tradeRepository.save(t1);
        tradeRepository.save(t2);
        tradeRepository.save(t3);
        tradeRepository.save(t4);
        tradeRepository.save(t5);
        //发送客户端请求(三条成功记录)
        ResponseEntity<List> suc = restTemplate.getForEntity("http://localhost:8080/trade/findbystate?state=success", List.class);
        ArrayList<HashMap> successList = (ArrayList<HashMap>)suc.getBody();
        Assert.assertEquals(successList.size(),3);
        //发送客户端请求(两条失败记录)
        ResponseEntity<List> fia = restTemplate.getForEntity("http://localhost:8080/trade/findbystate?state=failed", List.class);
        ArrayList<HashMap> fialList = (ArrayList<HashMap>)fia.getBody();
        Assert.assertEquals(fialList.size(),2);

    }

}
