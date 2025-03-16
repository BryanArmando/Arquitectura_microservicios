package com.transaccion.cuenta.kafka;

import com.transaccion.cuenta.dto.Cliente.ClienteValidationRequestDto;
import com.transaccion.cuenta.dto.Cliente.ClienteValidationResponseDto;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Log
public class KafkaConsumerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    private final ConcurrentHashMap<String, CompletableFuture<ClienteValidationResponseDto>> pendingRequests = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, CompletableFuture<ClienteValidationResponseDto>> pendingRequests1 = new ConcurrentHashMap<>();


    @KafkaListener(topics = "cliente-validation-response", groupId = "your-group-id")
    public void listenForValidationResponse(ClienteValidationResponseDto response) {
        CompletableFuture<ClienteValidationResponseDto> future = pendingRequests.remove(response.getRequestId());
        log.info("respuesta kafka : ");
        if (future != null) {
            log.info("respuesta kafka en if: " + response);
            future.complete(response);
        }
    }

    public CompletableFuture<ClienteValidationResponseDto> sendValidationRequest(ClienteValidationRequestDto request) {
        CompletableFuture<ClienteValidationResponseDto> future = new CompletableFuture<>();
        pendingRequests.put(request.getRequestId(), future);
        kafkaTemplate.send("cliente-validation-request", new ClienteValidationResponseDto(request.getRequestId(), null,  request.getClienteId(), null));
        return future;
    }

}
