package co.edu.usbcali.bancoweb.dataaccess.dao;

import co.edu.usbcali.bancoweb.dataaccess.api.Dao;
import co.edu.usbcali.bancoweb.modelo.Usuario;
import co.edu.usbcali.bancoweb.modelo.dto.UsuarioDTO;


/**
* Interface for   UsuarioDAO.
*
*/
public interface IUsuarioDAO extends Dao<Usuario, String> {
	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version 24 abr. /2018 21:04
	 * @since 1.8
	 * @param login
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public UsuarioDTO consultarUsuarioPorLogin(String login) throws Exception;
}
