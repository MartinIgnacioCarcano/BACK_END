package com.tup.buensabor.services;

import com.tup.buensabor.entities.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService extends BaseService<Pedido,Long> {

    public Page<Pedido> search(String filtro, Pageable pageable) throws Exception;

    public Page<Pedido> searchEstado(String filtro, Pageable pageable) throws Exception;
}
