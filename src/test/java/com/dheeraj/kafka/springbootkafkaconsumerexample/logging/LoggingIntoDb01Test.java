package com.dheeraj.kafka.springbootkafkaconsumerexample.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Driver;
import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Network;
import org.junit.jupiter.api.Test;

class LoggingIntoDb01Test {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link LoggingIntoDb01#LoggingIntoDb01()}
     *   <li>{@link LoggingIntoDb01#setDriver(Driver)}
     *   <li>{@link LoggingIntoDb01#setId(Long)}
     *   <li>{@link LoggingIntoDb01#setLogs(String)}
     *   <li>{@link LoggingIntoDb01#setNetwork(Network)}
     *   <li>{@link LoggingIntoDb01#setTimeStamp(String)}
     *   <li>{@link LoggingIntoDb01#toString()}
     *   <li>{@link LoggingIntoDb01#getDriver()}
     *   <li>{@link LoggingIntoDb01#getId()}
     *   <li>{@link LoggingIntoDb01#getLogs()}
     *   <li>{@link LoggingIntoDb01#getNetwork()}
     *   <li>{@link LoggingIntoDb01#getTimeStamp()}
     * </ul>
     */
    @Test
    void testConstructor() {
        LoggingIntoDb01 actualLoggingIntoDb01 = new LoggingIntoDb01();
        Driver driver = new Driver();
        actualLoggingIntoDb01.setDriver(driver);
        actualLoggingIntoDb01.setId(123L);
        actualLoggingIntoDb01.setLogs("Logs");
        Network network = new Network("Service tag", "Platform", "Utc timestamp", "42", "Os", "Os service pack", "Os type",
                "2020-03-01", "Driver id", "42", "Name", "1.0.2", "2020-03-01", "Criticality", "Pnpid", "Existing driver",
                "The characteristics of someone or something", "Type", "Category", "https://example.org/example", "42",
                "foo.txt", "Format", "File type", "Size", "Create by", "2020-03-01", true);

        actualLoggingIntoDb01.setNetwork(network);
        actualLoggingIntoDb01.setTimeStamp("Time Stamp");
        String actualToStringResult = actualLoggingIntoDb01.toString();
        assertSame(driver, actualLoggingIntoDb01.getDriver());
        assertEquals(123L, actualLoggingIntoDb01.getId().longValue());
        assertEquals("Logs", actualLoggingIntoDb01.getLogs());
        assertSame(network, actualLoggingIntoDb01.getNetwork());
        assertEquals("Time Stamp", actualLoggingIntoDb01.getTimeStamp());
        assertEquals("LoggingIntoDb{id=123, logs='Logs', timeStamp='Time Stamp'}", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link LoggingIntoDb01#LoggingIntoDb01(Long, String, String)}
     *   <li>{@link LoggingIntoDb01#setDriver(Driver)}
     *   <li>{@link LoggingIntoDb01#setId(Long)}
     *   <li>{@link LoggingIntoDb01#setLogs(String)}
     *   <li>{@link LoggingIntoDb01#setNetwork(Network)}
     *   <li>{@link LoggingIntoDb01#setTimeStamp(String)}
     *   <li>{@link LoggingIntoDb01#toString()}
     *   <li>{@link LoggingIntoDb01#getDriver()}
     *   <li>{@link LoggingIntoDb01#getId()}
     *   <li>{@link LoggingIntoDb01#getLogs()}
     *   <li>{@link LoggingIntoDb01#getNetwork()}
     *   <li>{@link LoggingIntoDb01#getTimeStamp()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        LoggingIntoDb01 actualLoggingIntoDb01 = new LoggingIntoDb01(123L, "Logs", "Time Stamp");
        Driver driver = new Driver();
        actualLoggingIntoDb01.setDriver(driver);
        actualLoggingIntoDb01.setId(123L);
        actualLoggingIntoDb01.setLogs("Logs");
        Network network = new Network("Service tag", "Platform", "Utc timestamp", "42", "Os", "Os service pack",
                "Os type", "2020-03-01", "Driver id", "42", "Name", "1.0.2", "2020-03-01", "Criticality", "Pnpid",
                "Existing driver", "The characteristics of someone or something", "Type", "Category",
                "https://example.org/example", "42", "foo.txt", "Format", "File type", "Size", "Create by", "2020-03-01",
                true);

        actualLoggingIntoDb01.setNetwork(network);
        actualLoggingIntoDb01.setTimeStamp("Time Stamp");
        String actualToStringResult = actualLoggingIntoDb01.toString();
        assertSame(driver, actualLoggingIntoDb01.getDriver());
        assertEquals(123L, actualLoggingIntoDb01.getId().longValue());
        assertEquals("Logs", actualLoggingIntoDb01.getLogs());
        assertSame(network, actualLoggingIntoDb01.getNetwork());
        assertEquals("Time Stamp", actualLoggingIntoDb01.getTimeStamp());
        assertEquals("LoggingIntoDb{id=123, logs='Logs', timeStamp='Time Stamp'}", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link LoggingIntoDb01#LoggingIntoDb01(String, String)}
     *   <li>{@link LoggingIntoDb01#setDriver(Driver)}
     *   <li>{@link LoggingIntoDb01#setId(Long)}
     *   <li>{@link LoggingIntoDb01#setLogs(String)}
     *   <li>{@link LoggingIntoDb01#setNetwork(Network)}
     *   <li>{@link LoggingIntoDb01#setTimeStamp(String)}
     *   <li>{@link LoggingIntoDb01#toString()}
     *   <li>{@link LoggingIntoDb01#getDriver()}
     *   <li>{@link LoggingIntoDb01#getId()}
     *   <li>{@link LoggingIntoDb01#getLogs()}
     *   <li>{@link LoggingIntoDb01#getNetwork()}
     *   <li>{@link LoggingIntoDb01#getTimeStamp()}
     * </ul>
     */
    @Test
    void testConstructor3() {
        LoggingIntoDb01 actualLoggingIntoDb01 = new LoggingIntoDb01("Logs", "Time Stamp");
        Driver driver = new Driver();
        actualLoggingIntoDb01.setDriver(driver);
        actualLoggingIntoDb01.setId(123L);
        actualLoggingIntoDb01.setLogs("Logs");
        Network network = new Network("Service tag", "Platform", "Utc timestamp", "42", "Os", "Os service pack",
                "Os type", "2020-03-01", "Driver id", "42", "Name", "1.0.2", "2020-03-01", "Criticality", "Pnpid",
                "Existing driver", "The characteristics of someone or something", "Type", "Category",
                "https://example.org/example", "42", "foo.txt", "Format", "File type", "Size", "Create by", "2020-03-01",
                true);

        actualLoggingIntoDb01.setNetwork(network);
        actualLoggingIntoDb01.setTimeStamp("Time Stamp");
        String actualToStringResult = actualLoggingIntoDb01.toString();
        assertSame(driver, actualLoggingIntoDb01.getDriver());
        assertEquals(123L, actualLoggingIntoDb01.getId().longValue());
        assertEquals("Logs", actualLoggingIntoDb01.getLogs());
        assertSame(network, actualLoggingIntoDb01.getNetwork());
        assertEquals("Time Stamp", actualLoggingIntoDb01.getTimeStamp());
        assertEquals("LoggingIntoDb{id=123, logs='Logs', timeStamp='Time Stamp'}", actualToStringResult);
    }
}

