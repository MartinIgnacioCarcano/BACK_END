package com.tup.buensabor.repositories;

import com.tup.buensabor.dtos.RankingProductoDTO;
import com.tup.buensabor.entities.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;


public interface PedidoRepository extends BaseRepository<Pedido,Long> {

    @Query(value = "SELECT * FROM pedido WHERE ID_CLIENTE = :filtro",
            countQuery = "SELECT count(*) FROM pedido",
            nativeQuery = true)
    Page<Pedido> search(@Param("filtro") String filtro, Pageable pageable);

    @Query(value = "SELECT * FROM pedido WHERE ESTADO = :filtro",
            countQuery = "SELECT count(*) FROM pedido",
            nativeQuery = true)
    Page<Pedido> searchEstado(@Param("filtro") String filtro, Pageable pageable);



}
