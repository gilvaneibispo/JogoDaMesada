package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Ob
 * @author Gilvanei
 */
public class Conta implements Serializable {

    private String conta;                   //Núero da conta.
    private double saldo;                   //saldo da conta.
    private final Pessoa pessoa;            //Títular da conta.
    private double divida;                  //Dividas do titular.

    /**
     * <strong>Construtor Conta: </strong>
     * Apenas inicializa alguns atributos, sem receber parâmetros.
     * @param pessoa
     */
    public Conta(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.saldo = 3000.00;
    }

    /**
     * @return o saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo o saldo para set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return a conta
     */
    public String getConta() {
        return conta;
    }

    /**
     * @param conta a conta para set
     */
    public void setConta(String conta) {
        this.conta = conta;
    }
    
    /**
     * @return a divida
     */
    public double getDivida() {
        return divida;
    }

    /**
     * @param valor
     */
    public void setDivida(double valor) {
        this.divida = this.divida + valor;
    }

    /**
     * <strong>Realizar Transferência: </strong>
     * Também baseado na iteração da lista de contas no Servidor, busca duas
     * contas uma de origem do valor e outra destino. Simplesmente diminui o
     * valor do saldo da primeira e soma no saldo da segunda.
     *
     * @param contaOrigem
     * @param contaDestino
     * @param valor
     * @return String
     */
    public synchronized boolean realizarTransferencia(Conta contaOrigem, Conta contaDestino, double valor) {

        if (contaOrigem.getSaldo() < valor) {
            int op = JOptionPane.showConfirmDialog(null, "Você não tem saldo suficiente!\n"
                    + "Deseja fazer um empréstimo?");
            if (op == 1) {
                this.emprestimo();
            }
            return false;
        } else {
            contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
            contaDestino.setSaldo(contaDestino.getSaldo() + valor);
            return true;
        }
    }
    
    //Generico...
    public void creditar(Conta conta, double valor){
        conta.setSaldo(conta.getSaldo() + valor);
    }
    //Se conhecemos o obj conta...
    public void creditar(double valor){
        this.setSaldo(this.getSaldo() + valor);
    }
    
    //Generico...
    public void debitar(Conta conta, double valor){
        conta.setSaldo(conta.getSaldo() - valor);
    }
    //Se conhecemos o obj conta...
    public void debitar(double valor){
        this.setSaldo(this.getSaldo() - valor);
    }

    public void emprestimo() {
    }
}
