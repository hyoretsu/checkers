package com.hyoretsu.checkers.gui;

import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.hyoretsu.checkers.Game;

/** Main window of the game */
public class Window extends JFrame {
 private Game game = new Game();
 private boolean firstClick = true;
 private SquareGUI originSquare = null;
 private BoardGUI boardGUI = new BoardGUI(this);

 public Window() {
  this.initComponents();
  this.boardGUI.update();

  super.setVisible(true);
  super.pack();
 }

 public Game getGame() {
  return this.game;
 }

 /**
  * Responds to clicks in the board
  *
  * @param clickedSquare Square the player just clicked.
  */
 public void respond(SquareGUI clickedSquare) {
  if (this.firstClick) {
   if (clickedSquare.hasPiece()) {
    this.originSquare = clickedSquare;
    this.originSquare.select();
    this.firstClick = false;
   } else { // Didn't click on a valid square
    JOptionPane.showMessageDialog(this, "Clique em uma peça.");
   }
  } else {
   this.game.movePiece(this.originSquare.getSquare(), clickedSquare.getSquare());
   this.originSquare.deselect();
   this.firstClick = true;
   this.boardGUI.update();
  }
 }

 private JPanel columnsPanel = new JPanel();
 private JPanel linesPanel = new JPanel();
 private JLabel vLabel1 = new JLabel();
 private JLabel vLabel2 = new JLabel();
 private JLabel vLabel3 = new JLabel();
 private JLabel vLabel4 = new JLabel();
 private JLabel vLabel5 = new JLabel();
 private JLabel vLabel6 = new JLabel();
 private JLabel vLabel7 = new JLabel();
 private JLabel vLabel8 = new JLabel();
 private JLabel hLabel1 = new JLabel();
 private JLabel hLabel2 = new JLabel();
 private JLabel hLabel3 = new JLabel();
 private JLabel hLabel4 = new JLabel();
 private JLabel hLabel5 = new JLabel();
 private JLabel hLabel6 = new JLabel();
 private JLabel hLabel7 = new JLabel();
 private JLabel hLabel8 = new JLabel();

 private void initComponents() {
  this.setTitle("Checkers");

  this.linesPanel.setLayout(new java.awt.GridLayout(8, 1));
  this.columnsPanel.setLayout(new java.awt.GridLayout(1, 8));

  List<JLabel> labels = Arrays.asList(this.vLabel1, this.vLabel2, this.vLabel3, this.vLabel4, this.vLabel5,
    this.vLabel6, this.vLabel7, this.vLabel8, this.hLabel1, this.hLabel2, this.hLabel3, this.hLabel4, this.hLabel5,
    this.hLabel6, this.hLabel7, this.hLabel8);

  labels.forEach(label -> {
   label.setFont(new java.awt.Font("Arimo", 0, 18));
   label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  });

  for (Integer i = 0; i < 8; i++) {
   JLabel lineLabel = labels.get(i);
   JLabel columnLabel = labels.get(i + 8);

   lineLabel.setText(String.valueOf(i + 1));
   columnLabel.setText(String.valueOf((char) (i + 65)));
   this.linesPanel.add(lineLabel);
   this.columnsPanel.add(columnLabel);
  }

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup().addContainerGap()
      .addComponent(this.linesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
        javax.swing.GroupLayout.PREFERRED_SIZE)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
        .addComponent(this.columnsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE).addComponent(
          this.boardGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
  layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup().addGap(10, 10, 10)
      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(this.linesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 576,
          javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(this.boardGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE))
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(this.columnsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
        javax.swing.GroupLayout.PREFERRED_SIZE)
      .addContainerGap()));

  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  pack();
 }
}
