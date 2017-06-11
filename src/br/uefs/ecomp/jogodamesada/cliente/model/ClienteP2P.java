/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.StringTokenizer;
import br.uefs.ecomp.jogodamesada.cliente.conexao.ProtocoloP2P;
import br.uefs.ecomp.jogodamesada.cliente.view.JogoDaMesada;
import java.util.Hashtable;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ClienteP2P {

    private InetAddress address;
    private LinkedList<Pessoa> ordemJogadas;
    private static ClienteP2P instance;
    private JogoDaMesada tabuleiro;
    private Pessoa jogadorAtual;
    private Pessoa sorteioAtual;
    private Pessoa jogadorLocal;
    private LinkedList<Pessoa> sorteios;
    private boolean jogadaRegular;
    private Cliente cliente;
    private Hashtable tabelaBolao;
    private Banco banco;
    private LinkedList numeroSorteio;
    private boolean numeroValido;

    public ClienteP2P(InetAddress address, Cliente cliente) throws IOException {
        this.address = address;
        this.ordemJogadas = new LinkedList<>();
        this.sorteios = new LinkedList<>();
        this.cliente = cliente;
        banco = new Banco();
    }

    public List<Pessoa> getJogadores() {
        for (Pessoa p : ordemJogadas) {
            System.out.println(p.getId() + " - " + p.getNome());
        }
        return this.ordemJogadas;
    }

    public void jogadas(String mensagem) throws IOException {
        StringTokenizer tokens = new StringTokenizer(mensagem, ProtocoloP2P.SEPARATOR);
        int protocolo = Integer.parseInt(tokens.nextToken());
        switch (protocolo) {
            case ProtocoloP2P.CONFIGURACOES:
                this.criaInstanciaPessoa(tokens);
                break;
            case ProtocoloP2P.MOVER_PEAO:
                tabuleiro.calculaDistancia(tokens.nextToken(), Integer.parseInt(tokens.nextToken()));
                this.mudarJogadorAtual();
                break;
            case ProtocoloP2P.PERDEU_A_VEZ:
                this.mudarJogadorAtual();
                break;
            case ProtocoloP2P.FELIZ_ANIVERSARIO:
                this.felizAniversario(Integer.parseInt(tokens.nextToken()));
                break;
            case ProtocoloP2P.SAIR_DA_PARTIDA:
                this.excluirJogador(tokens.nextToken());
                break;
            case ProtocoloP2P.CONCURSO_DE_BANDA_DE_ROCK:
                this.setJogadasRegulares(false);
                this.iniciarConcursoDeBandaDeRock(tokens.nextToken());
                break;
            case ProtocoloP2P.FINALIZAR_SORTEIOS:
                this.setJogadasRegulares(true);
                break;
            case ProtocoloP2P.PROXIMO_A_TENTAR:
                this.iniciarConcursoDeBandaDeRock(tokens.nextToken());
                break;
            case ProtocoloP2P.SOLICITAR_NUMERO_BOLAO_DE_ESPORTES:
                this.setJogadasRegulares(false);
                this.enviarNumerobolaoDeEsportes(tokens.nextToken());
                break;
            case ProtocoloP2P.CREDITAR_SORTE_GRANDE:
                this.creditarSorteGrande(Integer.parseInt(tokens.nextToken()));
                break;
            case ProtocoloP2P.RESGATAR_SORTE_GRANDE:
                this.resgatarSorteGrande(tokens.nextToken());
                break;
            case ProtocoloP2P.RESPONDER_NUMERO_BOLAO_DE_ESPORTES:
                this.respostaBolao(tokens.nextToken());
                break;
        }
    }

    private void criaInstanciaPessoa(StringTokenizer tokens) {
        while (tokens.hasMoreElements()) {
            String nomeJogador = tokens.nextToken();
            String id = "J" + tokens.nextToken();
            Pessoa pes = new Pessoa(id, nomeJogador);
            ordemJogadas.add(pes);
            setJogadorAtual(ordemJogadas.get(0));
            this.setJogadasRegulares(true);
        }
    }

    /**
     * @return the address
     */
    public InetAddress getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(InetAddress address) {
        this.address = address;
    }

    /**
     * @return the tabuleiro
     */
    public JogoDaMesada getTabuleiro() {
        return tabuleiro;
    }

    /**
     * @param tabuleiro the tabuleiro to set
     */
    public void setTabuleiro(JogoDaMesada tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    /**
     * @return the jogadorAtual
     */
    public Pessoa getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean eMeuTurno(String nome) {
        if (nome.equals(this.jogadorAtual.getNome()) && isJogadaRegular()) {
            return true;
        }
        return false;
    }

    /**
     * @param jogadorAtual the jogadorAtual to set
     */
    public void mudarJogadorAtual() {
        ordemJogadas.addLast(ordemJogadas.removeFirst());
        jogadorAtual = ordemJogadas.peekFirst();
    }
    
        public void mudarSorteioAtual() {
       sorteios.addLast(sorteios.removeFirst());
        sorteioAtual = sorteios.peekFirst();
    }

    private void setJogadorAtual(Pessoa get) {
        this.jogadorAtual = get;
    }

    private void felizAniversario(int id) {
        for (Pessoa p : ordemJogadas) {
            if (p.getId().equals(id)) {
                p.getConta().creditar(ordemJogadas.size() * 100.00);
            } else {
                if (p.getConta().getSaldo() < 100) {
                    if (this.tabuleiro.emprestimo(100.00)) {
                        p.getConta().debitar(100.00);
                    }
                }
                p.getConta().debitar(100.00);
            }
        }
    }

    private void excluirJogador(String id) {
        if (jogadorAtual.getId().equals(id)) {  //Se for a vez do jogador a ser excluido
            this.mudarJogadorAtual();    //ele passa a vez para o proximo
        }
        for (int i = 0; i < ordemJogadas.size(); i++) {
            Pessoa p = ordemJogadas.get(i);
            if (p.getId().equals(id)) {
                ordemJogadas.remove(p);
            }

        }
        if (ordemJogadas.size() == 1) {
            tabuleiro.informarVencedor();
        }
    }

    private void iniciarConcursoDeBandaDeRock(String id) throws IOException {

        if (jogadorAtual.getId().equals(id)) {
            sorteios.addAll(ordemJogadas);
            sorteioAtual = sorteios.getFirst();
        }
        if (sorteioAtual.getId().equals(id) && this.tabuleiro.ConcursoDeBandaDeRock() == 3) {
            sorteioAtual.getConta().creditar(1000.00);
            cliente.finalizarSorteios();
        } else {
            cliente.proximoATentar(pegarProximo());
        }
    }

    public String pegarProximo() {
        sorteios.addLast(sorteios.removeFirst());
        sorteioAtual = sorteios.peekFirst();
        return sorteioAtual.getId();
    }

    private void setJogadasRegulares(boolean b) {
        this.jogadaRegular = b;
    }

    /**
     * @return the jogadaRegular
     */
    public boolean isJogadaRegular() {
        return jogadaRegular;
    }

    public void bolaoDeEsportes(String id) throws IOException {
        tabelaBolao = new Hashtable(ordemJogadas.size());
        sorteios.addAll(ordemJogadas);
        sorteioAtual = sorteios.getFirst();
        numeroSorteio = new LinkedList();

        for (int i = 0; i < ordemJogadas.size(); i++) {
            this.cliente.solicitarNumeroBolaoEsportes(sorteioAtual.getId());
            while (true) {
                if (this.isNumeroValido()){
                    this.mudarSorteioAtual();
                    this.setNumeroValido(false);
                    break;
                }
            }

        }
        
        /* if (jogadorAtual.getId().equals(id) && this.tabelaBolao == null) {
            tabelaBolao = new Hashtable(ordemJogadas.size());
            sorteios.addAll(ordemJogadas);
            sorteioAtual = sorteios.getFirst();
        }
        if (sorteioAtual.getId().equals(id) && this.tabuleiro.participarBolaoEsportes() != 0) {

        }
         */
    }

    private void creditarSorteGrande(int valor) {
        this.banco.setSorteGrande(valor);
    }

    private void resgatarSorteGrande(String id) {
        jogadorAtual.getConta().creditar(banco.getSorteGrande());
        banco.resgatarPremio();
    }

    private void enviarNumerobolaoDeEsportes(String id) throws IOException {
        if (jogadorLocal.getId().equals(id)) {
            cliente.enviarRespostaBolaoEsportes(tabuleiro.participarBolaoEsportes());
        }
    }

    /**
     * @param jogadorLocal the jogadorLocal to set
     */
    public void setJogadorLocal(Pessoa jogadorLocal) {
        this.jogadorLocal = jogadorLocal;
    }

    private void respostaBolao(String num) throws IOException {
        if (this.jogadorLocal.getId().equals(jogadorAtual.getId())) {
            if (!tabelaBolao.contains(num) || num.equals("0")) {
                tabelaBolao.put(num, sorteioAtual.getId());
                this.setNumeroValido(true);
            }
            this.cliente.solicitarNumeroBolaoEsportes(sorteioAtual.getId());
        }

    }

    /**
     * @return the numeroValido
     */
    public boolean isNumeroValido() {
        return numeroValido;
    }

    /**
     * @param numeroValido the numeroValido to set
     */
    public void setNumeroValido(boolean numeroValido) {
        this.numeroValido = numeroValido;
    }

}
