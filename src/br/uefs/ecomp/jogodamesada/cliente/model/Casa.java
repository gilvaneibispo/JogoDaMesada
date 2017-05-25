/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import java.util.ArrayList;
import java.util.Collections;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Gilvanei
 */
public class Casa {

    private ArrayList<Carta> cartasSorteadas;
    private int posicaoArrayCartas;
    private ArrayList<Carta> cartas;

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
        cartasSorteadas = new ArrayList<>();
    }

    public Casa() {
        ID++;
        this.nome = null;
        this.descricao = null;
        this.valor = 0;
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

    void getCasaPosicao01() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void getCasaPosicao02() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void getAcaoCasaCorreio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getAcaoCasaCorreio(int qdt) {       
        for (int i = 0; i < qdt; i++) {
            cartasSorteadas.add(cartas.get(posicaoArrayCartas));
            posicaoArrayCartas++;
        }
    }

    public void acaoCasaSelecionada(int i) {
        Carta nova = cartasSorteadas.get(i);
        nova.acaoCarta(nova.getID());
    }
    
    public void embaralharCartas(){
        //Collections.shuffle(cartas);
    }
}
