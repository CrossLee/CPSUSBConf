package com.withiter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.withiter.frame.MainFrame;
import com.withiter.frame.MainPanel;
import com.withiter.frame.OptionPanel;

public class MenuListener implements ActionListener {

	private JButton btnVideo,btnWords, btnTemperature,
	 btnLog, btnWeather, btnBackup;
	

	public MenuListener(JButton btnVideo, JButton btnWords,
			JButton btnTemperature, JButton btnLog, JButton btnWeather, 
			JButton btnBackup) {
		this.btnVideo = btnVideo;
		this.btnWords = btnWords;
		this.btnTemperature = btnTemperature;
		this.btnLog = btnLog;
		this.btnWeather = btnWeather;
		this.btnBackup = btnBackup;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OptionPanel.instance().showAllBtns();
		if (e.getSource() == btnVideo) {
			MainPanel.instance().showVideos();
			MainFrame.CURRENT_PAGE = "video";
			OptionPanel.instance().jbtnEdit.setVisible(false);
		} else if (e.getSource() == btnWords) {
			MainPanel.instance().showNews();
			MainFrame.CURRENT_PAGE = "news";
			OptionPanel.instance().jbtnEdit.setVisible(false);
		} else if (e.getSource() == btnTemperature) {
			MainPanel.instance().showTemperature();
			MainFrame.CURRENT_PAGE = "temperature";
			OptionPanel.instance().jbtnAdd.setVisible(false);
			OptionPanel.instance().jbtnDel.setVisible(false);
		} else if (e.getSource() == btnLog) {
			MainPanel.instance().showLog();
			MainFrame.CURRENT_PAGE = "log";
			OptionPanel.instance().jbtnAdd.setVisible(false);
			OptionPanel.instance().jbtnDel.setVisible(false);
			OptionPanel.instance().jbtnEdit.setVisible(false);
		} 
	}

}
