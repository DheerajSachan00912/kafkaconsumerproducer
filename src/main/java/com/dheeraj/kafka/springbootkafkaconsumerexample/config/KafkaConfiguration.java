package com.dheeraj.kafka.springbootkafkaconsumerexample.config;


import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Driver;
import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Network;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String cBootstrapServers;

    //spring.kafka.producer.bootstrap-servers

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String cBootstrapServersProducer;

    @Value("${topicInfo.startingOffsets}")
    private String startingOffsets;

    @Value("${topicInfo.numRetries}")
    private String numRetries;

    //rety_backoff_ms_config
    @Value("${rety_backoff_ms_config}")
    private String backoff_ms_config;

    //topicInfo.maxOffsetsPerTriggerNetwork

    @Value("${topicInfo.maxOffsetsPerTriggerNetwork}")
    private String maxOffsetsPerTriggerNetwork;

    //topicInfo.maxOffsetsPerTriggerDriver
    @Value("${topicInfo.maxOffsetsPerTriggerDriver}")
    private String maxOffsetsPerTriggerDriver;

    @Value("${topicInfo.numRetries}")
    private String retries;

    //String Consumer
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxOffsetsPerTriggerDriver);
        config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, cBootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setRetryTemplate(retryTemplate());
        return factory;
    }

    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
      /* here retry policy is used to set the number of attempts to
        retry and what exceptions you wanted to try and
         what you don't want to retry.*/
        retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
        return retryTemplate;
    }

    private SimpleRetryPolicy getSimpleRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
        exceptionMap.put(IllegalArgumentException.class, false);
        exceptionMap.put(TimeoutException.class, true);
        return new SimpleRetryPolicy(Integer.parseInt(retries), exceptionMap, true);
    }

    //Driver Consumer
    @Bean
    public ConsumerFactory<String, Driver> userConsumerFactoryDriver() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxOffsetsPerTriggerDriver);
        config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, cBootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Driver.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Driver> userKafkaListenerFactoryDriver() {
        ConcurrentKafkaListenerContainerFactory<String, Driver> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactoryDriver());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setRetryTemplate(retryTemplate());

        return factory;
    }

    //Network Consumer
    @Bean
    public ConsumerFactory<String, Network> userConsumerFactoryNetwork() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxOffsetsPerTriggerNetwork);
        config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, cBootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Network.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Network> userKafkaListenerFactoryNetwork() {
        ConcurrentKafkaListenerContainerFactory<String, Network> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactoryNetwork());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setRetryTemplate(retryTemplate());

        return factory;
    }

    //String Producer

    @Bean
    public ProducerFactory<String, String> producerFactoryString() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, cBootstrapServersProducer);
        config.put(ProducerConfig.RETRIES_CONFIG, numRetries);
        config.put(ProducerConfig.ACKS_CONFIG, "all");
        config.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, backoff_ms_config);
        config.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        //config.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, startingOffsets);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, String> kafkaTemplateString() {

        return new KafkaTemplate<>(producerFactoryString());
    }

    //Network producer

    @Bean
    public ProducerFactory<String, Network> producerFactoryNetwork() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, cBootstrapServersProducer);
        config.put(ProducerConfig.RETRIES_CONFIG, numRetries);
        config.put(ProducerConfig.ACKS_CONFIG, "all");
        config.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, backoff_ms_config);
        config.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, startingOffsets);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, Network> kafkaTemplateNetwork() {

        return new KafkaTemplate<>(producerFactoryNetwork());
    }

    @Bean
    public ProducerFactory<String, Driver> producerFactoryDriver() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, cBootstrapServersProducer);
        config.put(ProducerConfig.RETRIES_CONFIG, numRetries);
        config.put(ProducerConfig.ACKS_CONFIG, "all");
        config.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, backoff_ms_config);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, startingOffsets);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Driver> kafkaTemplateDriver() {

        return new KafkaTemplate<>(producerFactoryDriver());
    }

}
