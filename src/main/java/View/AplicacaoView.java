package View;

import jakarta.inject.Inject;


import java.io.IOException;
import java.util.Scanner;

public class AplicacaoView {

    @Inject
    public ClienteView clienteView;

    @Inject
    public ExameView exameView;

    @Inject
    public LaudoView laudoView;



    public void init() throws IOException {

        int opcao = 0;
        System.out.println("Bem-vindo");
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(
                    "1 - Cadastrar Cliente\n" +
                    "2 - Cadastrar Exame\n" +
                    "3 - Cadastrar Laudo\n" +
                    "4 - Consultar Laudos\n" +
                    "0 - Sair");
            opcao = sc.nextInt();
            definirOpcao(sc, opcao);
        } while (opcao > 0);


    }

    private void definirOpcao(Scanner sc, int opcao) throws IOException {

    switch (opcao) {

        case 1:
            clienteView.create(sc);
            break;

        case 2:
            exameView.create(sc);
            break;

        case 3:
            laudoView.create(sc);
            break;

        case 4:
            laudoView.consultar(sc);


        case 0:
            System.exit(0);

        default:
            System.out.println("Digite uma opção válida");
    }


    }

}
