package View;

import Domain.Cliente;
import Domain.SexoEnum;
import Service.ClienteService;
import jakarta.inject.Inject;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Scanner;

public class ClienteViewImpl implements ClienteView {

    @Inject
    public ClienteService clienteService;


    @Override
    public Cliente create(Scanner sc) throws IOException {
        Cliente cliente = new Cliente();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.println("Informe o nome do cliente");
        sc.nextLine();
        cliente.setNome(sc.nextLine());
        System.out.println("Informe o cpf do cliente");
        cliente.setCpf(sc.next());
        int sexo;
         do {
             System.out.println("Informe o sexo do cliente:\n1 - Masculino\n2 - Feminino");
             sexo = sc.nextInt();
            if (sexo == 1) {
                cliente.setSexo(SexoEnum.MASCULINO);
            } else if (sexo == 2) {
                cliente.setSexo(SexoEnum.FEMININO);
            } else {
                sexo = 0;
                System.out.println("Digite uma opção válida");;
            }
        } while ((sexo == 0));
        System.out.printf("Informa a data de nascimento no padrão dd/mm/aaaa\n");
        //cliente.setDataNascimento(LocalDate.parse(sc.next(), formatter));
        cliente.setDataNascimento(sc.next());

        clienteService.create(cliente);
        System.out.println("Cliente cadastrado com sucesso");

        return cliente;




    }
}
