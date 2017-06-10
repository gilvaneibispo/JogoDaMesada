package br.uefs.ecomp.jogodamesada.cliente.controller;

import br.uefs.ecomp.jogodamesada.cliente.model.Carta;
import br.uefs.ecomp.jogodamesada.cliente.model.CartaXML;
import br.uefs.ecomp.jogodamesada.cliente.model.Casa;
import br.uefs.ecomp.jogodamesada.cliente.model.CasaXML;
import br.uefs.ecomp.jogodamesada.cliente.model.ClienteP2P;
import br.uefs.ecomp.jogodamesada.cliente.model.Pessoa;
import br.uefs.ecomp.jogodamesada.cliente.view.JogoDaMesada;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gilvanei
 */
public class ControllerTabuleiro {

    private ArrayList<Pessoa> jogadores;    //Todos os jogadores da sala.
    private final Carta CeE;                      //Compra e entretenimento.
    private Pessoa jogadorLocal;            //Jogador local da aplicação.
    private ClienteP2P clienteP2P;          //Referência para o objeto ClienteP2P
    private ArrayList casas;
    private ArrayList cartas;
    private JogoDaMesada mesa;              //
    private final Casa casaDaPosicao;             //Referencia ao objeto Casa para recuperar 
    //as ações nas posições do tabuleiro.

    public ControllerTabuleiro() {
        CeE = null;
        this.jogadorLocal = new Pessoa();
        this.casaDaPosicao = new Casa();
        this.recuperarCartas();
        this.recuperarCasas();
        this.embaralharCartas();
    }

    public void setJogadorLocal(Pessoa p) {
        this.jogadorLocal = p;
        this.casaDaPosicao.setPessoaConta(p);
    }

    public void setJogadores(ArrayList jogadores) {
        this.jogadores = jogadores;
    }

    public void setTela(JogoDaMesada mesa) {
        this.mesa = mesa;
        casaDaPosicao.setTelaPrincipal(mesa);
    }

    public ArrayList getJogadores() {
        return this.jogadores;
    }

    public void setClienteP2P(ClienteP2P clienteP2P) {
        this.clienteP2P = clienteP2P;
    }

    /**
     * <strong>Recuperar Casas: </strong>
     * Para recuperar as casas do jogo que estão salvas em XML e guarda em List.
     * É chamada no construtor dasta classe e é crucial para ofuncionamento do
     * jogo.
     */
    private void recuperarCasas() {

        CasaXML casa = new CasaXML();
        ArrayList<Casa> tempCasas = (ArrayList<Casa>) casa.LendoXML();
        this.casas = tempCasas;
        this.casaDaPosicao.setCasas(tempCasas);
    }

    /**
     * <strong>Recuperar Cartas: </strong>
     * Para recuperar as cartas do jogo que estão serializadas na instância de
     * LinkedList. É chamada no construtor dasta classe e é crucial para o
     * funcionamento do jogo.
     */
    private void recuperarCartas() {

        CartaXML carta = new CartaXML();
        ArrayList<Carta> tempCartas = (ArrayList<Carta>) carta.LendoXML();
        this.cartas = tempCartas;
        this.casaDaPosicao.setCartas(tempCartas);
    }

    public void getAcaoSorteGrande() throws IOException {
        casaDaPosicao.getCasaSorteGrande(jogadorLocal);
    }

    public void getAcaoParaPosicao(int casa) throws IOException {
        System.err.println("Passou casa " + casa);
        switch (casa) {
            case 1://| 11 | 19 | 22:
                casaDaPosicao.getAcaoCasaCorreio(1, casa);
                break;
            case 2:
                casaDaPosicao.getCasaPremio(casa);
                break;
            case 3:// | 16:
                casaDaPosicao.getAcaoCasaCorreio(3, casa);
                break;
            case 4:
                casaDaPosicao.getCasaCompraEntreterimento(casa);
                break;
            case 15:
                casaDaPosicao.getCasaCompraEntreterimento(casa);
                break;
            case 25:
                casaDaPosicao.getCasaCompraEntreterimento(casa);
                break;
            case 5 | 24:
                casaDaPosicao.getAcaoCasaCorreio(2, casa);
                break;
            case 6 | 20:
                casaDaPosicao.getCasaBolaoDeEsportes(casa);
                break;
            case 7:
                casaDaPosicao.getCasaPraiaNoDomingo(casa);
                break;
            case 8:
                casaDaPosicao.getCasaConcursoDeBandaDeRock(casa);
                break;
            case 9:
                casaDaPosicao.getCasaAchouUmComprador(casa);
                break;
            case 17:
                casaDaPosicao.getCasaAchouUmComprador(casa);
                break;
            case 23:
                casaDaPosicao.getCasaAchouUmComprador(casa);
                break;
            case 26:
                casaDaPosicao.getCasaAchouUmComprador(casa);
                break;
            case 10:
                casaDaPosicao.getCasaFelizAniversario(casa);
                break;
            case 12:
                casaDaPosicao.getCasaBolaoDeEsportes(casa);
                break;
            case 13:
                casaDaPosicao.getCasaBolaoDeEsportes(casa);
                break;
            case 14:
                casaDaPosicao.getCasaAjudeAFloresta(casa);
                break;
            case 18:
                casaDaPosicao.getCasaLanchonete(casa);
                break;
            case 21:
                casaDaPosicao.getCasaNegocioDeOcasiao(casa);
                break;
            case 27:
                casaDaPosicao.getCasaBolaoDeEsportes(casa);
                break;
            case 28:
                casaDaPosicao.getCasaComprasNoShopping(casa);
                break;
            /* case 29:
                casaDaPosicao.getCasaMaratonaBeneficiente();
                break;
             */
            case 30:
                casaDaPosicao.getCasaDiaDaMesada(casa);
                break;
        }
    }

    private void embaralharCartas() {
        if (casaDaPosicao.getCartas() != null || !casaDaPosicao.getCartas().isEmpty()) {
            casaDaPosicao.embaralharCartas();
        }
    }

    public void setJogadores(List<Pessoa> temp) {
        this.jogadores = (ArrayList<Pessoa>) temp;
    }
}
