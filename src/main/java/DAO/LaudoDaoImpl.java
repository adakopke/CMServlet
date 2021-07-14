package DAO;

import Domain.Laudo;

import java.io.*;

public class LaudoDaoImpl implements LaudoDao {
    @Override
    public void create(Laudo laudo) {

        try (FileWriter fw = new FileWriter("LAUDOS.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.printf(
                    laudo.getCliente().getCpf() + ";" +
                    laudo.getExame().getCodigo() + ";" +
                    laudo.getResultado() + ";" +
                    laudo.getData() +  "\n");
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }

    @Override
    public void resultado(String cpf, int codexame) {

        try {

            BufferedReader br = new BufferedReader(new FileReader("LAUDOS.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] cols = linha.split(";");

                if ((cols[0].equalsIgnoreCase(cpf)) && (cols[1].equalsIgnoreCase(String.valueOf(codexame)))) {
                    //TODO melhorar isso fiz com sout aqui pra ganhar tempo
                    System.out.println("Resultado obtido em " + cols[3] + " : " + cols[2]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }


  }
}
