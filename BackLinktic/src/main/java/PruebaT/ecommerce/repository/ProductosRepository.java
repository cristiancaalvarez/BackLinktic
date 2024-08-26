package PruebaT.ecommerce.repository;

import PruebaT.ecommerce.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {

    @Query("SELECT p FROM Productos p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Productos> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);

}