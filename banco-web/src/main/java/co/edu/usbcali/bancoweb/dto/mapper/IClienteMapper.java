package co.edu.usbcali.bancoweb.dto.mapper;

import co.edu.usbcali.bancoweb.modelo.Cliente;
import co.edu.usbcali.bancoweb.modelo.dto.ClienteDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IClienteMapper {
    public ClienteDTO clienteToClienteDTO(Cliente cliente)
        throws Exception;

    public Cliente clienteDTOToCliente(ClienteDTO clienteDTO)
        throws Exception;

    public List<ClienteDTO> listClienteToListClienteDTO(List<Cliente> clientes)
        throws Exception;

    public List<Cliente> listClienteDTOToListCliente(
        List<ClienteDTO> clienteDTOs) throws Exception;
}
