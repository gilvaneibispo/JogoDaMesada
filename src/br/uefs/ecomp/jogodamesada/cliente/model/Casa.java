/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import br.uefs.ecomp.jogodamesada.cliente.view.JogoDaMesada;
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

    public void getCasaComprasENatureza() {
        this.conta.debitar(200);
        this.valorSorteGrande = this.valorSorteGrande + 200;
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
        //Enviar mensagem para os outros jogadores atualizar o seus valorSorteGrande's...
        //ver como o essa classe pode conhecer clienteP2P
    }

    public void getCasaSorteGrande() {
        this.conta.creditar(valorSorteGrande);
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu R$ " + 
                String.format("%2f", this.valorSorteGrande) + " por tirar a sorte grande!");
    }

    public void getCasaPosicao05() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao06() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao07() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao08() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao09() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao010() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao11() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao13() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao14() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao15() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao18() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao19() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao20() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao21() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao22() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao23() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao24() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao25() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao26() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao29() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }

    public void getCasaPosicao30() {
        JOptionPane.showMessageDialog(null, "Parabéns: Você recebeu um prêmio de R$ 5.000,00!");
    }
}
