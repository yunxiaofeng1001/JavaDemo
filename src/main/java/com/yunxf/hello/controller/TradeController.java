package com.yunxf.hello.controller;

import com.yunxf.hello.entity.Trade;
import com.yunxf.hello.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yunxiaofeng on 2015/6/8.
 */
@RestController
@RequestMapping("trade")
public class TradeController {
    private static Logger logger = LoggerFactory.getLogger(TradeController.class);
    @Autowired
    private TradeService tradeService;

    @RequestMapping(method = RequestMethod.PUT)
    public Trade create(@RequestBody Trade trade){
        tradeService.create(trade);
        return trade;
    }

    @RequestMapping(value = "/findbystate" ,method = RequestMethod.GET)
    public List<Trade> getByState(@RequestParam String state){
        logger.info("state:"+state);
        return tradeService.getTradesByState(state);
    }

    @RequestMapping(value = "/find" ,method = RequestMethod.GET)
    public List<Trade> findAll(){
        return tradeService.findAll();
    }
}
