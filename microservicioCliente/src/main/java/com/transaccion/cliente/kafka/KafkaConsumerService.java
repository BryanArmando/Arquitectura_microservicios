package com.transaccion.cliente.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaccion.cliente.dto.ClienteResponseDto;
import com.transaccion.cliente.dto.ClienteValidationRequestDto;
import com.transaccion.cliente.dto.ClienteValidationResponseDto;
import com.transaccion.cliente.exception.EntityNotFoundException;
import com.transaccion.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumerService {

    private static final String RESPONSE_TOPIC = "cliente-validation-response";

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private ObjectMapper objectMapper;

    public KafkaConsumerService(KafkaTemplate<String, Object> kafkaTemplate, ClienteService clienteService, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.clienteService = clienteService;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "cliente-validation-request", groupId = "your-group-id")
    public void listenForValidationRequest(ClienteValidationResponseDto message) {
//        ClienteValidationRequestDto request = objectMapper.convertValue(message, ClienteValidationRequestDto.class);
        ClienteResponseDto clienteResponseDto;
        try {
            clienteResponseDto = clienteService.buscarPorId(message.getClienteId());
//            Map<String, Object> messageResponse = new HashMap<>();
//            messageResponse.put("requestId", request.getRequestId());
//            // Add other fields as necessary
//            messageResponse.put("clienteResponseDto", clienteResponseDto);
//            messageResponse.put("errorMessage", null);
            kafkaTemplate.send(RESPONSE_TOPIC, new ClienteValidationResponseDto(message.getRequestId(), clienteResponseDto, message.getClienteId(), null));
        } catch (EntityNotFoundException e) {
//            Map<String, Object> messageResponse = new HashMap<>();
//            messageResponse.put("requestId", request.getRequestId());
//            messageResponse.put("clienteResponseDto", null);
//            messageResponse.put("errorMessage", e.getMessage());
            kafkaTemplate.send(RESPONSE_TOPIC, new ClienteValidationResponseDto(message.getRequestId(), null, message.getClienteId(), e.getMessage()));
        }
    }

}
