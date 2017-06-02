package br.uefs.ecomp.jogodamesada.cliente.controller;

import br.uefs.ecomp.jogodamesada.cliente.model.Carta;
import br.uefs.ecomp.jogodamesada.cliente.model.CartaXML;
import br.uefs.ecomp.jogodamesada.cliente.model.Casa;
import br.uefs.ecomp.jogodamesada.cliente.model.ClienteP2P;
import br.uefs.ecomp.jogodamesada.cliente.model.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gilvanei
 */
public class ControllerTabuleiro {

    private ArrayList<Pessoa> jogadores;    //Todos os jogadores da sala.
    private Carta CeE;                      //Compra e entretenimento.
    private Pessoa jogadorLocal;            //Jogador local da aplicação.
    private ClienteP2P clienteP2P;          //Referência para o objeto ClienteP2P
    private Casa casaDaPosicao;             //Referencia ao objeto Casa para recuperar 
                                            //as ações nas posições do tabuleiro.
    
    public ControllerTabuleiro() {
        CeE = null;
        this.jogadorLocal = new Pessoa();
        this.casaDaPosicao = new Casa();
        this.recuperarCartas();
        this.embaralharCartas();
    }
    
    public void setJogadorLocal(Pessoa p){
        this.jogadorLocal = p;
        this.casaDaPosicao.setPessoaConta(p);
    }

    public void setJogadores(ArrayList jogadores) {
        this.jogadores = jogadores;
    }

    public ArrayList getJogadores() {
        return this.jogadores;
    }
    
    public void setClienteP2P(ClienteP2P clienteP2P){
        this.clienteP2P = clienteP2P;
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
        cartas = (ArrayList) cart.LendoXML();        
    }

    public void getAcaoParaPosicao(int casa) {        
        switch (casa) {
            case 1:
                casaDaPosicao.getCasaPremio(this.jogadorLocal);
                break;
            case 2:
                casaDaPosicao.getCasaComprasENatureza();
                break;
            case 3:
                casaDaPosicao.getAcaoCasaCorreio(3);
                break;
            case 4:
                casaDaPosicao.getCasaSorteGrande();
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
            case 90:
                casaDaPosicao.getCasaPosicao09();
                break;
            case 10:
                casaDaPosicao.getCasaPosicao010();
                break;
            case 11:
                casaDaPosicao.getCasaPosicao11();
                break;
            case 15:
                //casaDaPosicao.getAcaoCasaCorreio(2);
                break;
            case 13:
                casaDaPosicao.getCasaPosicao13();
                break;
            case 14:
                casaDaPosicao.getCasaPosicao14();
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
        }
    }

    private void embaralharCartas() {
        Casa casa = new Casa();
        casa.embaralharCartas();
    }

    public void setJogadores(List temp) {
        List<Pessoa> t = temp;
        for (Pessoa p : t) {
            System.out.println(p.getId() + " - " + p.getNome());
        }
    }
}
