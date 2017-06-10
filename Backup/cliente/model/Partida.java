/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import br.uefs.ecomp.jogodamesada.cliente.controller.ControllerComunicacao;
import java.util.List;

/**
 *
 * @author User
 */
public class Partida {
    private static Partida partida;

    private Partida() {
      
    }


    /**
     * Metodo e que retorna a instancia statica da classe caso ela ja exista, ou
     * cria uma nova instancia caso ela seja igual a null.
     *
     * @return ControllerComunicacao controller
     */

    public static Partida getInstance() {

        if (partida == null) {
            partida = new Partida();
        }
        return partida;
    }

}
