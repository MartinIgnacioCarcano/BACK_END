package com.tup.buensabor.services;

import com.tup.buensabor.entities.UnidadMedida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UnidadMedidaService extends BaseService<UnidadMedida, Long> {
    public Page<UnidadMedida> search(String filtro, Pageable pageable) throws Exception;

}
