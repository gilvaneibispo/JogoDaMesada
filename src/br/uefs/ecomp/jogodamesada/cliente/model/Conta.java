package br.uefs.ecomp.jogodamesada.cliente.model;

import com.sun.jmx.snmp.Timestamp;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Ob
 * @author Gilvanei
 */
public class Conta implements Serializable {

    private String conta;                   //Núero da conta.
    private double saldo;                   //saldo da conta.
    private Pessoa pessoa;
    
    /**
     * <strong>Construtor Conta: </strong>
     * Apenas inicializa alguns atributos, sem receber parâmetros.
     */
    public Conta(Pessoa pessoa){
        this.pessoa = pessoa;
        this.saldo = 3000.00;
    }

    /** @return o saldo */
    public double getSaldo() {
        return saldo;
    }

    /** @param saldo o saldo para set */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /** @return a conta */
    public String getConta() {
        return conta;
    }

    /** @param conta a conta para set */
    public void setConta(String conta) {
        this.conta = conta;
    }


    /**
     * <strong>Gera nº de Conta: </strong>
     * Gera o numero para casa instância de conta, usando como base os 8 últimos
     * dígitos da função nativa Timestamp.
     * @return String
     */
    public String geraNumeroDeConta() {
        Timestamp t = new Timestamp();
        String rt = "" + t.getDateTime();
        return rt.substring(4, 12);
    }

 
}
