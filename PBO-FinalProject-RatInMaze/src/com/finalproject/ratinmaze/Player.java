package com.finalproject.ratinmaze;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x, y;
	private Image player;
	
	//load image player
	ImageIcon p = new ImageIcon("res/Rat.png");
    
    public Player() {
    	this.setSize(Maze.panelSize, Maze.panelSize);
    	player = p.getImage();
    }
    
    public Image getPlayer() {
		return player;
	}
    
    public void paint(Graphics g) {
		g.drawImage(player,this.getX(),this.getY(), null);
    }
    
    public void moveLeft() {
    	if(x > 0 && Maze.map[x-1][y] == 1){
	    	this.setLocation(this.getX()-25, this.getY());
	    	x--;
    	}
    }

    public void moveRight() {
    	if(x < Maze.columns-1 && Maze.map[x+1][y] == 1){
	    	this.setLocation(this.getX()+25, this.getY());
	    	x++;
    	}
    }

    public void moveUp() {
    	if(y > 0 && Maze.map[x][y-1] == 1){
	    	this.setLocation(this.getX(), this.getY()-25);
	    	y--;
    	}
    }

    public void moveDown() {
    	if(y < Maze.rows-1 && Maze.map[x][y+1] == 1){
	    	this.setLocation(this.getX(), this.getY()+25);
	    	y++;
    	}
    }
}
