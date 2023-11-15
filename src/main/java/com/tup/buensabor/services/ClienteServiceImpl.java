package com.tup.buensabor.services;


import com.tup.buensabor.dtos.RankingProductoDTO;
import com.tup.buensabor.entities.Cliente;
import com.tup.buensabor.dtos.RankingClienteDTO;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long> implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(BaseRepository<Cliente, Long>baseRepository){
        super(baseRepository);
    }

    public Page<Cliente> search(String filtro, Pageable pageable) throws Exception{
        try {
            Page<Cliente> clientes = clienteRepository.search(filtro, pageable);
            return clientes;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


   @Override
    public Page<RankingClienteDTO> rankingCliente(Date desde, Date hasta, Pageable pageable) throws Exception {
        try {
            List<Object[]> rankingCliente = clienteRepository.rankingCliente(desde, hasta, pageable);


            List<RankingClienteDTO> listaRankings = new ArrayList<>();

            for (int i=0; i<rankingCliente.size(); i++) { //recorremos las tuplas o cantidad distinta de personas
                RankingClienteDTO rankingClienteDTO = new RankingClienteDTO();
                Object[] row = rankingCliente.get(i); //siempre 12 de lenght los 9 atributos de cliente y los 3 de retorno de la consulta
                rankingClienteDTO.setId((Long) row[0]);
                rankingClienteDTO.setNombre((String) row[1]);
                rankingClienteDTO.setApellido((String) row[2]);
                rankingClienteDTO.setTelefono((String) row[3]);
                rankingClienteDTO.setEmail((String) row[4]);
                rankingClienteDTO.setCantidad_pedidos(((Long) row[5]).intValue());
                if (row[6] != null){
                    rankingClienteDTO.setTotal_venta(((BigDecimal) row[6]).doubleValue());
                }
                else {
                    rankingClienteDTO.setTotal_venta(0);
                }

                listaRankings.add(rankingClienteDTO);

                //for (int j=0; j<row.length; j++) {
                //    System.out.println(j);
                //    System.out.println(row[j]);
                //}
            }
            // Page<RankingClienteDTO> rankings = new PageImpl<>(listaRankings, rankingCliente.getPageable(), rankingCliente.getTotalElements());
            Page<RankingClienteDTO> rankings = new PageImpl<>(listaRankings, pageable, rankingCliente.size());
            return rankings;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
