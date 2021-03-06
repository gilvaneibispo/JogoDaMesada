/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.State.TERMINATED;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.uefs.ecomp.jogodamesada.cliente.conexao.ProtocoloCliente;
import br.uefs.ecomp.jogodamesada.cliente.conexao.ProtocoloP2P;
import br.uefs.ecomp.jogodamesada.servidor.protocolos.ProtocoloServidor;


/**
 *
 * @author User
 */
public class Cliente {

    /**
     * @return the p
     */
    public ClienteP2P getP() {
        return p;
    }

    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private static Socket socket;
    private InetAddress multicastIP;
    private static Scanner teclado;
    private ClienteP2P p;
    private MultiCastRecebedor receiver;
    private  MultiCastEnvio envio;

    public Cliente() {
    }
    
    public List<Pessoa> getJogadores(){
        return getP().getJogadores();
    }

    public static void enviarMensagem(Object mensagem) {
        try {
            output.writeObject(mensagem);
        } catch (IOException ex) {
            System.out.println("Erro na comunicação");
        }

    }

    /**
     * Método responsável por receber uma mensagem.
     *
     * @return mensagem que foi recebida.
     */
    public static Object receberMensagem() {
        try {
            return input.readObject().toString();
        } catch (IOException ex) {
            System.out.println("Erro na comunicação");

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro interno do Sistema Operacilnal.");
        }
        return "";

    }

    public void acessarServidor(String ip, String port) throws IOException {
        int porta = Integer.parseInt(port);
        socket = new Socket(ip, porta);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    public int cadastrarUsuario(String nome, String senha) {
        enviarMensagem(ProtocoloCliente.CADASTRAR_USUARIO + ProtocoloCliente.SEPARATOR + nome + ProtocoloCliente.SEPARATOR + senha);
        return Integer.parseInt(receberMensagem().toString());
    }

    public int logar(String nome, String senha) throws IOException {
        enviarMensagem(ProtocoloCliente.LOGIN + ProtocoloCliente.SEPARATOR + nome + ProtocoloCliente.SEPARATOR + senha);
        int resposta = (int) Integer.parseInt(receberMensagem().toString());
        return resposta;
    }


    public boolean conectarSala(String players, String periodo) throws IOException {
        enviarMensagem(ProtocoloCliente.CONECTAR_SALA + ProtocoloCliente.SEPARATOR + players + ProtocoloCliente.SEPARATOR + periodo);
        SalaDeEspera sala = new SalaDeEspera(this, input);
        Thread espera = new Thread(sala);
        espera.start();

        while (!espera.getState().equals(TERMINATED)){
     
            
        }
        
        return true;
    }

    public void iniciarMultCast(InetAddress multicastIP) throws IOException {
        p = new ClienteP2P(multicastIP);
        System.err.println("aaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(multicastIP);
         receiver = new MultiCastRecebedor(multicastIP, this.getP());
         envio = new  MultiCastEnvio(multicastIP);
         this.getP().setAddress(multicastIP);
        receiver.start();
        

    }

    public void moverPeao(String id, int dado) throws IOException {
       StringBuilder data = new StringBuilder();
       data.append(ProtocoloP2P.MOVER_PEAO).append(ProtocoloP2P.SEPARATOR).append(id).append(ProtocoloServidor.SEPARATOR).append(dado);
       envio.sendPacket(data.toString());
    }

}
