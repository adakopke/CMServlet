package DAO;

import Domain.Cliente;

import java.io.IOException;
import java.util.List;

public interface ClienteDao {
    Cliente gravar(Cliente cliente) throws IOException;

    Cliente inserirNoArquivo(Cliente cliente) throws IOException;

    Cliente buscar(String cpf);

    public List<Cliente> getAll() throws IOException;

    Object alterarArquivo(Cliente cliente, String cpf) throws IOException;

    void reescreverArquivo(List<Cliente> clientes) throws IOException;

    void removerItemArquivo(String cpf) throws IOException;
}
