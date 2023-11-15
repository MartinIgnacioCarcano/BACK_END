package com.tup.buensabor.repositories;

import com.tup.buensabor.dtos.RankingClienteDTO;
import com.tup.buensabor.entities.Cliente;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface ClienteRepository extends BaseRepository<Cliente,Long> {

    @Query(value = "SELECT * FROM cliente WHERE nombre = :filtro OR apellido = :filtro",
            countQuery = "SELECT count(*) FROM cliente",
            nativeQuery = true)
    Page<Cliente> search(@Param("filtro") String filtro, Pageable pageable);

    @Query(
            value = "SELECT c.ID AS ClienteID, c.Nombre AS NombreCliente, c.apellido AS apellido , c.telefono AS telefono, " +
                    "c.email AS email, COUNT(p.id) AS CantidadPedidos, " +
                    "SUM(f.total_venta) AS TotalPrecioPedidos " +
                    "FROM Cliente c LEFT JOIN Pedido p ON c.ID = p.ID_CLIENTE " +
                    "LEFT JOIN Factura f ON p.id = f.ID_PEDIDO " +
                    "WHERE p.fecha_Pedido BETWEEN '2023-01-01' AND '2023-12-31' " +
                    "GROUP BY c.ID, c.Nombre ORDER BY CantidadPedidos DESC",
            countQuery = "SELECT count(*) FROM cliente",
            nativeQuery = true)
    List<Object[]> rankingCliente(@Param("desde") Date desde, @Param("hasta") Date hasta, Pageable pageable);


}
