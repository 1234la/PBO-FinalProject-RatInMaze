package com.finalproject.ratinmaze;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerMaze extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel counterLabel;
	Font font = new Font("Poppins", Font.BOLD, 20);
	
	Timer timer;
	int second, minute;
	String doubleDigitSecond, doubleDigitMinute;
	
	DecimalFormat dFormat = new DecimalFormat("00"); // format of minute and second
	
	public TimerMaze() {
		
		counterLabel = new JLabel("");
		counterLabel.setFont(font);
		counterLabel.setForeground(Color.decode("#ffc000"));
		
		this.add(counterLabel);
		this.setSize(170, 30);
		this.setLocation(345, 5);
		this.setVisible(true);
		this.setOpaque(false);
				
		counterLabel.setText("Time Left " + "01:00");
		second = 0;
		minute = 1;
		countDownTimer();
		timerStart();
	}

	public void countDownTimer() {
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				
				doubleDigitSecond = dFormat.format(second);
				doubleDigitMinute = dFormat.format(minute);
				counterLabel.setText("Time Left " + doubleDigitMinute + ":" + doubleDigitSecond);
				
				if(second == -1) {
					second = 59;
					minute--;
					
					doubleDigitSecond = dFormat.format(second);
					doubleDigitMinute = dFormat.format(minute);
					counterLabel.setText("Time Left " + doubleDigitMinute + ":" + doubleDigitSecond);
				}
					
				if(minute == 0 && second == 0) {
					timerStop();
				}
				
			}
		});
	}
	
	public void timerStart() {
		timer.start();
	}
	
	public void timerStop() {
		timer.stop();
	}
}
