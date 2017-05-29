/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.view;

import br.uefs.ecomp.jogodamesada.JogoDaMesada;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gilvanei
 */
public class ConfigurarHost extends javax.swing.JFrame {

    private JogoDaMesa clsseMae;

    /**
     * Creates new form ConfigurarHost
     */
    public ConfigurarHost() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setClasseMae(JogoDaMesa tab) {
        this.clsseMae = tab;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        endIp = new javax.swing.JTextField();
        lobelIP = new javax.swing.JLabel();
        labelPorta = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        porta = new javax.swing.JTextField();

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

        jSeparator1.setBackground(new java.awt.Color(88, 196, 196));
        jSeparator1.setForeground(new java.awt.Color(88, 196, 196));

        endIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endIpActionPerformed(evt);
            }
        });
        endIp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                endIpKeyPressed(evt);
            }
        });

        lobelIP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lobelIP.setForeground(new java.awt.Color(255, 255, 255));
        lobelIP.setText("Endereço IP:");

        labelPorta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        labelPorta.setForeground(new java.awt.Color(255, 255, 255));
        labelPorta.setText("Porta:");

        titulo.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setText("Configurar Host");

        porta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addGap(0, 126, Short.MAX_VALUE)
                        .addComponent(btnConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelPrincipalLayout.createSequentialGroup()
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lobelIP)
                            .addComponent(labelPorta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endIp)
                            .addComponent(porta))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endIp, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lobelIP))
                .addGap(18, 18, 18)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPorta)
                    .addComponent(porta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            this.clsseMae.setHost(endIp.getText());
            this.clsseMae.setPorta(porta.getText());
            JOptionPane.showMessageDialog(this, "Novo host configurado!");
            this.setVisible(false);
        } catch (HeadlessException io) {
            JOptionPane.showMessageDialog(this, "Houve um erro, tente novamente! [" + io.getMessage() + "]");
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void endIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endIpActionPerformed

    private void portaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portaActionPerformed

    private void endIpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_endIpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_endIpKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JTextField endIp;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelPorta;
    private javax.swing.JLabel lobelIP;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JTextField porta;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
