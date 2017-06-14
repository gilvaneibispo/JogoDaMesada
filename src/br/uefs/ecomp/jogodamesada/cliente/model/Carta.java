package br.uefs.ecomp.jogodamesada.cliente.model;

import br.uefs.ecomp.jogodamesada.cliente.controller.ControllerComunicacao;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Gilvanei
 */
public class Carta {

    @XmlElement(name = "ID")
    private static int ID;//identificador;

    @XmlElement(name = "nome")
    private final String nome;

    @XmlElement(name = "descricao")
    private final String descricao;

    @XmlElement(name = "tipo")
    private final int tipo; // 1 - Correios; 2 - Compra e entretenimento.

    public Carta(String nome, String descricao, int tipo) {        
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Carta() {
        this.nome = null;
        this.descricao = null;
        this.tipo = 1;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
    
    public int getID(){
        return ID;
    }
    
    public void acaoCarta(int carta){
      
        switch (carta) {
            case 1:
                
                break;
            case 2:
               
                break;
            case 3:
             
                break;
            case 4:
                
                break;
            case 5:

                break;
            case 6:
                
                break;
            case 7:
               // casaDaPosicao.getCasaPosicao07();
                break;
            case 8:
               // casaDaPosicao.getCasaPosicao08();
                break;
         
        }
    }
}
