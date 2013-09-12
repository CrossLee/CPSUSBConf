package com.withiter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.withiter.frame.MainPanel;

public class MenuListener implements ActionListener {

	private JButton btnVideo,btnWords, 
	btnWeather, btnLog, btnTemperature, btnBackup;
	

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
		if (e.getSource() == btnVideo) {
			MainPanel.instance().showVideos();
		} else if (e.getSource() == btnWords) {
			MainPanel.instance().showNews();
		} else if (e.getSource() == btnTemperature) {
			MainPanel.instance().showTemperature();
		} else if (e.getSource() == btnLog) {
			MainPanel.instance().showAllRoomsData();
		} else if (e.getSource() == btnWeather) {
			MainPanel.instance().showRoomTypesData();
		} else if (e.getSource() == btnBackup) {
			MainPanel.instance().showFoodsData();
		}
	}

}
