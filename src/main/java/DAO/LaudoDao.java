package DAO;

import Domain.Laudo;

public interface LaudoDao {
    void create(Laudo laudo);

    void resultado(String cpf, int codexame);

}
