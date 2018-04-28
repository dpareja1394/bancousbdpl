package co.edu.usbcali.bancoweb.modelo.control;

import co.edu.usbcali.bancoweb.modelo.Transaccion;
import co.edu.usbcali.bancoweb.modelo.dto.RespuestaTransaccionDTO;
import co.edu.usbcali.bancoweb.modelo.dto.TransaccionDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ITransaccionLogic {
    public List<Transaccion> getTransaccion() throws Exception;

    /**
         * Save an new Transaccion entity
         */
    public void saveTransaccion(Transaccion entity) throws Exception;

    /**
         * Delete an existing Transaccion entity
         *
         */
    public void deleteTransaccion(Transaccion entity) throws Exception;

    /**
        * Update an existing Transaccion entity
        *
        */
    public void updateTransaccion(Transaccion entity) throws Exception;

    /**
         * Load an existing Transaccion entity
         *
         */
    public Transaccion getTransaccion(Long tranId) throws Exception;

    public List<Transaccion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Transaccion> findPageTransaccion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTransaccion() throws Exception;

    public List<TransaccionDTO> getDataTransaccion() throws Exception;

    public void validateTransaccion(Transaccion transaccion)
        throws Exception;
    
    /**
     * 
     *
     *
     * @author Daniel Pareja Londoño
     * @version 27 abr. /2018 23:22
     * @since 1.8
     * @param numeroCuenta
     * @param usuLogin
     * @param valor
     * @return
     * @return <b>{@code }</b> Start here...
     *
     */
    public RespuestaTransaccionDTO consignar(String numeroCuenta, String usuLogin, Double valor, Long idCliente);
    
    /**
     * 
     *
     *
     * @author Daniel Pareja Londoño
     * @version 28 abr. /2018 02:01
     * @since 1.8
     * @param numeroCuenta
     * @param usuLogin
     * @param valor
     * @return
     * @throws Exception
     * @return <b>{@code }</b> Start here...
     *
     */
    public RespuestaTransaccionDTO retirar(String numeroCuenta, String usuLogin, Double valor, Long idCliente, String claveCuenta);
}
