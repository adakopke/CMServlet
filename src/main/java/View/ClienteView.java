package View;

import Domain.Cliente;

import java.io.IOException;
import java.util.Scanner;

public interface ClienteView {

    Cliente create(Scanner sc) throws IOException;
}
