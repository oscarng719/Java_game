package CS5700.FinalProject.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import CS5700.FinalProject.*;
import CS5700.FinalProject.Command.Combo;
import CS5700.FinalProject.Command.Command;
import CS5700.FinalProject.Command.RenameCommand;
import CS5700.FinalProject.Command.WeaponCommand;
import CS5700.FinalProject.Weapon.WeaponFactory;
import CS5700.FinalProject.Character;

public class GameBoard extends JFrame implements ActionListener
{
	Game gameData = new Game();
	String nameInput;
	private Container pane = getContentPane();
	GroupLayout gl = new GroupLayout(pane);
	
	private JButton[] player = new JButton[2];
	private JTextArea[] playerStatus = new JTextArea[2];
	private JButton[] enemy = new JButton[3];
	private JTextArea[] enemyStatus = new JTextArea[3];
	
	private JButton clear, rename, add, doCommand, combo,
						undoCommand, fireball, sword, arrow;
	
	private JTextField[] cmd1 = new JTextField[3];
	private JTextField[] cmd2 = new JTextField[3];
	private JLabel[] commandLabel = new JLabel[4];
	private JMenuBar menuMB = new JMenuBar();
	private JMenu gameM;
	private JMenuItem exitI, newgameI;
	private JTextField gameLVText = new JTextField();
	private JLabel cmdL = new JLabel("Command",SwingConstants.CENTER);
	private JLabel weaponL = new JLabel("Weapon",SwingConstants.CENTER);
	private JLabel playerL = new JLabel("Player",SwingConstants.CENTER);
	private JLabel enemyL = new JLabel("Enemy",SwingConstants.CENTER);
	private JLabel gameOverL = new JLabel("Game Over", SwingConstants.CENTER);
	private JLabel titleL = new JLabel("CS 5700 Final Project",SwingConstants.CENTER);
	private JLabel bgL = new JLabel(new ImageIcon("picture/bg.jpg"));
	private JButton startGameB = new JButton("Start Game");
	private JTextArea queue = new JTextArea();
	Border border = LineBorder.createGrayLineBorder();
	Boolean comboClicked = false;
	String name1, name2;
	
	public GameBoard()
	{
		setTitle("Final Project");
		setSize(600,700);
		setLocation(10,10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane.setLayout(null);
		pane.setBackground(Color.LIGHT_GRAY);
		
		gameData.RandomEnemie();
		
		mainMenu();
		
		setVisible(true);
		setResizable(false);
	}
	
	public void mainMenu()
	{
		bgL.setSize(600, 700);
		bgL.setLocation(0, 0);
		
		startGameB.setSize(150, 60);
		startGameB.setLocation(225, 300);
		startGameB.addActionListener(this);
		
		titleL.setSize(600, 60);
		titleL.setLocation(0, 80);
		titleL.setFont(new Font("Arial", Font.PLAIN, 40));
		titleL.setForeground (Color.WHITE);
		
		pane.add(titleL);
		pane.add(bgL);
		pane.add(startGameB);
		
	}
	
	public void initializeItem()
	{
		setJMenuBar(menuMB);
		gameM = new JMenu("Game");
		newgameI = new JMenuItem("New Game");
		gameM.add(newgameI);
		newgameI.addActionListener(this);
		
		exitI = new JMenuItem("Exit");
		gameM.add(exitI);
		exitI.addActionListener(this);
		menuMB.add(gameM);
		
		//initialize player and enemy
		for(int i=0;i<2;i++)
		{
			int h, m, s;
			String n = gameData.getPlayer(i).getName();
			h = gameData.getPlayer(i).getStatus().getHealth();
			m = gameData.getPlayer(i).getStatus().getMagika();
			s = gameData.getPlayer(i).getStatus().getStamina();
			
			player[i] = new JButton(new ImageIcon("picture/p0" + i+".jpg"));
			player[i].setSize(150,150);
			player[i].setLocation(i*150, 255);
			player[i].setBorderPainted(true);
			player[i].addActionListener(this);
			player[i].setEnabled(true);
			
			playerStatus[i] = new JTextArea();
			playerStatus[i].setSize(150, 75);
			playerStatus[i].setLocation(i*150, 405);
			playerStatus[i].setBorder(border);
			playerStatus[i].setEditable(false);
			playerStatus[i].setText(String.format("  Name: %s \n  Health: %d \n  Magika: %d \n  Stamina: %d",n,h,m,s));
		}
		
		for(int i=0;i<3;i++)
		{
			enemy[i] = new JButton();
			enemy[i].setSize(150,150);
			//enemy[i].setText("");
			enemy[i].setIcon(null);
			enemy[i].setLocation(i*150, 15);
			enemy[i].setBorderPainted(true);
			enemy[i].addActionListener(this);
			enemy[i].setEnabled(false);
			
			enemyStatus[i] = new JTextArea();
			enemyStatus[i].setText("");
			enemyStatus[i].setSize(150, 75);
			enemyStatus[i].setLocation(i*150, 165);
			enemyStatus[i].setBorder(border);
			enemyStatus[i].setEditable(false);
		}
		updateEnemy();
		
		//initialize command display
		commandLabel[0] = new JLabel("Character",SwingConstants.CENTER);
		commandLabel[1] = new JLabel("Action",SwingConstants.CENTER);
		commandLabel[2] = new JLabel("Target",SwingConstants.CENTER);
		commandLabel[3] = new JLabel("For combo",SwingConstants.RIGHT);
		
		for(int i=0; i<3;i++)
		{
			commandLabel[i].setSize(100, 30);
			commandLabel[i].setLocation(475, 250 + i*70);
			cmd1[i] = new JTextField("");
			cmd1[i].setSize(60, 30);
			cmd1[i].setLocation(455, 280 + i*70);
			cmd1[i].setBorder(border);
			cmd1[i].setEditable(false);
			cmd1[i].setBackground(Color.white);
			
			cmd2[i] = new JTextField(" None");
			cmd2[i].setSize(60, 30);
			cmd2[i].setLocation(530, 280 + i*70);
			cmd2[i].setBorder(border);
			cmd2[i].setEditable(false);
			cmd2[i].setBackground(Color.BLACK);
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
		
		fireball = new JButton(new ImageIcon("picture/fireball.jpg"));
		fireball.setSize(150,70);
		fireball.setLocation(300,270);
		fireball.addActionListener(this);
		
		sword = new JButton(new ImageIcon("picture/sword.jpg"));
		sword.setSize(150,70);
		sword.setLocation(300,340);
		sword.addActionListener(this);
		
		arrow = new JButton(new ImageIcon("picture/arrow.jpg"));
		arrow.setSize(150,70);
		arrow.setLocation(300,410);
		arrow.addActionListener(this);
		
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
		
		gameLVText.setSize(150,30);
		gameLVText.setLocation(450,0);
		gameLVText.setBorder(border);
		gameLVText.setEditable(false);
		gameLVText.setText(String.format("  Level : %d",gameData.getLevel()));
		
		queue.setSize(600,220);
		queue.setLocation(0,480);
		queue.setBorder(border);
		queue.setText(gameData.getInvoker().toString());
		
		gameOverL.setSize(600,700);
		gameOverL.setLocation(0,0);
		gameOverL.setBorder(border);
		gameOverL.setVisible(false);
	}
	
	public void getPlayerName()
	{
		name1 = (JOptionPane.showInputDialog(this, "Type in character 1 name:"));
		name2 = (JOptionPane.showInputDialog(this, "Type in character 2 name:"));
		
		if(name1.equals(""))
			gameData.getPlayer(0).setName("No Name");
		else
			gameData.getPlayer(0).setName(name1);
		
		if(name2.equals(""))
			gameData.getPlayer(1).setName("No Name");
		else
			gameData.getPlayer(1).setName(name2);
	}
	
	public void restart()
	{
		gameOverL.setVisible(false);
		addItem();
		gameData = new Game();
		
		for(int i=0;i<2;i++)
		{
			player[i].setText("");
			playerStatus[i].setText("");
			enemy[i].setText("");
			enemy[i].setIcon(null);
			enemy[i].setEnabled(false);
			enemyStatus[i].setText("");
		}
		enemy[2].setText("");
		enemyStatus[2].setText("");
		enemy[2].setIcon(null);
		enemy[2].setEnabled(false);
		
		updatePlayer();
		updateEnemy();
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
		pane.add(arrow);
		pane.add(cmdL);
		pane.add(weaponL);
		pane.add(combo);
		pane.add(queue);
		pane.add(playerL);
		pane.add(enemyL);
		pane.add(gameLVText);
	}
	
	public void updateEnemy()
	{
		for(int i=0;i<gameData.getEnemyNumber();i++)
		{
			int h, m, s, tag;
			tag = gameData.getEnemyTag(i);
			String n = gameData.getEnemy(i).getName();
			h = gameData.getEnemy(i).getStatus().getHealth();
			m = gameData.getEnemy(i).getStatus().getMagika();
			s = gameData.getEnemy(i).getStatus().getStamina();

			//enemy[i].setText(n);
			enemy[i].setIcon(new ImageIcon("picture/c0" + tag+".jpg"));
			enemyStatus[i].setText(String.format("  Name: %s \n  Health: %d \n  Magika: %d \n  Stamina: %d",n,h,m,s));
			if (gameData.getEnemy(i).getStatus().getHealth() >0)
				enemy[i].setEnabled(true);
		}
	}
	
	public void updatePlayer()
	{

		for(int i=0;i<2;i++)
		{
			int h, m, s;
			String n = gameData.getPlayer(i).getName();
			
			h = gameData.getPlayer(i).getStatus().getHealth();
			m = gameData.getPlayer(i).getStatus().getMagika();
			s = gameData.getPlayer(i).getStatus().getStamina();
			//player[i].setText(n);
			playerStatus[i].setText(String.format("  Name: %s \n  Health: %d \n  Magika: %d \n  Stamina: %d",n,h,m,s));
		}
	}
	
	public void clearCMD()
	{
		for(int i=0;i<gameData.getEnemyNumber();i++)
		{
			if (gameData.getEnemy(i).getStatus().getHealth() >0)
				enemy[i].setEnabled(true);
		}
		
		for(int i=0;i<3;i++)
		{
			cmd1[i].setText("");
			cmd1[i].setBackground(Color.white);
			cmd2[i].setText(" None");
			cmd2[i].setBackground(Color.black);
		}
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == newgameI)
		{
			restart();
		}
		else if(e.getSource() == exitI)
		{
			System.exit(0);
		}	
		else if(e.getSource() == startGameB)
		{
			getPlayerName();
			bgL.setVisible(false);
			startGameB.setVisible(false);
			titleL.setVisible(false);
			initializeItem();
			addItem();
			
		}
		else if(e.getSource() == clear)
		{
			clearCMD();
		}	
		else if(e.getSource() == rename)
		{
			if(comboClicked == false)
			{
				nameInput = (JOptionPane.showInputDialog(this, "Type in the name:"));
				cmd1[1].setText("Rename");
				cmd1[2].setText("");
				cmd1[1].setBackground(Color.YELLOW);
				for(int i=0;i<3;i++)
				{
					enemy[i].setEnabled(false);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(pane, "Turn off combo attack first!","Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}	
		else if(e.getSource() == fireball)
		{
			if(comboClicked == true && !(cmd1[1].getText().equals("")))
			{
				
					cmd2[1].setText("Fireball");
					cmd2[1].setBackground(Color.YELLOW);
					updateEnemy();
				
			}
			else
			{
				cmd1[1].setText("Fireball");
				cmd1[1].setBackground(Color.YELLOW);
				updateEnemy();
			}
		}
		else if(e.getSource() == sword)
		{
			if(comboClicked == true && !(cmd1[1].getText().equals("")))
			{
				
					cmd2[1].setText("Sword");
					cmd2[1].setBackground(Color.YELLOW);
					updateEnemy();
				
			}
			else
			{
				cmd1[1].setText("Sword");
				cmd1[1].setBackground(Color.YELLOW);
				updateEnemy();
			}
		}
		else if(e.getSource() == arrow)
		{
			if(comboClicked == true && !(cmd1[1].getText().equals("")))
			{
				
					cmd2[1].setText("Arrow");
					cmd2[1].setBackground(Color.YELLOW);
					updateEnemy();
				
			}
			else
			{
				cmd1[1].setText("Arrow");
				cmd1[1].setBackground(Color.YELLOW);
				updateEnemy();
			}
		}
		else if(e.getSource() == combo)
		{
			clearCMD();
			if(comboClicked == false)
			{
				for(int i=0;i<3;i++)
				{
					cmd2[i].setText("");
					cmd2[i].setBackground(Color.white);
				}
				comboClicked = true;
			}
			else
			{
				for(int i=0;i<3;i++)
				{
					cmd2[i].setText(" None");
					cmd2[i].setBackground(Color.black);
				}
				comboClicked = false;
			}
		}
		else if(e.getSource() == add)
		{
			if(comboClicked == false)
			{
				if(cmd1[1].getText().equals("Rename"))
				{
					if(cmd1[0].getText().equals("")||cmd1[1].getText().equals(""))
					{
						JOptionPane.showMessageDialog(pane, "Command input is not correct!","warning",
								JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						Character c = gameData.getCharacter(cmd1[0].getText());
						Command cmd = new RenameCommand(nameInput,c);
						gameData.addCommand(cmd);
						clearCMD();
					}
				}
				else
				{
					if(cmd1[0].getText().equals("")||cmd1[1].getText().equals("")||cmd1[2].getText().equals(""))
					{
						JOptionPane.showMessageDialog(pane, "Command input is not correct!","warning",
								JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						Character cTmp = gameData.getCharacter(cmd1[0].getText());
						Character eTmp = gameData.getCharacter(cmd1[2].getText());
						Command command1 = new WeaponCommand(WeaponFactory.buildWeapon(cmd1[1].getText()),cTmp,eTmp);
						gameData.addCommand(command1);
						clearCMD();
					}
				}
			}
			else
			{
				if(cmd1[0].getText().equals("")||cmd1[1].getText().equals("")||cmd1[2].getText().equals("")
					||cmd2[0].getText().equals("")||cmd2[1].getText().equals("")||cmd2[2].getText().equals(""))
				{
					JOptionPane.showMessageDialog(pane, "Command input is not correct!","warning",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					Character cTmp1 = gameData.getCharacter(cmd1[0].getText());
					Character eTmp1 = gameData.getCharacter(cmd1[2].getText());
					Character cTmp2 = gameData.getCharacter(cmd2[0].getText());
					Character eTmp2 = gameData.getCharacter(cmd2[2].getText());
					Command command1 = new WeaponCommand(WeaponFactory.buildWeapon(cmd1[1].getText()),cTmp1,eTmp1);
					Command command2 = new WeaponCommand(WeaponFactory.buildWeapon(cmd2[1].getText()),cTmp2,eTmp2);
					Combo comboCmd = new Combo(command1, command2);
					gameData.addCommand(comboCmd);
					clearCMD();
					comboClicked = false;
				}
			}
		}
		else if(e.getSource() == doCommand)
		{
			if(gameData.getInvoker().checkCmdList())
			{
				JOptionPane.showMessageDialog(pane,
					    "No command in the list","Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				gameData.doCommand();
				gameData.enemyAttack();
				updatePlayer();
				updateEnemy();
				checkStatus();
			}
		}
		else if(e.getSource() == undoCommand)
		{
			if(gameData.getInvoker().checkUnDoList())
			{
				JOptionPane.showMessageDialog(pane,
					    "No command in the undo list","Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				gameData.undoCommand();
				gameData.enemyAttackUndo();
				updatePlayer();
				updateEnemy();
			}
		}
		else
		{
			for(int i=0; i<2; i++)
			{
				if(e.getSource() == player[i])
				{
					if(comboClicked == true && !(cmd1[0].getText().equals("")))
					{
						
						cmd2[0].setText(gameData.getPlayer(i).getName());
						cmd2[0].setBackground(Color.YELLOW);
						break;
						
					}
					else
					{
						cmd1[0].setText(gameData.getPlayer(i).getName());
						cmd1[0].setBackground(Color.YELLOW);
						break;
					}
				}
			}
			
			for(int i=0; i<gameData.getEnemyNumber();i++)
			{
				if(e.getSource() == enemy[i])
				{
					if(comboClicked == true && !(cmd1[2].getText().equals("")))
					{
						
						cmd2[2].setText(gameData.getEnemy(i).getName());
						cmd2[2].setBackground(Color.YELLOW);
						break;
						
					}
					else
					{
						cmd1[2].setText(gameData.getEnemy(i).getName());
						cmd1[2].setBackground(Color.YELLOW);
						break;
					}
				}
			}
		}
		
		queue.setText(gameData.getInvoker().toString());
	}
	
	public void checkStatus()
	{
		gameData.checkStatus();
		for(int i=0; i<2;i++)
		{
			if(gameData.getPlayerDead()[i])
				player[i].setEnabled(false);
		}
		
		for(int i=0; i< gameData.getEnemyNumber();i++)
		{
			if(gameData.getEnemyDead()[i])
				enemy[i].setEnabled(false);
		}
		
		if(gameData.isGameOver())
		{
			gameOver();
		}
		
		if(gameData.isLevelUp())
		{
			levelUp();
		}
	}
	
	public void gameOver()
	{
		JOptionPane.showMessageDialog(pane, "You are dead!","Warning",
				JOptionPane.WARNING_MESSAGE);
		pane.removeAll();
		gameOverL.setSize(600,700);
		gameOverL.setLocation(0,0);
		gameOverL.setBorder(border);
		gameOverL.setVisible(true);
		pane.add(gameOverL);
		pane.repaint();
	}
	
	public void levelUp()
	{
		JOptionPane.showMessageDialog(pane, "Level Up!","Warning",
				JOptionPane.WARNING_MESSAGE);
		gameData.setLevel(gameData.getLevel() + 1);
		gameLVText.setText(String.format("  Level : %d",gameData.getLevel()));
		gameData.levelUP();
		updateEnemy();
		updatePlayer();
	}
}
