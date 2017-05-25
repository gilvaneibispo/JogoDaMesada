/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import br.uefs.ecomp.jogodamesada.cliente.conexao.ProtocoloP2P;

/**
 *
 * @author User
 */
public class ClienteP2P {

    private String nome;
    private MultiCastRecebedor recebedor;
    private MultiCastEnvio sender;
    private InetAddress address;
    private List<Player> ordemJogadas;

    public ClienteP2P(InetAddress address) throws IOException {
        this.address = address;
        this.ordemJogadas = new ArrayList<>();

    }

    public void jogadas(String mensagem) {
        StringTokenizer tokens = new StringTokenizer(mensagem, ProtocoloP2P.SEPARATOR);
        int protocolo = Integer.parseInt(tokens.nextToken());
        switch (protocolo) {
            case ProtocoloP2P.CONFIGURACOES:
                while (tokens.hasMoreElements()) {
                    Player p = new Player(tokens.nextToken(), Integer.parseInt(tokens.nextToken()));
                    ordemJogadas.add(p);
                }
                break;
            case ProtocoloP2P.MOVER_PEAO:
                break;
        }
    }

    /**
     * @return the address
     */
    public InetAddress getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(InetAddress address) {
        this.address = address;
    }
}
