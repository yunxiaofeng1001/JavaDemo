package com.yunxf.hello.repository;

import com.yunxf.hello.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by yunxiaofeng on 2015/6/8.
 */
public interface TradeRepository extends JpaRepository<Trade,Long> {
    List<Trade> findByState(String state);
//    @Query("from Trade t where t.state = :state")
//    List<Trade> findByState(@Param("state") String state);
}
