/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import br.uefs.ecomp.jogodamesada.cliente.controller.ControllerComunicacao;
import br.uefs.ecomp.jogodamesada.cliente.view.JogoDaMesada;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Gilvanei
 */
public class Casa {

    private ArrayList<Carta> cartasSorteadas;
    private int posicaoArrayCartas;
    private ArrayList<Carta> cartas;
    private int qtdCartas;
    private Conta conta;
    private Pessoa pessoa;
    private double valorSorteGrande;
    private JogoDaMesada tela;
    private Carta objCarta;

    @XmlElement(name = "ID")
    private static int ID;//identificador;

    @XmlElement(name = "nome")
    private final String nome;

    @XmlElement(name = "descricao")
    private final String descricao;

    @XmlElement(name = "valor")
    private final double valor;

    public Casa(String nome, String descricao, String tipo) {
        ID++;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = 0;
        
        qtdCartas = 30;
        cartasSorteadas = new ArrayList<>();
        valorSorteGrande = 0;
        objCarta = new Carta();
    }

    public void setPessoaConta(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.conta = pessoa.getConta();
        JOptionPane.showMessageDialog(null, "ID: " + this.pessoa.getId() + " | Nome: " + this.pessoa.getNome() + " | Saldo: "
                + this.conta.getSaldo());
    }

    public Casa() {
        ID++;
        this.nome = null;
        this.descricao = null;
        this.valor = 0;
    }
    
    public void setTelaPrincipal(JogoDaMesada tela){
        this.tela = tela;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the descricao
     */
    public double getValor() {
        return valor;
    }
    
    public void setCartas(ArrayList<Carta> cartas){
        this.cartas = cartas;
    }

    public void getAcaoCasaCorreio(int qdt) {
        System.out.println("Correios");
        for(int i = 1; qdt < 0; i++) {
            cartasSorteadas.add(cartas.get(posicaoArrayCartas));
            tela.setBotoesCarta(i, cartasSorteadas.get(i - 1).getID());
            posicaoArrayCartas++;
            qdt--;
        }
    }

    /*private boolean contem(int num) {
        boolean rt = false;
        for (int i = 0; i < this.posicoesSorteadas.length; i++) {
            if (this.posicoesSorteadas[i] == num) {
                rt = true;
            }
        }
        return rt;
    }*/

    public void acaoCasaSelecionada(int num){
        if(num == 1){            
            tela.setBotoesCarta(num, 0);
        }else if(num == 2){
            tela.setBotoesCarta(num, 0);
        }else if(num == 3){
            tela.setBotoesCarta(num, 0);
        }
        
        objCarta.getAcaoCarta(cartasSorteadas.get(num - 1).getID());
    }
    
    
    public void embaralharCartas() {
        //Collections.shuffle(cartas);
    }

    public void getCasaPremio(Pessoa p) {
        Conta c = p.getConta();
        c.creditar(5000.00);
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaComprasENatureza() throws IOException {
        this.conta.debitar(200);
        ControllerComunicacao.getInstance().creditarSorteGrande(200);
    }

    public void getCasaBolaoDeEsportes() throws IOException {
        System.out.println("Bolão de esportes");
        ControllerComunicacao.getInstance().bolaoDeEsportes(pessoa.getId());
    }

 

    public void getCasaConcursoDeBandaDeRock() throws IOException {
        ControllerComunicacao.getInstance().concursoDeBandaDeRock(pessoa.getId());
    }

 

    public void getCasaFelizAniversario() throws IOException {
       // tela.setStatusLabel("Feliz Aniversario");
        System.out.println("Feliz Aniversario");
        ControllerComunicacao.getInstance().felizAniversario(pessoa.getId());
    }

    public void getCasaPremio() {
         JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
         this.pessoa.getConta().creditar(5000.0);
    }

    public void getCasaCompraEntreterimento() {
        
    }

    public void getCasaPraiaNoDomingo() throws IOException {
       if(this.pessoa.getConta().getSaldo() < 500){
           tela.emprestimo(500);
       }
       ControllerComunicacao.getInstance().creditarSorteGrande(500);
    }

    public void getCasaAchouUmComprador() {
       
    }

    public void getCasaAjudeAFloresta() throws IOException {
        if(this.pessoa.getConta().getSaldo() < 700){
           tela.emprestimo(700);
       }
       ControllerComunicacao.getInstance().creditarSorteGrande(700);
    }

    public void getCasaLanchonete() throws IOException {
       while(this.pessoa.getConta().getSaldo() < 800){
           tela.emprestimo(500);
       }
       ControllerComunicacao.getInstance().creditarSorteGrande(800);
    }

    public void getCasaNegocioDeOcasiao() {
       
    }

    public void getCasaComprasNoShopping() throws IOException {
       while(this.pessoa.getConta().getSaldo() < 1000){
           tela.emprestimo(1000);
       }
       ControllerComunicacao.getInstance().creditarSorteGrande(1000);
    }

    public void getCasaMaratonaBeneficiente() throws IOException {
        ControllerComunicacao.getInstance().maratonaBeneficiente(this.pessoa.getId());
    }

    public void getCasaDiaDaMesada() {
       this.pessoa.getConta().creditar(5000);
       if (conta.juros() > conta.getSaldo()){
           tela.emprestimo(1000);
       }
       conta.cobrarJuros();
     
       double valor = tela.pagarDividas();
       if (valor > conta.getSaldo()){
           tela.setStatusLabel("O Seu saldo Não é Suficiente Para Quitar a Divida");
       }
       else{
           conta.pagarDivida(valor);
       }
    }
    
}
