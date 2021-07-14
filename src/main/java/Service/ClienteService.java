package Service;

import Domain.Cliente;

import java.io.IOException;
import java.util.List;

public interface ClienteService {

     void create(Cliente cliente) throws IOException;

     public List<Cliente> listarTodos() throws IOException;

     Cliente buscar(String cpf);

     Object alterar(Cliente cliente, String cpf) throws IOException;

    void remove(String cpf) throws IOException;
}
