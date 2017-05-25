/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.controller;

import br.uefs.ecomp.jogodamesada.cliente.model.Cliente;
import java.io.IOException;

/**
 *
 * @author User
 */
public class ControllerComunicacao {
      private Cliente c;
        private static ControllerComunicacao controller; 
    
    /**Contrutor da classe ControllerCliente,essa classe utiliza o padrão de projeto
     * singleton, com um contrutor privado que garante que apenas uma instancia dessa
     * classa possa ser criada, 
     * 
     */
    private ControllerComunicacao(){
        c = new Cliente();
    }
    /**Metodo e que retorna a instancia statica da classe caso ela ja
     * exista, ou cria uma nova instancia caso ela seja igual a null.
     * 
     * @return ControllerComunicacao controller
     */
    
     public static ControllerComunicacao getInstance() {

        if (controller == null) {
            controller = new ControllerComunicacao();
        }
        return controller;
    }
     
    /**Metodo  responsavel por delegar a responsabilidade de conexão a
     * classe cliente e retornando sua resposta pra GUI
    * 
    * @param ip host a se conectar
    * @param porta porta disponibilizada pelo servidor
    * @throws IOException exceção lançada caso não seja possivel estabelecer uma conexão
    * com o servidor
    */
   
    public void acessoServidor(String ip, String porta) throws IOException{
         c.acessarServidor(ip, porta);
    }

 

    public int cadastrarUsuario(String nome, String senha) {
        return c.cadastrarUsuario(nome, senha);
    }

    public int logar(String nome, String senha) throws IOException {
       return c.logar(nome, senha);
    }

    public  boolean conectarSala(String players, String periodo) throws IOException {
         return c.conectarSala(players, periodo);
    }
    
   
}
