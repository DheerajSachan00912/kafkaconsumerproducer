package com.dheeraj.kafka.springbootkafkaconsumerexample;


import com.dheeraj.kafka.springbootkafkaconsumerexample.config.KafkaConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = KafkaConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class TestClass {

    @Test
   public void hi(){

    }
}
