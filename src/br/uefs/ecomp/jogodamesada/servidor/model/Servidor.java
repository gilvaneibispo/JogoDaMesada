package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * <strong>Servidor: </strong>
 * Responsável apenas por receber as solicitações de conexão dos cliente e .
 * gerar novas instâncias de ServerServidor, as quais funcionaram de forma
 * paralela por meio de Threads.
 *
 * @author Gilvanei Bispo
 * @author Rodrigo Santos
 * @author Dermeval Neves
 */
public class Servidor {

    private final ServerSocket server;
    private DistribuicaoDePlayers distribuidor;

    public Servidor(int port) throws IOException {
        this.server = new ServerSocket(port);
        this.distribuidor = new DistribuicaoDePlayers();

    }

    public void iniciar() {
        System.out.println("Esperando conexões...");
        long tempInicial = System.currentTimeMillis();
        System.out.println(tempInicial);
        System.out.println("inicio da compilação.. " + new Date().getTime());
        while (true) {
            try {
                Socket clientSock = server.accept();
                System.err.println(clientSock.getInetAddress());
                ServerRecebedor r = new ServerRecebedor(clientSock, distribuidor);
                new Thread(r).start(); // mandou pra thread e iniciou ela

            } catch (IOException ex) {
                System.out.println("Erro na comunicação.");
            }
        }
    }

    public static void main(String[] args) {

        try {
            Servidor fs = new Servidor(12345);
            fs.iniciar();

        } catch (IOException ex) {
        }
    }
}
