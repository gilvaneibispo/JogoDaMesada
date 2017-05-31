/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.view;

import br.uefs.ecomp.jogodamesada.cliente.controller.ControllerComunicacao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gilvanei
 */
public class ConfigurandoSala extends JFrame {

    private JogoDaMesa classeMae;

    /**
     * Creates new form ConfigurandoSala
     */
    public ConfigurandoSala() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setClasseMae(JogoDaMesa classeMae) {
        this.classeMae = classeMae;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lobelIP = new javax.swing.JLabel();
        labelPorta = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        duracao = new javax.swing.JComboBox<>();
        jogadores = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        painelPrincipal.setBackground(new java.awt.Color(36, 46, 60));

        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnConfirmar.setText("Confirmar Dados");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        lobelIP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lobelIP.setForeground(new java.awt.Color(255, 255, 255));
        lobelIP.setText("Número de Jogadores:");

        labelPorta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        labelPorta.setForeground(new java.awt.Color(255, 255, 255));
        labelPorta.setText("Duração da Partida:");

        titulo.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setText("Que sala você quer?");

        duracao.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        duracao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha", "01 mês", "02 meses", "03 meses" }));

        jogadores.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jogadores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha...", "02 Jogadores", "03 Jogadores", "04 Jogadores", "05 Jogadores", "06 Jogadores" }));

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lobelIP)
                            .addComponent(labelPorta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jogadores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(duracao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                        .addGap(0, 126, Short.MAX_VALUE)
                        .addComponent(btnConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo)
                .addGap(71, 71, 71))
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lobelIP)
                    .addComponent(jogadores, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPorta)
                    .addComponent(duracao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja sair?", "Sair do Jogo", 2, 0) == 0) {
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed

        try {
            ControllerComunicacao c = ControllerComunicacao.getInstance();
            if (jogadores.getSelectedIndex() == 0 || duracao.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Por favor: Selecione todos os campos corretamente!");
            } else {
                JOptionPane.showMessageDialog(this, "Aguarde enquanto completamos a sala!");
                this.dispose();
                if (c.conectarSala("" + (jogadores.getSelectedIndex() + 1), "" + duracao.getSelectedIndex())) {
                    JOptionPane.showMessageDialog(this, "Sala encontrata!");
                    this.classeMae.setLogodo();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ConfigurandoSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<String> duracao;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> jogadores;
    private javax.swing.JLabel labelPorta;
    private javax.swing.JLabel lobelIP;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}