package com.withiter.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.withiter.dao.LogDao;
import com.withiter.dao.NewsDao;
import com.withiter.entity.Log;
import com.withiter.entity.News;
import com.withiter.utils.InfoUtils;

public class MainFrame extends JFrame implements Runnable {
	/**
	 * MainFrame
	 */
	private static final long serialVersionUID = -5988513125942516733L;
	private static MainFrame mainFrame;
	public static String CURRENT_PAGE = "";

	public static MainFrame instance() {
		if (mainFrame == null)
			mainFrame = new MainFrame();
		return mainFrame;
	}

	public MainFrame() {
		setTitle("电子站牌u盘系统");
		mainFrame = this;
		setUndecorated(true);
		setAlwaysOnTop(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		// int x = (int) screenSize.getWidth();
		// int y = (int) screenSize.getHeight();
		// this.setBounds((x - 1140) / 2, (y - 700) / 2, 1140, 700);
		setSize(screenSize);
		setMinimumSize(new Dimension(1024, 768));

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		// top option panel
		container.add(new OptionPanel(), BorderLayout.NORTH);
		// left menu panel
		container.add(new MenuPanel(), BorderLayout.WEST);
		// right bottom panel
		// container.add(new MainPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(this.getClass().getResource(
				"/images/icon.png")).getImage());

		((JPanel) this.getContentPane()).setOpaque(false);
		BackgroundPanel background = new BackgroundPanel();
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
	}

	public void open() {
		setVisible(true);
		LoginFrame.instance().setVisible(false);
		LoginFrame.getLoginDialog().setVisible(false);

		// load datas from usb
		 loadDatas();
		 
		// right bottom panel
		Container container = getContentPane();
		container.add(new MainPanel(), BorderLayout.CENTER);
	}

	// load all information from usb
	private void loadDatas() {
		InfoUtils.loadVideoInfo();
		try {
			InfoUtils.loadNewsInfo();
			InfoUtils.loadTemperatureInfo();
			InfoUtils.loadLogInfo();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<News> newsList = NewsDao.instance().getnewsList();
		for (News news : newsList) {
			System.out.println(news.index);
			System.out.println(news.content);
		}
		List<Log> logList = LogDao.instance().getlogList();
		for (Log log : logList) {
			System.out.println(log.ip);
			System.out.println(log.mac);
			System.out.println(log.date);
			System.out.println(log.operation);
			System.out.println(log.filename);
			System.out.println(log.result);
		}
	}

	@Override
	public void run() {
		instance();
	}
}
