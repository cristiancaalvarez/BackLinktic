package PruebaT.ecommerce.service.IService;

import PruebaT.ecommerce.dto.DetalleOrdenDTO;
import PruebaT.ecommerce.dto.OrdenDTO;

import java.util.List;

public interface IOrdenService {

    public List<OrdenDTO> listarOrden();
    public OrdenDTO buscarOrdenId(Integer id_orden);
    public OrdenDTO crearOrden(List<DetalleOrdenDTO> detalleOrden);
    void eliminarOrden(Integer id_orden);
}
