package com.withiter.main;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;

import com.withiter.dao.ConfigDao;
import com.withiter.frame.LoadingFrame;
import com.withiter.frame.LoginFrame;
import com.withiter.frame.MainFrame;
import com.withiter.jobs.USBListenerJob;

public class Main {

	public static void main(String[] args) {
		new Thread(new LoadingFrame()).start();
		new Thread(new USBListenerJob()).start();
		Font font = ConfigDao.instance().getConfig().getFont();
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put(key, font);
		}
		new Thread(new LoginFrame()).start();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
//		new Thread(new MainFrame()).start();
	}
}
