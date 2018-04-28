package co.edu.usbcali.bancoweb.dto.mapper;

import co.edu.usbcali.bancoweb.modelo.TipoTransaccion;
import co.edu.usbcali.bancoweb.modelo.dto.TipoTransaccionDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ITipoTransaccionMapper {
    public TipoTransaccionDTO tipoTransaccionToTipoTransaccionDTO(
        TipoTransaccion tipoTransaccion) throws Exception;

    public TipoTransaccion tipoTransaccionDTOToTipoTransaccion(
        TipoTransaccionDTO tipoTransaccionDTO) throws Exception;

    public List<TipoTransaccionDTO> listTipoTransaccionToListTipoTransaccionDTO(
        List<TipoTransaccion> tipoTransaccions) throws Exception;

    public List<TipoTransaccion> listTipoTransaccionDTOToListTipoTransaccion(
        List<TipoTransaccionDTO> tipoTransaccionDTOs) throws Exception;
}
