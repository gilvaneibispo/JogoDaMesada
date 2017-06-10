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
    private JogoDaMesada tabuleiro;
    private Pessoa jogadorAtual;
    private Pessoa sorteioAtual;
    private Pessoa jogadorLocal;
    private LinkedList<Pessoa> sorteios;
    private boolean jogadaRegular;
    private Cliente cliente;
    private Hashtable tabelaBolao;
    private Banco banco;
    private boolean numeroValido;
    private int idSala;
    private int duracao;
    
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
                //this.mudarJogadorAtual();
                break;
            case ProtocoloP2P.PERDEU_A_VEZ:
                this.mudarJogadorAtual();
                break;
            case ProtocoloP2P.FELIZ_ANIVERSARIO:
                this.felizAniversario(tokens.nextToken());
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
                this.iniciarConcursoDeBandaDeRock(pegarProximo());
                break;
            case ProtocoloP2P.SOLICITAR_NUMERO_BOLAO_DE_ESPORTES:
                //this.setJogadasRegulares(false);
                this.enviarNumerobolaoDeEsportes(tokens.nextToken());
                break;
            case ProtocoloP2P.CREDITAR_SORTE_GRANDE:
                this.creditarSorteGrande(Integer.parseInt(tokens.nextToken()));
                break;
            case ProtocoloP2P.RESGATAR_SORTE_GRANDE:
                this.resgatarSorteGrande(tokens.nextToken());
                break;
            case ProtocoloP2P.RESPONDER_NUMERO_BOLAO_DE_ESPORTES:
                this.respostaBolao(Integer.parseInt(tokens.nextToken()), tokens.nextToken());
                break;
            case ProtocoloP2P.PROXIMO_A_JOGAR:
                this.mudarJogadorAtual();
                break;
            case ProtocoloP2P.MARATONA_BENEFICIENTE:
                this.maratonaBeneficiente(tokens.nextToken());
                break;
            case ProtocoloP2P.CREDITAR_GANHADOR_MARATONA_BENEFICIENTE:
                this.creditarGanhadorSorteGrande(tokens.nextToken(), Double.parseDouble(tokens.nextToken()));
                break;
            case ProtocoloP2P.INFORMAR_GANHADOR_BOLAO_ESPORTES:
                this.creditarGanhadorbolaoesporte(tokens.nextToken(), Double.parseDouble(tokens.nextToken()));
                break;
        }
    }
    
    public void criaInstanciaPessoa(StringTokenizer tokens) {
        idSala = Integer.parseInt(tokens.nextToken());
        duracao = Integer.parseInt(tokens.nextToken());
        System.out.println("Id da Sala: " + getIdSala());
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
    
    public void mudarJogadorAtual() {
        ordemJogadas.addLast(ordemJogadas.removeFirst());
        jogadorAtual = ordemJogadas.peekFirst();
       if (jogadorAtual.isFim()){
           mudarJogadorAtual();
       }
    }
    
    public void mudarSorteioAtual() {
        sorteios.addLast(sorteios.removeFirst());
        sorteioAtual = sorteios.peekFirst();
    }
    
    private void setJogadorAtual(Pessoa get) {
        this.jogadorAtual = get;
    }
    
    private void felizAniversario(String id) {
        for (Pessoa p : ordemJogadas) {
            if (p.getId().equals(id)) {
                p.getConta().creditar((ordemJogadas.size() - 1) * 100.00);
                tabuleiro.setStatusLabel("Parabens Hj é o seu Aniversario");
            } else {
                if (p.getConta().getSaldo() < 100) {
                    if (this.tabuleiro.emprestimo(100.00)) {
                        
                    }
                }
                p.getConta().debitar(100.00);
                this.tabuleiro.getSaldo_label().setText("R$: " + this.jogadorLocal.getConta().getSaldo());
                tabuleiro.setStatusLabel("Aniversario de " + jogadorAtual.getNome());
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
                tabuleiro.setStatusLabel("O Player " + p.getNome() + " Se Desconectou da Partida!!");
                ordemJogadas.remove(p);
            }
            
        }
        
        if (ordemJogadas.size() == 1 && !jogadorLocal.getId().equals(id)) {
            tabuleiro.informarVencedor();
        }
    }
    
    private void iniciarConcursoDeBandaDeRock(String id) throws IOException {
        if (sorteioAtual == null) {
            System.out.println("Iniciei");
            sorteios.addAll(ordemJogadas);
            sorteioAtual = sorteios.getFirst();
        }
        System.err.println("Sorteio Atual" + sorteioAtual.getId());
        if (jogadorLocal.getId().equals(id)) {
            int dado = this.tabuleiro.ConcursoDeBandaDeRock();
            System.out.println("Dado sorteado" + dado);
            if (dado == 3) {
                jogadorLocal.getConta().creditar(1000.00);
                cliente.finalizarSorteios();
            } else {
                cliente.proximoATentar();
            }
        }
    }
    
    public String pegarProximo() {
        sorteios.addLast(sorteios.removeFirst());
        sorteioAtual = sorteios.peekFirst();
        return sorteioAtual.getId();
    }
    
    private void setJogadasRegulares(boolean b) {
        sorteioAtual = null;
        this.jogadaRegular = b;
    }

    /**
     * @return the jogadaRegular
     */
    public boolean isJogadaRegular() {
        return jogadaRegular;
    }
    
    public void bolaoDeEsportes(String id) throws IOException {
        System.out.println("Bolao Local" + Thread.currentThread().getId());
        tabelaBolao = new Hashtable(ordemJogadas.size());
        sorteios.addAll(ordemJogadas);
        sorteioAtual = sorteios.getFirst();
        
        for (int i = 0; i < ordemJogadas.size(); i++) {
            this.cliente.solicitarNumeroBolaoEsportes(sorteioAtual.getId());
            while (true) {
                if (this.isNumeroValido()) {
                    this.mudarSorteioAtual();
                    this.setNumeroValido(false);
                    break;
                }
            }
        }
        int dado = tabuleiro.sortearGanhadorBolao();
        while (!tabelaBolao.containsKey(dado)) {
            dado = tabuleiro.sortearGanhadorBolao();
        }
        System.out.println("OBjeto Para o Valor sorteado"+tabelaBolao.get(dado));
        cliente.informarGanhadorBolaoDeEsportes((String) tabelaBolao.get(dado), 1000.00 + (100.00 * tabelaBolao.size()));
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
            System.out.println("Enviando Resposta" + Thread.currentThread().getId());
            System.out.println("enviarNumerobolaoDeEsportes");
            int resposta = tabuleiro.participarBolaoEsportes();
            if (resposta != 0) {
                jogadorLocal.getConta().debitar(100.00);
                cliente.enviarRespostaBolaoEsportes(tabuleiro.participarBolaoEsportes(), jogadorLocal.getId());
            }
        }
    }

    /**
     * @param jogadorLocal the jogadorLocal to set
     */
    public void setJogadorLocal(Pessoa jogadorLocal) {
        this.jogadorLocal = jogadorLocal;
    }
    
    private void respostaBolao(int num, String id) throws IOException {
        System.out.println("Analisando resposta" + Thread.currentThread().getId());
        if (this.jogadorLocal.getId().equals(jogadorAtual.getId())) {
            if (!tabelaBolao.containsKey(num) || num == 0) {
                System.out.println("Resposta Bolão");
                tabelaBolao.put(num, id);
                System.out.println(tabelaBolao.size());
                this.setNumeroValido(true);
            } else {
                this.cliente.solicitarNumeroBolaoEsportes(sorteioAtual.getId());
                System.out.println("aqui nÃO É NULL");
            }
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
    
    private void maratonaBeneficiente(String id) throws IOException {
        if (!jogadorLocal.getId().equals(id)) {
            double valor = (tabuleiro.maratonaBenefiente() * 100);
            if (valor > jogadorLocal.getConta().getSaldo()) {
                tabuleiro.emprestimo(valor);
            }
            jogadorLocal.getConta().debitar(valor);
            tabuleiro.atalizarSaldo();
            this.cliente.creditarGanhadorDaMaratonaBeneficiente(id, valor);
        }
    }
    
    private synchronized void creditarGanhadorSorteGrande(String id, double valor) {
        if (this.jogadorLocal.getId().equals(id)) {
            jogadorLocal.getConta().creditar(valor);
            tabuleiro.atalizarSaldo();
            System.out.println("P2P" + jogadorLocal.getConta().getSaldo());
            System.out.println("Creditando" + valor + "Na conta" + jogadorLocal.getId());
        }
    }

    /**
     * @return the idSala
     */
    public int getIdSala() {
        return idSala;
    }
    
    private void creditarGanhadorbolaoesporte(String id, double valor) {
        if (jogadorLocal.getId().equals(id)) {
            jogadorLocal.getConta().creditar(valor);
            tabuleiro.atalizarSaldo();
        }
    }

    /**
     * @return the duracao
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * @param duracao the duracao to set
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
}
