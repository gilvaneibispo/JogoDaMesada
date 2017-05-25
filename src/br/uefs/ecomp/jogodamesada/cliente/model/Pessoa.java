package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <strong>Abstração de Pessoa:</strong>
 * Possue apenas características normais a uma pessoa e os getteres e setteres, 
 * além de um método util capaz de criar instâncias dessa classe a partir de um
 * array de string recebido em seu parâmetro.
 * @author Gilvanei Bispo
 */
public class Pessoa implements Serializable {

    private String nome;
    private String senha;
    private String login;
    //private Endereco endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, String senha, String login) {
        this.nome = nome;
        this.senha = senha;
        this.login = login;
        //this.endereco = endereco;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDocumento() {
        return login;
    }

    public void setDocumento(String login) {
        this.login = login;
    }

    /**
     * <strong>Lista de Pessoas: </strong>
     * Gera uma lista de pessoas a partir de um array de string, analisando as
     * posições deste array que já são preveamente definidos desda criação da 
     * string de envio do lado do cliente.
     * @param dados
     * @return 
     */
    public ArrayList listaDePessoas(String[] dados) {
        ArrayList<Pessoa> lista = new ArrayList();
        Pessoa pessoa;
        //Endereco end;
        for (int i = 3; i < dados.length; i = i + 6) {
            if (dados[i] != "null") {   //se a primeira posição do array é nula, no
                                        //caso o nome da pessoa, o restante também é, pois é 
                                        //validado no input da interface. Logo sabe-se
                                        //a quantidade de pessoas recebidas
                //end = new Endereco(dados[i + 3], dados[i + 4], dados[i + 5]);
                pessoa = new Pessoa(dados[i], dados[i + 1], dados[i + 2]);
                lista.add(pessoa);
            }
        }
        return lista;
    }
}
