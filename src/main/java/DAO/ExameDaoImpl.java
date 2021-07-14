package DAO;

import Domain.Exame;
import Domain.SexoEnum;

import java.io.*;

public class ExameDaoImpl implements ExameDao {
    @Override
    public void gravar(Exame exame) {

        try (FileWriter fw = new FileWriter("EXAMES.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.printf(exame.getCodigo() + ";" +
                    exame.getNome() + ";" +
                    exame.getUnidade() + ";" +
                    exame.getValorRefMin() + ";" +
                    exame.getValorRefMax() + ";" +
                    exame.getRestringeSexoAplicacao() + "\n");
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }

    @Override
    public void listar() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("EXAMES.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] cols = linha.split(";");
                System.out.println(
                        "COD_EXAME: " + cols[0] +
                                "  | NOME_EXAME: " + cols[1] +
                                "  | UNIDADE_MEDIDA: " + cols[2] +
                                "  | VALOR_MIN_REF: " + cols[3] +
                                "  | VALOR_MAX_REF: " + cols[4] +
                                "  | RESTRINGIDO_SEXO: " + cols[5] + "\n"
                );
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    @Override
    public Exame buscar(int codexame) {

        Exame exame = new Exame();

        try {

            BufferedReader br = new BufferedReader(new FileReader("EXAMES.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] cols = linha.split(";");

                if (cols[0].equalsIgnoreCase(String.valueOf(codexame))) {

                    exame.setCodigo(Integer.parseInt(cols[0]));
                    exame.setNome(cols[1]);
                    exame.setUnidade(cols[2]);
                    exame.setValorRefMin(Double.valueOf(cols[3]));
                    exame.setValorRefMax(Double.valueOf(cols[4]));
                    if (!cols[5].equalsIgnoreCase("null")) {
                        exame.setRestringeSexoAplicacao(SexoEnum.valueOf(cols[5]));
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }


        return exame;
    }


}

