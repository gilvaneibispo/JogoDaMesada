package br.uefs.ecomp.jogodamesada;

import br.uefs.ecomp.jogodamesada.view.JogoView;

public class JogoDaMesada {

    public static void main(String[] args) {
        /*ReceptorMulticast rm = new ReceptorMulticast();
        rm.configurar("224.2.2.3", 8888);
        new Thread(rm).start();

        //System.out.println("P2");
        EmissorMulticast em = new EmissorMulticast();
        em.configurar("224.2.2.3", 8888);

        //System.out.println("P3");
        em.iniciar("msg um");
        em.iniciar("emg 44");

        //System.out.println("P1");
        //System.out.println("nviada: " + i);
        //new ReceptorMulticast().iniciar("224.2.2.3", 8888);*/
        
        new JogoView();
    }
}
