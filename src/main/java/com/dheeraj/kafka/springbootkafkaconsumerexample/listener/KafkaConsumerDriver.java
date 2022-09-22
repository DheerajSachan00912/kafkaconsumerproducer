package com.dheeraj.kafka.springbootkafkaconsumerexample.listener;

import com.dheeraj.kafka.springbootkafkaconsumerexample.dao.UserRepository;
import com.dheeraj.kafka.springbootkafkaconsumerexample.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.dheeraj.kafka.springbootkafkaconsumerexample.logging.LoggingIntoDb01;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.*;

@Service
public class KafkaConsumer implements AcknowledgingMessageListener<String, Object> {
    Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    @Value("#{'${TopicInfo.mandatorySqlCols.cols_network}'.split(',')}")
    private List<String> myListNetwork;

    @Value("#{'${TopicInfo.mandatorySqlCols.cols_driver}'.split(',')}")
    private List<String> myListDriver;

    //TopicInfo.payload.cols_network

    @Value("#{'${TopicInfo.payload.cols_network}'.split(',')}")
    private List<String> myListNetworkPayload;

    @Value("#{'${TopicInfo.payload.cols_driver}'.split(',')}")
    private List<String> myListDriverPayload;

    @Value("${consumeTopicNetwork}")
    private boolean consumeTopicNetwork;

    @Value("${consumeTopicDriver}")
    private boolean consumeTopicDriver;

    @Autowired
    KafkaTemplate<String, Network> kafkaNetwork;

    @Autowired
    KafkaTemplate<String, Driver> kafkaDriver;

    @Autowired
    KafkaTemplate<String, String> kafkaString;

    @Autowired
    private UserRepository userRepository;

    @Value("${topic_driver}")
    private String driverTopic;

    @Value("${topic_network}")
    private String networkTopic;

    //topic_string_network
    @Value("${topic_string_network}")
    private String networkStringNetwork;

    //topic_string_driver
    @Value("${topic_string_driver}")
    private String networkStringDriver;

    /*@KafkaListener(topics = "kafka_topic_driver0309",
            containerFactory = "userKafkaListenerFactoryDriver")*/
    //@Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(1000))
    public void consume(Driver driver) throws JsonProcessingException {
        System.out.println("driversdfd " + driver);

        List<Details> detailsList = driver.getDetailslist();
        System.out.println("detailsListttt " + detailsList);
        Result result = driver.getResult();
        List<ActivityLogs> activityLogsList = driver.getActivitylogslist();

        System.out.println("1111 " + detailsList);
        String detailsPayload = "\"detailsList\":[";
        System.out.println("detailsPayloaddd " + detailsPayload);
        logger.info("detailsPayload :{}", detailsPayload);
        boolean flagDetails = false;
        boolean flagActivityLogs = false;

        if (myListDriverPayload.contains("Details.ID") && myListDriverPayload.contains("Details.Description")) {
            flagDetails = true;
            for (Details details :
                    detailsList) {
                String iD = details.getId();
                String desc = details.getDescription();
                detailsPayload = detailsPayload + "{\"iD\":\"" + iD + "\",\"" + "description\":\"" + desc + "\"},";


            }
            detailsPayload = detailsPayload + "]";

            detailsPayload = detailsPayload.replace(",]", "]");

            System.out.println("detailsPayloadALL  " + detailsPayload);

        }
        if (myListDriverPayload.contains("Details.ID") && !(myListDriverPayload.contains("Details.Description"))) {
            System.out.println("came heeerererse with only ID");
            flagDetails = true;
            for (Details details :
                    detailsList) {
                String iD = details.getId();
                detailsPayload = detailsPayload + "{\"iD\":\"" + iD + "\"},";

            }
            detailsPayload = detailsPayload + "]";
            detailsPayload = detailsPayload.replace(",]", "]");
            System.out.println("detailsPayloadwithID only " + detailsPayload);

        }

        if (myListDriverPayload.contains("Details.Description") && !(myListDriverPayload.contains("Details.ID"))) {
            System.out.println("came heeerererse with only Description");
            flagDetails = true;
            for (Details details :
                    detailsList) {
                String description = details.getDescription();
                detailsPayload = detailsPayload + "{\"description\":\"" + description + "\"},";

            }
            detailsPayload = detailsPayload + "]";
            detailsPayload = detailsPayload.replace(",]", "]");
            System.out.println("description only " + detailsPayload);

        }
        //Activity Logs
        String activityLogsPaylod = "\"activityLogsList\":[";

        if (myListDriverPayload.contains("ActivityLogs.LogEntry") && myListDriverPayload.contains("ActivityLogs.Level")) {
            flagActivityLogs = true;
            for (ActivityLogs activityLogs1 :
                    activityLogsList) {
                String logEntry = activityLogs1.getLogentry();
                String level = activityLogs1.getLevel();
                activityLogsPaylod = activityLogsPaylod + "{\"logEntry\":\"" + logEntry + "\",\"" + "level\":\"" + level + "\"},";


            }
            activityLogsPaylod = activityLogsPaylod + "]";

            activityLogsPaylod = activityLogsPaylod.replace(",]", "]");

            System.out.println("activityLogsList Both " + activityLogsPaylod);

        }
        if (myListDriverPayload.contains("ActivityLogs.LogEntry") && !(myListDriverPayload.contains("ActivityLogs.Level"))) {
            flagActivityLogs = true;

            for (ActivityLogs activityLogs1 :
                    activityLogsList) {
                String logEntry = activityLogs1.getLogentry();

                activityLogsPaylod = activityLogsPaylod + "{\"logEntry\":\"" + logEntry + "\"},";

            }
            activityLogsPaylod = activityLogsPaylod + "]";
            activityLogsPaylod = activityLogsPaylod.replace(",]", "]");
            System.out.println("activityLogsList LogEntry " + activityLogsPaylod);

        }
        System.out.println("activityLogsPaylodqw " + activityLogsPaylod);
        if (myListDriverPayload.contains("ActivityLogs.Level") && !(myListDriverPayload.contains("ActivityLogs.LogEntry"))) {
            flagActivityLogs = true;

            for (ActivityLogs activityLogs1 :
                    activityLogsList) {
                String level = activityLogs1.getLevel();

                activityLogsPaylod = activityLogsPaylod + "{\"level\":\"" + level + "\"},";

            }
            activityLogsPaylod = activityLogsPaylod + "]";
            activityLogsPaylod = activityLogsPaylod.replace(",]", "]");
            System.out.println("activityLogsPay Level " + activityLogsPaylod);

        }

        System.out.println("2222 " + activityLogsList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String data = objectMapper.writeValueAsString(driver);
        System.out.println("data " + data);
        String[] afterSplit = data.split(",");
        data = data.toLowerCase();
        boolean flag = false;

        System.out.println("myListDriverPayload " + myListDriverPayload);


        Map<String, String> map = new HashMap<>();
        for (String payloadString :
                myListDriverPayload) {
            payloadString = payloadString.toLowerCase();
            if (payloadString.contains(".")) {
                String whichObject = payloadString.substring(0, payloadString.indexOf("."));
                payloadString = payloadString.substring(payloadString.indexOf(".") + 1);
                System.out.println("After dot " + payloadString);

            }
            int k = 0;
            for (String afterSplitString :
                    afterSplit) {

                if (afterSplitString.toLowerCase().contains(payloadString)) {

                    afterSplitString = afterSplitString.substring(afterSplitString.lastIndexOf(": \"") + 3, afterSplitString.lastIndexOf("\""));
                    if (!(map.get(payloadString) == null)) {
                        k++;
                        payloadString = k + "" + payloadString;

                    }
                    map.put(payloadString, afterSplitString);

                }
                System.out.println("Data after split " + afterSplitString);
            }

        }
        System.out.println("Mapping you " + map);
        Set<String> keySet = map.keySet();
        Map<String, String> mapDetails = new HashMap<>();
        Map<String, String> mapActivityLogs = new HashMap<>();
        String finalPayloadDriver = "{";
        for (String key :
                keySet) {
            System.out.println("key " + key + " value " + map.get(key));

            if (!(key.contains("id") || key.contains("description") || key.contains("logEntry".toLowerCase())
                    || key.contains("level"))) {
                finalPayloadDriver = finalPayloadDriver + "\"" + key + "\":\"" + map.get(key) + "\",";

            } else if (key.contains("id") || key.contains("description")) {
                mapDetails.put(key, map.get(key));

            } else if (key.contains("logEntry".toLowerCase()) || key.contains("level") ||
                    key.contains("lineNo") || key.contains("dateTime")) {
                mapActivityLogs.put(key, map.get(key));
            }

        }


        finalPayloadDriver += "}";
        finalPayloadDriver = finalPayloadDriver.replace(",}", "}");
        System.out.println("finalPayloadDriver " + finalPayloadDriver);
        System.out.println("mapDetails " + mapDetails);
        System.out.println("mapActivityLogs " + mapActivityLogs);

        if (flagDetails == true) {
            System.out.println("Inside this789");

            finalPayloadDriver = finalPayloadDriver.replace("}", "," + detailsPayload + "}");

        }


        if (flagActivityLogs == true) {
            System.out.println("Inside this123");
            finalPayloadDriver = finalPayloadDriver.replace("]}", "]," + activityLogsPaylod + "}");

        }
        System.out.println("here is the final payload after everything " + finalPayloadDriver);

        for (String colsDriver :
                myListDriver) {
            if (colsDriver.contains(".")) {
                colsDriver = colsDriver.substring(colsDriver.lastIndexOf(".") + 1);
            }
            if (colsDriver.equalsIgnoreCase("iD") ||
                    colsDriver.equalsIgnoreCase("description")) {

                for (Details details :
                        detailsList) {
                    if (details.getId() == null || details.getDescription() == null) {

                        flag = true;
                    }

                }
            }
            if (colsDriver.equalsIgnoreCase("LogEntry") ||
                    colsDriver.equalsIgnoreCase("level")) {

                for (ActivityLogs activityLogs :
                        activityLogsList) {
                    if (activityLogs.getLogentry() == null || activityLogs.getLevel() == null) {

                        flag = true;
                    }

                }
            }
            System.out.println("colsDriver " + colsDriver);
            if (!(data.contains(colsDriver.toLowerCase()))) {
                System.out.println("89898");
                flag = true;
            }
        }

        if (flag == false) {
            logger.info("The driver without error :{} ", driver);
            System.out.println("driverTopic " + networkStringDriver);
            System.out.println("finalPayloadDrivereretre " + finalPayloadDriver);
            finalPayloadDriver = finalPayloadDriver.toLowerCase();
            logger.info("driverTopic :{} ", networkStringDriver);
            Gson g = new Gson();
            Driver driverToBeSent = g.fromJson(finalPayloadDriver, Driver.class);
            System.out.println("String to Jason Driver " + driverToBeSent);
            logger.info("String to Json Driver :{}", driverToBeSent);
            ListenableFuture<SendResult<String, Driver>> listenableFuture = null;
            if (consumeTopicDriver) {
                try {
                    listenableFuture = kafkaDriver.send(driverTopic, driverToBeSent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                logger.info("finalPayLoad :{} need not be consumed Driver :{}", driverToBeSent, consumeTopicDriver);
                logger.info("No Consumption form this topic Driver :{}", consumeTopicDriver);
            }

            //kafkaString.send(networkStringDriver, finalPayloadDriver);
        } else {
            String entitlement = driver.getEntitlement();
            if (entitlement == null) {
                entitlement = "Entitlement is null";

            }
            LoggingIntoDb01 logging = new LoggingIntoDb01("The driver with error  " + entitlement, new Date().toString());
            dbCall(logging);

            logger.info("The driver with error :{} ", logging);
        }
    }

    @Retryable(value = Exception.class, maxAttempts = 3)
    private void dbCall(LoggingIntoDb01 logging) {
        userRepository.save(logging);
    }

    @Recover
    private String recover(Exception exception) {
        logger.info("The driver with error dab connection failed :{} ", exception);
        exception.printStackTrace();
        return "Exception occurred" + exception;
    }


    /*@KafkaListener(topics = "kafka_topic_network0309",
            containerFactory = "userKafkaListenerFactoryNetwork")*/
    //@Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(1000))
    public void consumeJson(Network network) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String data = objectMapper.writeValueAsString(network);
            String[] afterSplit = data.split(",");
            System.out.println("length " + afterSplit.length);
            logger.debug("Inside consume of Network");
            logger.info("Inside consume of Network :{} ", network);
            logger.info("Inside consume of data :{} ", data);


            int i = 0;
            Map<String, String> map = new HashMap<>();
            for (String fromPayload :
                    myListNetworkPayload) {
                for (String networkObject :
                        afterSplit) {
                    if (networkObject.contains(fromPayload)) {
                        //System.out.println(networkObject.substring(networkObject.indexOf("\"")+1));
                        String rum = networkObject.substring(networkObject.indexOf(":") + 3, networkObject.lastIndexOf("\""));
                        System.out.println("rum " + rum + " " + rum.length());
                        map.put(fromPayload, rum);
                    }
                }
            }
            System.out.println("MAPisMade " + map);
            Set<String> keySet = map.keySet();
            String finalPayLoad = "{";
            String keyAndValue = "";
            for (String key :
                    keySet) {
                keyAndValue = "\"" + key + "\":\"" + map.get(key) + "\"," + keyAndValue;
                //System.out.println("keyAndValue "+keyAndValue);
            }

            // keyAndValue.replace(keyAndValue.lastIndexOf(","),"") ;
            finalPayLoad = finalPayLoad + keyAndValue + "}";
            finalPayLoad = finalPayLoad.replace(",}", "}");
            System.out.println("final " + finalPayLoad);

            for (String s :
                    myListNetworkPayload) {
                //System.out.println("payload in network " + s);

                // System.out.println("&& "+network);


            }


            boolean flag = false;

            System.out.println("Arrays " + Arrays.toString(afterSplit));
            System.out.println("myListNetwork " + myListNetwork);

            for (String string1 :
                    myListNetwork) {
                if (!data.contains(string1)) {
                    System.out.println("String one " + string1);
                    flag = true;
                    break;
                }
            }


            if (flag == false) {
                logger.info("The network without error :{} ", network);
                logger.info("networkTopic :{} ", networkTopic);
                System.out.println("networkTopic " + networkTopic);
            /*System.out.println("just before sending " + o + " " + network);
            o++;*/
                String networkString = network.toString();
                String[] g = networkString.split(",");

                finalPayLoad = finalPayLoad.replace("\\", "");
                System.out.println("finalPayLoad12312321 " + finalPayLoad);
                logger.info("networkStringNetwork :{}", networkStringNetwork);
                logger.info("finalPayLoad :{}", finalPayLoad);
                ListenableFuture<SendResult<String, String>> listenableFutureNetwork = null;

                if (consumeTopicNetwork) {
                    listenableFutureNetwork = kafkaString.send(networkStringNetwork, finalPayLoad);
                } else {
                    logger.info("finalPayLoad :{} need not be consumed :{}", finalPayLoad, consumeTopicNetwork);
                    logger.info("No Consumption form this topic :{}", consumeTopicNetwork);
                }


            } else {
                String os = network.getOs();
                if (os == null) {
                    os = "os is not mentioned";
                }
                LoggingIntoDb01 logging = new LoggingIntoDb01("The network with error " + os, new Date().toString());
                // logger.info("The network with error :{} ", network);
                dbCall(logging);

                logger.info("The network with error :{} ", network);
                logger.info("The network with error logging :{} ", logging);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    @KafkaListener(topics = "kafka_topic_driver0309",
            containerFactory = "userKafkaListenerFactoryDriver")
    public void onMessage(ConsumerRecord<String, Object> Object, Acknowledgment acknowledgment) {

        Driver driver = null;
        try {
            driver = (Driver) Object.value();
        } catch (Exception e) {
            System.out.println("yummmmyy "+e.toString());
        }
        logger.info("Datummm " + driver);

        acknowledgment.acknowledge();

        //String driver = driver1.value();
        System.out.println("typp   " + driver);

        //Driver driver2 = driver1.value();
        System.out.println("driversdfd " + driver);
        List<Details> detailsList = driver.getDetailslist();
        System.out.println("detailsListttt " + detailsList);
        Result result = driver.getResult();
        List<ActivityLogs> activityLogsList = driver.getActivitylogslist();

        System.out.println("1111 " + detailsList);
        String detailsPayload = "\"detailsList\":[";
        System.out.println("detailsPayloaddd " + detailsPayload);
        logger.info("detailsPayload :{}", detailsPayload);
        boolean flagDetails = false;
        boolean flagActivityLogs = false;

        if (myListDriverPayload.contains("Details.ID") && myListDriverPayload.contains("Details.Description")) {
            flagDetails = true;
            for (Details details :
                    detailsList) {
                String iD = details.getId();
                String desc = details.getDescription();
                detailsPayload = detailsPayload + "{\"iD\":\"" + iD + "\",\"" + "description\":\"" + desc + "\"},";


            }
            detailsPayload = detailsPayload + "]";

            detailsPayload = detailsPayload.replace(",]", "]");

            System.out.println("detailsPayloadALL  " + detailsPayload);

        }
        if (myListDriverPayload.contains("Details.ID") && !(myListDriverPayload.contains("Details.Description"))) {
            System.out.println("came heeerererse with only ID");
            flagDetails = true;
            for (Details details :
                    detailsList) {
                String iD = details.getId();
                detailsPayload = detailsPayload + "{\"iD\":\"" + iD + "\"},";

            }
            detailsPayload = detailsPayload + "]";
            detailsPayload = detailsPayload.replace(",]", "]");
            System.out.println("detailsPayloadwithID only " + detailsPayload);

        }

        if (myListDriverPayload.contains("Details.Description") && !(myListDriverPayload.contains("Details.ID"))) {
            System.out.println("came heeerererse with only Description");
            flagDetails = true;
            for (Details details :
                    detailsList) {
                String description = details.getDescription();
                detailsPayload = detailsPayload + "{\"description\":\"" + description + "\"},";

            }
            detailsPayload = detailsPayload + "]";
            detailsPayload = detailsPayload.replace(",]", "]");
            System.out.println("description only " + detailsPayload);

        }
        //Activity Logs
        String activityLogsPaylod = "\"activityLogsList\":[";

        if (myListDriverPayload.contains("ActivityLogs.LogEntry") && myListDriverPayload.contains("ActivityLogs.Level")) {
            flagActivityLogs = true;
            for (ActivityLogs activityLogs1 :
                    activityLogsList) {
                String logEntry = activityLogs1.getLogentry();
                String level = activityLogs1.getLevel();
                activityLogsPaylod = activityLogsPaylod + "{\"logEntry\":\"" + logEntry + "\",\"" + "level\":\"" + level + "\"},";


            }
            activityLogsPaylod = activityLogsPaylod + "]";

            activityLogsPaylod = activityLogsPaylod.replace(",]", "]");

            System.out.println("activityLogsList Both " + activityLogsPaylod);

        }
        if (myListDriverPayload.contains("ActivityLogs.LogEntry") && !(myListDriverPayload.contains("ActivityLogs.Level"))) {
            flagActivityLogs = true;

            for (ActivityLogs activityLogs1 :
                    activityLogsList) {
                String logEntry = activityLogs1.getLogentry();

                activityLogsPaylod = activityLogsPaylod + "{\"logEntry\":\"" + logEntry + "\"},";

            }
            activityLogsPaylod = activityLogsPaylod + "]";
            activityLogsPaylod = activityLogsPaylod.replace(",]", "]");
            System.out.println("activityLogsList LogEntry " + activityLogsPaylod);

        }
        System.out.println("activityLogsPaylodqw " + activityLogsPaylod);
        if (myListDriverPayload.contains("ActivityLogs.Level") && !(myListDriverPayload.contains("ActivityLogs.LogEntry"))) {
            flagActivityLogs = true;

            for (ActivityLogs activityLogs1 :
                    activityLogsList) {
                String level = activityLogs1.getLevel();

                activityLogsPaylod = activityLogsPaylod + "{\"level\":\"" + level + "\"},";

            }
            activityLogsPaylod = activityLogsPaylod + "]";
            activityLogsPaylod = activityLogsPaylod.replace(",]", "]");
            System.out.println("activityLogsPay Level " + activityLogsPaylod);

        }

        System.out.println("2222 " + activityLogsList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String data = null;
        try {
            data = objectMapper.writeValueAsString(driver);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("data " + data);
        String[] afterSplit = data.split(",");
        data = data.toLowerCase();
        boolean flag = false;

        System.out.println("myListDriverPayload " + myListDriverPayload);


        Map<String, String> map = new HashMap<>();
        for (String payloadString :
                myListDriverPayload) {
            payloadString = payloadString.toLowerCase();
            if (payloadString.contains(".")) {
                String whichObject = payloadString.substring(0, payloadString.indexOf("."));
                payloadString = payloadString.substring(payloadString.indexOf(".") + 1);
                System.out.println("After dot " + payloadString);

            }
            int k = 0;
            for (String afterSplitString :
                    afterSplit) {

                if (afterSplitString.toLowerCase().contains(payloadString)) {

                    afterSplitString = afterSplitString.substring(afterSplitString.lastIndexOf(": \"") + 3, afterSplitString.lastIndexOf("\""));
                    if (!(map.get(payloadString) == null)) {
                        k++;
                        payloadString = k + "" + payloadString;

                    }
                    map.put(payloadString, afterSplitString);

                }
                System.out.println("Data after split " + afterSplitString);
            }

        }
        System.out.println("Mapping you " + map);
        Set<String> keySet = map.keySet();
        Map<String, String> mapDetails = new HashMap<>();
        Map<String, String> mapActivityLogs = new HashMap<>();
        String finalPayloadDriver = "{";
        for (String key :
                keySet) {
            System.out.println("key " + key + " value " + map.get(key));

            if (!(key.contains("id") || key.contains("description") || key.contains("logEntry".toLowerCase())
                    || key.contains("level"))) {
                finalPayloadDriver = finalPayloadDriver + "\"" + key + "\":\"" + map.get(key) + "\",";

            } else if (key.contains("id") || key.contains("description")) {
                mapDetails.put(key, map.get(key));

            } else if (key.contains("logEntry".toLowerCase()) || key.contains("level") ||
                    key.contains("lineNo") || key.contains("dateTime")) {
                mapActivityLogs.put(key, map.get(key));
            }

        }


        finalPayloadDriver += "}";
        finalPayloadDriver = finalPayloadDriver.replace(",}", "}");
        System.out.println("finalPayloadDriver " + finalPayloadDriver);
        System.out.println("mapDetails " + mapDetails);
        System.out.println("mapActivityLogs " + mapActivityLogs);

        if (flagDetails == true) {
            System.out.println("Inside this789");

            finalPayloadDriver = finalPayloadDriver.replace("}", "," + detailsPayload + "}");

        }


        if (flagActivityLogs == true) {
            System.out.println("Inside this123");
            finalPayloadDriver = finalPayloadDriver.replace("]}", "]," + activityLogsPaylod + "}");

        }
        System.out.println("here is the final payload after everything " + finalPayloadDriver);

        for (String colsDriver :
                myListDriver) {
            if (colsDriver.contains(".")) {
                colsDriver = colsDriver.substring(colsDriver.lastIndexOf(".") + 1);
            }
            if (colsDriver.equalsIgnoreCase("iD") ||
                    colsDriver.equalsIgnoreCase("description")) {

                for (Details details :
                        detailsList) {
                    if (details.getId() == null || details.getDescription() == null) {

                        flag = true;
                    }

                }
            }
            if (colsDriver.equalsIgnoreCase("LogEntry") ||
                    colsDriver.equalsIgnoreCase("level")) {

                for (ActivityLogs activityLogs :
                        activityLogsList) {
                    if (activityLogs.getLogentry() == null || activityLogs.getLevel() == null) {

                        flag = true;
                    }

                }
            }
            System.out.println("colsDriver " + colsDriver);
            if (!(data.contains(colsDriver.toLowerCase()))) {
                System.out.println("89898");
                flag = true;
            }
        }

        if (flag == false) {
            logger.info("The driver2 without error :{} ", driver);
            System.out.println("driverTopic " + networkStringDriver);
            System.out.println("finalPayloadDrivereretre " + finalPayloadDriver);
            finalPayloadDriver = finalPayloadDriver.toLowerCase();
            logger.info("driverTopic :{} ", networkStringDriver);
            Gson g = new Gson();
            Driver driverToBeSent = g.fromJson(finalPayloadDriver, Driver.class);
            System.out.println("String to Jason Driver " + driverToBeSent);
            logger.info("String to Json Driver :{}", driverToBeSent);
            ListenableFuture<SendResult<String, Driver>> listenableFuture = null;
            if (consumeTopicDriver) {
                try {
                    listenableFuture = kafkaDriver.send(driverTopic, driverToBeSent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                logger.info("finalPayLoad :{} need not be consumed Driver :{}", driverToBeSent, consumeTopicDriver);
                logger.info("No Consumption form this topic Driver :{}", consumeTopicDriver);
            }

            //kafkaString.send(networkStringDriver, finalPayloadDriver);
        } else {
            String entitlement = driver.getEntitlement();
            if (entitlement == null) {
                entitlement = "Entitlement is null";

            }
            LoggingIntoDb01 logging = new LoggingIntoDb01("The driver2 with error  " + entitlement, new Date().toString());
            dbCall(logging);

            logger.info("The driver2 with error :{} ", logging);
        }

        //AcknowledgingMessageListener.super.onMessage(data, acknowledgment, consumer);
    }
}
