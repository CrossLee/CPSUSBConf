package com.withiter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.withiter.frame.BookRoomDialog;
import com.withiter.frame.ConfigDialog;
import com.withiter.frame.LoginFrame;
import com.withiter.frame.MainFrame;
import com.withiter.frame.MainPanel;
import com.withiter.frame.TakeRoomDialog;

public class OptionListener implements ActionListener{
	JButton jbtBookRoom;
	JButton jbtTakeRoom;
	JButton jbtRefresh;
	JButton jbtConfig;
	JButton jbtLock;
	JButton jbtExit;

	public OptionListener(JButton jbtBookRoom, JButton jbtTakeRoom,
			JButton jbtRefresh, JButton jbtConfig, JButton jbtLock,
			JButton jbtExit) {
		super();
		this.jbtBookRoom = jbtBookRoom;
		this.jbtTakeRoom = jbtTakeRoom;
		this.jbtRefresh = jbtRefresh;
		this.jbtConfig = jbtConfig;
		this.jbtLock = jbtLock;
		this.jbtExit = jbtExit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbtBookRoom){
			BookRoomDialog.instance().open();
		}else if(e.getSource() == jbtTakeRoom){
			TakeRoomDialog.instance().open();
		}else if(e.getSource() == jbtRefresh){
			MainPanel.instance().refresh();
		}else if(e.getSource() == jbtConfig){
			ConfigDialog.instance().open();
		}else if(e.getSource() == jbtLock){
			LoginFrame.instance().open();
			MainFrame.instance().dispose();
		}else if(e.getSource() == jbtExit){
			System.exit(0);
		}
	}
}
