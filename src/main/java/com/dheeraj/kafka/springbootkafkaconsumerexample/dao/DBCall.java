package com.dheeraj.kafka.springbootkafkaconsumerexample.dao;

import com.dheeraj.kafka.springbootkafkaconsumerexample.logging.LoggingIntoDb01;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


@Component
public class DBCall {
    Logger logger = LoggerFactory.getLogger(DBCall.class);

    @Value("${topicInfo.numRetries}")
    private String att;

    @Autowired
    private UserRepository userRepository;

    public DBCall(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Retryable(value = SQLException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void dbCall(LoggingIntoDb01 logging) throws SQLException {
        System.out.println("TRuyuyhu");

        userRepository.save(logging);

    }

    @Recover
    public void recover(Exception exception, LoggingIntoDb01 logging) {
        logger.info("The driver with error dab connection failed :{} ", exception);
        exception.printStackTrace();

    }
}
