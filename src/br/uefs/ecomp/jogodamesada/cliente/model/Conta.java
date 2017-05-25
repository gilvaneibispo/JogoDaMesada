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
    private ArrayList<Pessoa> titulares;    //Lista de titulares.
    
    /**
     * <strong>Construtor Conta: </strong>
     * Apenas inicializa alguns atributos, sem receber parâmetros.
     */
    public Conta(){
        this.titulares = new ArrayList();
        this.saldo = 0;
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

    
    /** @return the titulares */
    public ArrayList<Pessoa> getTitulares() {
        return titulares;
    }

    /** @param titulares the titulares to set */
    public void setTitulares(ArrayList<Pessoa> titulares) {
        this.titulares = titulares;
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

    /**
     * <strong>Criar Conta: </strong>
     * Preenche o valor dos atributos da instância de Conta criada antes da chamada
     * deste método. Incluindo uma lista do objeto Pessoa, referindo-se aos titulares
     * desta conta. No final retorna o número da conta que foi criado pelo método
     * <pre> geraNumeroDeConta() </pre>.
     * @param titulares
     * @return 
     */
    public String criarConta(ArrayList titulares) {
        this.setTitulares((ArrayList<Pessoa>) titulares);
        return this.getConta();
    }
}
