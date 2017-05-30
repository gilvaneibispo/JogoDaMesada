package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.Serializable;

/**
 * <strong>Abstração de Pessoa:</strong>
 * Possue apenas características normais a uma pessoa e os getteres e setteres,
 * além de um método util capaz de criar instâncias dessa classe a partir de um
 * array de string recebido em seu parâmetro.
 *
 * @author Gilvanei Bispo
 */
public class Pessoa implements Serializable {

    private String nome;
    private String id;
    private int mesAtual;
    private Conta conta;
    //private Endereco endereco;

    public Pessoa() {

    }

    public Pessoa(String id, String nome) {
        this.nome = nome;
        this.id = id;
        conta = new Conta(this);
        mesAtual = 1;
    }

    /**
     * @return o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome String para 'setar' o nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return this.conta;
    }
    
    public void setMes(int mes) {
        this.mesAtual = mes;
    }

    public int getMes() {
        return this.mesAtual;
    }
}
