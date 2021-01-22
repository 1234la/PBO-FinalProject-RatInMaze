package com.finalproject.ratinmaze;

public class TestMaze {

	public static void main(String args[]){
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Menu();
			}
		});
    }
}
