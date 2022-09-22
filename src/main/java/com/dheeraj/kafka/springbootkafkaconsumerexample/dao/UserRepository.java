package com.dheeraj.kafka.springbootkafkaconsumerexample.dao;


import com.dheeraj.kafka.springbootkafkaconsumerexample.logging.LoggingIntoDb01;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LoggingIntoDb01,Long> {
}
