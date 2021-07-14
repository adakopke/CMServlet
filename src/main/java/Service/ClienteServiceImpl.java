package Service;

import DAO.ClienteDao;
import Domain.Cliente;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.List;

public class ClienteServiceImpl implements ClienteService {

    @Inject
    public ClienteDao clienteDao;


    @Override
    public void create(Cliente cliente) throws IOException {
        clienteDao.gravar(cliente);
    }


    public List<Cliente> listarTodos() throws IOException {
        return clienteDao.getAll();
    }

    @Override
    public Cliente buscar(String cpf) {
        return clienteDao.buscar(cpf);

    }

    @Override
    public Object alterar(Cliente cliente, String cpf) throws IOException {
        return clienteDao.alterarArquivo(cliente, cpf);
    }

    @Override
    public void remove(String cpf) throws IOException {
        clienteDao.removerItemArquivo(cpf);
    }
}
