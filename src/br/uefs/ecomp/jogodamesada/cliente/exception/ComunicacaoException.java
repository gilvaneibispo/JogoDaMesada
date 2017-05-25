package br.uefs.ecomp.jogodamesada.cliente.exception;

/**
 *<strong>Comunicação Exception: </strong>
 * Exceção gerada na notificação de casos que a mensagem enviada pelo servidor
 * não foi compreendida pelo ClienteTCP (não está em seu catálogo).
 * @author Gilvanei Bispo
 */
public class ComunicacaoException extends Exception{
    public ComunicacaoException(String msg){
        super(msg);
    }
}
