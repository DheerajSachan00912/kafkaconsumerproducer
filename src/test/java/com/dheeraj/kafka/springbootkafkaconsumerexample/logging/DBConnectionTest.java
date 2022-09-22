package com.dheeraj.kafka.springbootkafkaconsumerexample.logging;

import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Driver;
import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Network;

import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DBConnection.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DBConnectionTest {
    @Autowired
    private DBConnection dBConnection;

    /**
     * Method under test: {@link DBConnection#connectDBNetwork(LoggingIntoDb01)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConnectDBNetwork() throws SQLException {


        LoggingIntoDb01 loggingIntoDb01 = new LoggingIntoDb01();
        loggingIntoDb01.setDriver(new Driver());
        loggingIntoDb01.setId(123L);
        loggingIntoDb01.setLogs("Logs");
        loggingIntoDb01
                .setNetwork(new Network("Service tag", "Platform", "Utc timestamp", "42", "Os", "Os service pack", "Os type",
                        "2020-03-01", "Driver id", "42", "Name", "1.0.2", "2020-03-01", "Criticality", "Pnpid", "Existing driver",
                        "The characteristics of someone or something", "Type", "Category", "https://example.org/example", "42",
                        "foo.txt", "Format", "File type", "Size", "Create by", "2020-03-01", true));
        loggingIntoDb01.setTimeStamp("Time Stamp");
        dBConnection.connectDBNetwork(loggingIntoDb01);
    }

    /**
     * Method under test: {@link DBConnection#connectDBDriver(LoggingIntoDb01)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConnectDBDriver() throws SQLException {


        LoggingIntoDb01 loggingIntoDb01 = new LoggingIntoDb01();
        loggingIntoDb01.setDriver(new Driver());
        loggingIntoDb01.setId(123L);
        loggingIntoDb01.setLogs("Logs");
        loggingIntoDb01
                .setNetwork(new Network("Service tag", "Platform", "Utc timestamp", "42", "Os", "Os service pack", "Os type",
                        "2020-03-01", "Driver id", "42", "Name", "1.0.2", "2020-03-01", "Criticality", "Pnpid", "Existing driver",
                        "The characteristics of someone or something", "Type", "Category", "https://example.org/example", "42",
                        "foo.txt", "Format", "File type", "Size", "Create by", "2020-03-01", true));
        loggingIntoDb01.setTimeStamp("Time Stamp");
        dBConnection.connectDBDriver(loggingIntoDb01);
    }
}

