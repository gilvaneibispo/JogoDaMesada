package br.uefs.ecomp.jogodamesada.cliente.controller;

import br.uefs.ecomp.jogodamesada.cliente.model.Carta;
import br.uefs.ecomp.jogodamesada.cliente.model.CartaXML;
import br.uefs.ecomp.jogodamesada.cliente.model.Casa;
import br.uefs.ecomp.jogodamesada.cliente.model.ClienteP2P;
import br.uefs.ecomp.jogodamesada.cliente.model.Pessoa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gilvanei
 */
public class ControllerAcoesTabuleiro {

    private ArrayList<Pessoa> jogadores;    //Todos os jogadores da sala.
    private Carta CeE;                      //Compra e entretenimento.
    private Pessoa jogadorLocal;            //Jogador local da aplicação.
    private ClienteP2P clienteP2P;          //Referência para o objeto ClienteP2P
    private Casa casaDaPosicao;             //Referencia ao objeto Casa para recuperar 
    //as ações nas posições do tabuleiro.

    public ControllerAcoesTabuleiro() {
        CeE = null;
        this.jogadorLocal = new Pessoa();
        this.casaDaPosicao = new Casa();
        this.recuperarCartas();
        this.embaralharCartas();
    }

    public void setJogadorLocal(Pessoa p) {
        this.jogadorLocal = p;
        this.casaDaPosicao.setPessoaConta(p);
    }

    public void setJogadores(ArrayList jogadores) {
        this.jogadores = jogadores;
    }

    public ArrayList getJogadores() {
        return this.jogadores;
    }

    public void setClienteP2P(ClienteP2P clienteP2P) {
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

    public void getAcaoParaPosicao(int casa) throws IOException {
        switch (casa) {
            case 1 | 11 | 19 | 22:
                casaDaPosicao.getAcaoCasaCorreio(1);
                break;
            case 2:
                casaDaPosicao.getCasaPremio();
                break;
            case 3 | 16:
                casaDaPosicao.getAcaoCasaCorreio(3);
                break;
            case 4:
                casaDaPosicao.getCasaCompraEntreterimento();
                break;
            case 15:
                casaDaPosicao.getCasaCompraEntreterimento();
                break;
            case 25:
                casaDaPosicao.getCasaCompraEntreterimento();
                break;
            case 5 | 24:
                casaDaPosicao.getAcaoCasaCorreio(2);
                break;
            case 6 | 20:
                casaDaPosicao.getCasaBolaoDeEsportes();
                break;
            case 7:
                casaDaPosicao.getCasaPraiaNoDomingo();
                break;
            case 8:
                casaDaPosicao.getCasaConcursoDeBandaDeRock();
                break;
            case 9:
                casaDaPosicao.getCasaAchouUmComprador();
                break;
            case 17:
                casaDaPosicao.getCasaAchouUmComprador();
                break;
            case 23:
                casaDaPosicao.getCasaAchouUmComprador();
                break;
            case 26:
                casaDaPosicao.getCasaAchouUmComprador();
                break;
            case 10:
                casaDaPosicao.getCasaFelizAniversario();
                break;
            case 12:
                casaDaPosicao.getCasaBolaoDeEsportes();
                break;
            case 13:
                casaDaPosicao.getCasaBolaoDeEsportes();
                break;
            case 14:
                casaDaPosicao.getCasaAjudeAFloresta();
                break;
            case 18:
                casaDaPosicao.getCasaLanchonete();
                break;
            case 21:
                casaDaPosicao.getCasaNegocioDeOcasiao();
                break;
            case 27:
                casaDaPosicao.getCasaBolaoDeEsportes();
                break;
            case 28:
                casaDaPosicao.getCasaComprasNoShopping();
                break;
             case 30:
                casaDaPosicao.getCasaMaratonaBeneficiente();
                break;
             
             case 32:
                casaDaPosicao.getCasaDiaDaMesada();
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
