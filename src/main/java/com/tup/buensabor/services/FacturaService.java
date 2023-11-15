package com.tup.buensabor.services;

import com.tup.buensabor.entities.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacturaService extends BaseService<Factura,Long> {

    public Page<Factura> search(String filtro, Pageable pageable) throws Exception;
}
