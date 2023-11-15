package com.tup.buensabor.services;

import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetalleArticuloManufacturadoService extends BaseService<DetalleArticuloManufacturado, Long> {

    public Page<DetalleArticuloManufacturado> search(String filtro, Pageable pageable) throws Exception;
}
