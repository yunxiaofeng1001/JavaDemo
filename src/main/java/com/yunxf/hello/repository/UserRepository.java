package com.yunxf.hello.repository;

import com.yunxf.hello.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by yunxiaofeng on 2015/6/8.
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

}
