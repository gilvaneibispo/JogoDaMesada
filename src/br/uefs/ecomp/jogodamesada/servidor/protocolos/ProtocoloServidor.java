package br.uefs.ecomp.jogodamesada.servidor.protocolos;

/**
 * <strong>Protocolo Servidor: </strong>
 * Assim como os demais protocolos esta classe padroniza as comunicação, esta
 * vez as solicitações do cliente ao servidor.
 *
 * @author Gilvanei Bispo
 * @author Rodrigo Santos
 * @author Dermeval Neves
 */
public class ProtocoloServidor {

    public static final int USUARIO_CADASTRADO = 1;
    public static final int ERRO_CADASTRO = 2;
    public static final int LOGIN_SUCESSO = 3;
    public static final int ERRO_LOGIN = 4;
    public static final int PARTIDA_ENCONTRADA = 5;
    public static final String SEPARATOR = "-";

}
