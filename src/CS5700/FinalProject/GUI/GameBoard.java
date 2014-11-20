package CS5700.FinalProject.GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import CS5700.FinalProject.Game;
import CS5700.FinalProject.Status;

public class GameBoard extends JFrame implements ActionListener
{
	Game gameDate = new Game();
	private Container pane = getContentPane();
	GroupLayout gl = new GroupLayout(pane);
	
	private JButton[] player = new JButton[2];
	private JTextArea[] playerStatus = new JTextArea[2];
	private JButton[] enemy = new JButton[3];
	private JTextArea[] enemyStatus = new JTextArea[3];
	
	private JButton clear, rename, add, doCommand, combo,
						undoCommand, fireball, sword, hotdog;
	
	private JTextField[] cmd1 = new JTextField[3];
	private JTextField[] cmd2 = new JTextField[3];
	private JLabel[] commandLabel = new JLabel[4];
	private JMenuBar menuMB = new JMenuBar();
	private JMenuItem exitI, newgameI;
	private JTextField gameLV = new JTextField();
	private JLabel cmdL = new JLabel("Command",SwingConstants.CENTER);
	private JLabel weaponL = new JLabel("Weapon",SwingConstants.CENTER);
	private JLabel playerL = new JLabel("Player",SwingConstants.CENTER);
	private JLabel enemyL = new JLabel("Enemy",SwingConstants.CENTER);
	private JTextArea queue = new JTextArea();
	Border border = LineBorder.createGrayLineBorder();
	Boolean comboClicked = false;
	
	public GameBoard()
	{
		setTitle("Final Project");
		setSize(600,700);
		setLocation(10,10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane.setLayout(null);
		pane.setBackground(Color.LIGHT_GRAY);
		
		gameDate.RandomEnemie();
		
		initializeItem();
		addItem();
		
		setVisible(true);
		setResizable(false);
	}
	
	public void initializeItem()
	{
		//initialize player and enemy
		for(int i=0;i<2;i++)
		{
			int h, m, s;
			String n = gameDate.getCharacter("Player"+(i+1)).getName();
			
			h = gameDate.getCharacter("Player"+(i+1)).getStatus().getHealth();
			m = gameDate.getCharacter("Player"+(i+1)).getStatus().getMagika();
			s = gameDate.getCharacter("Player"+(i+1)).getStatus().getStamina();
			
			player[i] = new JButton("Player"+(i+1));
			player[i].setSize(150,150);
			player[i].setLocation(i*150, 255);
			player[i].setBorderPainted(true);
			player[i].addActionListener(this);
			
			playerStatus[i] = new JTextArea();
			playerStatus[i].setSize(150, 75);
			playerStatus[i].setLocation(i*150, 405);
			playerStatus[i].setBorder(border);
			playerStatus[i].setEditable(false);
			playerStatus[i].setText(String.format("  Name: %s \n  Health: %d \n  Magika: %d \n  Stamina: %d",n,h,m,s));
		}
		
		for(int i=0;i<3;i++)
		{
			int h, m, s;
			String n = gameDate.getEnemy(i).getName();
			h = gameDate.getEnemy(i).getStatus().getHealth();
			m = gameDate.getEnemy(i).getStatus().getMagika();
			s = gameDate.getEnemy(i).getStatus().getStamina();
			
			enemy[i] = new JButton(n);
			enemy[i].setSize(150,150);
			enemy[i].setLocation(i*150, 15);
			enemy[i].setBorderPainted(true);
			enemy[i].addActionListener(this);
			
			enemyStatus[i] = new JTextArea();
			enemyStatus[i].setSize(150, 75);
			enemyStatus[i].setLocation(i*150, 165);
			enemyStatus[i].setBorder(border);
			enemyStatus[i].setEditable(false);
			enemyStatus[i].setText(String.format("  Name: %s \n  Health: %d \n  Magika: %d \n  Stamina: %d",n,h,m,s));
			
			if(i >= gameDate.getEnemyNumber())
			{
				enemy[i].setText("");
				enemyStatus[i].setText("");
			}
		}
		
		//initialize command display
		commandLabel[0] = new JLabel("Character",SwingConstants.CENTER);
		commandLabel[1] = new JLabel("Action",SwingConstants.CENTER);
		commandLabel[2] = new JLabel("Target",SwingConstants.CENTER);
		commandLabel[3] = new JLabel("For combo",SwingConstants.RIGHT);
		
		for(int i=0; i<3;i++)
		{
			commandLabel[i].setSize(100, 30);
			commandLabel[i].setLocation(475, 250 + i*70);
			cmd1[i] = new JTextField();
			cmd1[i].setSize(60, 30);
			cmd1[i].setLocation(455, 280 + i*70);
			cmd1[i].setBorder(border);
			cmd1[i].setEditable(false);
			
			cmd2[i] = new JTextField(" None");
			cmd2[i].setSize(60, 30);
			cmd2[i].setLocation(530, 280 + i*70);
			cmd2[i].setBorder(border);
			cmd2[i].setEditable(false);
		}
		
		commandLabel[3].setSize(100, 30);
		commandLabel[3].setLocation(491, 450);
		
		//initialize button, label, text field, and menu
		enemyL.setSize(450, 15);
		enemyL.setBackground(Color.red);
		enemyL.setLocation(0,0);
		enemyL.setOpaque(true);
		enemyL.setBorder(border);
		enemyL.setForeground(Color.white);
		
		playerL.setSize(300, 15);
		playerL.setBackground(Color.blue);
		playerL.setLocation(0,240);
		playerL.setOpaque(true);
		playerL.setBorder(border);
		playerL.setForeground(Color.white);
		
		clear = new JButton("Clear");
		clear.setSize(150,30);
		clear.setLocation(450,60);
		clear.addActionListener(this);
		
		rename = new JButton("Rename");
		rename.setSize(150,30);
		rename.setLocation(450,90);
		rename.addActionListener(this);
		
		add = new JButton("Add Command");
		add.setSize(150,30);
		add.setLocation(450,120);
		add.addActionListener(this);
		
		doCommand = new JButton("Do");
		doCommand.setSize(150,30);
		doCommand.setLocation(450,150);
		doCommand.addActionListener(this);
		
		undoCommand = new JButton("Undo");
		undoCommand.setSize(150,30);
		undoCommand.setLocation(450,180);
		undoCommand.addActionListener(this);
		
		combo = new JButton("Combo Attack");
		combo.setSize(150,30);
		combo.setLocation(450,210);
		combo.addActionListener(this);
		
		fireball = new JButton("Fireball");
		fireball.setSize(150,70);
		fireball.setLocation(300,270);
		fireball.addActionListener(this);
		
		sword = new JButton("Sword");
		sword.setSize(150,70);
		sword.setLocation(300,340);
		sword.addActionListener(this);
		
		hotdog = new JButton("Hotdog");
		hotdog.setSize(150,70);
		hotdog.setLocation(300,410);
		hotdog.addActionListener(this);
		
		cmdL.setSize(150, 30);
		cmdL.setBackground(Color.green);
		cmdL.setLocation(450, 30);
		cmdL.setOpaque(true);
		cmdL.setBorder(border);
		
		weaponL.setSize(150, 30);
		weaponL.setBackground(Color.green);
		weaponL.setLocation(300,240);
		weaponL.setOpaque(true);
		weaponL.setBorder(border);
		
		gameLV.setSize(150,30);
		gameLV.setLocation(450,0);
		gameLV.setBorder(border);
		gameLV.setEditable(false);
		gameLV.setText(String.format("  Level : %d",gameDate.getLevel()));
		
		queue.setSize(600,220);
		queue.setLocation(0,480);
		queue.setBorder(border);
		queue.setText(gameDate.getInvoker().toString());
	}
	
	public void addItem()
	{
		for(int i=0;i<2;i++)
		{
			pane.add(player[i]);
			pane.add(playerStatus[i]);
		}
		
		for(int i=0;i<3;i++)
		{
			pane.add(enemy[i]);
			pane.add(enemyStatus[i]);
			pane.add(cmd1[i]);
			pane.add(cmd2[i]);
			pane.add(commandLabel[i]);
		}
		
		pane.add(commandLabel[3]);
		pane.add(clear);
		pane.add(rename);
		pane.add(add);
		pane.add(doCommand);
		pane.add(undoCommand);
		pane.add(fireball);
		pane.add(sword);
		pane.add(hotdog);
		pane.add(cmdL);
		pane.add(weaponL);
		pane.add(combo);
		pane.add(queue);
		pane.add(playerL);
		pane.add(enemyL);
		pane.add(gameLV);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		
		
	}
}
