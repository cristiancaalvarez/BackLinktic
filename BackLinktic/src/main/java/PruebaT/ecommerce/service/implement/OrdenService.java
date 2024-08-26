package PruebaT.ecommerce.service.implement;


import PruebaT.ecommerce.dto.DetalleOrdenDTO;
import PruebaT.ecommerce.dto.OrdenDTO;
import PruebaT.ecommerce.exception.ProductoNoEncontradoException;
import PruebaT.ecommerce.exception.RecursoNoEncontradoException;
import PruebaT.ecommerce.exception.StockInsuficienteException;
import PruebaT.ecommerce.model.DetalleOrden;
import PruebaT.ecommerce.model.Ordenes;
import PruebaT.ecommerce.model.Productos;
import PruebaT.ecommerce.repository.DetalleOrdenesRepository;
import PruebaT.ecommerce.repository.OrdenesRepository;
import PruebaT.ecommerce.repository.ProductosRepository;
import PruebaT.ecommerce.service.IService.IOrdenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenService implements IOrdenService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdenesRepository ordenesRepository;

    @Autowired
    private DetalleOrdenesRepository detalleOrdenesRepository;

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public List<OrdenDTO> listarOrden() {
        var ordenes = this.ordenesRepository.findAll();
        return ordenes.stream()
                .map(orden -> this.modelMapper.map(orden , OrdenDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrdenDTO buscarOrdenId(Integer pedidoId) {
        var orden =  this.ordenesRepository.findById(pedidoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("orden no encontrado"));
        return this.modelMapper.map(orden, OrdenDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrdenDTO crearOrden(List<DetalleOrdenDTO> detalleOrden) {
        try {
            double total = 0.0;
            for (DetalleOrdenDTO detalle : detalleOrden) {
                int productoId = detalle.getProducto().getId();
                Productos producto = productosRepository.findById(productoId)
                        .orElseThrow(() -> new ProductoNoEncontradoException("No existe el producto con ID: " + productoId));

                int cantidad = detalle.getCantidad();
                if (producto.getStock() < cantidad) {
                    throw new StockInsuficienteException("No hay suficiente stock para el producto: " + producto.getNombre());
                }

                double precioUnitario = producto.getPrecio();
                double subtotal = cantidad * precioUnitario;
                total += subtotal;

                producto.setStock(producto.getStock() - cantidad);
                productosRepository.save(producto);

                detalle.setPrecioUnidad(precioUnitario);
                detalle.setSubtotal(subtotal);
            }

            Ordenes ordenes = new Ordenes();
            ordenes.setFecha(new Date());
            ordenes.setTotal(total);

            // Guardar la orden en la base de datos
            ordenes = ordenesRepository.save(ordenes);

            for (DetalleOrdenDTO detalleOrdenDTO : detalleOrden) {
                DetalleOrden detalleOrden1 = modelMapper.map(detalleOrdenDTO, DetalleOrden.class);
                detalleOrden1.setOrden(ordenes);
                detalleOrdenesRepository.save(detalleOrden1);
            }

            return modelMapper.map(ordenes, OrdenDTO.class);
        } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear la orden: " + e.getMessage(), e);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void eliminarOrden(Integer id_orden) {
        var orden = ordenesRepository.findById(id_orden)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden no encontrada"));

        // Restaurar el stock de los productos
        for (DetalleOrden detalleOrden : orden.getDetalles()) {
            Productos producto = detalleOrden.getProducto();
            producto.setStock(producto.getStock() + detalleOrden.getCantidad());
            productosRepository.save(producto);
        }
        // Eliminar la orden
        ordenesRepository.delete(orden);
    }
}
