package com.mycompany.tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
        JButton resetButton=new JButton(),xStarts=new JButton(),oStarts=new JButton();
	boolean playerXTurn;
        int xWins=0,oWins=0;

	TicTacToe(int xWins,int oWins){
		this.xWins=xWins; this.oWins=oWins;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,700);
//		frame.getContentPane().setBackground(new Color(50,50,50));
                frame.setTitle("TiC-TaC-ToE");
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                
                // defining title panel, textfield & adding textfield to title panel & finally adding title panel to frame.
                title_panel.setLayout(new BorderLayout());
//		title_panel.setBounds(0,0,100,50);
		
		textfield.setBackground(new Color(25,25,25));// sets bg color of t-t-toe textfield black
		textfield.setForeground(Color.green);//(new Color(25,255,0));// sets foreground of textfield
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setOpaque(true);
                textfield.setText("TiC-TaC-ToE");

                title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		
                //defining button panel, buttons, then: adding buttons to button panel, then adding button pannel to frame
		button_panel.setLayout(new GridLayout(4,3));
//		button_panel.setBackground(Color.BLUE);//new Color(150,150,150));
		
		for(int i=0;i<9;i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
                //X button               
                button_panel.add(xStarts);
                xStarts.setFont(new Font("Ink Free",Font.BOLD,80));
                xStarts.setText("X");
                xStarts.setFocusable(false);
                xStarts.setForeground(Color.red);
                xStarts.setBackground(Color.black);
                xStarts.addActionListener(this);
                
                //reset button
                button_panel.add(resetButton);
                resetButton.setFont(new Font("Ink Free",Font.ITALIC,60));
                resetButton.setText("Reset");
                resetButton.setFocusable(false);
                resetButton.setForeground(Color.magenta);
                resetButton.setBackground(Color.black);
                resetButton.addActionListener(this);
                
                //O button
                button_panel.add(oStarts);
                oStarts.setFont(new Font("Ink Free",Font.BOLD,80));
                oStarts.setText("O");
                oStarts.setFocusable(false);
                oStarts.setForeground(Color.blue);
                oStarts.setBackground(Color.black);
                oStarts.addActionListener(this);

                
		frame.add(button_panel);
		
                for(int i=0;i<9;i++) buttons[i].setEnabled(false);
	}
        
        @Override
	public  void actionPerformed(ActionEvent e) {
		
		for(int i=0;i<9;i++) {
			if(e.getSource()==buttons[i]) {
				if(playerXTurn) {
					if(buttons[i].getText().equals("")) {
						buttons[i].setForeground(Color.red);//new Color(255,0,0));
						buttons[i].setText("X");
						playerXTurn=false;
						textfield.setText("O turn");
						check();
					}
                                        break;
				}
				else {
					if(buttons[i].getText().equals("")) {
						buttons[i].setForeground(new Color(0,0,255));
						buttons[i].setText("O");
						playerXTurn=true;
						textfield.setText("X turn");
						check();
					}
                                        break;
				}
			}
                        else if(e.getSource()==resetButton){
                            frame.dispose();
                            new TicTacToe(xWins,oWins);
                            break;
                        }
                        else if(e.getSource()==xStarts || e.getSource()==oStarts){
                            //starting the game
                            if(e.getSource()==xStarts){
                                playerXTurn=true;
                                textfield.setText("X turn");
                            }
                            else{
                                playerXTurn=false;
                            textfield.setText("O turn");
                            }
                            for(int j=0;j<9;j++) buttons[j].setEnabled(true);
                            xStarts.setEnabled(false);
                            oStarts.setEnabled(false);
                            break;
                        }
		}
	}
  
        public  void check() {
		//check X or O win conditions horizontally
		     if(checkX(0,1,2) || checkO(0,1,2)) win(0,1,2);
		else if(checkX(3,4,5) || checkO(3,4,5)) win(3,4,5);
		else if(checkX(6,7,8) || checkO(6,7,8)) win(6,7,8);
                //checking column wise
		else if(checkX(0,3,6) || checkO(0,3,6)) win(0,3,6);
		else if(checkX(1,4,7) || checkO(1,4,7)) win(1,4,7);
		else if(checkX(2,5,8) || checkO(2,5,8)) win(2,5,8);
                // checking diagonals
		else if(checkX(0,4,8) || checkO(0,4,8)) win(0,4,8);
		else if(checkX(2,4,6) || checkO(2,4,6)) win(2,4,6);
		
                //Game Ties case
                else if(allBoxesMarked()){
                    for(int i=0;i<9;i++) buttons[i].setEnabled(false);
                    textfield.setText("Game Ties");
                }
	}
        
        public  void win(int a,int b,int c) {
		buttons[a].setBackground(Color.orange);
		buttons[b].setBackground(Color.orange);
		buttons[c].setBackground(Color.orange);
                
		for(int i=0;i<9;i++) buttons[i].setEnabled(false);
		if(buttons[a].getText().equals("X")){ xWins++; textfield.setText("X wins: "+xWins);}
                else {oWins++;   textfield.setText("O wins: "+oWins);}
	}
        
        public boolean allBoxesMarked(){
                for(int i=0;i<9;i++)  if(buttons[i].getText().equals("")) return false;			
		return true;
        }
        
        public boolean checkX(int a, int b, int c){
            return (buttons[a].getText().equals("X") && buttons[b].getText() .equals("X") && buttons[c].getText() .equals("X")); 
        }
        
        public boolean checkO(int a, int b, int c){
            return (buttons[a].getText().equals("O") && buttons[b].getText() .equals("O") && buttons[c].getText() .equals("O"));
        }
        
        public static void main(String[] args) {
            new TicTacToe(0,0);
        }
}
