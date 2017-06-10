package br.uefs.ecomp.jogodamesada.cliente.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class teste {

    public static void main(String[] args) throws InterruptedException {
        /*
        //cria um ArrayList chamado "numeros"
        List numeros = new ArrayList();

        //adiciona 5 elementos ao ArrayList com o método "add"
        //cada númeor é sempre inserido no fim da lista
        numeros.add(10);
        numeros.add(1000);
        numeros.add(500);
        numeros.add(40);
        numeros.add(-1);

        //ordena / reverte / embaralha o conteúdo do ArrayList
        System.out.println("antes de ordenar: " + numeros.toString());

        Collections.sort(numeros);

        System.out.println("depois de ordenar: " + numeros.toString());

        Collections.reverse(numeros);

        System.out.println("depois de reverter: " + numeros.toString());

        Collections.shuffle(numeros);

        System.out.println("depois de embaralhar: " + numeros.toString());
        
        Collections.shuffle(numeros);

        System.out.println("depois de embaralhar novamente: " + numeros.toString());
        
        Collections.shuffle(numeros);

        System.out.println("depois de embaralhar mais uma vez: " + numeros.toString());
    }*/

        CartaXML xml1 = new CartaXML();
        ArrayList<Carta> cc = (ArrayList<Carta>) xml1.LendoXML();
        
        
        

        CasaXML xml2 = new CasaXML();
        ArrayList<Casa> c = (ArrayList<Casa>) xml2.LendoXML();
        
        System.err.println("Tamanho: " + c.size());
        
        Casa casa = new Casa();
        casa.setCartas(cc);
        casa.setCasas(c);
        
        casa.embaralharCartas();
        
        for (Carta d : cc) {
            System.out.println(d.getID());
            System.err.println(d.getNome());
            System.out.println(d.getDescricao());
            System.err.println("- - - - - - - - - - - - - - - - -");
        }
        
        if (c.isEmpty()) {
            System.err.println("A lista está vazia!");
        } else {
            for (Casa d : c) {
                System.out.println(d.getID());
                System.err.println(d.getNome());
                System.out.println(d.getDescricao());
                System.err.println("- - - - - - - - - - - - - - - - -");
            }
        }
    }
}
