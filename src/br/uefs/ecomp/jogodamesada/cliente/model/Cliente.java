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
import br.uefs.ecomp.jogodamesada.cliente.view.JogoDaMesada;
import br.uefs.ecomp.jogodamesada.servidor.protocolos.ProtocoloServidor;
import java.util.StringTokenizer;

/**
 *
 * @author User
 */
public class Cliente {

    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private static Socket socket;
    private InetAddress multicastIP;
    private static Scanner teclado;
    private ClienteP2P clienteP2P;
    private MultiCastRecebedor r;
    private MultiCastEnvio envio;

    /**
     * @return the clienteP2P
     */
    public ClienteP2P getClienteP2P() {
        return clienteP2P;
    }

    public Cliente() {
    }

    public List<Pessoa> getJogadores() {
        return getClienteP2P().getJogadores();
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

    public boolean conectarSala(String players, String periodo) throws IOException, ClassNotFoundException {
        enviarMensagem(ProtocoloCliente.CONECTAR_SALA + ProtocoloCliente.SEPARATOR + players + ProtocoloCliente.SEPARATOR + periodo);
        SalaDeEspera sala = new SalaDeEspera(this, input);
        Thread espera = new Thread(sala);
        espera.start();

        while (!espera.getState().equals(TERMINATED)) {

        }
        String mensagem = (String) input.readObject();
        StringTokenizer tokens = new StringTokenizer(mensagem, ProtocoloP2P.SEPARATOR);
        clienteP2P.criaInstanciaPessoa(tokens);

        return true;
    }

    public void iniciarMultCast(InetAddress multicastIP) throws IOException {
        clienteP2P = new ClienteP2P(multicastIP, this);
        System.out.println(multicastIP);
        r = new MultiCastRecebedor(multicastIP, this.getClienteP2P());
        Thread recebedor = new Thread(r);
        recebedor.start();
        envio = new MultiCastEnvio(multicastIP);
        this.getClienteP2P().setAddress(multicastIP);

    }

    public void moverPeao(String id, int dado) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.MOVER_PEAO).append(ProtocoloP2P.SEPARATOR).append(id).append(ProtocoloServidor.SEPARATOR).append(dado);
        envio.sendPacket(data.toString());
    }

    public void perdeuSuaVez() throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.PERDEU_A_VEZ);
        envio.sendPacket(data.toString());
    }

    public void felizAniversario(String id) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.FELIZ_ANIVERSARIO).append(ProtocoloP2P.SEPARATOR).append(id);
        envio.sendPacket(data.toString());
    }

    public void sairDaPartida(String id) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.SAIR_DA_PARTIDA).append(ProtocoloP2P.SEPARATOR).append(id);
        envio.sendPacket(data.toString());
    }

    public void concursoDeBandaDeRock(String id) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.CONCURSO_DE_BANDA_DE_ROCK).append(ProtocoloP2P.SEPARATOR).append(id);
        envio.sendPacket(data.toString());
    }

    public void maratonaBeneficiente(String id) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.MARATONA_BENEFICIENTE).append(ProtocoloP2P.SEPARATOR).append(id);
        envio.sendPacket(data.toString());
    }

    public void finalizarSorteios() throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.FINALIZAR_SORTEIOS);
        envio.sendPacket(data.toString());
    }

    public void proximoATentar() throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.PROXIMO_A_TENTAR);
        envio.sendPacket(data.toString());
    }

    public void bolaoDeEsportes(String id) throws IOException {
        clienteP2P.bolaoDeEsportes(id);
    }

    public void creditarSorteGrande(int valor) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.CREDITAR_SORTE_GRANDE).append(ProtocoloP2P.SEPARATOR).append(valor);
        envio.sendPacket(data.toString());
    }

    public void resgatarSorteGrande(String id) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.RESGATAR_SORTE_GRANDE).append(ProtocoloP2P.SEPARATOR).append(id);
        envio.sendPacket(data.toString());
    }

    public void solicitarNumeroBolaoEsportes(String id) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.SOLICITAR_NUMERO_BOLAO_DE_ESPORTES).append(ProtocoloP2P.SEPARATOR).append(id);
        envio.sendPacket(data.toString());
    }

    public void enviarRespostaBolaoEsportes(int valor, String id) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.RESPONDER_NUMERO_BOLAO_DE_ESPORTES).append(ProtocoloP2P.SEPARATOR).append(valor)
                .append(ProtocoloP2P.SEPARATOR).append(id);
        envio.sendPacket(data.toString());
    }

    public void proximoAJogar() throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.PROXIMO_A_JOGAR);
        envio.sendPacket(data.toString());
    }

    public void creditarGanhadorDaMaratonaBeneficiente(String id, double valor) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.CREDITAR_GANHADOR_MARATONA_BENEFICIENTE).append(ProtocoloP2P.SEPARATOR).append(id)
                .append(ProtocoloP2P.SEPARATOR).append(valor);
        envio.sendPacket(data.toString());
    }

    public List<Pessoa> solicitarRank(String id, double saldo) throws IOException, ClassNotFoundException {
        enviarMensagem(ProtocoloCliente.SOLICITAR_RANK + ProtocoloCliente.SEPARATOR + id + ProtocoloCliente.SEPARATOR + 
                clienteP2P.getIdSala() + ProtocoloCliente.SEPARATOR + saldo);
        return (List<Pessoa>) input.readObject();
    }

    public void informarGanhadorBolaoDeEsportes(String id, double valor) throws IOException {
       StringBuilder data = new StringBuilder();
        data.append(ProtocoloP2P.INFORMAR_GANHADOR_BOLAO_ESPORTES).append(ProtocoloP2P.SEPARATOR).append(id)
                .append(ProtocoloP2P.SEPARATOR).append(valor);
        envio.sendPacket(data.toString());
    }

}
