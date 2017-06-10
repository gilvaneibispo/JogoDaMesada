/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author User
 */
public class GerenciadorDeIp {

    private InetAddress address;

    /**
     * O MulticastIPManager é o gerenciador de IP altomático, usado para
     * endereçar cada player para o determinado grupo multicasting.
     */
    public GerenciadorDeIp() {
        try {
            address = InetAddress.getByName("224.0.0.0");
        } catch (UnknownHostException ex) {
            System.err.println("Erro no ao gerar o IP de Multicast");
            System.exit(1);
        }
    }

    /**
     * O método increase verifica o IP atual e pega o próximo IP lógico.
     * Exemplo: atual 224.0.0.0, próximo 224.0.0.1.
     *
     * @param ip
     * @return
     * @throws UnknownHostException
     */
    private InetAddress increase(InetAddress ip) throws UnknownHostException {
        byte[] b = ip.getAddress();

        b[3]++;
        if (b[3] == 0) {
            b[2]++;
            if (b[2] == 0) {
                b[1]++;
                if (b[1] == 0) {
                    b[0]++;
                }
            }
        }

        return InetAddress.getByAddress(b);
    }

    /**
     * Retorna para o GameStarter o próximo IP válido para iniciar o jogo.
     *
     * @return
     * @throws UnknownHostException
     */
    public InetAddress getMulticastIP() throws UnknownHostException {
        address = increase(address);
        return increase(address);
    }
}
