package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity//indica que es una entidad
@Table(name="pais")//indica tabla con la que se relaciona la entidad
@Getter
@Setter
public class PaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String denominacion;

    @Column(name = "cant_habitantes")//cuando el atributo se llama igual que la columna este atributo no es necesario
    private Long cantidadHabitantes;

    private Long superficie;//m2

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//FetchType.EAGER refiere a que la inicialición es de tipo temprana, cascade = CascadeType.ALL que todas las operaciones sean consecuente con el continente como mysql
    @JoinColumn(name = "continente_id", insertable = false, updatable = false)//define como joinear con la table continente abajo, a través de continente_id -- unicamente para obtener info
    private ContinenteEntity continente;

    @Column(name = "continente_id", nullable = false)
    private Long continenteId;

    @ManyToMany(//Cuando creo un país puedo crear un ícono pero no al revés
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
    })
    @JoinTable(
            name = "icon_pais",
            joinColumns = @JoinColumn(name = "pais_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id"))
    private Set<IconEntity> icons = new HashSet<>();//icons atributo dl mappedBy en la IconEntity

    //Redefine la comparación de países
    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final PaisEntity other = (PaisEntity) obj;
        return other.id == this.id;
    }

}
