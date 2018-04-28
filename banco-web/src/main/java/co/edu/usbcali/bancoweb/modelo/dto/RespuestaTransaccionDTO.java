package co.edu.usbcali.bancoweb.modelo.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RespuestaTransaccionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RespuestaTransaccionDTO.class);
    
    private int codigo;
    private String respuesta;
	public RespuestaTransaccionDTO(int codigo, String respuesta) {
		super();
		this.codigo = codigo;
		this.respuesta = respuesta;
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version 27 abr. /2018 23:14
	 * @since 1.8
	 * @return La entidad codigo
	 *
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 *
	 * @param codigo El/La codigo a setear
	 * @author Daniel Pareja Londoño
	 * @version 27 abr. /2018 23:14
	 * @since 1.8
	 *
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version 27 abr. /2018 23:14
	 * @since 1.8
	 * @return La entidad respuesta
	 *
	 */
	public String getRespuesta() {
		return respuesta;
	}
	/**
	 *
	 * @param respuesta El/La respuesta a setear
	 * @author Daniel Pareja Londoño
	 * @version 27 abr. /2018 23:14
	 * @since 1.8
	 *
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
