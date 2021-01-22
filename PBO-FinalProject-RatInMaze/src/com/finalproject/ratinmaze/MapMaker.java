package com.finalproject.ratinmaze;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MapMaker extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int rows = 20;
    static int columns = 20;
    int panelSize = 25;
    static int map[][] = new int[columns][rows];
    ArrayList<String> mapList = new ArrayList<String>();
    int level = 0;
    boolean levelsExistAlready = false;
    
    //load logo
    ImageIcon logo = new ImageIcon("res/Logo.png");
    
    public MapMaker(){
    	getMapList();
    	getLevelChoice();
    	if(level != -1){
	        loadMap();
	        this.setIconImage(logo.getImage());
	        this.setResizable(false);
	        this.setSize((columns*panelSize)+60, (rows*panelSize)+80);
	        this.getContentPane().setBackground(Color.BLACK);
	        this.setTitle("Maze Map Maker");
	        this.setLayout(null);
	        
	        this.addWindowListener(new WindowAdapter(){
	            public void windowClosing(WindowEvent e) {
	                saveMap();
	                new Menu();
	            }
	        });
	        
	        this.setLocationRelativeTo(null);
	        
	      //menampilkan map 
	        for(int y = 0; y < columns; y++){
	            for(int x = 0; x < rows; x++){
	            	MapMakerTile tile = new MapMakerTile(x, y);
	                tile.setSize(panelSize-1, panelSize-1);
	                tile.setLocation((x*panelSize)+23, (y*panelSize)+25);
	                if(map[x][y] == 0){
	                    tile.setBackground(Color.GRAY);
	                }else{
	                    tile.setBackground(Color.WHITE);
	                }
	                
	                //tile.setVisible(true);
	                //menambahkan tile di frame agar muncul
	                this.add(tile);
	            }
	        }
	        this.setVisible(true);
    	}
    	
    }
    
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
    
    public void getLevelChoice(){
    	if(levelsExistAlready){
	    	String maps[] = new String[99];
	    	mapList.toArray(maps);
	    	maps[mapList.size()] = "New level";
	    	String choice = (String)JOptionPane.showInputDialog(null, "Which level would you like to play?", "Maze Level Selector", JOptionPane.QUESTION_MESSAGE, null, maps, maps[0]);
	    	System.out.println(choice);
	    	if(choice != null && !choice.equals("New level")){
	    		level = Integer.parseInt((choice.replace("Level ", "").replace(".map", "")));
	    	}
	    	//Jika tidak jadi membuat map baru
	    	else if(choice == null){
	    		level = -1;
	    	}
	    	//new level
	    	else{
	    		level = mapList.size();
	    	}
    	}
    }
    
    public void saveMap(){
        try{
        //membuat file baru dengan memanggil class PrintWrite
        PrintWriter writer = new PrintWriter("Level "+level+".map", "UTF-8");
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
                writer.print(map[x][y]);
            }
            writer.print("\r\n");
        }
        writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(){
        try{
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("Level "+level+".map"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
                sb.append(line);
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
                    	//Mengembalikan Integer
                        map[x][y] = Integer.parseInt(mapChar);
                    }else{//If it is a line break
                        x--;
                        //System.out.print(mapChar);
                    }
                    //untuk menandakan baris ke
                    counter++;
                }
            }
        }
        //jika new level
        catch(Exception e){
            System.out.println("Unable to load existing map(if exists), creating new map.");
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    map[x][y] = 0;
                }
            }
        }
    }
}


