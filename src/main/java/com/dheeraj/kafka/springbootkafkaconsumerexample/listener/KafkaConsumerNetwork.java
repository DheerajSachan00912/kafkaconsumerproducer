package com.dheeraj.kafka.springbootkafkaconsumerexample.listener;

import com.dheeraj.kafka.springbootkafkaconsumerexample.dao.DBCall;
import com.dheeraj.kafka.springbootkafkaconsumerexample.dao.UserRepository;
import com.dheeraj.kafka.springbootkafkaconsumerexample.logging.LoggingIntoDb01;
import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Driver;
import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Network;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

import java.sql.SQLException;
import java.util.*;

@Service
public class KafkaConsumerNetwork implements AcknowledgingMessageListener<String, Object> {

    Logger logger = LoggerFactory.getLogger(KafkaConsumerDriver.class);
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

    @Autowired
    DBCall dbCall;

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

    @Override
    @KafkaListener(topics = "${topic_network}",
            autoStartup = "${spring.kafka.consumer.topic.activation-status.network}",
            containerFactory = "userKafkaListenerFactoryNetwork")
    //@Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(1000))
    public void onMessage(ConsumerRecord<String, Object> Object, Acknowledgment acknowledgment) {
        acknowledgment.acknowledge();
        Network network = null;
        try {
            network = (Network) Object.value();
        } catch (Exception e) {
            System.out.println("yummmmyy12312 " + e.toString());
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String data = objectMapper.writeValueAsString(network);
            String[] afterSplit = data.split(",");
            System.out.println("length " + afterSplit.length);
            logger.debug("Inside consume of Network23423432");
            logger.info("Inside consume of Network12213 :{} ", network);
            logger.info("Inside consume of datajingle :{} ", data);


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
                dbCall.dbCall(logging);

                logger.info("The network with error :{} ", network);
                logger.info("The network with error logging :{} ", logging);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

  /*  @Retryable(value = Exception.class, maxAttempts = 3)
    private void dbCall(LoggingIntoDb01 logging) {
        userRepository.save(logging);
    }

    @Recover
    private String recover(Exception exception) {
        logger.info("The driver with error dab connection failed :{} ", exception);
        exception.printStackTrace();
        return "Exception occurred" + exception;
    }*/

}
