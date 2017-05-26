package br.uefs.ecomp.jogodamesada.cliente.view;

import br.uefs.ecomp.jogodamesada.cliente.model.Casa;
import br.uefs.ecomp.jogodamesada.cliente.model.Pessoa;
import br.uefs.ecomp.jogodamesada.cliente.model.Tabuleiro;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private String[] jogadorLocal;
    private Casa casa;
    private Tabuleiro objTabuleiro;
    private String host;
    private String porta;

    public JogoDaMesa() {
        initComponents();
        this.configuracoesAdicionais();
        this.configuraIcon();
        jogadorLocal = new String[2]; //Código - Nome/Login;
        objTabuleiro = new Tabuleiro();
        casa = new Casa();
        host = "127.0.0.1";
        porta = "12345";
    }

    public String getHost() {
        return this.host;
    }

    public String getPorta() {
        return this.porta;
    }

    public void setHost(String h) {
        this.host = h;
    }

    public void setPorta(String p) {
        this.porta = p;
    }
    
    public void setNomeJogadorLocal(String nome ){
        this.jogadorLocal[1] = nome;
    }

    private void configuraIcon() {
        URL url = this.getClass().getResource("..\\image\\explosao.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
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

        tabuleiro.setBounds(0, 0, 450, 1050);
        
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
    }

    public void atualizarJogadorAtual() {
        ArrayList<Pessoa> jogadores = (ArrayList<Pessoa>) objTabuleiro.getJogadores();
        jogadores.stream().filter((p) -> (this.jogadorLocal[1].equals(p.getNome()))).forEachOrdered((p) -> {
            this.jogadorLocal[0] = "J" + (jogadores.indexOf(p) + 1);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        tabuleiro = new javax.swing.JPanel();
        fundoTabuleiro = new javax.swing.JLabel();
        painelSuperior = new javax.swing.JPanel();
        icone = new javax.swing.JLabel();
        nome_jogo = new javax.swing.JLabel();
        dia_seg = new javax.swing.JLabel();
        dia_dom = new javax.swing.JLabel();
        dia_ter = new javax.swing.JLabel();
        dia_qua = new javax.swing.JLabel();
        dia_qui = new javax.swing.JLabel();
        dia_sex = new javax.swing.JLabel();
        dia_sab = new javax.swing.JLabel();
        statusAtual = new javax.swing.JPanel();
        btn_sair = new javax.swing.JButton();
        btn_jogar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_jogadores = new javax.swing.JList<>();
        PainelDado = new javax.swing.JPanel();
        dado_label = new javax.swing.JLabel();
        dado_num = new javax.swing.JLabel();
        painelCartas = new javax.swing.JPanel();
        carta02 = new javax.swing.JButton();
        carta01 = new javax.swing.JButton();
        carta03 = new javax.swing.JButton();
        btnConectar = new javax.swing.JButton();
        btnCadastro = new javax.swing.JButton();
        Extra = new javax.swing.JButton();
        btnEmprestimo = new javax.swing.JButton();
        hostConfig = new javax.swing.JButton();

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

        tabuleiro.setBackground(new java.awt.Color(30, 30, 30));

        fundoTabuleiro.setBackground(new java.awt.Color(23, 55, 67));
        fundoTabuleiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/uefs/ecomp/jogodamesada/cliente/image/fundo_painel.png"))); // NOI18N

        javax.swing.GroupLayout tabuleiroLayout = new javax.swing.GroupLayout(tabuleiro);
        tabuleiro.setLayout(tabuleiroLayout);
        tabuleiroLayout.setHorizontalGroup(
            tabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabuleiroLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(fundoTabuleiro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabuleiroLayout.setVerticalGroup(
            tabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabuleiroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fundoTabuleiro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        icone.setText("Icone");

        nome_jogo.setFont(new java.awt.Font("Tempus Sans ITC", 3, 18)); // NOI18N
        nome_jogo.setText("Jogo da Mesada");

        dia_seg.setFont(new java.awt.Font("Bodoni MT Black", 3, 18)); // NOI18N
        dia_seg.setForeground(new java.awt.Color(153, 0, 153));
        dia_seg.setText("Segunda");

        dia_dom.setFont(new java.awt.Font("Bodoni MT Black", 3, 18)); // NOI18N
        dia_dom.setForeground(new java.awt.Color(153, 0, 153));
        dia_dom.setText("Domingo");

        dia_ter.setFont(new java.awt.Font("Bodoni MT Black", 3, 18)); // NOI18N
        dia_ter.setForeground(new java.awt.Color(153, 0, 153));
        dia_ter.setText("Terça");

        dia_qua.setFont(new java.awt.Font("Bodoni MT Black", 3, 18)); // NOI18N
        dia_qua.setForeground(new java.awt.Color(153, 0, 153));
        dia_qua.setText("Quarta");

        dia_qui.setFont(new java.awt.Font("Bodoni MT Black", 3, 18)); // NOI18N
        dia_qui.setForeground(new java.awt.Color(153, 0, 153));
        dia_qui.setText("Quinta");

        dia_sex.setFont(new java.awt.Font("Bodoni MT Black", 3, 18)); // NOI18N
        dia_sex.setForeground(new java.awt.Color(153, 0, 153));
        dia_sex.setText("Sexta");

        dia_sab.setFont(new java.awt.Font("Bodoni MT Black", 3, 18)); // NOI18N
        dia_sab.setForeground(new java.awt.Color(153, 0, 153));
        dia_sab.setText("Sábado");

        javax.swing.GroupLayout painelSuperiorLayout = new javax.swing.GroupLayout(painelSuperior);
        painelSuperior.setLayout(painelSuperiorLayout);
        painelSuperiorLayout.setHorizontalGroup(
            painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSuperiorLayout.createSequentialGroup()
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelSuperiorLayout.createSequentialGroup()
                        .addGap(477, 477, 477)
                        .addComponent(icone))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelSuperiorLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(dia_dom)
                        .addGap(57, 57, 57)
                        .addComponent(dia_seg)
                        .addGap(72, 72, 72)
                        .addComponent(dia_ter)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelSuperiorLayout.createSequentialGroup()
                        .addComponent(dia_qua)
                        .addGap(69, 69, 69)
                        .addComponent(dia_qui)
                        .addGap(68, 68, 68)
                        .addComponent(dia_sex)
                        .addGap(93, 93, 93)
                        .addComponent(dia_sab))
                    .addComponent(nome_jogo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelSuperiorLayout.setVerticalGroup(
            painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(icone)
                    .addComponent(nome_jogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dia_dom)
                    .addComponent(dia_qua)
                    .addComponent(dia_seg)
                    .addComponent(dia_ter)
                    .addComponent(dia_qui)
                    .addComponent(dia_sex)
                    .addComponent(dia_sab)))
        );

        statusAtual.setBackground(new java.awt.Color(0, 180, 0));

        javax.swing.GroupLayout statusAtualLayout = new javax.swing.GroupLayout(statusAtual);
        statusAtual.setLayout(statusAtualLayout);
        statusAtualLayout.setHorizontalGroup(
            statusAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        statusAtualLayout.setVerticalGroup(
            statusAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );

        btn_sair.setBackground(new java.awt.Color(153, 0, 153));
        btn_sair.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        btn_sair.setText("Sair");
        btn_sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });

        btn_jogar.setBackground(new java.awt.Color(153, 0, 153));
        btn_jogar.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        btn_jogar.setText("Jogar");
        btn_jogar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jogarActionPerformed(evt);
            }
        });

        lista_jogadores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lista_jogadores);

        PainelDado.setBackground(new java.awt.Color(255, 0, 102));

        dado_label.setText("Dado:");

        dado_num.setFont(new java.awt.Font("Stencil", 3, 48)); // NOI18N
        dado_num.setText("4");

        javax.swing.GroupLayout PainelDadoLayout = new javax.swing.GroupLayout(PainelDado);
        PainelDado.setLayout(PainelDadoLayout);
        PainelDadoLayout.setHorizontalGroup(
            PainelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dado_label)
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dado_num, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PainelDadoLayout.setVerticalGroup(
            PainelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dado_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dado_num))
        );

        painelCartas.setBackground(new java.awt.Color(250, 130, 0));

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

        btnConectar.setBackground(new java.awt.Color(153, 0, 153));
        btnConectar.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        btnConectar.setText("Jogar Agora");
        btnConectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        btnCadastro.setBackground(new java.awt.Color(153, 0, 153));
        btnCadastro.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        btnCadastro.setText("Sou Novo Aqui");
        btnCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroActionPerformed(evt);
            }
        });

        Extra.setBackground(new java.awt.Color(153, 0, 153));
        Extra.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        Extra.setText("Extra");
        Extra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Extra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExtraActionPerformed(evt);
            }
        });

        btnEmprestimo.setBackground(new java.awt.Color(153, 0, 153));
        btnEmprestimo.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        btnEmprestimo.setText("Pedir Empréstimo");
        btnEmprestimo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmprestimoActionPerformed(evt);
            }
        });

        hostConfig.setText("Configurar Host");
        hostConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hostConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(painelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(painelCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_jogar, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(statusAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(PainelDado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(tabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEmprestimo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(btnCadastro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConectar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Extra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)))
                .addGap(75, 75, 75))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hostConfig)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addComponent(tabuleiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_jogar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(statusAtual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PainelDado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painelCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Extra, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hostConfig)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        if (JOptionPane.showConfirmDialog(btn_sair, "Tem certeza que deseja sair?", "Sair do Jogo", 2, 0) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_btn_sairActionPerformed

    private void btn_jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jogarActionPerformed
        int digitado = jogarDado();
        dado_num.setText(digitado + "");
        this.atualizarJogadorAtual();
        this.calculaDistancia(jogadorLocal[0], digitado);
        objTabuleiro.getAcaoParaPosicao(posicaoAtual);
    }//GEN-LAST:event_btn_jogarActionPerformed

    private void carta01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carta01ActionPerformed
        casa.acaoCasaSelecionada(1);
    }//GEN-LAST:event_carta01ActionPerformed

    private void carta02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carta02ActionPerformed
        casa.acaoCasaSelecionada(2);
    }//GEN-LAST:event_carta02ActionPerformed

    private void carta03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carta03ActionPerformed
        casa.acaoCasaSelecionada(3);
    }//GEN-LAST:event_carta03ActionPerformed

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        Login login = new Login();
        login.setClasseMae(this);
    }//GEN-LAST:event_btnConectarActionPerformed

    private void btnCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroActionPerformed
        Cadastro cad = new Cadastro();
        cad.setClasseMae(this);
    }//GEN-LAST:event_btnCadastroActionPerformed

    private void ExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExtraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExtraActionPerformed

    private void btnEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmprestimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEmprestimoActionPerformed

    private void hostConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostConfigActionPerformed
        ConfigurarHost hostCon = new ConfigurarHost();
        hostCon.setClasseMae(this);
    }//GEN-LAST:event_hostConfigActionPerformed

    public void setLogodo(boolean logou){
        //Habilitar os botões do jogo...
        JOptionPane.showMessageDialog(this, "Você está logado");
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
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lista_jogadores;
    private javax.swing.JLabel nome_jogo;
    private javax.swing.JPanel painelCartas;
    private javax.swing.JPanel painelSuperior;
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
