package com.withiter.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.withiter.dao.NewsDao;
import com.withiter.entity.News;

public class NewsDialog extends JDialog {
	private static final long serialVersionUID = 1664501901587549323L;
	private static NewsDialog newsDialog;
	private JTextArea jTextArea;

	public static NewsDialog instance() {
		if (newsDialog == null)
			newsDialog = new NewsDialog();
		return newsDialog;
	}

	public NewsDialog() {
		super(MainFrame.instance(), "添加新闻", true);
		setLayout(null);
		setSize(420, 420);
		setLocationRelativeTo(null);
		newsDialog = this;
		JLabel jlbName = new JLabel("新闻内容(100字)：");
		JButton ensure = new JButton("确定");
		JButton cancel = new JButton("取消");

		jTextArea = new JTextArea();
		jlbName.setBounds(60, 30, 300, 30);
		jTextArea.setBounds(60, 60, 300, 200);
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
				
				News news = new News(content);
				NewsDao.instance().getnewsList().add(news);
			}
		});
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewsDialog.instance().dispose();
			}
		});

		add(jlbName);
		add(jTextArea);
		add(ensure);
		add(cancel);
	}

	public void open() {
		setVisible(true);
	}
}
