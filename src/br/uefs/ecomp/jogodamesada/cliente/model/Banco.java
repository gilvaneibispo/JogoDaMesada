package br.uefs.ecomp.jogodamesada.cliente.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <strong>Banco: </strong>
 * Abstração de um banco real, esta classe apenas contem o comportamento
 * primário necessário para o funcionamento bancário, como depósito e
 * trasferência.
 *
 * @author Gilvanei Bispo
 */
public class Banco {
/*
    private final ArrayList<Conta> contas;

    public Banco() {
        contas = new ArrayList();
    }

    
     * <strong>Criar Conta: </strong>
     * Cria nos novas conta, instanciando o objeto, e atribui as posições do
     * vetor que recebe as posições equivalentes em cadas contrutor. Cria também
     * uma lista de Pessoa atraves da invocação do metodo listaDePessoa().
     *
     * @param dados
     * @return Conta
   
    public Conta criarConta(Pessoa titular) {
        Conta conta = new Conta();
        ArrayList<Pessoa> pessoa = new ArrayList();
        conta.setTitulares(pessoa);
        conta.setConta("colocar o código do jogador");
        return conta;
    }

    /**
     * <strong>Conectar: </strong>
     * Recupera a lista de conta no servidor através da instância de servidor
     * recebida no construtor e itera ela, em cada conta há também a iteração de
     * titulares, ja que este é o detentor da senha. Durante as iterações as
     * conta são comparada com a recebida, assim como a senha nas interações de
     * titulares.
     *
     * @param conta
     * @param senha
     * @return String
     *
    public String conectar(String conta, String senha) {

        String rt = null;
        boolean achou = false, senhaCerta = false;

        for (Conta contaAtual : contas) {

            String numConta = contaAtual.getConta();
            if (conta.equals(numConta)) {
                achou = true;
                for (Pessoa pes : contaAtual.getTitulares()) {
                    //String senhaAtual = pes.getSenha();
                    //if (senha.equals(senhaAtual)) {
                    //retorna código de sucesso mais o nome e o saldo...   
                    senhaCerta = true;
                    rt = "020101#" + pes.getNome() + "#" + contaAtual.getSaldo();
                }
            }
        }

        if (!senhaCerta) {
            rt = "020102"; //Senha incorreta
        }
        if (!achou) {
            rt = "020103"; //conta não existe.
        }
        return rt;
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
     *
    public synchronized String realizarTransferencia(String contaOrigem, String contaDestino, double valor) {
        String rt = null;                                           //String de retorno
        Iterator<Conta> iterator = contas.iterator();               //Iterador para Conta.        
        Conta co = new Conta(), cd = new Conta();                   //Objetos Contas para contas origem e destinos.
        boolean achouCo = false, achouCd = false;                   //Para identificar se as contas foram encontradas
        //dentro do arrayList (base de dados).

        while (iterator.hasNext()) {

            Conta atual = iterator.next();

            if (atual.getConta().compareTo(contaOrigem) == 0) {
                co = atual; //conta origem encontrada e setada na instacia co da classe Conta.
                achouCo = true;
            }
            if (atual.getConta().compareTo(contaDestino) == 0) {
                cd = atual; //conta destino encontrada e setada na instacia cd da classe Conta.
                achouCd = true;
            }
        }

        if (!achouCo) {
            rt = "020302";//Conta origem não encontrada.
            co = null;
        }
        if (!achouCd) {
            rt = "020303"; //Conta destino não encontrada.
            cd = null;
        }

        Se as contas existem realiza-se a transferencia. 
        if (co != null && cd != null) {
            if (co.getSaldo() < valor) {
                rt = "020304"; //Sem saldo
            } else {
                co.setSaldo(co.getSaldo() - valor);
                cd.setSaldo(cd.getSaldo() + valor);
                rt = "020301#" + co.getSaldo() + "#" + cd.getSaldo(); //Transferido com sucesso
            }
        }
        return rt;
    }

    /**
     * <strong>Realizar Despósito: </strong>
     * Também baseado na iteração da lista de contas no Servidor, busca a conta
     * informada e adiciona o valor, também recebido como parâmetro.
     *
     * @param conta
     * @param valor
     * @return String
     
    public synchronized String realizarDeposito(String conta, double valor) {
        String rt = null;
        boolean achou = false;
        Iterator<Conta> iterator = contas.iterator();

        Conta objConta = new Conta();

        while (iterator.hasNext()) {
            Conta atual = iterator.next();
            if (atual.getConta().compareTo(conta) == 0) {
                objConta = atual;
                achou = true;
            }
        }

        if (!achou) {
            rt = "020402"; //Conta não encontrada
            objConta = null;
        }

        if (objConta != null) {
            objConta.setSaldo(objConta.getSaldo() + valor);
            rt = "020401#" + objConta.getSaldo(); //Deposito realizado com sucesso e retorna novo saldo.
        }
        return rt;
    }

    //apenas para debug
    public void listarContas() {
        Iterator<Conta> iterator = contas.iterator(); //Iterador para Conta.

        while (iterator.hasNext()) {

            Conta atual = iterator.next();

            System.out.println(
                    "_______________________________________________________________\n"
                    + atual.getTitulares().get(0).getNome() + " :: " + atual.getSaldo() + " => "
                    + " cr: " + atual.getConta() + " ag:"
                    + " senha: " + atual.getTitulares().get(0).getNome());
        }
    }
    */
}
