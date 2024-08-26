package PruebaT.ecommerce.repository;

import PruebaT.ecommerce.model.Ordenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenesRepository extends JpaRepository<Ordenes, Integer> {
}
