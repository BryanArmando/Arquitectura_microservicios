package com.transaccion.cuenta.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaccion.cuenta.dto.Cliente.ClienteValidationRequestDto;
import com.transaccion.cuenta.dto.Cliente.ClienteValidationResponseDto;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Log
public class KafkaConsumerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    private final ConcurrentHashMap<String, CompletableFuture<ClienteValidationResponseDto>> pendingRequests = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, CompletableFuture<ClienteValidationResponseDto>> pendingRequests1 = new ConcurrentHashMap<>();

    private ObjectMapper objectMapper;

    @KafkaListener(topics = "cliente-validation-response", groupId = "your-group-id")
    public void listenForValidationResponse(ClienteValidationResponseDto response) {
        CompletableFuture<ClienteValidationResponseDto> future = pendingRequests.remove(response.getRequestId());
        /*ClienteValidationResponseDto responseDto;
        responseDto = objectMapper.convertValue(response, ClienteValidationResponseDto.class);

        CompletableFuture<Map<String, Object>> future = pendingRequests.remove(responseDto.getRequestId());*/
        log.info("respuesta kafka : ");
        if (future != null) {
            log.info("respuesta kafka en if: " + response);
            future.complete(response);
        }
    }

    public CompletableFuture<ClienteValidationResponseDto> sendValidationRequest(ClienteValidationRequestDto request) {
        CompletableFuture<ClienteValidationResponseDto> future = new CompletableFuture<>();
        pendingRequests.put(request.getRequestId(), future);
//        Map<String, Object> message = new HashMap<>();
//        message.put("requestId", request.getRequestId());
//        message.put("clienteId", request.getClienteId());
        // Send the request to Kafka
        kafkaTemplate.send("cliente-validation-request", new ClienteValidationResponseDto(request.getRequestId(), null,  request.getClienteId(), null));
        return future;
    }

}
