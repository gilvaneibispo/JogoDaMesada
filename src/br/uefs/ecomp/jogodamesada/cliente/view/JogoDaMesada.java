package br.uefs.ecomp.jogodamesada.cliente.view;

import br.uefs.ecomp.jogodamesada.cliente.controller.ControllerComunicacao;
import br.uefs.ecomp.jogodamesada.cliente.model.*;
import br.uefs.ecomp.jogodamesada.cliente.controller.ControllerAcoesTabuleiro;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class JogoDaMesada extends javax.swing.JFrame {

    private JLabel J1;
    private JLabel J2;
    private JLabel J3;
    private JLabel J4;
    private JLabel J5;
    private JLabel J6;

    private Movimento M1;
    private Movimento M2;
    private Movimento M3;
    private Movimento M4;
    private Movimento M5;
    private Movimento M6;

    private double emprestimo;

    private int posicaoAtual;
    private Pessoa jogadorLocal;
    private Casa casa;
    private ControllerAcoesTabuleiro objTabuleiro;
    private String host;
    private String porta;
    private List<Pessoa> jogadores;
    private Pessoa jogadorAtual;
    private JLabel fundoPainel = new JLabel();

    public JogoDaMesada() {
        initComponents();
        iniciaPeoes();
        this.configuracoesAdicionais();
        this.configuraIcon();
        jogadorLocal = new Pessoa();
        objTabuleiro = new ControllerAcoesTabuleiro();
        casa = new Casa();
        host = "127.0.0.1";
        porta = "12345";
        PreparaFimConexao p = new PreparaFimConexao();
        p.preparaFimConexao(this);
        //this.emprestimo(1000);

    }

    public void iniciaPeoes() {
        J1 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\P1.png")));
        J2 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\P2.png")));
        J3 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\P3.png")));
        J4 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\P4.png")));
        J5 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\P5.png")));
        J6 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\P6.png")));

        M1 = new Movimento(J1);
        M2 = new Movimento(J2);
        M3 = new Movimento(J3);
        M4 = new Movimento(J4);
        M5 = new Movimento(J5);
        M6 = new Movimento(J6);
    }

    /**
     * Apenas para setar alguns componentes da interface, estes que já foram inicializados
     * no construtor, agora são posicionados e/ou carregado valores padrões.
     */
    private void configuracoesAdicionais() {

        fundoPainel.setIcon(new ImageIcon(getClass().getResource("..\\image\\fundo_painel.png")));
        fundoPainel.setBounds(12, 12, 1030, 300);

        J1.setBounds(25, 12, 100, 50);
        J2.setBounds(25, 12, 100, 50);
        J3.setBounds(25, 12, 100, 50);
        J4.setBounds(25, 12, 100, 50);
        J5.setBounds(25, 12, 100, 50);
        J6.setBounds(25, 12, 100, 50);

        tabuleiro.add(fundoPainel);

        tabuleiro.add(J1);
        tabuleiro.add(J2);
        tabuleiro.add(J3);
        tabuleiro.add(J4);
        tabuleiro.add(J5);
        tabuleiro.add(J6);

        tabuleiro.setComponentZOrder(J1, 0);
        tabuleiro.setComponentZOrder(J2, 1);
        tabuleiro.setComponentZOrder(J3, 2);
        tabuleiro.setComponentZOrder(J4, 3);
        tabuleiro.setComponentZOrder(J5, 4);
        tabuleiro.setComponentZOrder(J6, 5);
        tabuleiro.setComponentZOrder(fundoPainel, 6);

        tabuleiro.setBounds(0, 0, 300, 1050);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height - 40);

        ImageIcon cartaDefault = new ImageIcon(getClass().getResource("..\\image\\cartaDefault.png"));
        cartaDefault.setDescription("Carta um");

        carta01.setIcon(cartaDefault);
        carta01.setEnabled(false);

        carta02.setIcon(cartaDefault);
        carta02.setEnabled(false);

        carta03.setIcon(cartaDefault);
        carta03.setEnabled(false);

        this.setBackground(new Color(255, 255, 255));

        String[] temp = new String[1];
        temp[0] = "Aguardando jogadores...";
        this.lista_jogadores.setListData(temp);

        this.btnEmprestimo.setEnabled(false);
        this.btn_jogar.setEnabled(false);

        this.status_label.setText("Iniciando aplicação...");
        this.nome_label.setText("");
        this.saldo_label.setText("");

        this.statusAtual.setBounds(0, 0, 405, 95);
        
        this.nome_casa_label.setText("Carregando...");
        this.descricao_casa_label.setText("");
    }

    private void configuraIcon() {
        URL url = this.getClass().getResource("..\\image\\icon_preto.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
    }

    /**
     * Desativa ou ativa algumas funções específicas para jogador logado.
     */
    private void controleDeFuncoesDeLogin() {
        this.nome_label.setText(this.jogadorLocal.getNome());
        this.getSaldo_label().setText("R$: " + this.jogadorLocal.getConta().getSaldo());
        this.btnConectar.setEnabled(false);
        this.btnCadastro.setEnabled(false);
    }

    /**
     * Oculta os peões em excesso no tabuleiro, deixando apenas a quantidade de
     * jogdores.
     * @param numJogadores 
     */
    public void desabilitaJogadoresEmExcesso(int numJogadores) {
        for (int e = 6; e > numJogadores; e--) {
            JLabel excesso = this.getElementoJogador("J" + e);
            excesso.setVisible(false);
        }
    }

    /**
     * Seta a JList de jogadores na lateral da tela, exibindo o nome, saldo
     * e o mês corrente do atual jogador.
     * @param jogador 
     */
    public void setJListJogadores(List<Pessoa> jogador) {
        String[] listData = new String[6];

        for (int j = 0; j < jogador.size(); j++) {
            listData[j] = this.jogadores.get(j).getId() + " : " + this.jogadores.get(j).getNome()
                    + " [mês: " + this.jogadores.get(j).getMes() + "]";
        }

        this.lista_jogadores.setListData(listData);
    }

    /**
     * Gera um Ranking de jogadores no final da partida e mostra na lateral da
     * interface.
     * @param jogador 
     */
    public void setRankFinal(List<Pessoa> jogador) {
        String[] listData = new String[6];
        System.out.println("Tamanho" + jogador.size());

        for (int j = 1; j < jogador.size() + 1; j++) {
            System.out.println("Entrou");
            listData[j] = this.jogadores.get(j).getId() + " : " + this.jogadores.get(j).getNome()
                    + " [Saldo Final: " + this.jogadores.get(j).getConta().getSaldo() + "]";
        }

        this.lista_jogadores.setListData(listData);
    }

    /**
     ***************************************************************************
     ** METODOS SETTERES **
     * **************************************************************************
     */
    public void setHost(String h) {
        this.host = h;
    }

    public void setPorta(String p) {
        this.porta = p;
    }

    public void setNomeJogadorLocal(String nome) {
        this.jogadorLocal.setNome(nome);
    }

    public void setJogadores(List<Pessoa> jogadores) {
        this.jogadores = jogadores;
    }

    public void setStatusLabel(String msg) {
        this.status_label.setText(msg);
    }

    public void setNomeCasaLabel(String msg) {
        this.nome_casa_label.setText(msg);
    }

    public void setDescricaoCasaLabel(String msg) {
        this.descricao_casa_label.setText(msg);
    }

    /**
     * Organiza a troca das cartas na tela, setando pelo index do botão desejado.
     * @param botao
     * @param cartaId 
     */
    public void setBotoesCarta(int botao, int cartaId) {
        if (botao == 1) {
            this.setBackgroundBotaoCarta(this.carta01, cartaId);
        } else if (botao == 2) {
            this.setBackgroundBotaoCarta(this.carta02, cartaId);
        } else if (botao == 3) {
            this.setBackgroundBotaoCarta(this.carta03, cartaId);
        }
    }

    /**
     * Seta a imagem dos botoes cartas, gerando assim uma carta clicável na
     * interface. Também habilida o botão selecionado, já que por padão os botões
     * de carta vem desabilitados.
    * @param botao
     * @param cartaId 
     */
    private void setBackgroundBotaoCarta(JButton botao, int cartaId) {
        ImageIcon img = null;
        String caminho = "..\\image\\";
        switch (cartaId) {
            case 0:
                img = new ImageIcon(getClass().getResource("..\\image\\cartaDefault.png"));
                botao.setEnabled(false);
                break;
            case 1:
                img = new ImageIcon(getClass().getResource(caminho + "cartaconta.JPG"));
                botao.setEnabled(true);
                break;
            case 2:
                img = new ImageIcon(getClass().getResource(caminho + "paguevizinho.jpg"));
                botao.setEnabled(true);
                break;
            case 3:
                img = new ImageIcon(getClass().getResource(caminho + "dinheiroextra.jpg"));
                botao.setEnabled(true);
                break;
            case 4:
                img = new ImageIcon(getClass().getResource(caminho + "doacao.jpg"));
                botao.setEnabled(true);
                break;
            case 5:
                img = new ImageIcon(getClass().getResource(caminho + "cobracamostro.jpg"));
                botao.setEnabled(true);
                break;
            case 6:
                img = new ImageIcon(getClass().getResource(caminho + "frente.jpg"));
                botao.setEnabled(true);
                break;
            default:
                img = new ImageIcon("..\\image\\cartaDefault.png");
                botao.setEnabled(false);
        }
        System.err.println("Ação: " + cartaId);
        botao.setIcon(img);
    }

    /**
     * Configura uma sequencia de informações necessária para o funcionamento
     * mas que só há disponibilidade despois de conectar ao sevidor e fazer login
     * no sistema. Por exemplo, recupera a lista de jogadores vinda do servidor,
     * chama funções de configuração e etc...
     * @param participantes 
     */
    public void setLogodo(int participantes) {
        //new BarraDeProgresso();

        //Recuperando a lista de jogadores recebida do servidor na classe ClienteP2P
        ControllerComunicacao cc = ControllerComunicacao.getInstance();
        List temp = cc.getJogadores();
        ClienteP2P cp2p = cc.getClienteP2P();

        //Setando a instancia de interface padrão na classe ClienteP2P
        ControllerComunicacao.getInstance().getCliente().getClienteP2P().setTabuleiro(this);

        //Setando valores importantes...
        this.setJogadores(temp);
        this.setJListJogadores(temp);
        this.atualizandoJogadorLocal(temp);
        this.controleDeFuncoesDeLogin();
        this.desabilitaJogadoresEmExcesso(temp.size());
        this.habilitarJogador();
        //this.jogadorAway();
        //this.fimPartida();

        //Setando JogadorLocal e lista de todos os jogadores da sala no ControllerAcoesTabuleiro 
        objTabuleiro.setJogadores(temp);
        objTabuleiro.setJogadorLocal(this.jogadorLocal);
        objTabuleiro.setClienteP2P(cp2p);
        
        this.hostConfig.setVisible(false);
    }

    /**
     * Gera uma Thread de classe interna que sempre verifica se é a vez do jogdor
     * local jogar.
     */
    private void habilitarJogador() {
        HabilitaJogador habilitar = new HabilitaJogador(this);
        Thread h = new Thread(habilitar);
        h.start();
    }

    private void jogadorAway() {
        JogadorAway away = new JogadorAway(this);
        Thread a = new Thread(away);
        a.start();
    }

    /**
     ***************************************************************************
     ** METODOS GETTERES **
     * **************************************************************************
     */
    
    /**
     * Retorna o atributo 'host'.
     * @return 
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Retorna o atributo 'porta'.
     * @return 
     */
    public String getPorta() {
        return this.porta;
    }

    /**
     * Retorna um JLabel apartir de um ID em String.
     * @param jogador
     * @return 
     */
    public JLabel getElementoJogador(String jogador) {
        JLabel rt = null;
        switch (jogador) {
            case "J1":
                rt = J1;
                break;
            case "J2":
                rt = J2;
                break;
            case "J3":
                rt = J3;
                break;
            case "J4":
                rt = J4;
                break;
            case "J5":
                rt = J5;
                break;
            case "J6":
                rt = J6;
                break;
        }
        return rt;
    }

    /**
     * Retorna a instância da Classe interna Movimento especifica de cada JLabel
     * (Jogador) a partir de um ID em String.
     * @param jogador
     * @return 
     */
    public Movimento getClasseJogador(String jogador) {
        Movimento rt = null;
        switch (jogador) {
            case "J1":
                rt = M1;
                break;
            case "J2":
                rt = M2;
                break;
            case "J3":
                rt = M3;
                break;
            case "J4":
                rt = M4;
                break;
            case "J5":
                rt = M5;
                break;
            case "J6":
                rt = M6;
                break;
        }
        return rt;
    }

    /**
     * Recupera a posição atual de um JLabel (Jogador) emcima do tabuleiro.
     * @param id_jogador
     * @return 
     */
    public int getPosicaoPeaoAtual(String id_jogador) {
        Movimento mtemp = getClasseJogador(id_jogador);
        return mtemp.getPosicao();
    }

    /**
     ***************************************************************************
     ** METODOS LOGICOS **
     * **************************************************************************
     */
    
    /**
     * Atualiza os dados do jogador local, comparando o nome de todos os
     * jogadores recebidos do servidor com o Jogador Local para encontrá-lo
     * dentro da lista.
     * @param listaJogador 
     */
    private void atualizandoJogadorLocal(List<Pessoa> listaJogador) {
        for (Pessoa p : listaJogador) {
            if (this.jogadorLocal.getNome().equals(p.getNome())) {
                this.jogadorLocal = p;
                ControllerComunicacao.getInstance().getCliente().getClienteP2P().setJogadorLocal(jogadorLocal);
            }
        }
    }

    /**
     * Sorteia um número de 1 a 6 randomicamente para representar o dado.
     * @return 
     */
    private int jogarDado() {
        Random gerador = new Random();
        int t = 0;

        //Não permite t igual a zero (o dado não pode ser zero).
        while (t == 0) {
            t = gerador.nextInt(7);
        }

        return t;
    }

    /**
     * Responsável por fazer o peão andar sobre o tabuleiro (JPanel) usando uma
     * instância da classe Movimento.
     * @param jogador
     * @param dado 
     */
    public void calculaDistancia(String jogador, int dado) {
        int dist = dado * 148;      //148 tamanha da casa no tabuleiro
        JLabel jg = new JLabel();   //JLabel atual

        /*
        As linha abaixo recupera o JLabel do jogador que lançou o dado, assim
        como a instância da classe movimento.
        */
        jg = this.getElementoJogador(jogador);
        Movimento move = this.getClasseJogador(jogador);
        jogadorAtual = ControllerComunicacao.getInstance().getClienteP2P().getJogadorAtual();
        
        /*
        Faz o peão se mover (apenas visualmente), atualiza a posição do mesmo
        para usos futuros e seta a  distância percorrida pelo peão.
        */
        try {
            move.setXAtual(move.getXAtual() + dist);
            move.setPosicao(dado);
            move.setJogador(jg);
            move.setValor(dist);
            move.start();
        } catch (Exception io) {
            if (io.getMessage() != null) {
                System.out.println(io.getMessage());
            }
        }
    }

    /**
     ***************************************************************************
     ** METODOS DE EVENTOS **
     * **************************************************************************
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        hostConfig = new javax.swing.JButton();
        btn_jogar = new javax.swing.JButton();
        PainelDado = new javax.swing.JPanel();
        dado_label = new javax.swing.JLabel();
        dado_num = new javax.swing.JLabel();
        btn_sair = new javax.swing.JButton();
        tabuleiro = new javax.swing.JPanel();
        btnEmprestimo = new javax.swing.JButton();
        painelCartas = new javax.swing.JPanel();
        carta02 = new javax.swing.JButton();
        carta01 = new javax.swing.JButton();
        carta03 = new javax.swing.JButton();
        btnCadastro = new javax.swing.JButton();
        btnConectar = new javax.swing.JButton();
        statusAtual = new javax.swing.JPanel();
        casa_label = new javax.swing.JLabel();
        nome_casa_label = new javax.swing.JLabel();
        descricao_casa_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_jogadores = new javax.swing.JList<>();
        nome_jogo = new javax.swing.JLabel();
        icone = new javax.swing.JLabel();
        dia_sex = new javax.swing.JLabel();
        dia_sab = new javax.swing.JLabel();
        dia_qui = new javax.swing.JLabel();
        dia_dom = new javax.swing.JLabel();
        dia_seg = new javax.swing.JLabel();
        dia_ter = new javax.swing.JLabel();
        dia_qua = new javax.swing.JLabel();
        linhaSuperior = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        nome_label = new javax.swing.JLabel();
        saldo_label = new javax.swing.JLabel();
        status_label = new javax.swing.JLabel();
        btn_teste = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        JListCompra = new javax.swing.JList<>();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Mesada");
        setBackground(new java.awt.Color(150, 200, 150));
        setBounds(new java.awt.Rectangle(15, 35, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(36, 46, 68));

        hostConfig.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        hostConfig.setText("Configurar Host");
        hostConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hostConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostConfigActionPerformed(evt);
            }
        });

        btn_jogar.setBackground(new java.awt.Color(255, 255, 255));
        btn_jogar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btn_jogar.setForeground(new java.awt.Color(36, 46, 60));
        btn_jogar.setText("Jogar");
        btn_jogar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jogarActionPerformed(evt);
            }
        });

        PainelDado.setBackground(new java.awt.Color(56, 66, 80));

        dado_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        dado_label.setForeground(new java.awt.Color(255, 255, 255));
        dado_label.setText("Dado:");

        dado_num.setFont(new java.awt.Font("Century Gothic", 3, 48)); // NOI18N
        dado_num.setForeground(new java.awt.Color(255, 255, 255));
        dado_num.setText("0");

        javax.swing.GroupLayout PainelDadoLayout = new javax.swing.GroupLayout(PainelDado);
        PainelDado.setLayout(PainelDadoLayout);
        PainelDadoLayout.setHorizontalGroup(
            PainelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PainelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(dado_num, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dado_label))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        PainelDadoLayout.setVerticalGroup(
            PainelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadoLayout.createSequentialGroup()
                .addComponent(dado_label)
                .addGap(7, 7, 7)
                .addComponent(dado_num, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_sair.setBackground(new java.awt.Color(255, 255, 255));
        btn_sair.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btn_sair.setForeground(new java.awt.Color(36, 46, 60));
        btn_sair.setText("Sair");
        btn_sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });

        tabuleiro.setBackground(new java.awt.Color(88, 196, 196));

        javax.swing.GroupLayout tabuleiroLayout = new javax.swing.GroupLayout(tabuleiro);
        tabuleiro.setLayout(tabuleiroLayout);
        tabuleiroLayout.setHorizontalGroup(
            tabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1051, Short.MAX_VALUE)
        );
        tabuleiroLayout.setVerticalGroup(
            tabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        btnEmprestimo.setBackground(new java.awt.Color(255, 255, 255));
        btnEmprestimo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnEmprestimo.setForeground(new java.awt.Color(36, 46, 60));
        btnEmprestimo.setText("Pedir Empréstimo");
        btnEmprestimo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmprestimoActionPerformed(evt);
            }
        });

        painelCartas.setBackground(new java.awt.Color(56, 66, 80));

        carta02.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        carta02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carta02ActionPerformed(evt);
            }
        });

        carta01.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        carta01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carta01ActionPerformed(evt);
            }
        });

        carta03.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        carta03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carta03ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelCartasLayout = new javax.swing.GroupLayout(painelCartas);
        painelCartas.setLayout(painelCartasLayout);
        painelCartasLayout.setHorizontalGroup(
            painelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCartasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(carta01, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(carta02, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(carta03, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelCartasLayout.setVerticalGroup(
            painelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCartasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carta02, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(carta03, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(carta01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnCadastro.setBackground(new java.awt.Color(255, 255, 255));
        btnCadastro.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCadastro.setForeground(new java.awt.Color(36, 46, 60));
        btnCadastro.setText("Sou Novo Aqui");
        btnCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroActionPerformed(evt);
            }
        });

        btnConectar.setBackground(new java.awt.Color(255, 255, 255));
        btnConectar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnConectar.setForeground(new java.awt.Color(36, 46, 60));
        btnConectar.setText("Jogar Agora");
        btnConectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        statusAtual.setBackground(new java.awt.Color(56, 66, 80));

        casa_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        casa_label.setForeground(new java.awt.Color(255, 255, 255));
        casa_label.setText("Casa:");

        nome_casa_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        nome_casa_label.setForeground(new java.awt.Color(255, 255, 255));
        nome_casa_label.setText("Carregando...");

        descricao_casa_label.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        descricao_casa_label.setForeground(new java.awt.Color(255, 255, 255));
        descricao_casa_label.setText("Descrição...");

        javax.swing.GroupLayout statusAtualLayout = new javax.swing.GroupLayout(statusAtual);
        statusAtual.setLayout(statusAtualLayout);
        statusAtualLayout.setHorizontalGroup(
            statusAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusAtualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusAtualLayout.createSequentialGroup()
                        .addComponent(casa_label)
                        .addGap(18, 18, 18)
                        .addComponent(nome_casa_label))
                    .addComponent(descricao_casa_label))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        statusAtualLayout.setVerticalGroup(
            statusAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusAtualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(casa_label)
                    .addComponent(nome_casa_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descricao_casa_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lista_jogadores.setBackground(new java.awt.Color(56, 66, 80));
        lista_jogadores.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lista_jogadores.setForeground(new java.awt.Color(255, 255, 255));
        lista_jogadores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lista_jogadores);

        nome_jogo.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        nome_jogo.setForeground(new java.awt.Color(255, 255, 255));
        nome_jogo.setText("Jogo da Mesada");

        icone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/uefs/ecomp/jogodamesada/cliente/image/icon_branco.png"))); // NOI18N

        dia_sex.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        dia_sex.setForeground(new java.awt.Color(204, 204, 204));
        dia_sex.setText("Sexta");

        dia_sab.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        dia_sab.setForeground(new java.awt.Color(204, 204, 204));
        dia_sab.setText("Sábado");

        dia_qui.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        dia_qui.setForeground(new java.awt.Color(204, 204, 204));
        dia_qui.setText("Quinta");

        dia_dom.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        dia_dom.setForeground(new java.awt.Color(204, 204, 204));
        dia_dom.setText("Domingo");

        dia_seg.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        dia_seg.setForeground(new java.awt.Color(204, 204, 204));
        dia_seg.setText("Segunda");

        dia_ter.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        dia_ter.setForeground(new java.awt.Color(204, 204, 204));
        dia_ter.setText("Terça");

        dia_qua.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        dia_qua.setForeground(new java.awt.Color(204, 204, 204));
        dia_qua.setText("Quarta");

        linhaSuperior.setBackground(new java.awt.Color(88, 196, 196));
        linhaSuperior.setForeground(new java.awt.Color(88, 196, 196));

        jSeparator1.setForeground(new java.awt.Color(88, 196, 196));

        nome_label.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        nome_label.setForeground(new java.awt.Color(255, 255, 255));
        nome_label.setText("Nome");

        saldo_label.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        saldo_label.setForeground(new java.awt.Color(255, 255, 255));
        saldo_label.setText("Saldo");

        status_label.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        status_label.setForeground(new java.awt.Color(255, 255, 255));
        status_label.setText("Status");

        btn_teste.setText("Botão de Teste");
        btn_teste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_testeActionPerformed(evt);
            }
        });

        JListCompra.setBackground(new java.awt.Color(56, 66, 80));
        JListCompra.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        JListCompra.setForeground(new java.awt.Color(255, 255, 255));
        JListCompra.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(JListCompra);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(linhaSuperior, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tabuleiro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(dia_dom, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(dia_seg, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(dia_ter, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(dia_qua, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(dia_qui, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(dia_sex, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(dia_sab, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(btnConectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hostConfig)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nome_label)
                                .addGap(369, 369, 369)
                                .addComponent(saldo_label))
                            .addComponent(status_label)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(statusAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PainelDado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_jogar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 99, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(btn_teste)
                .addGap(333, 333, 333)
                .addComponent(icone)
                .addGap(18, 18, 18)
                .addComponent(nome_jogo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(icone)
                            .addComponent(nome_jogo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btn_teste, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(linhaSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dia_seg, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dia_ter)
                                .addComponent(dia_qua)
                                .addComponent(dia_qui)
                                .addComponent(dia_sex)
                                .addComponent(dia_sab)
                                .addComponent(dia_dom)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(painelCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nome_label)
                            .addComponent(saldo_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status_label)
                        .addGap(10, 10, 10)
                        .addComponent(hostConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(btnEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(statusAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(PainelDado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_jogar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(519, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 264, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Apenas chama a janela de login setando a classe mãe na mesma.
     * @param evt 
     */
    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        Login login = new Login();
        login.setClasseMae(this);
    }//GEN-LAST:event_btnConectarActionPerformed

    /**
     * Apenas chama a janela de cadastro setando a classe mãe na mesma.
     * @param evt 
     */
    private void btnCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroActionPerformed
        Cadastro cad = new Cadastro();
        cad.setClasseMae(this);
    }//GEN-LAST:event_btnCadastroActionPerformed

    /**
     * Chama no ControllerTabuleiro a ação referente a carta 03, mas so se a mesma
     * estiver habilitada/setada na interface.
     * @param evt 
     */
    private void carta03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carta03ActionPerformed
        try {
            objTabuleiro.acaoCartaSelecionada(3);
        } catch (IOException ex) {
            Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_carta03ActionPerformed

    /**
     * Chama no ControllerTabuleiro a ação referente a carta 01, mas so se a mesma
     * estiver habilitada/setada na interface.
     * @param evt 
     */
    private void carta01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carta01ActionPerformed
        try {
            objTabuleiro.acaoCartaSelecionada(1);
        } catch (IOException ex) {
            Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_carta01ActionPerformed

    /**
     * Chama no ControllerTabuleiro a ação referente a carta 02, mas so se a mesma
     * estiver habilitada/setada na interface.
     * @param evt 
     */
    private void carta02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carta02ActionPerformed
        try {
            objTabuleiro.acaoCartaSelecionada(2);
        } catch (IOException ex) {
            Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_carta02ActionPerformed

    private void btnEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmprestimoActionPerformed

    }//GEN-LAST:event_btnEmprestimoActionPerformed

    /**
     * Considera o fato do jogador sair da aplicação pelo botão sair, chamando
     * o método responsável por fazer o tratamento.
     * @param evt 
     */
    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        if (JOptionPane.showConfirmDialog(btn_sair, "Tem certeza que deseja sair?", "Sair do Jogo", 2, 0) == 0) {
            try {
                ControllerComunicacao.getInstance().sairDaPartida(jogadorLocal.getId());
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }


    }//GEN-LAST:event_btn_sairActionPerformed

    /**
     * Trata a ação de lançamento do dado, chamando as ações especificas, lançando
     * o valor do dado para os demais jogadores, fazzendo o peão andar nas demais
     * aplicações clientes abertas e conectadas a sala. Além de verificar se o jogador
     * tirou a sorte grande (6 no dado).
     * @param evt 
     */
    private void btn_jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jogarActionPerformed
        try {
            int digitado = this.jogarDado();
            dado_num.setText(Helpper.toString(digitado));
            JOptionPane.showMessageDialog(this, this.jogadorLocal.getNome() + " : " + this.jogadorLocal.getNome());
            //this.calculaDistancia(this.jogadorLocal.getId(), digitado);
            //objTabuleiro.getAcaoParaPosicao(posicaoAtual);
            ControllerComunicacao.getInstance().moverPeao(this.jogadorLocal.getId(), digitado);
            Thread.sleep(1000);
            objTabuleiro.getAcaoParaPosicao(this.getPosicaoPeaoAtual(jogadorLocal.getId()));
 if (digitado == 6) {
                ControllerComunicacao.getInstance().resgatarSorteGrande(this.jogadorLocal.getId());
                this.setStatusLabel("Voce Teve A Sorte Grande");
                System.out.println("Voce Teve A Sorte Grande!!");
            }
            System.err.println("Interface" + jogadorLocal.getConta().getSaldo());
            this.getSaldo_label().setText("R$: " + this.jogadorLocal.getConta().getSaldo());
            ControllerComunicacao.getInstance().proximoAJogar();
        } catch (IOException ex) {
            Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_jogarActionPerformed

    /**
     * Apenas chama a janela de configuração de host, setando a classe mãe do mesmo.
     * @param evt 
     */
    private void hostConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostConfigActionPerformed
        ConfigurarHost hostCon = new ConfigurarHost();
        hostCon.setClasseMae(this);
    }//GEN-LAST:event_hostConfigActionPerformed

    //para debug...
    private void btn_testeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_testeActionPerformed
        this.calculaDistancia(this.jogadorLocal.getId(), 1);
        int p = this.getPosicaoPeaoAtual(jogadorLocal.getId());
        System.err.println("Posição atual: " + p);
        try {
            objTabuleiro.getAcaoParaPosicao(p);
        } catch (IOException ex) {
            Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_testeActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            //ex.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "Erro no carregamento gráfico: " + ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JogoDaMesada().setVisible(true);
        });
    }

    public boolean emprestimo(double valor){
        JOptionPane.showMessageDialog(null, "Seu Saldo é insuficiente\n Realize um Emprestimo No Valor De" + valor);
        Emprestimo e = new Emprestimo();
        e.setClasseMae(this);
        System.out.println("");
        if (jogadorLocal.getConta().getSaldo() > valor && !e.isVisible()) {
            return true;
        } else {
            emprestimo(valor);
        }
        return false;
    }

    public int ConcursoDeBandaDeRock() {
        JOptionPane.showMessageDialog(this, "Voce esta participando Do Concurso De Banda De Rock\n Por Favor Clique Para Jogar o dado!");
        int dado = this.jogarDado();
        dado_num.setText(dado + "");
        return dado;
    }

    private int valorBolaoEsporte;

    public void setValorBolaoEsporte(int valor) {
        this.valorBolaoEsporte = valor;
    }

    public int participarBolaoEsportes() {
        JOptionPane.showMessageDialog(this, "Informe Um Valor Para Participar Do Bolao!");
        BolaoDeEsporte valor = new BolaoDeEsporte();
        valor.setClasseMae(this);
        if(valor.isVisible()){
        return valorBolaoEsporte;
        }
        return 7;
    }

    public void informarVencedor() {
        JOptionPane.showMessageDialog(null, "Os demais particpantes desistiram parabens voce é o vencedor!");
        System.exit(0);
    }

    public double pagarDividas() {
        String num = JOptionPane.showInputDialog("Informe um numero caso deseje participar do bolao de esportes!!");
        return Double.parseDouble(num);
    }

    public int maratonaBenefiente() {
        JOptionPane.showMessageDialog(this, "Voce esta participando Da Maratona Beneficiente\n Por Favor Clique Para Jogar o dado!");
        System.out.println("Passou");
        int dado = this.jogarDado();
        dado_num.setText(dado + "");
        return dado;
    }

    public void atalizarSaldo() {
        this.getSaldo_label().setText("R$: " + this.jogadorLocal.getConta().getSaldo());
    }

    public int sortearGanhadorBolao() {
        JOptionPane.showMessageDialog(this, "Jogue O Dado Para definir o ganhadoir do bolçao!");
        int dado = this.jogarDado();
        dado_num.setText(dado + "");
        return dado;
    }

    public boolean pagarContas() {
        return true;
    }

    public String escolhaUmVizinho() {
        int i = 1;
        return "J" + 1;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> JListCompra;
    private javax.swing.JPanel PainelDado;
    private javax.swing.JButton btnCadastro;
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnEmprestimo;
    private javax.swing.JButton btn_jogar;
    private javax.swing.JButton btn_sair;
    private javax.swing.JButton btn_teste;
    private javax.swing.JButton carta01;
    private javax.swing.JButton carta02;
    private javax.swing.JButton carta03;
    private javax.swing.JLabel casa_label;
    private javax.swing.JLabel dado_label;
    private javax.swing.JLabel dado_num;
    private javax.swing.JLabel descricao_casa_label;
    private javax.swing.JLabel dia_dom;
    private javax.swing.JLabel dia_qua;
    private javax.swing.JLabel dia_qui;
    private javax.swing.JLabel dia_sab;
    private javax.swing.JLabel dia_seg;
    private javax.swing.JLabel dia_sex;
    private javax.swing.JLabel dia_ter;
    private javax.swing.JButton hostConfig;
    private javax.swing.JLabel icone;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator linhaSuperior;
    private javax.swing.JList<String> lista_jogadores;
    private javax.swing.JLabel nome_casa_label;
    private javax.swing.JLabel nome_jogo;
    private javax.swing.JLabel nome_label;
    private javax.swing.JPanel painelCartas;
    private javax.swing.JLabel saldo_label;
    private javax.swing.JPanel statusAtual;
    private javax.swing.JLabel status_label;
    private javax.swing.JPanel tabuleiro;
    // End of variables declaration//GEN-END:variables

    /**
     * Classe interna para gerar efeito visual de deslocamento dos peões sobre o
     * tabuleiro. Funciona basicamente trocando a posição do peão em um tempo
     * pequeno, gerando a sensação de movimento. Para tal usa-se uma Thread que
     * paralelamente ao resto das ações da aplicação faz a mudança de posição da
     * JLabel em alguns milisegundos.
     */
    class Movimento extends Thread {

        private int xAtual, yAtual;
        private int valor = 0;
        private JLabel jg = null;
        private int larguraDaCasa = 148; //tamanho horizontal das casas.
        private int alturaDaCasa = 62;   //tamanho vertical das casas.
        private int posicao;

        public Movimento(JLabel jogador) {
            this.jg = jogador;
            xAtual = 0;
            yAtual = 12;
            posicao = 0;
        }

        public void setValor(int value) {
            valor = value;
        }

        public void setJogador(JLabel jogador) {
            jg = jogador;
        }

        public int getYAtual() {
            return yAtual;
        }

        public int getXAtual() {
            return xAtual;
        }

        public void setXAtual(int x) {
            xAtual = x;
        }

        @Override
        public void run() {

            int aceleracao = 1;
            int tempo = 0;

            while (true) {

                if (aceleracao < 30 && tempo == 0) {
                    aceleracao++;
                }

                tempo++;
                if (tempo > 1) {
                    tempo = 0;
                }
                try {
                    sleep(aceleracao);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Movimento.class.getName()).log(Level.SEVERE, null, ex);
                }

                /*
                Se a posição atual for menor que a posição prevista ele continua atualizando a posição dp JLabel
                */
                if (jg.getX() < xAtual) {
                    if (jg.getX() > 1000) {
                        yAtual = yAtual + 62;
                        xAtual = xAtual - 1064;
                        jg.setBounds(35, yAtual, 100, 50);
                    }

                    if (jg.getY() > 300) {
                        yAtual = yAtual - 300;                        
                        jg.setBounds(35, yAtual, 100, 50);
                    }
                    //Não permite parar nas posições acima de 30 (dia do mês maior que 30). 
                    if (jg.getY() > 200 && jg.getY() < 300 && jg.getX() > 294) {
                        if (jogadorAtual.getId().equals(jogadorLocal.getId()) && !jogadorAtual.isFim()) {
                            try {
                                objTabuleiro.getAcaoParaPosicao(31);
                            } catch (IOException ex) {
                                Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        /*
                        Verifica se o jogador terminou a partida.
                        */
                        if (jogadorAtual.getMes() == ControllerComunicacao.getInstance()
                                .getClienteP2P().getDuracao()) {
                            if (!jogadorAtual.isFim()) {

                                System.out.println("Terminei");
                                ControllerComunicacao.getInstance().getClienteP2P().comunicarChegada(jogadorAtual);
                                if (ControllerComunicacao.getInstance().getClienteP2P().getPessoasRestantes() == 0) {

                                    System.out.println("HORA DE CALCULAR O RANK");
                                    try {
                                        ArrayList rankFinal = (ArrayList) ControllerComunicacao.getInstance().solicitarRank(jogadorLocal.getId(), jogadorLocal.getConta().getSaldo());
                                        System.out.println(rankFinal);
                                        setRankFinal(rankFinal);
                                    } catch (IOException ex) {
                                        Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        } else {
                            System.out.println("Proximo mês");
                            jogadorAtual.setMes(jogadorAtual.getMes() + 1);
                            setJListJogadores(ControllerComunicacao.getInstance().getClienteP2P().getJogadores());
                            yAtual = 12;
                            xAtual = xAtual - 147;
                        }
                        //jg.setBounds(0, 12, 100, 50);

                    }
                    jg.setBounds(jg.getX() + 8, yAtual, 100, 50);

                }
            }
        }

        /**
         * Soma a posição atual para a instância de movimento com o valor do
         * dado, gerando uma nova posição a cada jogada do jogador relacionado
         * esta instância.
         * @param pos 
         */
        public void setPosicao(int pos) {
            if (posicao + pos >= 31) {
                int diferenca = 31 - posicao;
                posicao = 1 + (pos - diferenca);
            } else {
                posicao = posicao + pos;
            }
        }

        /**
         * Retorna o atributo posição
         * @return 
         */
        public int getPosicao() {
            return posicao;
        }

    }

    class HabilitaJogador extends Thread {

        private JogoDaMesada tabuleiro;

        public HabilitaJogador(JogoDaMesada tabuleiro) {
            this.tabuleiro = tabuleiro;
        }

        @Override
        public void run() {
            while (true) {
                if (ControllerComunicacao.getInstance().getCliente().getClienteP2P().eMeuTurno(jogadorLocal)) {
                    this.tabuleiro.btn_jogar.setEnabled(true);
                    this.tabuleiro.btnEmprestimo.setEnabled(true);
                } else {
                    this.tabuleiro.btn_jogar.setEnabled(false);
                    this.tabuleiro.btnEmprestimo.setEnabled(false);
                }
            }

        }
    }

    /**
     * Classe interna para verificar se é a vez deste jogador e se o mesmo jogou.
     * Funciona com Thread e verifica se o jogador tomará a ação a tempo. Caso
     * não desabilita o botão jogar da interface e avisa que ele estorou o tempo
     * de jogo.
     */
    class JogadorAway extends Thread {

        private JogoDaMesada tabuleiro;

        public JogadorAway(JogoDaMesada tabuleiro) {
            this.tabuleiro = tabuleiro;
        }

        @Override
        public void run() {
            long tempInicial = 0;  //Tempo inicial
            while (true) {
                if (this.tabuleiro.btn_jogar.isEnabled() || this.tabuleiro.btnEmprestimo.isEnabled()) {
                    //Se um dos botoes estiver enable, ou seja passiveis de serem clicados oq acontece apenas na vez do player
                    if (tempInicial == 0) {
                        tempInicial = System.currentTimeMillis();  //O tempo inicial assume o valor do relogio 
                    }
                    if (System.currentTimeMillis() > (tempInicial + 30000)) {
                        try {
                            //Se o valor atual do relogio do sisteman for maior que o valor inicial + 30 segundos 
                            ControllerComunicacao.getInstance().perdeuSuaVez();
                            //O player perde a sua vez
                            tempInicial = 0;
                            JOptionPane.showMessageDialog(tabuleiro, "Desculpa: Você perdeu a vez de jogar!");

                        } catch (IOException ex) {
                            Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    tempInicial = 0;   //Se os botoes não forem passiveis de receber um click o relogio assume o valor 0
                }
            }
        }
    }

    /**
     * @return the saldo_label
     */
    public javax.swing.JLabel getSaldo_label() {
        return saldo_label;

    }

    /**
     * Classe interna para tratar a saida de jogadores via botão fechar.
     */
    public class PreparaFimConexao {

        /**
         * Metodo que inutiliza o botão de fechar do frame, e implementa uma
         * classe anonima WindowAdapter, sobreescrevendo o metodo windowClosing
         * que funciona como um ouvinte e executa a finalização da comunicação
         * entre cliente e servidor quando o ouvinte é ascionado
         *
         * @param fj
         */
        public void preparaFimConexao(JFrame fj) {
            //Inutiliza o botão fechar do frame:
            fj.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            fj.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        ControllerComunicacao.getInstance().sairDaPartida(jogadorLocal.getId());
                    } catch (IOException ex) {
                        Logger.getLogger(JogoDaMesada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // cu.finalizarConexao();
                    JOptionPane.showMessageDialog(null, "MUITO OBRIGADO!");
                    System.exit(0);

                }
            });
        }
    }

    /**
     * @return the emprestimo
     */
    public double getEmprestimo() {
        return emprestimo;
    }

    /**
     * @param emprestimo the emprestimo to set
     */
    public void setEmprestimo(double emprestimo) {
        this.emprestimo = emprestimo;
    }

}
