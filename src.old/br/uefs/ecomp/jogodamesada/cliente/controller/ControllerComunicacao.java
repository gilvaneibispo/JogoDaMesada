/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.controller;

import br.uefs.ecomp.jogodamesada.cliente.model.Cliente;
import br.uefs.ecomp.jogodamesada.cliente.model.Pessoa;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author User
 */
public class ControllerComunicacao {

    /**
     * @return the c
     */
    public Cliente getC() {
        return c;
    }

    private Cliente c;
    private static ControllerComunicacao controller;

    /**
     * Contrutor da classe ControllerCliente,essa classe utiliza o padrão de
     * projeto singleton, com um contrutor privado que garante que apenas uma
     * instancia dessa classa possa ser criada,
     *
     */
    private ControllerComunicacao() {
        c = new Cliente();
    }
    
    public List<Pessoa> getJogadores(){
        return getC().getJogadores();
    }

    /**
     * Metodo e que retorna a instancia statica da classe caso ela ja exista, ou
     * cria uma nova instancia caso ela seja igual a null.
     *
     * @return ControllerComunicacao controller
     */

    public static ControllerComunicacao getInstance() {

        if (controller == null) {
            controller = new ControllerComunicacao();
        }
        return controller;
    }

    /**
     * Metodo responsavel por delegar a responsabilidade de conexão a classe
     * cliente e retornando sua resposta pra GUI
     *
     * @param ip host a se conectar
     * @param porta porta disponibilizada pelo servidor
     * @throws IOException exceção lançada caso não seja possivel estabelecer
     * uma conexão com o servidor
     */
    public void acessoServidor(String ip, String porta) throws IOException {
        getC().acessarServidor(ip, porta);
    }

    public int cadastrarUsuario(String nome, String senha) {
        return getC().cadastrarUsuario(nome, senha);
    }

    public int logar(String nome, String senha) throws IOException {
        return getC().logar(nome, senha);
    }

    public boolean conectarSala(String players, String periodo) throws IOException {
        return getC().conectarSala(players, periodo);
    }

    public void moverPeao(String id, int dado) throws IOException {
        getC().moverPeao(id, dado);
    }

}
