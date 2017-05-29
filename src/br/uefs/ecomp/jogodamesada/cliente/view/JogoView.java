/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.view;

import java.awt.Component;
import java.awt.HeadlessException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Gilvanei
 */
public class JogoView extends JFrame {

    private int xAtual, yAtual;
    JLabel lEsp = new JLabel(new ImageIcon(getClass().getResource("..\\image\\explosao.png")));
    JLabel lCarroUm = new JLabel(new ImageIcon(getClass().getResource("..\\image\\carro1.png")));
    JLabel lCarroDois = new JLabel(new ImageIcon(getClass().getResource("..\\image\\carro2.png")));

    public JogoView() throws HeadlessException {
        this.configuraJanela();
        calculaDistancia(15);
        //xAtual = 0;
        //yAtual = 0;
    }

    public void calculaDistancia(int num) {
        //cada casa 150px;
        int dist = num * 150;

        xAtual = xAtual + dist;

        Movimento move = new Movimento();
        move.setValor(dist);
        move.start();
    }

    public void setPosicao(int posicao) {

    }

    private void configuraJanela() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);

        lCarroUm.setBounds(20, 0, 100, 50);
        lCarroDois.setBounds(1200, 0, 100, 50);

        lEsp.setVisible(false);

        add(lCarroUm);
        add(lCarroDois);
        add(lEsp);
    }

    public boolean bateu(Component a, Component b) {
        int aX = a.getX();
        int aY = a.getY();
        int ladoDireitoA = aX + a.getWidth();
        int ladoEsquerdoA = aX;
        int ladoBaixoA = aY + a.getHeight();
        int ladoCimaA = aY;

        int bX = b.getX();
        int bY = b.getY();
        int ladoDireitoB = bX + b.getWidth();
        int ladoEsquerdoB = bX;
        int ladoBaixoB = bY + b.getHeight();
        int ladoCimaB = bY;

        boolean bateu = false;

        boolean cDireita = false;
        boolean cCima = false;
        boolean cBaixo = false;
        boolean cEsquerda = false;

        if (ladoDireitoA >= ladoEsquerdoB) {
            cDireita = true;
        }
        if (ladoCimaA <= ladoBaixoB) {
            cCima = true;
        }
        if (ladoBaixoA >= ladoCimaB) {
            cBaixo = true;
        }
        if (ladoEsquerdoA <= ladoDireitoB) {
            cEsquerda = true;
        }

        if (cDireita && cEsquerda && cBaixo && cCima) {
            bateu = true;
        }

        return bateu;
    }

    class Movimento extends Thread {

        int valor = 0;

        public void setValor(int value) {
            valor = value;
        }

        @Override
        public void run() {

            System.out.println(valor);

            int aceleracao = 1;
            int tempo = 0;
            while (true) {

                if (aceleracao < 30 && tempo == 0) {
                    aceleracao++;
                }

                tempo++;
                if (tempo > 1) {
                    tempo = 0;
                }
                try {
                    sleep(aceleracao);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Movimento.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (lCarroUm.getX() < xAtual) {
                    if (lCarroUm.getX() > 1050) {
                        yAtual = yAtual + 100;
                        xAtual = xAtual - 1050;
                        lCarroUm.setBounds(0, yAtual, 100, 50);
                    }

                    lCarroUm.setBounds(lCarroUm.getX() + 8, yAtual, 100, 50);

                }
                if (lCarroDois.getX() > 0) {
                    lCarroDois.setBounds(lCarroDois.getX() - 5, 0, 100, 50);
                }

                if (bateu(lCarroUm, lCarroDois)) {
                    //JOptionPane.showMessageDialog(null, "Bateu!");

                    lEsp.setBounds(lCarroUm.getX() + 75, 0, 50, 50);
                    lEsp.setVisible(true);

                    try {
                        sleep(100);
                        lEsp.setVisible(false);
                        return;
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }
    }
}
