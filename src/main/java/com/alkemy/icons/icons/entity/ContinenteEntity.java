package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity//indica que es una entidad
@Table(name="continente")//indica tabla con la que se relaciona la entidad
@Getter
@Setter
public class ContinenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//defino estrategia para el id, sequense 1,2,3,...n
    private Long id;

    private String imagen;

    private String denominacion;
}
