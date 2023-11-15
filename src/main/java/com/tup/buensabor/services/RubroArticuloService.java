package com.tup.buensabor.services;

import com.tup.buensabor.entities.RubroArticulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RubroArticuloService extends BaseService<RubroArticulo, Long> {

    public Page<RubroArticulo> search(String filtro, Pageable pageable) throws Exception;
}
