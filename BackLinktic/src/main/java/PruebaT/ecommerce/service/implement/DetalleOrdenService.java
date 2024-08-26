package PruebaT.ecommerce.service.implement;

import PruebaT.ecommerce.dto.DetalleOrdenDTO;
import PruebaT.ecommerce.model.DetalleOrden;
import PruebaT.ecommerce.repository.DetalleOrdenesRepository;
import PruebaT.ecommerce.service.IService.IDetalleOrdenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleOrdenService implements IDetalleOrdenService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DetalleOrdenesRepository detalleOrdenesRepository;

    @Override
    public List<DetalleOrdenDTO> listarDetalleOrdenIdOrden(Integer pedidoId) {
        List<DetalleOrden> detalleOrden = detalleOrdenesRepository.findByOrdenId(pedidoId);
        return detalleOrden.stream()
                .map(detalle -> modelMapper.map(detalle, DetalleOrdenDTO.class))
                .collect(Collectors.toList());
    }
}
