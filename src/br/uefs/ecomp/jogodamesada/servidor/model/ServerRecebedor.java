/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.uefs.ecomp.jogodamesada.cliente.conexao.ProtocoloCliente;
import br.uefs.ecomp.jogodamesada.servidor.protocolos.ProtocoloServidor;
import java.net.InetAddress;
import java.util.Collections;

/**
 *
 * @author User
 */
public class ServerRecebedor implements Runnable {

    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private List<Usuario> usuarios;
    private List<Sala> salas;
    private Usuario usuario;
    private DistribuicaoDePlayers distribuidor;

    /**
     * Construtor do ServerRecebedor
     *
     * @param clientSocket
     * @param distribuidor
     */
    public ServerRecebedor(Socket clientSocket, DistribuicaoDePlayers distribuidor) throws IOException {
        this.clientSocket = clientSocket;
        this.usuarios = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.distribuidor = distribuidor;
        try {
            input = new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
        }

    }

    /**
     * Este método é responsável por enviar uma menságem atravéz da comunicação
     * para o Cliente
     *
     * @param mensagem
     */
    public void enviarMensagem(Object mensagem) {
        try {
            output.writeObject(mensagem);
        } catch (IOException ex) {
            Logger.getLogger(ServerRecebedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Este método é responsável por receber uma menságem atravéz da comunicação
     * do servidor
     *
     * @return string da mensagem
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public Object receberMensagem() throws IOException, ClassNotFoundException {
        return input.readObject().toString();
    }

    @Override
    public void run() {
        carregarArquivos("Usuarios.txt", "Salas.txt");
        while (true) {
            try {
                String texto = (String) receberMensagem();
                String pacote[] = texto.split("-");
                int resposta = Integer.parseInt(pacote[0]);
                switch (resposta) {
                    case ProtocoloCliente.CADASTRAR_USUARIO:
                        cadastrarUsuario(pacote[1], pacote[2]);
                        salvarUsuarios();
                        break;
                    case ProtocoloCliente.LOGIN:
                        logarUsuario(pacote[1], pacote[2]);
                        break;
                    case ProtocoloCliente.CONECTAR_SALA:
                        this.distribuidor.addPlayer(Integer.parseInt(pacote[1]), Integer.parseInt(pacote[2]), usuario);
                        System.out.println("Jogador esperando na fila.");
                        Sala sala = this.distribuidor.testarCapacidadeSalas();
                        if (sala != null) {
                            salas.add(sala);
                            salvarSalas();
                        }
                        break;
                    case ProtocoloCliente.SOLICITAR_RANK:
                        this.processarRank(Integer.parseInt(pacote[1]), Integer.parseInt(pacote[1]), Double.parseDouble(pacote[3]));
                        break;
                }
            } catch (IOException ex) {
                try {
                    input.close();
                    output.close();
                    clientSocket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ServerRecebedor.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerRecebedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void carregarArquivos(String diretorioA, String diretorioB) {

        try {
            FileInputStream arquivoLeitura = new FileInputStream(diretorioA);
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            usuarios = (List<Usuario>) objLeitura.readObject();
            arquivoLeitura = new FileInputStream(diretorioB);
            objLeitura = new ObjectInputStream(arquivoLeitura);
            salas = (List<Sala>) objLeitura.readObject();
            Sala.setNumeroSalas(salas.size());

            arquivoLeitura.close();
            objLeitura.close();
            arquivoLeitura.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Não tem arquivo de backup.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Formato do arquivo diferente.");
        } catch (IOException ex) {

        }
    }

    private void salvarUsuarios() {
        try {
            FileOutputStream fs = new FileOutputStream("Usuarios.txt");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(usuarios);

            fs.flush();
            fs.close();
            os.flush();
            os.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Não tem arquivo de backup.");
        } catch (IOException ex) {
            System.out.println("Erro na comunicação.aki");

        }
    }

    private void salvarSalas() {
        try {
            FileOutputStream fs = new FileOutputStream("Salas.txt");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(salas);

            fs.flush();
            fs.close();
            os.flush();
            os.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Não tem arquivo de backup.");
        } catch (IOException ex) {
            System.out.println("Erro na comunicação.aki");

        }
    }

    private void cadastrarUsuario(String nome, String senha) {
        if (!buscarUsu(nome, senha)) {
            Usuario u = new Usuario(nome, senha, this.output, this.clientSocket.getInetAddress());
            usuarios.add(u);
            enviarMensagem(ProtocoloServidor.USUARIO_CADASTRADO);
        } else {
            enviarMensagem(ProtocoloServidor.ERRO_CADASTRO);
        }
    }

    private boolean buscarUsu(String nome, String senha) {
        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    private Usuario buscarUsuario(String nome, String senha) {
        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }

    private void logarUsuario(String nome, String senha) {
        if (buscarUsu(nome, senha)) {
            enviarMensagem(ProtocoloServidor.LOGIN_SUCESSO);
            this.usuario = buscarUsuario(nome, senha);
            usuario.setOutput(output);
        } else {
            enviarMensagem(ProtocoloServidor.ERRO_LOGIN);
        }
    }

    private synchronized void processarRank(int id, int numSala, double saldo) throws IOException {
        for (Sala sala : salas) {
            if (sala.getIdSala() == numSala) {
                Usuario u = sala.getUsuariosSala().get(id - 1);
                u.setSaldoFinal(saldo);
                sala.setSolicitacoes(sala.getSolicitacoes() + 1);
            }
            if (sala.getSolicitacoes() == sala.getTotalPlayers()) {
                List<Usuario> players = sala.getUsuariosSala();
                Collections.sort(players);

                for (Usuario player : players) {
                    player.getOutput().writeObject(players);
                }
            }
        }

    }
}
