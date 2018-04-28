package co.edu.usbcali.bancoweb.modelo.control;

import co.edu.usbcali.bancoweb.modelo.Usuario;
import co.edu.usbcali.bancoweb.modelo.dto.UsuarioDTO;

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
public interface IUsuarioLogic {
    public List<Usuario> getUsuario() throws Exception;

    /**
         * Save an new Usuario entity
         */
    public void saveUsuario(Usuario entity) throws Exception;

    /**
         * Delete an existing Usuario entity
         *
         */
    public void deleteUsuario(Usuario entity) throws Exception;

    /**
        * Update an existing Usuario entity
        *
        */
    public void updateUsuario(Usuario entity) throws Exception;

    /**
         * Load an existing Usuario entity
         *
         */
    public Usuario getUsuario(String usuUsuario) throws Exception;

    public List<Usuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;

    public void validateUsuario(Usuario usuario) throws Exception;
    
    /**
     * 
     *
     *
     * @author Daniel Pareja Londoño
     * @version 24 abr. /2018 21:06
     * @since 1.8
     * @param login
     * @return
     * @throws Exception
     * @return <b>{@code }</b> Start here...
     *
     */
    public UsuarioDTO autenticarUsuario(String login, String clave) throws Exception;
}
