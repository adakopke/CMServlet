package Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Laudo {


    private Cliente cliente;
    private Exame exame;
    private String resultado;
    private String comentarios;
    private String data;


}
