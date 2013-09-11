package com.withiter.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.withiter.dao.VideoDao;
import com.withiter.entity.USBConfig;
import com.withiter.entity.Video;

public class MainFrame extends JFrame implements Runnable {
	/**
	 * MainFrame
	 */
	private static final long serialVersionUID = -5988513125942516733L;
	private static MainFrame mainFrame;

	public static MainFrame instance() {
		if (mainFrame == null)
			mainFrame = new MainFrame();
		return mainFrame;
	}

	public MainFrame() {
		setTitle("电子站牌u盘导入软件");
		mainFrame = this;
		setUndecorated(true);
//		loadDatas();
		// setAlwaysOnTop(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
//		int x = (int) screenSize.getWidth();
//		int y = (int) screenSize.getHeight();
//		this.setBounds((x - 1140) / 2, (y - 700) / 2, 1140, 700);
		setSize(screenSize);
		setMinimumSize(new Dimension(1024, 768));

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// top option panel
		container.add(new OptionPanel(), BorderLayout.NORTH);
		// left menu panel
		container.add(new MenuPanel(), BorderLayout.WEST);
		// right bottom panel
//		container.add(new MainPanel(), BorderLayout.CENTER);
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
		loadDatas();
		// right bottom panel
		Container container = getContentPane();
		container.add(new MainPanel(), BorderLayout.CENTER);
	}

	// load all information from usb
	private void loadDatas(){
		System.out.println("start to load datas from usb");
		String rootPath = USBConfig.drivePath;
		if(rootPath == null){
			JOptionPane.showMessageDialog(null, "未检测到U盘，请在登陆前插入U盘。点击确定退出系统", "提示",
					JOptionPane.OK_OPTION);
			System.exit(0);
		}
		String videonewFolder = rootPath + USBConfig.VIDEO_NEW_FOLDER + "\\";
		String initnewFolder = rootPath + USBConfig.INIT_NEW_FOLDER + "\\";
		String videoiniFile = initnewFolder + "video.ini";
		String newsiniFile = initnewFolder + "news.ini";
		String temperatureiniFile = initnewFolder + "temperature.ini";
		String logfile = rootPath + "logfile.txt";
		
		System.out.println(videonewFolder);
		System.out.println(initnewFolder);
		System.out.println(videoiniFile);
		System.out.println(newsiniFile);
		System.out.println(temperatureiniFile);
		System.out.println(logfile);
		
		File videoFilesPath = new File(videonewFolder);
		File[] videos = videoFilesPath.listFiles();
		Video v = null;
		for(File f : videos){
			System.out.println(f.getName());
			String name = f.getName();
			String ext = name.substring(name.lastIndexOf("."), name.length());
			String path = f.getAbsolutePath();
			String size = String.valueOf((f.length()/1024/1024));
			String updateDate = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date(f.lastModified()));
			v = new Video(name, ext, path, size, updateDate);
			VideoDao.instance().addVideo(v);
		}
	}
	
	@Override
	public void run() {
		instance();
	}
}
