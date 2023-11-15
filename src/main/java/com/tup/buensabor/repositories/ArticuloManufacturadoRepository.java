package com.tup.buensabor.repositories;

import com.tup.buensabor.dtos.RankingProductoDTO;
import com.tup.buensabor.entities.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long>{

    @Query(value = "SELECT * FROM articulo_manufacturado WHERE denominacion = :filtro OR descripcion = :filtro",
            countQuery = "SELECT count(*) FROM articulo_manufacturado",
            nativeQuery = true)
    Page<ArticuloManufacturado> search(@Param("filtro") String filtro, Pageable pageable);

    @Query(value = "SELECT am.id AS ID_PRODUCTO, am.denominacion AS NombreProducto, SUM(dp.cantidad) AS CantidadVendida, " +
            "SUM(dp.subtotal) AS TotalVentas " +
            "FROM Articulo_Manufacturado AS am " +
            "JOIN Detalle_Pedido AS dp ON am.id = dp.id_articulo_manufacturado " +
            "JOIN Pedido AS pe ON dp.id_pedido = pe.id " +
            "WHERE pe.fecha_Pedido BETWEEN '2023-01-01' AND '2023-12-31'" +
            "GROUP BY am.id, am.denominacion " +
            "ORDER BY CantidadVendida DESC",
            countQuery = "SELECT count(*) FROM pedido",
            nativeQuery = true)
    List<Object[]> rankingProductos(@Param("desde") Date desde, @Param("hasta") Date hasta, Pageable pageable);

}
