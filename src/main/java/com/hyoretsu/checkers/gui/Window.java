package com.hyoretsu.checkers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.hyoretsu.checkers.Game;

/** Main window of the game */
public class Window extends JFrame {
 private Game game;
 private boolean firstClick = true;
 private SquareGUI originSquare = null;
 private SquareGUI destinationSquare = null;

 public Window() {
  initComponents();
  newGame();

  // Add action listener to "new" menu
  newMenu.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    newGame();
   }
  });

  // Add action listener to "exit" menu
  exitMenu.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    dispose();
   }
  });

  super.setLocationRelativeTo(null);
  super.setVisible(true);
  super.pack();
 }

 /** Creates a new game and updates the board GUI. */
 private void newGame() {
  if (!firstClick) {
   firstClick = true;
   originSquare.deselect();
  }
  game = new Game();
  update();
 }

 /**
  * Responds to clicks in the board
  *
  * @param clickedSquare Square the player just clicked.
  */
 public void respond(SquareGUI clickedSquare) {
  if (firstClick) {
   if (clickedSquare.hasPiece()) {
    originSquare = clickedSquare;
    originSquare.select();
    firstClick = false;
   } else { // Didn't click on a valid square
    JOptionPane.showMessageDialog(this, "Clique em uma peça.");
   }
  } else {
   destinationSquare = clickedSquare;
   game.movePiece(originSquare.getPosX(), originSquare.getPosY(), destinationSquare.getPosX(),
     destinationSquare.getPosY());
   originSquare.deselect();
   firstClick = true;
   update();
  }
 }

 private void update() {
  boardGUI.update(game);
 }

 /**
  * This method is called from within the constructor to initialize the form.
  * WARNING: Do NOT modify this code. The content of this method is always
  * regenerated by the Form Editor.
  */
 @SuppressWarnings("unchecked")
 // <editor-fold defaultstate="collapsed" desc="Generated
 // Code">//GEN-BEGIN:initComponents
 private void initComponents() {
  linesPanel = new javax.swing.JPanel();
  jLabel3 = new javax.swing.JLabel();
  jLabel4 = new javax.swing.JLabel();
  jLabel5 = new javax.swing.JLabel();
  jLabel6 = new javax.swing.JLabel();
  jLabel7 = new javax.swing.JLabel();
  jLabel8 = new javax.swing.JLabel();
  jLabel2 = new javax.swing.JLabel();
  jLabel1 = new javax.swing.JLabel();
  columnsPanel = new javax.swing.JPanel();
  lbl_a = new javax.swing.JLabel();
  lbl_b = new javax.swing.JLabel();
  lbl_c = new javax.swing.JLabel();
  lbl_d = new javax.swing.JLabel();
  lbl_e = new javax.swing.JLabel();
  lbl_f = new javax.swing.JLabel();
  lbl_g = new javax.swing.JLabel();
  lbl_h = new javax.swing.JLabel();
  boardGUI = new BoardGUI(this);
  jMenuBar1 = new javax.swing.JMenuBar();
  archiveMenu = new javax.swing.JMenu();
  newMenu = new javax.swing.JMenuItem();
  jSeparator1 = new javax.swing.JPopupMenu.Separator();
  exitMenu = new javax.swing.JMenuItem();

  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

  linesPanel.setLayout(new java.awt.GridLayout(8, 1));

  jLabel3.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
  jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel3.setText("7");
  linesPanel.add(jLabel3);

  jLabel4.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
  jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel4.setText("6");
  linesPanel.add(jLabel4);

  jLabel5.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
  jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel5.setText("5");
  linesPanel.add(jLabel5);

  jLabel6.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
  jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel6.setText("4");
  linesPanel.add(jLabel6);

  jLabel7.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
  jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel7.setText("3");
  linesPanel.add(jLabel7);

  jLabel8.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
  jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel8.setText("2");
  linesPanel.add(jLabel8);

  jLabel2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
  jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel2.setText("1");
  linesPanel.add(jLabel2);

  jLabel1.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
  jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel1.setText("0");
  linesPanel.add(jLabel1);

  columnsPanel.setLayout(new java.awt.GridLayout(1, 8));

  lbl_a.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
  lbl_a.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  lbl_a.setText("0");
  columnsPanel.add(lbl_a);

  lbl_b.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
  lbl_b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  lbl_b.setText("1");
  columnsPanel.add(lbl_b);

  lbl_c.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
  lbl_c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  lbl_c.setText("2");
  columnsPanel.add(lbl_c);

  lbl_d.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
  lbl_d.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  lbl_d.setText("3");
  columnsPanel.add(lbl_d);

  lbl_e.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
  lbl_e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  lbl_e.setText("4");
  columnsPanel.add(lbl_e);

  lbl_f.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
  lbl_f.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  lbl_f.setText("5");
  columnsPanel.add(lbl_f);

  lbl_g.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
  lbl_g.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  lbl_g.setText("6");
  columnsPanel.add(lbl_g);

  lbl_h.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
  lbl_h.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  lbl_h.setText("7");
  columnsPanel.add(lbl_h);

  archiveMenu.setText("Jogo");

  newMenu.setAccelerator(
    javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
  newMenu.setText("Novo");
  archiveMenu.add(newMenu);
  archiveMenu.add(jSeparator1);

  exitMenu.setAccelerator(
    javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
  exitMenu.setText("Sair");
  archiveMenu.add(exitMenu);

  jMenuBar1.add(archiveMenu);

  setJMenuBar(jMenuBar1);

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout
    .setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout.createSequentialGroup().addContainerGap()
            .addComponent(linesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(columnsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE).addComponent(
                boardGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
  layout
    .setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addGap(10, 10, 10)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 576,
              javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(boardGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE))
          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
          .addComponent(columnsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE)
          .addContainerGap()));

  pack();
 }// </editor-fold>//GEN-END:initComponents

 // Variables declaration - do not modify//GEN-BEGIN:variables
 private javax.swing.JLabel jLabel1;
 private javax.swing.JLabel jLabel2;
 private javax.swing.JLabel jLabel3;
 private javax.swing.JLabel jLabel4;
 private javax.swing.JLabel jLabel5;
 private javax.swing.JLabel jLabel6;
 private javax.swing.JLabel jLabel7;
 private javax.swing.JLabel jLabel8;
 private javax.swing.JMenuBar jMenuBar1;
 private javax.swing.JPopupMenu.Separator jSeparator1;
 private javax.swing.JLabel lbl_a;
 private javax.swing.JLabel lbl_b;
 private javax.swing.JLabel lbl_c;
 private javax.swing.JLabel lbl_d;
 private javax.swing.JLabel lbl_e;
 private javax.swing.JLabel lbl_f;
 private javax.swing.JLabel lbl_g;
 private javax.swing.JLabel lbl_h;
 private javax.swing.JMenu archiveMenu;
 private javax.swing.JMenuItem newMenu;
 private javax.swing.JMenuItem exitMenu;
 private javax.swing.JPanel columnsPanel;
 private javax.swing.JPanel linesPanel;
 private BoardGUI boardGUI;
 // End of variables declaration//GEN-END:variables
}
