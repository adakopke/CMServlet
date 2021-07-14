package Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exame {
    private int codigo;
    private String nome;
    private String unidade;
    private Double valorRefMin;
    private Double valorRefMax;
    private SexoEnum restringeSexoAplicacao;


}
