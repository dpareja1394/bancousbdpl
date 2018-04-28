package co.edu.usbcali.bancoweb.modelo.control;

import co.edu.usbcali.bancoweb.dataaccess.dao.*;
import co.edu.usbcali.bancoweb.dto.mapper.ITransaccionMapper;
import co.edu.usbcali.bancoweb.exceptions.*;
import co.edu.usbcali.bancoweb.modelo.*;
import co.edu.usbcali.bancoweb.modelo.dto.RespuestaTransaccionDTO;
import co.edu.usbcali.bancoweb.modelo.dto.TransaccionDTO;
import co.edu.usbcali.bancoweb.utilities.Constantes;
import co.edu.usbcali.bancoweb.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("TransaccionLogic")
public class TransaccionLogic implements ITransaccionLogic {
    private static final Logger log = LoggerFactory.getLogger(TransaccionLogic.class);

    /**
     * DAO injected by Spring that manages Transaccion entities
     *
     */
    @Autowired
    private ITransaccionDAO transaccionDAO;
    @Autowired
    private ITransaccionMapper transaccionMapper;
    @Autowired
    private Validator validator;
    
    /**
    * Logic injected by Spring that manages Cuenta entities
    *
    */
    @Autowired
    ICuentaLogic logicCuenta1;

    /**
    * Logic injected by Spring that manages TipoTransaccion entities
    *
    */
    @Autowired
    ITipoTransaccionLogic logicTipoTransaccion2;

    /**
    * Logic injected by Spring that manages Usuario entities
    *
    */
    @Autowired
    IUsuarioLogic logicUsuario3;
    
    @Autowired
    IClienteLogic clienteLogic;

    public void validateTransaccion(Transaccion transaccion)
        throws Exception {
        try {
            Set<ConstraintViolation<Transaccion>> constraintViolations = validator.validate(transaccion);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<Transaccion> constraintViolation : constraintViolations) {
                    strMessage.append(constraintViolation.getPropertyPath()
                                                         .toString());
                    strMessage.append(" - ");
                    strMessage.append(constraintViolation.getMessage());
                    strMessage.append(". \n");
                }

                throw new Exception(strMessage.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Transaccion> getTransaccion() throws Exception {
        log.debug("finding all Transaccion instances");

        List<Transaccion> list = new ArrayList<Transaccion>();

        try {
            list = transaccionDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Transaccion failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Transaccion");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveTransaccion(Transaccion entity) throws Exception {
        log.debug("saving Transaccion instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Transaccion");
            }

            validateTransaccion(entity);

            if (getTransaccion(entity.getTranId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            transaccionDAO.save(entity);

            log.debug("save Transaccion successful");
        } catch (Exception e) {
            log.error("save Transaccion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteTransaccion(Transaccion entity) throws Exception {
        log.debug("deleting Transaccion instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Transaccion");
        }

        if (entity.getTranId() == null) {
            throw new ZMessManager().new EmptyFieldException("tranId");
        }

        try {
            transaccionDAO.delete(entity);

            log.debug("delete Transaccion successful");
        } catch (Exception e) {
            log.error("delete Transaccion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateTransaccion(Transaccion entity) throws Exception {
        log.debug("updating Transaccion instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Transaccion");
            }

            validateTransaccion(entity);

            transaccionDAO.update(entity);

            log.debug("update Transaccion successful");
        } catch (Exception e) {
            log.error("update Transaccion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<TransaccionDTO> getDataTransaccion() throws Exception {
        try {
            List<Transaccion> transaccion = transaccionDAO.findAll();

            List<TransaccionDTO> transaccionDTO = new ArrayList<TransaccionDTO>();

            for (Transaccion transaccionTmp : transaccion) {
                TransaccionDTO transaccionDTO2 = transaccionMapper.transaccionToTransaccionDTO(transaccionTmp);
                transaccionDTO.add(transaccionDTO2);
            }

            return transaccionDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Transaccion getTransaccion(Long tranId) throws Exception {
        log.debug("getting Transaccion instance");

        Transaccion entity = null;

        try {
            entity = transaccionDAO.findById(tranId);
        } catch (Exception e) {
            log.error("get Transaccion failed", e);
            throw new ZMessManager().new FindingException("Transaccion");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Transaccion> findPageTransaccion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Transaccion> entity = null;

        try {
            entity = transaccionDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Transaccion Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberTransaccion() throws Exception {
        Long entity = null;

        try {
            entity = transaccionDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Transaccion Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Transaccion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Transaccion> list = new ArrayList<Transaccion>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between " + value +
                        " and " + value2 + ")")
                        : (tempWhere + " AND (model." + variable + " between " +
                        value + " and " + value2 + ")");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = transaccionDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

    /**
     * 
     * @author Daniel Pareja Londoño
     * @version 24 abr. /2018 21:47
     *
     * (non-Javadoc)
     * @see co.edu.usbcali.bancoweb.modelo.control.ITransaccionLogic#consignar(java.lang.String, java.lang.String, java.lang.Double)
     *
     */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RespuestaTransaccionDTO consignar(String numeroCuenta, String usuLogin, Double valor, Long idCliente){
		RespuestaTransaccionDTO respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_EXITOSO, Constantes.ESTADO_TRANSACCION_EXITOSO);;
		
		try {
			if(numeroCuenta == null || numeroCuenta.trim().equals("")) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "Debe ingresar la Cuenta para realizar la consignación");
				return respuesta;
			}
			
			if(numeroCuenta.trim().length()!=16) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "El número de cuenta debe tener 16 dígitos");
				return respuesta;
			}
			
			String nuevoNumeroCuenta = "";
			for(int i = 0; i<numeroCuenta.trim().length(); i+=4) {
				if(i==0) {
					nuevoNumeroCuenta = numeroCuenta.substring(i, i+4);
				}else {
					nuevoNumeroCuenta += "-"+numeroCuenta.substring(i, i+4);
				}
			}
			log.info(nuevoNumeroCuenta);
			
			if(usuLogin == null || usuLogin.trim().equals("")) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "Usuario no autenticado");
				return respuesta;
			}
			if(valor == null || valor<=0) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "Ingrese valor a consignar");
				return respuesta;
			}
			
			Cuenta cuenta = logicCuenta1.getCuenta(nuevoNumeroCuenta);
			if(cuenta == null) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "No se ha encontrado la cuenta "+numeroCuenta);
				return respuesta;
			}
			
			Cliente cliente = clienteLogic.getCliente(idCliente);
			if(cliente==null) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "No existe un cliente con Id "+idCliente);
				return respuesta;
			}
			
			Cliente clienteCuenta = clienteLogic.getCliente(cuenta.getCliente().getClieId());
			if(!clienteCuenta.getClieId().equals(cliente.getClieId())) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "La cuenta "+numeroCuenta+" no pertenece al cliente "+idCliente);
				return respuesta;
			}
			
			
			Usuario usuario = logicUsuario3.getUsuario(usuLogin);
			if(usuario == null) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "No se ha encontrado el usuario "+usuLogin);
				return respuesta;
			}
			
			Object[] variables = {"nombre", true, Constantes.CONSIGNACION, "="};
			List<TipoTransaccion> listaTipoTransaccion = logicTipoTransaccion2.findByCriteria(variables, null, null);
			TipoTransaccion tipoTransaccion = listaTipoTransaccion.isEmpty()?null:
				listaTipoTransaccion.get(0);
			
			if(tipoTransaccion == null) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "No se ha encontrado el tipo de Transaccion "+Constantes.CONSIGNACION);
				return respuesta;
			}
			
			Double saldoActualCuenta = cuenta.getSaldo();
			Double saldoPosteriorTransaccion = saldoActualCuenta+valor;
			
			cuenta.setSaldo(saldoPosteriorTransaccion);
			
			logicCuenta1.updateCuenta(cuenta);
			
			Transaccion consignacion = new Transaccion();
			consignacion.setCuenta(cuenta);
			consignacion.setFecha(new Date());
			consignacion.setTipoTransaccion(tipoTransaccion);
			consignacion.setUsuario(usuario);
			consignacion.setValor(valor);
			
			transaccionDAO.save(consignacion);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return respuesta;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)	
	public RespuestaTransaccionDTO retirar(String numeroCuenta, String usuLogin, Double valor, Long idCliente, String claveCuenta) {
		RespuestaTransaccionDTO respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_EXITOSO, Constantes.ESTADO_TRANSACCION_EXITOSO);;
		
		try {
			if(numeroCuenta == null || numeroCuenta.trim().equals("")) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "Debe ingresar la Cuenta para realizar la consignación");
				return respuesta;
			}
			
			if(numeroCuenta.trim().length()!=16) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "El número de cuenta debe tener 16 dígitos");
				return respuesta;
			}
			
			String nuevoNumeroCuenta = "";
			for(int i = 0; i<numeroCuenta.trim().length(); i+=4) {
				if(i==0) {
					nuevoNumeroCuenta = numeroCuenta.substring(i, i+4);
				}else {
					nuevoNumeroCuenta += "-"+numeroCuenta.substring(i, i+4);
				}
			}
			log.info(nuevoNumeroCuenta);
			
			if(usuLogin == null || usuLogin.trim().equals("")) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "Usuario no autenticado");
				return respuesta;
			}
			if(valor == null || valor<=0) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "Ingrese valor a consignar");
				return respuesta;
			}
			
			Cuenta cuenta = logicCuenta1.getCuenta(nuevoNumeroCuenta);
			if(cuenta == null) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "No se ha encontrado la cuenta "+numeroCuenta);
				return respuesta;
			}
			
			Cliente cliente = clienteLogic.getCliente(idCliente);
			if(cliente==null) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "No existe un cliente con Id "+idCliente);
				return respuesta;
			}
			
			Cliente clienteCuenta = clienteLogic.getCliente(cuenta.getCliente().getClieId());
			if(!clienteCuenta.getClieId().equals(cliente.getClieId())) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "La cuenta "+numeroCuenta+" no pertenece al cliente "+idCliente);
				return respuesta;
			}
			
			if(!cuenta.getClave().trim().equals(claveCuenta)) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "Clave errónea");
				return respuesta;
			}
			
			
			Usuario usuario = logicUsuario3.getUsuario(usuLogin);
			if(usuario == null) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "No se ha encontrado el usuario "+usuLogin);
				return respuesta;
			}
			
			Object[] variables = {"nombre", true, Constantes.RETIRO, "="};
			List<TipoTransaccion> listaTipoTransaccion = logicTipoTransaccion2.findByCriteria(variables, null, null);
			TipoTransaccion tipoTransaccion = listaTipoTransaccion.isEmpty()?null:
				listaTipoTransaccion.get(0);
			
			if(tipoTransaccion == null) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "No se ha encontrado el tipo de Transaccion "+Constantes.RETIRO);
				return respuesta;
			}
			
			Double saldoActualCuenta = cuenta.getSaldo();
			Double saldoPosteriorTransaccion = saldoActualCuenta-valor;
			
			if(saldoPosteriorTransaccion < 0) {
				respuesta = new RespuestaTransaccionDTO(Constantes.CODIGO_TRANSACCION_FALLIDO, "El monto a retirar excede el saldo de la cuenta");
				return respuesta;
			}
			
			cuenta.setSaldo(saldoPosteriorTransaccion);
			
			logicCuenta1.updateCuenta(cuenta);
			
			Transaccion consignacion = new Transaccion();
			consignacion.setCuenta(cuenta);
			consignacion.setFecha(new Date());
			consignacion.setTipoTransaccion(tipoTransaccion);
			consignacion.setUsuario(usuario);
			consignacion.setValor(valor);
			
			transaccionDAO.save(consignacion);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return respuesta;
	}
}
