package com.tup.buensabor.services;

import com.tup.buensabor.entities.DetalleFactura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetalleFacturaService extends BaseService<DetalleFactura, Long>{

    public Page<DetalleFactura> search(String filtro, Pageable pageable) throws Exception;
}
