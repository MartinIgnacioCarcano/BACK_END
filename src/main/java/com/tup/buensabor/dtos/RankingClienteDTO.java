package com.tup.buensabor.dtos;

import jakarta.persistence.NamedNativeQuery;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RankingClienteDTO extends GenericoDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private int cantidad_pedidos;
    private double total_venta;



}
