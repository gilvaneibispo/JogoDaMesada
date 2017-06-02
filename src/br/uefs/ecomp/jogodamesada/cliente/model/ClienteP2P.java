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

    public ClienteP2P(InetAddress address) throws IOException {
        this.address = address;
        this.ordemJogadas = new LinkedList<>();

    }

    public List<Pessoa> getJogadores() {
        for (Pessoa p : ordemJogadas) {
            System.out.println(p.getId() + " - " + p.getNome());
        }
        return this.ordemJogadas;
    }

    public void jogadas(String mensagem) {
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
                JOptionPane.showMessageDialog(tabuleiro, "Desculpa: Você perdeu a vez de jogar!");
                this.mudarJogadorAtual();
                break;
            case ProtocoloP2P.FELIZ_ANIVERSARIO:
                this.felizAniversario(Integer.parseInt(tokens.nextToken()));
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
        if (nome.equals(this.jogadorAtual.getNome())) {
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
}
