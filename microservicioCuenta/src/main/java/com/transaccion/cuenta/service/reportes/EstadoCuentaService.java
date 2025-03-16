package com.transaccion.cuenta.service.reportes;

import com.transaccion.cuenta.dto.Cliente.ClienteValidationRequestDto;
import com.transaccion.cuenta.dto.Cliente.ClienteValidationResponseDto;
import com.transaccion.cuenta.dto.reportes.EstadoCuentaCabeceraReporteDto;
import com.transaccion.cuenta.entity.Cuenta;
import com.transaccion.cuenta.exception.EntityNotFoundException;
import com.transaccion.cuenta.kafka.KafkaConsumerService;
import com.transaccion.cuenta.mapper.CuentaMapper;
import com.transaccion.cuenta.mapper.MovimientoMapper;
import com.transaccion.cuenta.repository.CuentaRepository;
import com.transaccion.cuenta.repository.MovimientoRepository;
import com.transaccion.cuenta.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class EstadoCuentaService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Autowired
    private CuentaMapper cuentaMapper;

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
            estadoCuentaCabeceraReporteDto.setMensajeError("Cliente no dispone de cuentas asociadas");
        } else {
            estadoCuentaCabeceraReporteDto.setEstadoCuentaTipoCuentaDtoList(cuentaMapper.entityToEstadoCuenta(cuentasList));
        }

        return estadoCuentaCabeceraReporteDto;
    }
}
