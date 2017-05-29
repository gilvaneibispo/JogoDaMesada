package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.Serializable;

/**
 * <strong>Abstração de Pessoa:</strong>
 * Possue apenas características normais a uma pessoa e os getteres e setteres, 
 * além de um método util capaz de criar instâncias dessa classe a partir de um
 * array de string recebido em seu parâmetro.
 * @author Gilvanei Bispo
 */
public class Pessoa implements Serializable {

    private String nome;
    private String id;
    //private Endereco endereco;

    public Pessoa() {
    }

    public Pessoa(String id, String nome) {
        this.nome = nome;
        this.id = id;
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
}
