package com.yunxf.test;

import com.yunxf.hello.Application;
import com.yunxf.hello.entity.User;
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
import java.util.ArrayList;
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

    RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void abcTest(){
        User u = new User();
        u.setName("yunxiaofeng");
        userRepository.save(u);
        User u1 = new User();
        u1.setName("admin");
        userRepository.save(u1);
        ResponseEntity<List> a = restTemplate.getForEntity("http://localhost:8080/users", List.class);
        ArrayList<HashMap> arr = (ArrayList<HashMap>)a.getBody();

        for(HashMap reU : arr){
            System.out.println("id="+ reU.get("id"));
            System.out.println("name="+reU.get("name"));
        }
        System.out.println(arr.get(0).toString());
//        System.out.println("--------------------"+restTemplate.getForEntity("http://localhost:8080/users", ArrayList.class).toString());


    }
}
