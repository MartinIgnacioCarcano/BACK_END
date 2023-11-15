package com.tup.buensabor.services;


import com.tup.buensabor.dtos.RankingClienteDTO;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.repositories.ArticuloManufacturadoRepository;
import com.tup.buensabor.dtos.RankingProductoDTO;
import com.tup.buensabor.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService{

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    public ArticuloManufacturadoServiceImpl(BaseRepository<ArticuloManufacturado, Long> baseRepository){
        super(baseRepository);
    }

    public Page<ArticuloManufacturado> search(String filtro, Pageable pageable) throws Exception{
        try {
            Page<ArticuloManufacturado> articuloManufacturados = articuloManufacturadoRepository.search(filtro, pageable);
            return articuloManufacturados;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }





    @Override
    public Page<RankingProductoDTO> rankingProducto(Date desde, Date hasta, Pageable pageable) throws Exception {
        try {
            List<Object[]> rankingProductos = articuloManufacturadoRepository.rankingProductos(desde, hasta, pageable);


            List<RankingProductoDTO> listaRankings = new ArrayList<>();

            for (int i=0; i<rankingProductos.size(); i++) { //recorremos las tuplas o cantidad distinta de personas
                RankingProductoDTO rankingProductoDTO = new RankingProductoDTO();
                Object[] row = rankingProductos.get(i); //siempre 12 de lenght los 9 atributos de cliente y los 3 de retorno de la consulta
                rankingProductoDTO.setId((Long) row[0]);
                rankingProductoDTO.setDenominacion((String) row[1]);
                Integer miEnteroObj = ((Long) row[2]).intValue();
                rankingProductoDTO.setCantidad((int) miEnteroObj);
                rankingProductoDTO.setPrecioVenta((BigDecimal) row[3]);

                listaRankings.add(rankingProductoDTO);

            }
            // Page<RankingClienteDTO> rankings = new PageImpl<>(listaRankings, rankingCliente.getPageable(), rankingCliente.getTotalElements());
            Page<RankingProductoDTO> rankings = new PageImpl<>(listaRankings, pageable, rankingProductos.size());
            return rankings;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /*
    * @Override
    public Page<RankingProductoDTO> rankingProducto(Date desde, Date hasta, Pageable pageable) throws Exception {
        try {
            Page<ArticuloManufacturado> rankingProducto = articuloManufacturadoRepository.rankingProducto(desde, hasta, pageable);

            List<RankingProductoDTO> listaRankings = new ArrayList<>();

            for (ArticuloManufacturado articuloManufacturado : rankingProducto) {
                RankingProductoDTO rankingProductoDTO = new RankingProductoDTO(articuloManufacturado.getId());
                rankingProductoDTO.setId(articuloManufacturado.getId());
                rankingProductoDTO.setDenominacion(articuloManufacturado.getDenominacion());
                rankingProductoDTO.setPrecioVenta(articuloManufacturado.getPrecioVenta());
                listaRankings.add(rankingProductoDTO);
            }
            Page<RankingProductoDTO> rankings = new PageImpl<>(listaRankings, rankingProducto.getPageable(), rankingProducto.getTotalElements());
            return rankings;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    * */
}
