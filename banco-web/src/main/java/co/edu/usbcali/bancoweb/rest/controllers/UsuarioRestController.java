package co.edu.usbcali.bancoweb.rest.controllers;

import co.edu.usbcali.bancoweb.dto.mapper.IUsuarioMapper;
import co.edu.usbcali.bancoweb.modelo.*;
import co.edu.usbcali.bancoweb.modelo.dto.UsuarioDTO;
import co.edu.usbcali.bancoweb.presentation.businessDelegate.IBusinessDelegatorView;

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
@RequestMapping("/usuario")
public class UsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IUsuarioMapper usuarioMapper;

    @PostMapping(value = "/saveUsuario")
    public void saveUsuario(@RequestBody
    UsuarioDTO usuarioDTO) throws Exception {
        try {
            Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);

            businessDelegatorView.saveUsuario(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteUsuario/{usuUsuario}")
    public void deleteUsuario(@PathVariable("usuUsuario")
    String usuUsuario) throws Exception {
        try {
            Usuario usuario = businessDelegatorView.getUsuario(usuUsuario);

            businessDelegatorView.deleteUsuario(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateUsuario/")
    public void updateUsuario(@RequestBody
    UsuarioDTO usuarioDTO) throws Exception {
        try {
            Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);

            businessDelegatorView.updateUsuario(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataUsuario")
    public List<UsuarioDTO> getDataUsuario() throws Exception {
        try {
            return businessDelegatorView.getDataUsuario();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getUsuario/{usuUsuario}")
    public UsuarioDTO getUsuario(@PathVariable("usuUsuario")
    String usuUsuario) throws Exception {
        try {
            Usuario usuario = businessDelegatorView.getUsuario(usuUsuario);

            return usuarioMapper.usuarioToUsuarioDTO(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
    
    
    @CrossOrigin
    @GetMapping(value = "/autenticarUsuario/{usu}/{pwd}")
    public UsuarioDTO autenticarUsuario(@PathVariable("usu") String usu, @PathVariable("pwd") String pwd) throws Exception {
    		UsuarioDTO usuario = new UsuarioDTO();     
    		try {
    			usuario= businessDelegatorView.autenticarUsuario(usu, pwd);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return usuario;
    }
    
}
