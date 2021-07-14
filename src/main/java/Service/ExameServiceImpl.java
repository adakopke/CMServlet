package Service;

import DAO.ExameDao;
import Domain.Exame;
import jakarta.inject.Inject;



public class ExameServiceImpl implements ExameService {

    @Inject
    public ExameDao exameDao;

    @Override
    public void create(Exame exame) {
        exameDao.gravar(exame);
    }

    @Override
    public void listar() {
        exameDao.listar();

    }

    @Override
    public Exame buscar(int codexame) {
        return exameDao.buscar(codexame);
    }
}
