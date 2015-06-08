package com.yunxf.hello;

import com.yunxf.hello.entity.Trade;
import com.yunxf.hello.entity.User;
import com.yunxf.hello.repository.UserRepository;
import com.yunxf.hello.service.TradeService;
import com.yunxf.hello.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yunxiaofeng on 2015/6/8.
 */
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User user  = new User();
        Trade trade = new Trade();
        user.setName("zhangsan");
        UserService userService = event.getApplicationContext().getBean(UserService.class);
        User u = userService.create(user);
        trade.setCreateTime(new Date());
        trade.setMoney(new BigDecimal(86.5));
        trade.setMoneyType("rmb");
        trade.setState("success");
        trade.setType("pay");
        trade.setUser(u);
        TradeService tradeService = event.getApplicationContext().getBean(TradeService.class);
        tradeService.create(trade);

    }
}
