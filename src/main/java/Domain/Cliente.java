package Domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente {

    private String cpf;
    private String nome;
    private SexoEnum sexo;
    private String dataNascimento;

}
