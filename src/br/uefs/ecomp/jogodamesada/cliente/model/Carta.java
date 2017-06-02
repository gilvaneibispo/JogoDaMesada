package br.uefs.ecomp.jogodamesada.cliente.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Gilvanei
 */
public class Carta {

    @XmlElement(name = "ID")
    private static int ID;//identificador;

    @XmlElement(name = "nome")
    private final String nome;

    @XmlElement(name = "descricao")
    private final String descricao;

    @XmlElement(name = "tipo")
    private final int tipo; // 1 - Correios; 2 - Compra e entretenimento.

    public Carta(String nome, String descricao, int tipo) {        
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Carta() {
        this.nome = null;
        this.descricao = null;
        this.tipo = 1;
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
    
    public int getID(){
        return ID;
    }
    
    public void acaoCarta(int carta){/*
        Casa casaDaPosicao = null;
        switch (casa) {
            case 1:
                casaDaPosicao.getCasaPosicao01();
                break;
            case 2:
                casaDaPosicao.getCasaPosicao02();
                break;
            case 3:
                casaDaPosicao.getAcaoCasaCorreio(3);
                break;
            case 4:
                casaDaPosicao.getCasaPosicao03();
                break;
            case 5:
                casaDaPosicao.getCasaPosicao05();
                break;
            case 6:
                casaDaPosicao.getCasaPosicao06();
                break;
            case 7:
                casaDaPosicao.getCasaPosicao07();
                break;
            case 8:
                casaDaPosicao.getCasaPosicao08();
                break;
            case 9:
                casaDaPosicao.getCasaPosicao09();
                break;
            case 10:
                casaDaPosicao.getCasaPosicao010();
                break;
            case 11:
                casaDaPosicao.getCasaPosicao11();
                break;
            case 12:
                casaDaPosicao.getAcaoCasaCorreio(2);
                break;
            case 13:
                casaDaPosicao.getCasaPosicao13();
                break;
            case 14:
                casaDaPosicao.getCasaPosicao14();
                break;
            case 15:
                casaDaPosicao.getAcaoCasaCorreio(2);
                break;
            case 16:
                casaDaPosicao.getCasaPosicao15();
                break;
            case 17:
                casaDaPosicao.getAcaoCasaCorreio(1);
                break;
            case 18:
                casaDaPosicao.getCasaPosicao18();
                break;
            case 19:
                casaDaPosicao.getCasaPosicao19();
                break;
            case 20:
                casaDaPosicao.getCasaPosicao20();
                break;
            case 21:
    casaDaPosicao.getCasaPosicao21();
                break;
            case 22:
                casaDaPosicao.getCasaPosicao22();
                break;
            case 23:
                casaDaPosicao.getCasaPosicao23();
                break;
            case 24:
                casaDaPosicao.getCasaPosicao24();
                break;
            case 25:
                casaDaPosicao.getCasaPosicao25();
                break;
            case 26:
                casaDaPosicao.getCasaPosicao26();
                break;
            case 27:
                casaDaPosicao.getAcaoCasaCorreio(3);
                break;
            case 28:
                casaDaPosicao.getAcaoCasaCorreio(1);
                break;
            case 29:
                casaDaPosicao.getCasaPosicao29();
                break;
            case 30:
                casaDaPosicao.getCasaPosicao30();
                break;
        }*/
    }

    void getAcaoCarta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
