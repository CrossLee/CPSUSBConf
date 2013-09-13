package com.withiter.frame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

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

//	public void showAllRoomsData() {
//		dataType = "AllRooms";
//		Object[] head = { "房号", "房间类型", "房间楼层", "房间价格", "加钟费用" };
//		showData(RoomDao.instance().getRoomsData(), head);
//		JMenuItem add = new JMenuItem("    添加房间    ");
//		JMenuItem addRooms = new JMenuItem("    批量添加    ");
//		JMenuItem delete = new JMenuItem("    删除房间    ");
//		JPopupMenu menu = new JPopupMenu();
//		menu.add(add);
//		menu.add(addRooms);
//		menu.add(delete);
//		TableListener tableListener = new TableListener(menu);
//		table.addMouseListener(tableListener);
//		table.addMouseMotionListener(tableListener);
//		add.addActionListener(tableListener);
//		addRooms.addActionListener(tableListener);
//		delete.addActionListener(tableListener);
//	}
//
//	public void showExpiredRoomsData() {
//		dataType = "ExpiredRooms";
//		Object[] head = { "房号", "房间类型", "房间消费", "开始时间", "结束时间" };
//		showData(RoomDao.instance().getExpiredRoomsData(), head);
//		JPopupMenu menu = new JPopupMenu();
//		JMenuItem checkout = new JMenuItem("    房间结算    ");
//		JMenuItem addHours = new JMenuItem("    房间加钟    ");
//		menu.add(checkout);
//		menu.add(addHours);
//		TableListener tableListener = new TableListener(menu);
//		table.addMouseListener(tableListener);
//		table.addMouseMotionListener(tableListener);
//		checkout.addActionListener(tableListener);
//		addHours.addActionListener(tableListener);
//	}
//
//	public void showRoomTypesData() {
//		dataType = "RoomTypes";
//		Object[] head = { "ID", "类型名称", "房间价格", "加钟费用" };
//		showData(RoomTypeDao.instance().getRoomTypesData(), head);
//		JMenuItem add = new JMenuItem("    添加类型    ");
//		JMenuItem edit = new JMenuItem("    编辑类型    ");
//		JMenuItem delete = new JMenuItem("    删除类型    ");
//		JPopupMenu menu = new JPopupMenu();
//		menu.add(add);
//		menu.add(edit);
//		menu.add(delete);
//		TableListener tableListener = new TableListener(menu);
//		table.addMouseListener(tableListener);
//		table.addMouseMotionListener(tableListener);
//		add.addActionListener(tableListener);
//		edit.addActionListener(tableListener);
//		delete.addActionListener(tableListener);
//	}

//	public void showRoomsBookedData() {
//		dataType = "RoomsBooked";
//		Object[] head = { "房号", "房间类型", "电话号码", "预订时长", "预订时间" };
//		showData(BookingDao.instance().getBookingsData(), head);
//		JMenuItem take = new JMenuItem("    开通房间    ");
//		JMenuItem delete = new JMenuItem("    删除订单    ");
//		JPopupMenu menu = new JPopupMenu();
//		menu.add(take);
//		menu.add(delete);
//		TableListener tableListener = new TableListener(menu);
//		table.addMouseListener(tableListener);
//		table.addMouseMotionListener(tableListener);
//		take.addActionListener(tableListener);
//		delete.addActionListener(tableListener);
//	}
	
	public void showVideos(){
		dataType = "video";
		Object[] head = { "视频名称", "后缀类型", "文件路径", "文件大小（M）", "添加时间" };
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
	public void showNews(){
		dataType = "news";
		Object[] head = {"新闻内容"};
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
	public void showTemperature(){
		dataType = "temperature";
		Object[] head = { "温度信息"};
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
	public void showLog(){
		dataType = "log";
		Object[] head = { "IP地址", "MAC地址", "日期", "操作", "文件名称", "成功/失败"};
		LogDao ld = LogDao.instance();
		showData(ld.getLogsData(), head);
//		JMenuItem add = new JMenuItem("编辑温度信息");
//		JPopupMenu menu = new JPopupMenu();
//		menu.add(add);
//		TableListener tableListener = new TableListener(menu);
//		table.addMouseListener(tableListener);
//		table.addMouseMotionListener(tableListener);
//		add.addActionListener(tableListener);
	}

//	public void showRoomTakenData() {
//		dataType = "RoomsBooked";
//		Object[] head = { "房号", "房间类型", "电话号码", "预订时长", "预订时间" };
//		showData(RoomDao.instance().getRoomsTakenData(), head);
//		JPopupMenu menu = new JPopupMenu();
//		JMenuItem buyFood = new JMenuItem("    添加酒水    ");
//		JMenuItem checkout = new JMenuItem("    房间结算    ");
//		JMenuItem addHours = new JMenuItem("    房间加钟    ");
//		menu.add(buyFood);
//		menu.add(checkout);
//		menu.add(addHours);
//		TableListener tableListener = new TableListener(menu);
//		table.addMouseListener(tableListener);
//		table.addMouseMotionListener(tableListener);
//		buyFood.addActionListener(tableListener);
//		checkout.addActionListener(tableListener);
//		addHours.addActionListener(tableListener);
//	}
//
//	public void showFoodsData() {
//		dataType = "FoodsData";
//		Object[] head = { "ID", "食品名称", "食品种类", "食品单价", "剩余数量" };
//		showData(FoodDao.instance().getFoodsData(), head);
//		JPopupMenu menu = new JPopupMenu();
//		JMenuItem checkout = new JMenuItem("    添加食品    ");
//		JMenuItem addHours = new JMenuItem("    删除食品    ");
//		menu.add(checkout);
//		menu.add(addHours);
//		TableListener tableListener = new TableListener(menu);
//		table.addMouseListener(tableListener);
//		table.addMouseMotionListener(tableListener);
//		checkout.addActionListener(tableListener);
//		addHours.addActionListener(tableListener);
//	}

	public void showData(Object[][] data, Object[] head) {
		table.removeAll();
		table = new DataTable(data, head);
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
