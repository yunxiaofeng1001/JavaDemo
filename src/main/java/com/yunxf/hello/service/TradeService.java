package com.yunxf.hello.service;

import com.google.common.collect.Lists;
import com.yunxf.hello.entity.Trade;
import com.yunxf.hello.repository.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.nimbus.State;
import java.util.List;

/**
 * Created by yunxiaofeng on 2015/6/8.
 */
@Service
public class TradeService {

    private static Logger logger = LoggerFactory.getLogger(TradeService.class);
    @Autowired
    private TradeRepository TradeDao;

    /**
     * 创建订单
     * @param trade
     * @return
     */
    @Transactional
    public Trade create(Trade trade){
        return TradeDao.save(trade);
    }

    /**
     * 通过订单状态查询
     * @param state
     * @return
     */
    @Transactional(readOnly = true)
    public List<Trade> getTradesByState(String state){
        logger.info("stateService:"+state);
        return TradeDao.findByState(state);
    }

    /**
     * 查询全部订单
     * @return
     */
    @Transactional(readOnly = true)
    public List<Trade> findAll(){
        return Lists.newArrayList(TradeDao.findAll());
    }
}
