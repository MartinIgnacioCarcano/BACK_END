package com.tup.buensabor.services;

import com.tup.buensabor.entities.DetalleFactura;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DetalleFacturaServiceImpl extends BaseServiceImpl<DetalleFactura, Long> implements DetalleFacturaService{

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    public DetalleFacturaServiceImpl(BaseRepository<DetalleFactura, Long>baseRepository){
        super(baseRepository);
    }

    @Override
    public Page<DetalleFactura> search(String filtro, Pageable pageable) throws Exception{
        try {
            Page<DetalleFactura> detalleFacturas = detalleFacturaRepository.search(filtro, pageable);
            return detalleFacturas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
