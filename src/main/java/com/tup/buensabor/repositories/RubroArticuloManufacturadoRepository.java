package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.RubroArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RubroArticuloManufacturadoRepository extends BaseRepository<RubroArticuloManufacturado,Long> {

    @Query(value = "SELECT * FROM rubro_articulo_manufacturado WHERE denominacion = :filtro",
            countQuery = "SELECT count(*) FROM rubro_articulo_manufacturado",
            nativeQuery = true)
    Page<RubroArticuloManufacturado> search(@Param("filtro") String filtro, Pageable pageable);
}
