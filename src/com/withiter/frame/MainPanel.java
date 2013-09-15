package com.withiter.frame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

import com.withiter.dao.LogDao;
import com.withiter.dao.NewsDao;
import com.withiter.dao.TemperatureDao;
import com.withiter.dao.VideoDao;
import com.withiter.listener.TableListener;

public class MainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4090036947302273308L;
	private static MainPanel mainPanel;
	private DataTable table;
	private JScrollPane jscrolPane;
	private String dataType;

	public static MainPanel instance() {
		if (mainPanel == null)
			mainPanel = new MainPanel();
		return mainPanel;
	}

	public MainPanel() {
		mainPanel = this;
		setOpaque(false);
		setLayout(new BorderLayout());
		table = new DataTable(null, null);
		jscrolPane = new JScrollPane();
		jscrolPane.setOpaque(false);
		jscrolPane.getViewport().setOpaque(false);
		jscrolPane.setAutoscrolls(true);
		add(jscrolPane);
		showVideos();
	}

	@Override
	public void paint(Graphics g) {
		ImageIcon icon = new ImageIcon(this.getClass().getResource(
				"/images/scrollpane.png"));
		Image img = icon.getImage();
		g.drawImage(img, jscrolPane.getX(), jscrolPane.getY(),
				jscrolPane.getWidth(), jscrolPane.getHeight(), this);
		super.paint(g);
	}

	public DataTable getTable() {
		return table;
	}

	public void showVideos() {
		dataType = "video";
		Object[] head = {"选择", "视频名称", "后缀类型", "文件路径", "文件大小（M）", "添加时间" };
		VideoDao vd = VideoDao.instance();
		showData(vd.getVideosData(), head);
		JMenuItem add = new JMenuItem("添加视频");
		JMenuItem delete = new JMenuItem("删除视频");
		JPopupMenu menu = new JPopupMenu();
		menu.add(add);
		menu.add(delete);
		TableListener tableListener = new TableListener(menu);
		table.addMouseListener(tableListener);
		table.addMouseMotionListener(tableListener);
		add.addActionListener(tableListener);
		delete.addActionListener(tableListener);
	}

	public void showNews() {
		dataType = "news";
		Object[] head = {"选择", "新闻内容" };
		NewsDao nd = NewsDao.instance();
		showData(nd.getNewssData(), head);
		JMenuItem add = new JMenuItem("添加新闻");
		JMenuItem delete = new JMenuItem("删除新闻");
		JPopupMenu menu = new JPopupMenu();
		menu.add(add);
		menu.add(delete);
		TableListener tableListener = new TableListener(menu);
		table.addMouseListener(tableListener);
		table.addMouseMotionListener(tableListener);
		add.addActionListener(tableListener);
		delete.addActionListener(tableListener);
	}

	public void showTemperature() {
		dataType = "temperature";
		Object[] head = { "温度信息" };
		TemperatureDao td = TemperatureDao.instance();
		showData(td.getTemperaturesData(), head);
		JMenuItem add = new JMenuItem("编辑温度信息");
		JPopupMenu menu = new JPopupMenu();
		menu.add(add);
		TableListener tableListener = new TableListener(menu);
		table.addMouseListener(tableListener);
		table.addMouseMotionListener(tableListener);
		add.addActionListener(tableListener);
	}

	public void showLog() {
		dataType = "log";
		Object[] head = { "IP地址", "MAC地址", "日期", "操作", "文件名称", "成功/失败" };
		LogDao ld = LogDao.instance();
		showData(ld.getLogsData(), head);
	}

	public void showData(Object[][] data, Object[] head) {
		table.removeAll();
		table = new DataTable(data, head);
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(60);
		firsetColumn.setMaxWidth(100);
		firsetColumn.setMinWidth(60);
		jscrolPane.setViewportView(table);
	}

	public void refresh() {
		if (dataType.equals("video")) {
			showVideos();
		} else if (dataType.equals("news")) {
			showNews();
		} else if (dataType.equals("temperature")) {
			showTemperature();
		} else if (dataType.equals("log")) {
			showLog();
		}
	}
}
