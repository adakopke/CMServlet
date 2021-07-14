package View;

import Domain.Exame;
import Domain.Cliente;
import Domain.SexoEnum;
import Service.ExameService;
import jakarta.inject.Inject;

import java.util.Scanner;

public class ExameViewImpl implements ExameView {

    @Inject
    public ExameService exameService;

    @Override
    public void create(Scanner sc) {
            int restrito;
            Exame exame = new Exame();
            System.out.println("Informe o código do exame");
            exame.setCodigo(sc.nextInt());
            sc.nextLine();
            System.out.println("Informe o nome do exame");
            exame.setNome(sc.nextLine());
            System.out.println("Informe a unidade de medida do exame");
            exame.setUnidade(sc.nextLine());
            System.out.println("Informe valor mínimo de referência do exame");
            exame.setValorRefMin(sc.nextDouble());
            System.out.println("Informe valor máximo de referência do exame");
            exame.setValorRefMax(sc.nextDouble());


                System.out.println("Informe se o exame é restrito ao sexo do requerente:\n" +
                        "1 - Masculino\n" +
                        "2 - Feminino\n" +
                        "0 - Sem restrição");

        do {
                restrito = sc.nextInt();
                switch (restrito) {
                    case 1:
                        exame.setRestringeSexoAplicacao(SexoEnum.MASCULINO);
                        break;

                    case 2:
                        exame.setRestringeSexoAplicacao(SexoEnum.FEMININO);
                        break;

                    case 0:
                        break;

                    default:
                        System.out.printf("Digite uma opção válida\n");
                        restrito = -1;
                        break;
                }
            } while (restrito == -1);


            exameService.create(exame);
            System.out.println("Exame cadastrado com sucesso");
        }

    }

