package co.edu.usbcali.bancoweb.dataaccess.dao;

import co.edu.usbcali.bancoweb.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.bancoweb.modelo.Usuario;
import co.edu.usbcali.bancoweb.modelo.dto.UsuarioDTO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Example;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * Usuario entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Usuario
 */
@Scope("singleton")
@Repository("UsuarioDAO")
public class UsuarioDAO extends HibernateDaoImpl<Usuario, String>
    implements IUsuarioDAO {
    private static final Logger log = LoggerFactory.getLogger(UsuarioDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IUsuarioDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IUsuarioDAO) ctx.getBean("UsuarioDAO");
    }

    /**
     * 
     * @author Daniel Pareja Londoño
     * @version 24 abr. /2018 21:05
     *
     * (non-Javadoc)
     * @see co.edu.usbcali.bancoweb.dataaccess.dao.IUsuarioDAO#consultarUsuarioPorLogin(java.lang.String)
     *
     */
	@Override
	public UsuarioDTO consultarUsuarioPorLogin(String login) throws Exception {
		UsuarioDTO usuario = null;
		try {
			Query query = getSession().getNamedQuery("consultar_usuario_por_login");
			query.setParameter("pUsu", login);
			query.setResultTransformer(Transformers.aliasToBean(UsuarioDTO.class));
			List<UsuarioDTO> usuarios = query.list();
			
			usuario = usuarios.isEmpty()?null:usuarios.get(0);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return usuario;
	}
}
