package br.uefs.ecomp.jogodamesada.cliente.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Gilvanei
 */
public class Tabuleiro {    
   
    private ArrayList<Pessoa> jogadores;
    private Carta CeE; //Compra e entretenimento.

    public Tabuleiro() {
        CeE = null;
        this.recuperarCartas();
        this.embaralharCartas();
    }
    
    public void setJogadores(ArrayList jogadores){
        this.jogadores = jogadores;
    }
    
    public ArrayList getJogadores(){
        return this.jogadores;
    }

    /**
     * <strong>Recuperar Cartas: </strong>
     * Para recuperar as cartas do jogo que estão serializadas na instância de
     * LinkedList. É chamada no construtor dasta classe e é crucial para o
     * funcionamento do jogo.
     */
    private void recuperarCartas() {
        
        ArrayList<Carta> cartas = new ArrayList();

        CartaXML cart = new CartaXML();
        //this.cartas = (LinkedList) cart.LendoXML();
        cartas = (ArrayList) cart.LendoXML();

        for (Iterator it = cartas.iterator(); it.hasNext();) {
            Carta cartaAtual = (Carta) it.next();
        }
    }

 public void getAcaoParaPosicao(int casa) {/*
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
    
    private void embaralharCartas() {
        Casa casa = new Casa();
        casa.embaralharCartas();
    }

    public void setJogadores(List temp) {
        List<Pessoa> t = temp;
        for(Pessoa p : t){
            System.out.println(p.getId() + " - " + p.getNome());
        }
    }
}
