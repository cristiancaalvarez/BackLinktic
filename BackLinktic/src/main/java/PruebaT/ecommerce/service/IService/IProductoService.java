package PruebaT.ecommerce.service.IService;

import PruebaT.ecommerce.dto.ProductosDTO;

import java.util.List;

public interface IProductoService {
    public List<ProductosDTO> listarProductos();
    public List<ProductosDTO> findByNombreContainingIgnoreCase(String producto);
    public ProductosDTO actualizarProducto(ProductosDTO producto);
    public ProductosDTO guardarProducto(ProductosDTO producto);
    void eliminarProducto(Integer id_producto);
}
