/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import br.uefs.ecomp.jogodamesada.cliente.controller.ControllerComunicacao;
import br.uefs.ecomp.jogodamesada.cliente.view.JogoDaMesada;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Gilvanei
 */
public class Casa {

    private List<Carta> cartasSorteadas;
    private int posicaoArrayCartas;
    private ArrayList<Carta> cartas;
    private ArrayList<Casa> casas;
    private Conta conta;
    private Pessoa pessoa;
    private double valorSorteGrande;
    private JogoDaMesada tela;
    private final Carta objCarta;
    private Properties pro;

    //@XmlElement(name = "ID")
    private int ID;//identificador;

    @XmlElement(name = "nome")
    private final String nome;

    @XmlElement(name = "descricao")
    private final String descricao;

    public Casa(String nome, String descricao) {
        ID = 0;
        this.nome = nome;
        this.descricao = descricao;

        cartasSorteadas = new ArrayList<>();
        valorSorteGrande = 0;
        posicaoArrayCartas = 0;
        objCarta = new Carta();
    }

    public Casa() {
        ID = 0;
        this.nome = null;
        this.descricao = null;

        cartasSorteadas = new ArrayList<>();
        valorSorteGrande = 0;
        posicaoArrayCartas = 0;
        objCarta = new Carta();
    }

    public void setPessoaConta(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.conta = pessoa.getConta();
    }

    public void setCasas(ArrayList casas) {
        this.casas = casas;
    }

    /* 
    Os métodos getteres abaixo são usados para gerar os campos no XML.
    A anotação @XmlElement é resposável por atribuir um nome à a tag xml
    é associá-la com o campo retornado no método.
     */
    /**
     * @return nome da casa.
     */
    //@XmlElement(name = "nome")
    public String getNome() {
        return nome;
    }

    /**
     * @return o ID da casa.
     */
    //@XmlElement(name = "ID")
    public int getID() {
        return ID;
    }

    /**
     * @return a descrição da casa.
     */
    //@XmlElement(name = "descricao")
    public String getDescricao() {
        return descricao;
    }

    public ArrayList<Carta> getCartas() {
        return this.cartas;
    }

    public void setTelaPrincipal(JogoDaMesada tela) {
        this.tela = tela;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public void getAcaoCasaCorreio(int qtd, int casa) throws IOException {
        System.out.println("TAMANHO CARTAS: " + cartas.size());
        try {
            for (int i = 1; i < qtd; i++) {
                System.out.println("Posição: " + posicaoArrayCartas);
                Carta tempCarta = cartas.get(posicaoArrayCartas);
                System.out.println(tempCarta);
                cartasSorteadas.add(tempCarta);
                tela.setBotoesCarta(i, tempCarta.getID());
                System.setProperty("user.jogodamesada.carta0" + i, Helpper.toString(tempCarta.getID()));
                posicaoArrayCartas++;
            }

            System.out.println("1P: " + System.getProperty("user.jogodamesada.carta01")
                    + " 2P: " + System.getProperty("user.jogodamesada.carta02")
                    + " 3P: " + System.getProperty("user.jogodamesada.carta03"));
        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println(ioobe.getMessage());
        }
    }

    public void acaoCasaSelecionada(int botao) throws IOException {// throws IOException {
        Carta temp = new Carta();

        switch (botao) {
            case 1:
                temp.acaoCarta(Integer.parseInt(System.getProperty("user.jogodamesada.carta01")));
                tela.setBotoesCarta(botao, 0);
                break;
            case 2:
                temp.acaoCarta(Integer.parseInt(System.getProperty("user.jogodamesada.carta02")));
                tela.setBotoesCarta(botao, 0);
                break;
            case 3:
                temp.acaoCarta(Integer.parseInt(System.getProperty("user.jogodamesada.carta03")));
                tela.setBotoesCarta(botao, 0);
                break;
            default:
                break;
        }
        //int get = botao - 1;
        //objCarta.getAcaoCarta(cartasSorteadas.get(get).getID());
    }

    public void embaralharCartas() {
        Collections.shuffle(cartas);
    }

    private void setDadosCartaNaInterface(int idCasa) {
        //System.out.println(casas.size());
        //tela.setNomeCasaLabel(this.casas.get(idCasa).getNome());
        //tela.setDescricaoCasaLabel(this.casas.get(idCasa).getDescricao());
    }

    /*
    Todos os métodos abaixo são responsáveis por executar a lógica de cada casa.
    Eles que são responsáveis por pelo que é executado assim que o peão para em
    uma casa do tabuleiro.
     */
    public void getCasaSorteGrande(Pessoa jogador) throws IOException {
        tela.setNomeCasaLabel("Sorte Grande");
        tela.setDescricaoCasaLabel("Você ganhou o bolão sorte grande");
        ControllerComunicacao.getInstance().resgatarSorteGrande(jogador.getId());
        System.out.println("Voce Teve A Sorte Grande!!");
    }

    public void getCasaPremio(Pessoa p, int idCasa) {
        Conta c = p.getConta();
        c.creditar(5000.00);
        this.setDadosCartaNaInterface(idCasa);
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaComprasENatureza(int casa) throws IOException {
        this.conta.debitar(200);
        this.setDadosCartaNaInterface(casa);
        ControllerComunicacao.getInstance().creditarSorteGrande(200);
    }

    public void getCasaBolaoDeEsportes(int casa) throws IOException {
        System.out.println("Bolão de esportes");
        this.setDadosCartaNaInterface(casa);
        ControllerComunicacao.getInstance().bolaoDeEsportes(pessoa.getId());
    }

    public void getCasaConcursoDeBandaDeRock(int casa) throws IOException {
        this.setDadosCartaNaInterface(casa);
        ControllerComunicacao.getInstance().concursoDeBandaDeRock(pessoa.getId());
    }

    public void getCasaFelizAniversario(int casa) throws IOException {
        this.setDadosCartaNaInterface(casa);
        System.out.println("Feliz Aniversario");
        ControllerComunicacao.getInstance().felizAniversario(pessoa.getId());
    }

    public void getCasaPremio(int casa) {
        this.setDadosCartaNaInterface(casa);
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
        this.pessoa.getConta().creditar(5000.0);
    }

    public void getCasaCompraEntreterimento(int casa) {
        this.setDadosCartaNaInterface(casa);
    }

    public void getCasaPraiaNoDomingo(int casa) throws IOException {
        this.setDadosCartaNaInterface(casa);
        if (this.pessoa.getConta().getSaldo() < 500) {
            tela.emprestimo(500);
        }
        ControllerComunicacao.getInstance().creditarSorteGrande(500);
    }

    public void getCasaAchouUmComprador(int casa) {
        this.setDadosCartaNaInterface(casa);
    }

    public void getCasaAjudeAFloresta(int casa) throws IOException {
        this.setDadosCartaNaInterface(casa);
        if (this.pessoa.getConta().getSaldo() < 700) {
            tela.emprestimo(1000);
        }
        ControllerComunicacao.getInstance().creditarSorteGrande(700.0);
    }

    public void getCasaLanchonete(int casa) throws IOException {
        this.setDadosCartaNaInterface(casa);
        while (this.pessoa.getConta().getSaldo() < 800) {
            tela.emprestimo(1000);
        }
        ControllerComunicacao.getInstance().creditarSorteGrande(800.0);
    }

    public void getCasaNegocioDeOcasiao(int casa) {
        this.setDadosCartaNaInterface(casa);
    }

    public void getCasaComprasNoShopping(int casa) throws IOException {
        this.setDadosCartaNaInterface(casa);
        while (this.pessoa.getConta().getSaldo() < 1000) {
            tela.emprestimo(1000);
        }
        ControllerComunicacao.getInstance().creditarSorteGrande(1000.0);
    }

    public void getCasaMaratonaBeneficiente(int casa) throws IOException {
        this.setDadosCartaNaInterface(casa);
        ControllerComunicacao.getInstance().maratonaBeneficiente(this.pessoa.getId());
    }

    public void getCasaDiaDaMesada(int casa) {
        // this.setDadosCartaNaInterface(casa);
        this.pessoa.getConta().creditar(5000);
        if (conta.juros() > conta.getSaldo()) {
            tela.emprestimo(1000);
        }
        conta.cobrarJuros();

        double valor = tela.pagarDividas();
        if (valor > conta.getSaldo()) {
            tela.setStatusLabel("O Seu saldo Não é Suficiente Para Quitar a Divida");
        } else {
            conta.pagarDivida(valor);
        }
    }

    /**
     * @return the pro
     */
    public Properties getPro() {
        return pro;
    }

}
