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
import jogodamesada.Servidor.Protocolos.ProtocoloServidor;

/**
 *
 * @author User
 */
public class ServerRecebedor implements Runnable {

    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private List<Usuario> usuarios;
    private List<Sala> salasEspera;
    private List<Sala> salas;
    private Usuario usuario;
    private DistribuicaoDePlayers distribuidor;

    /**
     * Construtor do ServerRecebedor
     *
     * @param clientSocket
     */
    public ServerRecebedor(Socket clientSocket, DistribuicaoDePlayers distribuidor) throws IOException {
        this.clientSocket = clientSocket;
        this.usuarios = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.salasEspera = new ArrayList<>();
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
     */
    public Object receberMensagem() throws IOException, ClassNotFoundException {
        return input.readObject().toString();
    }

    @Override
    public void run() {
        carregarUsuarios();
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
                        this.distribuidor.testarCapacidadeSalas();
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerRecebedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerRecebedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void carregarUsuarios() {
        try {
            FileInputStream arquivoLeitura = new FileInputStream("Usuarios.txt");
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            usuarios = (List<Usuario>) objLeitura.readObject();

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

    private void cadastrarUsuario(String nome, String senha) {
        if (!buscarUsu(nome, senha)) {
            Usuario u = new Usuario(nome, senha, this.output);
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
}
