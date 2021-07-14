package DAO;

import Domain.Cliente;
import Domain.SexoEnum;
import jakarta.annotation.PostConstruct;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClienteDaoImpl implements ClienteDao {

    private Path path;

    @Override
    public Cliente gravar(Cliente cliente) {
        final var caminhoDoArquivo = "C:\\Users\\55329\\IdeaProjects\\CMServlet\\src\\main\\java\\CLIENTES.txt";
        try (var arquivo = new FileWriter(caminhoDoArquivo, true)) {
            var escreverArquivo = new PrintWriter(arquivo);
            escreverArquivo.printf("%s;%s;%s;%s\r\n", cliente.getCpf(), cliente.getNome(), cliente.getSexo(), cliente.getDataNascimento());
            init(cliente);
        } catch (Exception ex) {
            System.out.println("Não foi possivel criar o arquivo");
        }
        return cliente;
    }

    public void init(Cliente cliente) {
        try {
            String caminho = "C:\\Users\\55329\\IdeaProjects\\CMServlet\\src\\main\\java\\CLIENTES.txt";
            path = Paths.get(caminho);
            if (path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public Cliente inserirNoArquivo(Cliente cliente) throws IOException {
        write(format(cliente), StandardOpenOption.APPEND);
        return cliente;
    }


    //escreve o arquivo ou adiciona um conteudo junto ao mesmo.
    private void write(String clienteStr, StandardOpenOption option) throws IOException {
        final var caminhoDoArquivo = "C:\\Users\\55329\\IdeaProjects\\CMServlet\\src\\main\\java\\CLIENTES.txt";
        Path caminhoArquivo = Paths.get(caminhoDoArquivo);
        try(BufferedWriter bf = Files.newBufferedWriter(caminhoArquivo, option)){
        bf.flush();
        bf.write(clienteStr);
        }
    }

    private String format(Cliente cliente){
        return String.format("%s;%s;%s;%s \r\n",cliente.getCpf(), cliente.getNome(), cliente.getSexo(), cliente.getDataNascimento());
    }


    @Override
    public Cliente buscar(String cpf) {
        Cliente cliente = new Cliente();
        try {

            BufferedReader br = new BufferedReader(new FileReader("CLIENTES.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] cols = linha.split(";");

                if (cols[0].equalsIgnoreCase(cpf)) {

                    cliente.setCpf(cols[0]);
                    cliente.setNome(cols[1]);
                    cliente.setSexo(SexoEnum.valueOf(cols[2]));
               //     cliente.setDataNascimento(LocalDate.parse(cols[3]));
                    cliente.setDataNascimento((cols[3]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }


        return cliente;
    }

    @Override
    public List<Cliente> getAll() throws IOException {
        final var caminhoDoArquivo = "C:\\Users\\55329\\IdeaProjects\\CMServlet\\src\\main\\java\\CLIENTES.txt";
        Path caminhoArquivo = Paths.get(caminhoDoArquivo);
        List<Cliente> clientes;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo.toFile()))) {
            clientes = br.lines()
                    .filter(Objects::nonNull)
                    .filter(Predicate.not(String::isEmpty))
                    .map(this::convert)
                    .collect(Collectors.toList());
       }

        return clientes;
    }

    private Cliente convert(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ";");
        Cliente cliente = new Cliente();
        //cliente.setIdentificador(token.nextToken());
        cliente.setCpf(token.nextToken());
        cliente.setNome(token.nextToken());
        cliente.setSexo(SexoEnum.valueOf(token.nextToken()));
        //   cliente.setDataNascimento(LocalDate.parse(token.nextToken()));
        cliente.setDataNascimento(token.nextToken());
        return cliente;

    }

    @Override
    public Object alterarArquivo(Cliente cliente, String cpf) throws IOException {
        List<Cliente> clientes = getAll();
        Optional<Cliente> optionalCliente = clientes.stream()
                .filter(clienteSearch -> clienteSearch.getCpf().equals(cpf)).findFirst();
        if(optionalCliente.isPresent()){
            System.out.println("CONTEUDO ENCONTRADO");
            optionalCliente.get().setDataNascimento(cliente.getDataNascimento());
            optionalCliente.get().setNome(cliente.getNome());
            optionalCliente.get().setSexo(cliente.getSexo());

            reescreverArquivo(clientes);
            return optionalCliente.get();
        }
        return cliente;


    }

    @Override
    public void reescreverArquivo(List<Cliente> clientes) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (Cliente clienteBuilder: clientes) {
            builder.append(format(clienteBuilder));
        }

        write(builder.toString(),StandardOpenOption.CREATE);
    }

    @Override
    public void removerItemArquivo(String cpf) throws IOException {
        List<Cliente> clientes = getAll();
        List<Cliente> clienteResultante = new ArrayList<>();
        for (Cliente cliente:clientes){
            if(!cliente.getCpf().equals(cpf)){
                clienteResultante.add(cliente);
            }
        }
        eraseContent();
        reescreverArquivo(clienteResultante);


    }

    private void eraseContent() throws IOException {
        final var caminhoDoArquivo = "C:\\Users\\55329\\IdeaProjects\\CMServlet\\src\\main\\java\\CLIENTES.txt";
        Path caminhoArquivo = Paths.get(caminhoDoArquivo);
        BufferedWriter writer = Files.newBufferedWriter(caminhoArquivo);
        writer.write("");
        writer.flush();


    }


}

