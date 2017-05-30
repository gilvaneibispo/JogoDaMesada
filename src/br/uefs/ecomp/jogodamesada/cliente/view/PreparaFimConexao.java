/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.view;

import br.uefs.ecomp.jogodamesada.cliente.controller.ControllerComunicacao;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
/**Classe que prepara açoes especificas quando um JFrame é fechado
 *
 * @author Rodrigo Brito Santos
 */
public class PreparaFimConexao {
    
    /**Metodo que inutiliza o botão de fechar do frame, e implementa uma classe 
     * anonima  WindowAdapter, sobreescrevendo o metodo windowClosing
     * que funciona como um ouvinte e executa a finalização da comunicação
     * entre cliente e servidor quando o ouvinte é ascionado
     * 
     * @param fj 
     */
    public static void preparaFimConexao(JFrame fj) {
        //Inutiliza o botão fechar do frame:
        fj.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fj.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                ControllerComunicacao cu = ControllerComunicacao.getInstance();
               // cu.finalizarConexao();

                JOptionPane.showMessageDialog(null, "MUITO OBRIGADO!");
                System.exit(0);   

            }
        });
    }
}
