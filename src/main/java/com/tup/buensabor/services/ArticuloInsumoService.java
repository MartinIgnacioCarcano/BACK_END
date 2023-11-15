package com.tup.buensabor.services;

import com.tup.buensabor.entities.ArticuloInsumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ArticuloInsumoService extends BaseService<ArticuloInsumo, Long> {
    public Page<ArticuloInsumo> search(String filtro, Pageable pageable) throws Exception;
}
