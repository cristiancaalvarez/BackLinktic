package PruebaT.ecommerce.repository;

import PruebaT.ecommerce.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleOrdenesRepository extends JpaRepository<DetalleOrden, Integer> {

    @Query("SELECT do FROM DetalleOrden do WHERE do.orden.id = :ordenId")
    List<DetalleOrden> findByOrdenId(Integer ordenId);
}
