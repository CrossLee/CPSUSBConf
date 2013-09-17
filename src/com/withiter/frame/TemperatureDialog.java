package com.withiter.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.withiter.dao.TemperatureDao;
import com.withiter.entity.Temperature;
import com.withiter.utils.BusyDialog;
import com.withiter.utils.LodingWorker;
import com.withiter.utils.TemperatureWorker;

public class TemperatureDialog extends JDialog {
	private static final long serialVersionUID = 1354178515868556087L;
	private static TemperatureDialog temperatureDialog;
	private JTextArea jTextArea;

	public static TemperatureDialog instance() {
		if (temperatureDialog == null)
			temperatureDialog = new TemperatureDialog();
		return temperatureDialog;
	}

	public TemperatureDialog() {
		super(MainFrame.instance(), "编辑温度", true);
		setLayout(null);
		setSize(420, 420);
		setLocationRelativeTo(null);
		temperatureDialog = this;
		JLabel jlbName = new JLabel("温度内容(100字)：");
		JButton ensure = new JButton("确定");
		JButton cancel = new JButton("取消");

		jTextArea = new JTextArea();
		jlbName.setBounds(60, 30, 300, 30);
		jTextArea.setBounds(60, 60, 300, 50);
		jTextArea.setAutoscrolls(true);
		jTextArea.setWrapStyleWord(true);
		jTextArea.setLineWrap(true);
		ensure.setBounds(115, 320, 70, 40);
		cancel.setBounds(210, 320, 70, 40);

		ensure.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String content = jTextArea.getText().trim();
				if(content.isEmpty()){
					JOptionPane.showMessageDialog(null, "请输入内容", "提示", JOptionPane.OK_OPTION);
					return;
				}
				if(content.length() > 100){
					JOptionPane.showMessageDialog(null, "内容不能超过100个字符", "提示", JOptionPane.OK_OPTION);
					return;
				}
				TemperatureDialog.instance().dispose();
				List<Temperature> tList = TemperatureDao.instance().gettemperatureList();
				if(tList == null || tList.size() < 1){
					System.err.println("TemperatureDao.instance().gettemperatureList() 为 null 或者 size 为 0");
				}
				Temperature t = tList.get(0);
				t.content = content;
				BusyDialog bd = new BusyDialog();
				ExecutorService executor = Executors
						.newCachedThreadPool();

				CountDownLatch latch = new CountDownLatch(1);
				TemperatureWorker w1 = new TemperatureWorker(latch, "TemperatureWorker");
				LodingWorker loading = new LodingWorker(latch, bd);

				executor.execute(w1);
				executor.execute(loading);
				executor.shutdown();
			}
		});
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TemperatureDialog.instance().dispose();
//				TemperatureDialog.instance().jTextArea.setText("");
			}
		});

		add(jlbName);
		add(jTextArea);
		add(ensure);
		add(cancel);
	}

	public void open(String tempStr) {
		jTextArea.setText(tempStr);
		setVisible(true);
	}
}
