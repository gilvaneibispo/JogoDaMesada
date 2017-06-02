/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.controller;

import br.uefs.ecomp.jogodamesada.cliente.model.Cliente;
import br.uefs.ecomp.jogodamesada.cliente.model.ClienteP2P;
import br.uefs.ecomp.jogodamesada.cliente.model.Pessoa;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author User
 */
public class ControllerComunicacao {
    
    private Cliente cliente;
    private static ControllerComunicacao controller;

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
  
    /**
     * Contrutor da classe ControllerCliente,essa classe utiliza o padrão de
     * projeto singleton, com um contrutor privado que garante que apenas uma
     * instancia dessa classa possa ser criada,
     *
     */
    private ControllerComunicacao() {
        cliente = new Cliente();
    }
    
    public List<Pessoa> getJogadores(){
        return getCliente().getJogadores();
    }
    
    public ClienteP2P getClienteP2P(){
        return this.getCliente().getClienteP2P();
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
        getCliente().acessarServidor(ip, porta);
    }

    public int cadastrarUsuario(String nome, String senha) {
        return getCliente().cadastrarUsuario(nome, senha);
    }

    public int logar(String nome, String senha) throws IOException {
        return getCliente().logar(nome, senha);
    }

    public boolean conectarSala(String players, String periodo) throws IOException {
        return getCliente().conectarSala(players, periodo);
    }

    public void moverPeao(String id, int dado) throws IOException {
        getCliente().moverPeao(id, dado);
    }

    public void perdeuSuaVez() throws IOException {
        cliente.perdeuSuaVez();
    }
    public void felizAniversario(int id) throws IOException{
        cliente.felizAniversario(id);
    }

}
