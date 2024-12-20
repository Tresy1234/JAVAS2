package ism.com.entities;

import lombok.Data;

@Data
public class Niveau {
    private Long id;               // Identifiant unique du niveau
    private String name;           // Nom du niveau (ex : Licence, Master)
}
