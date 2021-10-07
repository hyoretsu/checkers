package com.hyoretsu.checkers.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.hyoretsu.checkers.Square;
import com.hyoretsu.checkers.dtos.Change;
import com.hyoretsu.checkers.util.Const;
import com.hyoretsu.checkers.util.Hooks;

/** Main window of the game */
public class Window extends JFrame {
 private BoardGUI boardGUI;
 private List<SquareGUI> validMoves;
 private SquareGUI originSquare;
 private Boolean firstClick;

 public Window() {
  this.validMoves = new ArrayList<>();
  this.boardGUI = new BoardGUI(Const.size, this);
  this.originSquare = null;
  this.firstClick = true;
  this.initComponents();

  super.setLocationRelativeTo(null);
  super.setVisible(true);
  super.pack();
 }

 /**
  * Responds to clicks in the board
  *
  * @param clickedSquare Square the player just clicked.
  */
 public void respond(SquareGUI clickedSquare) {
  // Starting a new movement
  if (this.firstClick) {
   // Clicked square is empty
   if (!Hooks.hasPiece(clickedSquare)) {
    return;
   }

   // Piece isn't part of the turn
   if (Hooks.getPiece(clickedSquare).getColor() != Hooks.getTurn()) {
    JOptionPane.showMessageDialog(this, "It's currently not your turn.");
    return;
   }

   this.validMoves.clear();
   Hooks.validMoves(clickedSquare).forEach(square -> {
    SquareGUI squareGUI = convertToGUI(square);
    this.validMoves.add(squareGUI);
    squareGUI.select();
   });

   // There are no valid moves
   if (this.validMoves.size() == 0) {
    // Don't start a movement
    this.firstClick = true;
    return;
   }

   this.originSquare = clickedSquare;
   this.firstClick = false;
  } else { // Selecting a square to move to
   // Move isn't valid
   if (!validMoves.contains(clickedSquare)) {
    return;
   }

   List<Change> changes = Hooks.getPiece(this.originSquare).move(Hooks.getSquare(clickedSquare));
   this.boardGUI.update(changes, this.validMoves);

   // Not a simple move (capturing)
   if (changes.size() > 1) {
    this.validMoves.clear();
    List<Square> furtherCaptures = Hooks.getPiece(clickedSquare).filterCaptures();

    if (!furtherCaptures.isEmpty()) {
     this.originSquare = clickedSquare;
     furtherCaptures.forEach(move -> {
      this.validMoves.add(this.convertToGUI(move));
      convertToGUI(move).select();
     });

     return;
    }
   }

   // Reset click logic
   this.firstClick = true;
   Hooks.nextTurn();
  }

  return;
 }

 private SquareGUI convertToGUI(Square square) {
  return this.boardGUI.getSquares()[square.getPosX()][square.getPosY()];
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

 // JPanel (UI) stuff
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

  for (int i = 0; i < 8; i++) {
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
