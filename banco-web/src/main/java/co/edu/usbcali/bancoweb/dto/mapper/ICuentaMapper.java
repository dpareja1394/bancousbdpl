package co.edu.usbcali.bancoweb.dto.mapper;

import co.edu.usbcali.bancoweb.modelo.Cuenta;
import co.edu.usbcali.bancoweb.modelo.dto.CuentaDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ICuentaMapper {
    public CuentaDTO cuentaToCuentaDTO(Cuenta cuenta) throws Exception;

    public Cuenta cuentaDTOToCuenta(CuentaDTO cuentaDTO)
        throws Exception;

    public List<CuentaDTO> listCuentaToListCuentaDTO(List<Cuenta> cuentas)
        throws Exception;

    public List<Cuenta> listCuentaDTOToListCuenta(List<CuentaDTO> cuentaDTOs)
        throws Exception;
}
