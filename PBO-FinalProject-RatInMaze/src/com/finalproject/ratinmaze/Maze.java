package com.finalproject.ratinmaze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Maze extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int rows = 20;
    public static final int columns = 20;
    public static final int panelSize = 25;
    public static int map[][] = new int[columns][rows];
    public static int endLevelLoc;
   
    private Player p;
    TimerMaze t;

    //load logo
    ImageIcon logo = new ImageIcon("res/Logo.png");
	    
    public Maze(String str){
    	
    	playSound sound = new playSound(); 
    	
    	loadMap(str);
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.setSize((columns*panelSize)+60, (rows*panelSize)+80);
        this.getContentPane().setBackground(Color.BLACK);
        this.setTitle("RAT IN MAZE");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				
				revalidate();
				repaint();
				
				//Player movement
				if(key == KeyEvent.VK_W){
					p.moveUp();
				}
				if(key == KeyEvent.VK_A){
					p.moveLeft();
				}
				if(key == KeyEvent.VK_S){
					p.moveDown();
				}
				if(key == KeyEvent.VK_D){
					p.moveRight();
				}
				
				//Jika sudah mencapai final
				if(p.x == columns-1 && p.y == endLevelLoc){
					t.timerStop();
					JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					sound.stopSound();
					new Menu();
				}
				
				if(t.minute == 0 && t.second == 0) {
					t.timerStop();
					JOptionPane.showMessageDialog(null, "Game Over, Time is Up!", "End Game", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					sound.stopSound();
					new Menu();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                //System.out.println((columns*panelSize)+50+"-"+((rows*panelSize)+70));
                System.exit(0);
            }
        });
        
        this.setLocationRelativeTo(null);
        
     // Audio
//    	a = new Audio();
//    	this.add(a);
        
        //Create player
    	p = new Player();
    	p.setVisible(true);
    	this.add(p);
    	
    	// Maze Timer
    	t = new TimerMaze();
    	this.add(t);
    	
    	
    	
    	//Color Map
    	 for(int y = 0; y < columns; y++){
             for(int x = 0; x < rows; x++){
                 Tile tile = new Tile(x, y);
                 tile.setSize(panelSize, panelSize);
                 //set lokasi gambar maze
                 tile.setLocation((x*panelSize)+23, (y*panelSize)+25);
                 if(map[x][y] == 0){
                     tile.setBackground(Color.BLACK);
                 }else{
                     tile.setBackground(new Color (255, 192, 0));
                     tile.setWall(false);
                     
                     //First Location Player
                     if(x == 0){
//                     	
                     	//lokasi awal player 
                     	p.setLocation((x*panelSize)+30, (y*panelSize)+40);
                     	
                     	//untuk mengeset koordinat y
                     	p.y = y;
                     }
                     //sudah paling kanan
                     if(x == columns-1){
                     	endLevelLoc = y;
                     }
                 }
                 
                 tile.setVisible(true);
                 this.add(tile);
             }
         }
        this.setVisible(true);
    }
    
    public void loadMap(String str){
        try{
        	//Membuat objek BufferedReader untuk mengambil input dari file
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(str));
            //Membuat objek StringBuilder
            StringBuilder sb = new StringBuilder();
            //Membaca isi file  & mengembalikan baris lengkap
            String line = br.readLine();
            
            //Menampilkan map
            while (line != null) {
                sb.append(line);
                //membuat enter (/r/n)
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String mapStr = sb.toString();
            
            int counter = 0;
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                	//Create hashmap
                    String mapChar = mapStr.substring(counter, counter+1);
                    if(!mapChar.equals("\r\n") && !mapChar.equals("\n")&& !mapChar.equals("\r")){
                    	//If it's a number
                        System.out.print(mapChar);
                    	//Mengembalikan ke integer
                        map[x][y] = Integer.parseInt(mapChar);
                    }
                    else{
                    	//If it is a line break
                        x--;
                        System.out.print(mapChar);
                    }
                    counter++;
                }
            }
        }catch(Exception e){
            System.out.println("Unable to load existing map(if exists), creating new map.");
        }
    }
    
    //Draw Player
	public void paint(Graphics g) {
		super.paint(g);
		p.paint(g);
    }
    
}


