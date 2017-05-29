package br.uefs.ecomp.jogodamesada.cliente.view;

import br.uefs.ecomp.jogodamesada.cliente.controller.ControllerComunicacao;
import br.uefs.ecomp.jogodamesada.cliente.model.*;
import br.uefs.ecomp.jogodamesada.cliente.model.Tabuleiro;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class JogoDaMesa extends javax.swing.JFrame {

    private JLabel J1 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\jogador-01.png")));
    private JLabel J2 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\jogador-02.png")));
    private JLabel J3 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\jogador-01.png")));
    private JLabel J4 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\jogador-02.png")));
    private JLabel J5 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\jogador-01.png")));
    private JLabel J6 = new JLabel(new ImageIcon(getClass().getResource("..\\image\\jogador-02.png")));

    private Movimento M1 = new Movimento(J1);
    private Movimento M2 = new Movimento(J2);
    private Movimento M3 = new Movimento(J3);
    private Movimento M4 = new Movimento(J4);
    private Movimento M5 = new Movimento(J5);
    private Movimento M6 = new Movimento(J6);

    private int posicaoAtual;
    private Pessoa jogadorLocal;
    private Casa casa;
    private Tabuleiro objTabuleiro;
    private String host;
    private String porta;
    private List<Pessoa> jogadores;

    public JogoDaMesa() {
        initComponents();
        this.configuracoesAdicionais();
        this.configuraIcon();
        jogadorLocal = new Pessoa();
        objTabuleiro = new Tabuleiro();
        casa = new Casa();
        host = "127.0.0.1";
        porta = "12345";
    }

    private void configuracoesAdicionais() {

        J1.setBounds(0, 12, 100, 50);
        J2.setBounds(0, 12, 100, 50);
        J3.setBounds(0, 12, 100, 50);
        J4.setBounds(0, 12, 100, 50);
        J5.setBounds(0, 12, 100, 50);
        J6.setBounds(0, 12, 100, 50);

        tabuleiro.add(J1);
        tabuleiro.add(J2);
        tabuleiro.add(J3);
        tabuleiro.add(J4);
        tabuleiro.add(J5);
        tabuleiro.add(J6);

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

        this.dia_ter.setHorizontalTextPosition(SwingConstants.CENTER);
        this.dado_num.setHorizontalTextPosition(SwingConstants.CENTER);
        this.dia_dom.setHorizontalTextPosition(SwingConstants.CENTER);
        this.dia_seg.setHorizontalTextPosition(SwingConstants.CENTER);
        this.dia_qua.setHorizontalTextPosition(SwingConstants.CENTER);
        this.dia_qui.setHorizontalTextPosition(SwingConstants.CENTER);
        this.dia_sex.setHorizontalTextPosition(SwingConstants.CENTER);
        this.dia_sab.setHorizontalTextPosition(SwingConstants.CENTER);

        this.setBackground(new Color(255, 255, 255));
        
        String[] temp = new String[1];
        temp[0] = "Aguardando jogadores...";
        this.lista_jogadores.setListData(temp);
    }

    private void configuraIcon() {
        URL url = this.getClass().getResource("..\\image\\icon_preto.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
    }

    private void desabilidaFuncoesParaUsusarioNaoLogado() {

    }

    public void desabilitaJogadoresEmExcesso(int numJogadores) {
        int desabilitaveis = 6 - numJogadores;

        for (int e = 6; e > desabilitaveis; e--) {
            JLabel excesso = this.getElementoJogador("J" + e);
            excesso.setVisible(false);
        }
    }

    public void setJListJogadores(List jogador) {
        //this.jogadores;
        String[] listData = new String[6];

        //int tam = 6 - jogador.size();

        //System.out.println("Tamanho array: " + listData.length + " | Tamanho Lista Jogadores: " + jogador.size()
        //        + " | Tamanho de 'tam': " + tam);

        for (int j = 0; j < jogador.size(); j++) {
            //System.out.println("Entrou -> " + j);
            listData[j] = this.jogadores.get(j).getId() + " : " + this.jogadores.get(j).getNome();
        }

        this.lista_jogadores.setListData(listData);
    }

    public void usandoLista() {
        ControllerComunicacao cm = ControllerComunicacao.getInstance();
        List<Pessoa> temp = cm.getJogadores();

        for (Pessoa p : temp) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
        }
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

    public void setLogodo(boolean logou) {
        JOptionPane.showMessageDialog(this, "Você está logado");
        ControllerComunicacao cc = ControllerComunicacao.getInstance();
        List temp = cc.getJogadores();
        this.setJogadores(temp);
        this.setJListJogadores(temp);
        this.atualizandoJogadorLocal(temp);
        this.desabilitaJogadoresEmExcesso(temp.size());
        objTabuleiro.setJogadores(temp);
    }

    /**
     ***************************************************************************
     ** METODOS GETTERES **
     * **************************************************************************
     */
    public String getHost() {
        return this.host;
    }

    public String getPorta() {
        return this.porta;
    }

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
     ***************************************************************************
     ** METODOS LOGICOS **
     * **************************************************************************
     */
    private void atualizandoJogadorLocal(List<Pessoa> listaJogador) {
        for (Pessoa p : listaJogador) {
            if (this.jogadorLocal.getNome().equals(p.getNome())) {
                this.jogadorLocal = p;
            }
        }
    }

    private int jogarDado() {
        Random gerador = new Random();
        int t = 0;

        //Não permite t igual a zero (o dado não pode ser zero).
        while (t == 0) {
            t = gerador.nextInt(7);
        }

        return t;
    }

    public void calculaDistancia(String jogador, int dado) {
        //cada casa 150px;
        int dist = dado * 140;
        JLabel jg = new JLabel();

        jg = this.getElementoJogador(jogador);
        Movimento move = this.getClasseJogador(jogador);

        try {
            move.setXAtual(move.getXAtual() + dist);
            move.setJogador(jg);
            move.setValor(dist);
            move.start();
        } catch (Exception io) {
            if (io.getMessage() != null) {
                System.out.println(io.getMessage());
            }
        }
    }

    public boolean bateu(Component a, Component b) {
        int aX = a.getX();
        int aY = a.getY();
        int ladoDireitoA = aX + a.getWidth();
        int ladoEsquerdoA = aX;
        int ladoBaixoA = aY + a.getHeight();
        int ladoCimaA = aY;

        int bX = b.getX();
        int bY = b.getY();
        int ladoDireitoB = bX + b.getWidth();
        int ladoEsquerdoB = bX;
        int ladoBaixoB = bY + b.getHeight();
        int ladoCimaB = bY;

        boolean bateu = false;

        boolean cDireita = false;
        boolean cCima = false;
        boolean cBaixo = false;
        boolean cEsquerda = false;

        if (ladoDireitoA >= ladoEsquerdoB) {
            cDireita = true;
        }
        if (ladoCimaA <= ladoBaixoB) {
            cCima = true;
        }
        if (ladoBaixoA >= ladoCimaB) {
            cBaixo = true;
        }
        if (ladoEsquerdoA <= ladoDireitoB) {
            cEsquerda = true;
        }

        if (cDireita && cEsquerda && cBaixo && cCima) {
            bateu = true;
        }

        return bateu;
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
        fundoTabuleiro = new javax.swing.JLabel();
        btnEmprestimo = new javax.swing.JButton();
        painelCartas = new javax.swing.JPanel();
        carta02 = new javax.swing.JButton();
        carta01 = new javax.swing.JButton();
        carta03 = new javax.swing.JButton();
        btnCadastro = new javax.swing.JButton();
        Extra = new javax.swing.JButton();
        btnConectar = new javax.swing.JButton();
        statusAtual = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        dado_num.setText("4");

        javax.swing.GroupLayout PainelDadoLayout = new javax.swing.GroupLayout(PainelDado);
        PainelDado.setLayout(PainelDadoLayout);
        PainelDadoLayout.setHorizontalGroup(
            PainelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadoLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(PainelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(dado_num, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dado_label))
                .addGap(27, 27, 27))
        );
        PainelDadoLayout.setVerticalGroup(
            PainelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dado_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dado_num, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
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

        fundoTabuleiro.setBackground(new java.awt.Color(23, 55, 67));
        fundoTabuleiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/uefs/ecomp/jogodamesada/cliente/image/fundo_painel.png"))); // NOI18N

        javax.swing.GroupLayout tabuleiroLayout = new javax.swing.GroupLayout(tabuleiro);
        tabuleiro.setLayout(tabuleiroLayout);
        tabuleiroLayout.setHorizontalGroup(
            tabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabuleiroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fundoTabuleiro)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        tabuleiroLayout.setVerticalGroup(
            tabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabuleiroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fundoTabuleiro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        Extra.setBackground(new java.awt.Color(255, 255, 255));
        Extra.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        Extra.setForeground(new java.awt.Color(36, 46, 60));
        Extra.setText("Extra");
        Extra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Extra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExtraActionPerformed(evt);
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

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Casa:");

        nome_casa_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        nome_casa_label.setForeground(new java.awt.Color(255, 255, 255));
        nome_casa_label.setText("Título");

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
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(nome_casa_label))
                    .addComponent(descricao_casa_label))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        statusAtualLayout.setVerticalGroup(
            statusAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusAtualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nome_casa_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descricao_casa_label)
                .addContainerGap(40, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(painelCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_jogar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(statusAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                        .addComponent(PainelDado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(454, 454, 454)
                                .addComponent(btnEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(linhaSuperior, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tabuleiro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dia_sab, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(hostConfig)
                        .addGap(952, 952, 952)
                        .addComponent(Extra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(508, 508, 508)
                .addComponent(icone)
                .addGap(18, 18, 18)
                .addComponent(nome_jogo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(icone)
                    .addComponent(nome_jogo))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(390, 390, 390)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PainelDado, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_jogar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addGap(18, 18, 18)
                                .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Extra, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(490, 490, 490))
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
                        .addGap(41, 41, 41)
                        .addComponent(hostConfig)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 942, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        Login login = new Login();
        login.setClasseMae(this);
    }//GEN-LAST:event_btnConectarActionPerformed

    private void ExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExtraActionPerformed
        this.usandoLista();
    }//GEN-LAST:event_ExtraActionPerformed

    private void btnCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroActionPerformed
        Cadastro cad = new Cadastro();
        cad.setClasseMae(this);
    }//GEN-LAST:event_btnCadastroActionPerformed

    private void carta03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carta03ActionPerformed
        casa.acaoCasaSelecionada(3);
    }//GEN-LAST:event_carta03ActionPerformed

    private void carta01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carta01ActionPerformed
        casa.acaoCasaSelecionada(1);
    }//GEN-LAST:event_carta01ActionPerformed

    private void carta02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carta02ActionPerformed
        casa.acaoCasaSelecionada(2);
    }//GEN-LAST:event_carta02ActionPerformed

    private void btnEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmprestimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEmprestimoActionPerformed

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        if (JOptionPane.showConfirmDialog(btn_sair, "Tem certeza que deseja sair?", "Sair do Jogo", 2, 0) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_btn_sairActionPerformed

    private void btn_jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jogarActionPerformed
        int digitado = this.jogarDado();
        dado_num.setText(digitado + "");
        JOptionPane.showMessageDialog(this, this.jogadorLocal.getNome() + " : " + this.jogadorLocal.getNome());
        this.calculaDistancia(this.jogadorLocal.getId(), digitado);
        objTabuleiro.getAcaoParaPosicao(posicaoAtual);
    }//GEN-LAST:event_btn_jogarActionPerformed

    private void hostConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostConfigActionPerformed
        ConfigurarHost hostCon = new ConfigurarHost();
        hostCon.setClasseMae(this);
    }//GEN-LAST:event_hostConfigActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            //ex.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "Erro no carregamento gráfico: " + ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JogoDaMesa().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Extra;
    private javax.swing.JPanel PainelDado;
    private javax.swing.JButton btnCadastro;
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnEmprestimo;
    private javax.swing.JButton btn_jogar;
    private javax.swing.JButton btn_sair;
    private javax.swing.JButton carta01;
    private javax.swing.JButton carta02;
    private javax.swing.JButton carta03;
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
    private javax.swing.JLabel fundoTabuleiro;
    private javax.swing.JButton hostConfig;
    private javax.swing.JLabel icone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator linhaSuperior;
    private javax.swing.JList<String> lista_jogadores;
    private javax.swing.JLabel nome_casa_label;
    private javax.swing.JLabel nome_jogo;
    private javax.swing.JPanel painelCartas;
    private javax.swing.JPanel statusAtual;
    private javax.swing.JPanel tabuleiro;
    // End of variables declaration//GEN-END:variables

    class Movimento extends Thread {

        private int xAtual, yAtual;
        private int valor = 0;
        private JLabel jg = null;
        private int larguraDaCasa = 120;
        private int alturaDaCasa = 62;

        public Movimento(JLabel jogador) {
            this.jg = jogador;
            xAtual = 0;
            yAtual = 12;
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
                icone.setText("" + jg.getX());
                if (jg.getX() < xAtual) {
                    if (jg.getX() > 984) {
                        yAtual = yAtual + 62;
                        xAtual = xAtual - 984;
                        jg.setBounds(0, yAtual, 100, 50);
                    }
                    dado_label.setText("" + jg.getY());
                    if (jg.getY() > 300) {
                        yAtual = yAtual - 300;
                        jg.setBounds(0, yAtual, 100, 50);
                    }

                    //Não permite parar nas posições acima de 30 (dia do mês maior que 30). 
                    if (jg.getY() > 200 && jg.getY() < 300 && jg.getX() > 240) {
                        //jg.setBounds(0, 12, 100, 50);
                        yAtual = 12;
                        xAtual = xAtual - 240;
                    }

                    jg.setBounds(jg.getX() + 8, yAtual, 100, 50);
                }

                int linhasInteiras = 0;
                int ultimaLinha = 0;

                if (jg.getY() <= alturaDaCasa) {
                    linhasInteiras = 0;
                } else if (jg.getY() > alturaDaCasa && jg.getY() < alturaDaCasa * 2) {
                    linhasInteiras = 7;
                } else if (jg.getY() > alturaDaCasa * 2 && jg.getY() < alturaDaCasa * 3) {
                    linhasInteiras = 14;
                } else if (jg.getY() > alturaDaCasa * 3 && jg.getY() < alturaDaCasa * 4) {
                    linhasInteiras = 21;
                } else if (jg.getY() > alturaDaCasa * 4) {
                    linhasInteiras = 28;
                }

                if (jg.getX() <= larguraDaCasa) {
                    ultimaLinha = 1;
                } else if (jg.getX() > larguraDaCasa && jg.getX() < larguraDaCasa * 2) {
                    ultimaLinha = 2;
                } else if (jg.getX() > larguraDaCasa * 2 && jg.getX() < larguraDaCasa * 3) {
                    ultimaLinha = 3;
                } else if (jg.getX() > larguraDaCasa * 3 && jg.getX() < larguraDaCasa * 4) {
                    ultimaLinha = 4;
                } else if (jg.getX() > larguraDaCasa * 4 && jg.getX() < larguraDaCasa * 5) {
                    ultimaLinha = 5;
                } else if (jg.getX() > larguraDaCasa * 5 && jg.getX() < larguraDaCasa * 6) {
                    ultimaLinha = 6;
                } else if (jg.getX() > larguraDaCasa * 6) {
                    ultimaLinha = 7;
                }

                posicaoAtual = linhasInteiras + ultimaLinha;
            }
        }

        private int calculaPosicao() {

            int linhasInteiras = 0;
            int ultimaLinha = 0;

            if (jg.getY() <= alturaDaCasa) {
                linhasInteiras = 0;
            } else if (jg.getY() > alturaDaCasa && jg.getY() > alturaDaCasa * 2) {
                linhasInteiras = 7;
            } else if (jg.getY() > alturaDaCasa * 2 && jg.getY() > alturaDaCasa * 3) {
                linhasInteiras = 14;
            } else if (jg.getY() > alturaDaCasa * 3 && jg.getY() > alturaDaCasa * 4) {
                linhasInteiras = 21;
            } else if (jg.getY() > alturaDaCasa * 4) {
                linhasInteiras = 28;
            }

            if (jg.getX() <= larguraDaCasa) {
                ultimaLinha = 1;
            } else if (jg.getX() > larguraDaCasa && jg.getX() > larguraDaCasa * 2) {
                ultimaLinha = 2;
            } else if (jg.getX() > larguraDaCasa * 2 && jg.getX() > larguraDaCasa * 3) {
                ultimaLinha = 3;
            } else if (jg.getX() > larguraDaCasa * 3 && jg.getX() > larguraDaCasa * 4) {
                ultimaLinha = 4;
            } else if (jg.getX() > larguraDaCasa * 4 && jg.getX() > larguraDaCasa * 5) {
                ultimaLinha = 5;
            } else if (jg.getX() > larguraDaCasa * 5 && jg.getX() > larguraDaCasa * 6) {
                ultimaLinha = 6;
            } else if (jg.getX() > larguraDaCasa * 6) {
                ultimaLinha = 7;
            }
            return linhasInteiras + ultimaLinha;
        }
    }
}
