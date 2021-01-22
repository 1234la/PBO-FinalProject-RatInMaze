package com.finalproject.ratinmaze;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Menu{

	ArrayList<String> mapList = new ArrayList<String>();
	JComboBox<String> lvlList;

	public static final int menuWidth = 150; 	// Width of each button/item on display
	public static final int menuHeight = 50;	// Height of each button/item on display
	public static final int menuY = 110; 		// Button/item location on display
	public static final int menuX = 500;
	public static final int WIDTH = 700;
	public static final int HEIGHT = 550;
	
	JFrame Menu = new JFrame("Rat In Maze.");
	JButton Play = new JButton("Play");
	JButton Exit = new JButton("Exit");
	JButton Help = new JButton ("Help");
	JButton MapMaker = new JButton("Map Maker");
	ImageIcon picture = new ImageIcon("res/Maze.png");
	JLabel imageLabel = new JLabel(picture);
	JLabel Title = new JLabel("RAT IN MAZE");
	
	
	public Menu() {
			
			playSound sound = new playSound();
//			ImageIcon logo = new ImageIcon("res/Logo.png");
			
	    	//Load map list
	    	getMapList();
	    	lvlList = new JComboBox<String>(mapList.toArray(new String[mapList.size()]));

	        //Menu Variables
//	    	Menu.setIconImage(logo.getImage());
	        Menu.setResizable(false);
	        Menu.setSize(WIDTH, HEIGHT);
	        
	        Menu.getContentPane().setBackground(Color.BLACK);
	        Menu.setLayout(null);
	        Menu.setLocationRelativeTo(null);
	        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        Menu.setUndecorated(true);
	        
	        //Title
	    	Font font = new Font("Poppins", Font.BOLD, 70);
			Title.setFont(font);
			Title.setForeground(new Color (255, 192, 0));
			Title.setBounds((WIDTH-400)/2, -150, 500, 400);
			Menu.add(Title);
			Menu.setVisible(true);
			
	        //Play Button Variables
	        Play.setSize(menuWidth,menuHeight);
	        Play.setLocation(menuX, menuY);
	        Play.setBackground(new Color (112, 173, 70));
	        Play.setFont(new Font("Poppins", Font.BOLD, 20));
	        Play.setForeground(Color.BLACK);
	        Menu.add(Play);
	        
	        Play.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                Play.setBackground(new Color(250, 196, 31));
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                Play.setBackground(new Color (112, 173, 70));
	            }
	        });
	        
	        Play.addActionListener(new ActionListener(){
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new Maze(lvlList.getSelectedItem().toString());
					Menu.setVisible(false);
					sound.stopSound();
				}
	        	
	        });	
	        
	        //Map Maker Button Variables
	        MapMaker.setSize(menuWidth,menuHeight);
	        MapMaker.setLocation(menuX, menuY + 85);
	        MapMaker.setBackground(new Color (112, 173, 70));
	        MapMaker.setFont(new Font("Poppins", Font.BOLD, 19));
	        MapMaker.setForeground(Color.BLACK);
	        Menu.add(MapMaker);
	        
	        MapMaker.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                MapMaker.setBackground(new Color(250, 196, 31));
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                MapMaker.setBackground(new Color (112, 173, 70));
	            }
	        });
	        
	        MapMaker.addActionListener(new ActionListener(){
	
				@Override
				public void actionPerformed(ActionEvent e) {
					new MapMaker();
//					Menu.setVisible(false);
				}
	        	
	        });
	        
	        //Level Selector
	        lvlList.setSize(menuWidth, menuHeight);
	        lvlList.setLocation(menuX, menuY + 170);
	        lvlList.setFont(new Font("Poppins", Font.BOLD, 15));
	        lvlList.setBackground(new Color (112, 173, 70));
	        lvlList.setForeground(Color.BLACK);
	        Menu.add(lvlList);
	        
	        lvlList.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                lvlList.setBackground(new Color(250, 196, 31));
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                lvlList.setBackground(new Color (112, 173, 70));
	            }
	        });
	        
	        //Help Button Variables
	        Help.setSize(menuWidth,menuHeight);
	        Help.setLocation(menuX ,menuY + 255);
	        Help.setBackground(new Color (112, 173, 70));
	        Help.setFont(new Font("Poppins", Font.BOLD, 20));
	        Help.setForeground(Color.BLACK);
	        Menu.add(Help);
	        
	        Help.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                Help.setBackground(new Color(250, 196, 31));
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                Help.setBackground(new Color (112, 173, 70));
	            }
	        });
	        
	        Help.addActionListener(new ActionListener(){
	
				@Override
				public void actionPerformed(ActionEvent e) {
					//Pressed Help Button
					JOptionPane.showMessageDialog(null, "Help rat to get out of the maze! Press 'W', 'A', 'S', 'D' to run.", "Help", JOptionPane.INFORMATION_MESSAGE);
				
				}
	        });
	        
	        //Exit Button Variables
	        Exit.setSize(menuWidth,menuHeight);
	        Exit.setLocation(menuX ,menuY + 340);
	        Exit.setBackground(new Color (112, 173, 70));
	        Exit.setFont(new Font("Poppins", Font.BOLD, 20));
	        Exit.setForeground(Color.BLACK);
	        Menu.add(Exit);
	        
	        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                Exit.setBackground(new Color(236, 82, 75));
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                Exit.setBackground(new Color (112, 173, 70));
	            }
	        });
	        
	        Exit.addActionListener(new ActionListener(){
	
				@Override
				public void actionPerformed(ActionEvent e) {
		            System.exit(0);
				}
	        });
	        
	        //Display Picture
	        imageLabel.setBounds((WIDTH-600)/2, 105, 412, 412);
	        
	        imageLabel.setVisible(true);
	        Menu.add(imageLabel);
	        Menu.setVisible(true);
	    }
		
	
	    static boolean levelsExistAlready = false;
	
	    public void getMapList(){
	    	for(int i = 0; i < 99; i++){
	    		File map = new File("./Level "+i+".map");
	    		if(map.exists()){
	    			System.out.println("Level "+i+" exists");
	    			mapList.add("Level "+i+".map");
	    			levelsExistAlready = true;
	    		}
	    	}
	    }
}

