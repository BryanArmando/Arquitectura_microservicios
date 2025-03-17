package com.transaccion.cliente.kafka;

import com.transaccion.cliente.dto.ClienteResponseDto;
import com.transaccion.cliente.dto.ClienteValidationResponseDto;
import com.transaccion.cliente.exception.EntityNotFoundException;
import com.transaccion.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final String RESPONSE_TOPIC = "cliente-validation-response";

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;



    @KafkaListener(topics = "cliente-validation-request", groupId = "msKafBr")
    public void listenForValidationRequest(ClienteValidationResponseDto message) {
        ClienteResponseDto clienteResponseDto;
        try {
            clienteResponseDto = clienteService.buscarPorId(message.getClienteId());
            kafkaTemplate.send(RESPONSE_TOPIC, new ClienteValidationResponseDto(message.getRequestId(), clienteResponseDto, message.getClienteId(), null));
        } catch (EntityNotFoundException e) {
            kafkaTemplate.send(RESPONSE_TOPIC, new ClienteValidationResponseDto(message.getRequestId(), null, message.getClienteId(), e.getMessage()));
        }
    }

}
