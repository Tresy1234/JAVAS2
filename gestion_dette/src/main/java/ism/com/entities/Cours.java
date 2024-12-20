package ism.com.entities;

import java.util.List;
import lombok.Data;

@Data
public class Cours {
    private Long id;               
    private String module;         
    private List<Classe> classes;  
}
