package com.transaccion.cuenta.service.reportes;

import com.transaccion.cuenta.dto.cliente.ClienteValidationRequestDto;
import com.transaccion.cuenta.dto.cliente.ClienteValidationResponseDto;
import com.transaccion.cuenta.dto.reportes.EstadoCuentaCabeceraReporteDto;
import com.transaccion.cuenta.entity.Cuenta;
import com.transaccion.cuenta.exception.EntityNotFoundException;
import com.transaccion.cuenta.kafka.KafkaConsumerService;
import com.transaccion.cuenta.mapper.CuentaMapper;
import com.transaccion.cuenta.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Clase service para generacion de estado de cuenta
 * @author BryanArmando
 */
@Service
public class EstadoCuentaService {


    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    @Autowired
    private CuentaRepository cuentaRepository;


    @Autowired
    private CuentaMapper cuentaMapper;

    /**
     * Obtiene reporte para estado de cuenta en rango de fechas
     * @param clienteId id de cliente
     * @param fechaInicio fecha inicial para filtro
     * @param fechaFin fecha final para filtro
     * @return reporte
     */
    public EstadoCuentaCabeceraReporteDto obtenerReporteEstadoCuenta(Integer clienteId, LocalDate fechaInicio, LocalDate fechaFin){
        if (fechaFin.isBefore(fechaInicio)){
            throw new EntityNotFoundException("Rango de fechas ingresado es incorreco");
        }
        String requestId = UUID.randomUUID().toString();
        ClienteValidationRequestDto validationRequest = new ClienteValidationRequestDto(requestId, clienteId);
        CompletableFuture<ClienteValidationResponseDto> futureResponse = kafkaConsumerService.sendValidationRequest(validationRequest);

        ClienteValidationResponseDto validationResponse;
        try {
            validationResponse = futureResponse.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new EntityNotFoundException("Error al validar el cliente");
        }
        if (validationResponse.getErrorMessage() != null) {
            throw new EntityNotFoundException(validationResponse.getErrorMessage());
        }

        List<Cuenta> cuentasList = cuentaRepository.findByClienteIdAndMovimientosFechaCreacionBetween(clienteId,
                Timestamp.valueOf(fechaInicio.atStartOfDay()), Timestamp.valueOf(fechaFin.atStartOfDay()));
        EstadoCuentaCabeceraReporteDto estadoCuentaCabeceraReporteDto = new EstadoCuentaCabeceraReporteDto(
                validationResponse.getClienteResponseDto().getNombre(),
                validationResponse.getClienteResponseDto().getIdentificacion(),
                fechaInicio,
                fechaFin,null,null
        ) ;
        if (cuentasList.isEmpty()){
            estadoCuentaCabeceraReporteDto.setObservacion("Cliente no dispone de cuentas asociadas");
        } else {
            estadoCuentaCabeceraReporteDto.setEstadoCuentaTipoCuentaDtoList(cuentaMapper.entityToEstadoCuenta(cuentasList));
        }

        return estadoCuentaCabeceraReporteDto;
    }
}
