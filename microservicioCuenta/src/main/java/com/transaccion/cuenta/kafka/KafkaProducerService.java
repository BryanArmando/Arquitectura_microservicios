package com.transaccion.cuenta.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String REQUEST_TOPIC = "cliente-validation-request";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendValidationRequest(Object message) {
        kafkaTemplate.send(REQUEST_TOPIC, message);
    }

}

