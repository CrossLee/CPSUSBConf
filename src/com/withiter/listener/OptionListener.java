package com.withiter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.withiter.frame.BookRoomDialog;
import com.withiter.frame.ConfigDialog;
import com.withiter.frame.ImageButton;
import com.withiter.frame.LoginFrame;
import com.withiter.frame.MainFrame;
import com.withiter.frame.MainPanel;
import com.withiter.frame.TakeRoomDialog;

public class OptionListener implements ActionListener{
	JButton jbtnAdd;
	JButton jbtnDel;
	JButton jbtnEdit;
	JButton jbtRefresh;
	JButton jbtExit;
	JButton jbtLock;
	
	public OptionListener(JButton jbtnAdd, JButton jbtnDel,
			JButton jbtnEdit, JButton jbtRefresh, JButton jbtExit,
			JButton jbtLock) {
		super();
		this.jbtnAdd = jbtnAdd;
		this.jbtnDel = jbtnDel;
		this.jbtnEdit = jbtnEdit;
		this.jbtRefresh = jbtRefresh;
		this.jbtExit = jbtExit;
		this.jbtLock = jbtLock;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// check the current page
		if(MainFrame.CURRENT_PAGE.equals("video")){
			if(e.getSource() == jbtnAdd){
				BookRoomDialog.instance().open();
			}else if(e.getSource() == jbtnDel){
				TakeRoomDialog.instance().open();
			}else if(e.getSource() == jbtRefresh){
				MainPanel.instance().refresh();
			}else if(e.getSource() == jbtExit){
				System.exit(0);
			}
		}
		if(MainFrame.CURRENT_PAGE.equals("news")){
			if(e.getSource() == jbtnAdd){
				BookRoomDialog.instance().open();
			}else if(e.getSource() == jbtnDel){
				TakeRoomDialog.instance().open();
			}else if(e.getSource() == jbtRefresh){
				MainPanel.instance().refresh();
			}else if(e.getSource() == jbtExit){
				System.exit(0);
			}
		}
		if(MainFrame.CURRENT_PAGE.equals("temperature")){
			if(e.getSource() == jbtnEdit){
//				ConfigDialog.instance().open();
			}else if(e.getSource() == jbtRefresh){
				MainPanel.instance().refresh();
			}else if(e.getSource() == jbtExit){
				System.exit(0);
			}
		}
		if(MainFrame.CURRENT_PAGE.equals("log")){
			if(e.getSource() == jbtRefresh){
				MainPanel.instance().refresh();
			}else if(e.getSource() == jbtExit){
				System.exit(0);
			}
		}
	}
}
