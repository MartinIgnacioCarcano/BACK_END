package com.tup.buensabor.services;

import com.tup.buensabor.entities.DetallePedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetallePedidoService extends BaseService<DetallePedido, Long> {

    public Page<DetallePedido> search(String filtro, Pageable pageable) throws Exception;
}
