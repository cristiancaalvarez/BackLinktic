package PruebaT.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name = "detalle_pedidos")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos producto;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private Ordenes orden;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unidad")
    private Double precioUnidad;

    @Column(name = "subtotal")
    private Double subtotal;

}
