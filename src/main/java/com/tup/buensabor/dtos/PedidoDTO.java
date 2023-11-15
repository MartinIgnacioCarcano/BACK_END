package com.tup.buensabor.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PedidoDTO extends GenericoDTO {

    private Date fechaPedido;
    private BigDecimal total;
}
