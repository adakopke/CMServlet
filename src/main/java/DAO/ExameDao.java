package DAO;

import Domain.Exame;

public interface ExameDao {
    void gravar(Exame exame);
    void listar();
    Exame buscar(int codexame);

}
