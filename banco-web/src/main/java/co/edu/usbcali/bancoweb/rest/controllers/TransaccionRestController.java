package co.edu.usbcali.bancoweb.rest.controllers;

import co.edu.usbcali.bancoweb.dto.mapper.ITransaccionMapper;
import co.edu.usbcali.bancoweb.modelo.*;
import co.edu.usbcali.bancoweb.modelo.dto.RespuestaTransaccionDTO;
import co.edu.usbcali.bancoweb.modelo.dto.TransaccionDTO;
import co.edu.usbcali.bancoweb.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.bancoweb.utilities.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/transaccion")
public class TransaccionRestController {
    private static final Logger log = LoggerFactory.getLogger(TransaccionRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ITransaccionMapper transaccionMapper;

    @PostMapping(value = "/saveTransaccion")
    public void saveTransaccion(@RequestBody
    TransaccionDTO transaccionDTO) throws Exception {
        try {
            Transaccion transaccion = transaccionMapper.transaccionDTOToTransaccion(transaccionDTO);

            businessDelegatorView.saveTransaccion(transaccion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteTransaccion/{tranId}")
    public void deleteTransaccion(@PathVariable("tranId")
    Long tranId) throws Exception {
        try {
            Transaccion transaccion = businessDelegatorView.getTransaccion(tranId);

            businessDelegatorView.deleteTransaccion(transaccion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateTransaccion/")
    public void updateTransaccion(@RequestBody
    TransaccionDTO transaccionDTO) throws Exception {
        try {
            Transaccion transaccion = transaccionMapper.transaccionDTOToTransaccion(transaccionDTO);

            businessDelegatorView.updateTransaccion(transaccion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataTransaccion")
    public List<TransaccionDTO> getDataTransaccion() throws Exception {
        try {
            return businessDelegatorView.getDataTransaccion();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getTransaccion/{tranId}")
    public TransaccionDTO getTransaccion(@PathVariable("tranId")
    Long tranId) throws Exception {
        try {
            Transaccion transaccion = businessDelegatorView.getTransaccion(tranId);

            return transaccionMapper.transaccionToTransaccionDTO(transaccion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
    
    @CrossOrigin
    @PostMapping(value = "/consignar/{numeroCuenta}/{usuLogin}/{valor}/{idCliente}")
    public RespuestaTransaccionDTO consignar(@PathVariable("numeroCuenta") String numeroCuenta,
    		@PathVariable("usuLogin") String usuLogin, 
    		@PathVariable("valor") Double valor, @PathVariable("idCliente") Long idCliente) throws Exception {
    		RespuestaTransaccionDTO respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, Constantes.ESTADO_TRANSACCION_FALLIDO);
    		try {
    			respuesta = businessDelegatorView.consignar(numeroCuenta, usuLogin, valor, idCliente);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    		return respuesta;
    }
    
    @CrossOrigin
    @PostMapping(value = "/retirar/{numeroCuenta}/{usuLogin}/{valor}/{idCliente}/{claveCuenta}")
    public RespuestaTransaccionDTO retirar(@PathVariable("numeroCuenta") String numeroCuenta,
    		@PathVariable("usuLogin") String usuLogin, 
    		@PathVariable("valor") Double valor,
    		@PathVariable("idCliente") Long idCliente,
    		@PathVariable("claveCuenta") String claveCuenta) throws Exception {
    	    RespuestaTransaccionDTO respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, Constantes.ESTADO_TRANSACCION_FALLIDO);
    		try {
            respuesta = businessDelegatorView.retirar(numeroCuenta, usuLogin, valor, idCliente, claveCuenta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return respuesta;
    }
}
