package com.withiter.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.withiter.listener.OptionListener;

public class OptionPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1613590806361203632L;
	private static OptionPanel optionPanel;
	
	public ImageButton jbtnAdd = new ImageButton("add");
	public ImageButton jbtnDel = new ImageButton("delete");
	public ImageButton jbtnEdit = new ImageButton("edit");
	public ImageButton jbtRefresh = new ImageButton("refresh");
	public ImageButton jbtExit = new ImageButton("exit");
	public ImageButton jbtLock = new ImageButton("lock");
	
	static public OptionPanel instance(){
		if(optionPanel == null)
			optionPanel = new OptionPanel();
		return optionPanel;
	}
	
	public void showAllBtns(){
		jbtnAdd.setVisible(true);
		jbtnDel.setVisible(true);
		jbtnEdit.setVisible(true);
		jbtRefresh.setVisible(true);
		jbtExit.setVisible(true);
	}

	public OptionPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 10));
		setSize(1366, 150);
		setOpaque(false);
		optionPanel = this;
		
		jbtLock.setVisible(false);
		
		jbtnAdd.setToolTipText("添加");
		jbtnDel.setToolTipText("删除");
		jbtnEdit.setToolTipText("编辑");
		jbtRefresh.setToolTipText("刷新");
		jbtExit.setToolTipText("退出");
		jbtLock.setToolTipText("注销");

		jbtnAdd.setPreferredSize(new Dimension(80, 80));
		jbtnDel.setPreferredSize(new Dimension(80, 80));
		jbtnEdit.setPreferredSize(new Dimension(80, 80));
		jbtRefresh.setPreferredSize(new Dimension(80, 80));
		jbtExit.setPreferredSize(new Dimension(80, 80));
		jbtLock.setPreferredSize(new Dimension(80, 80));

		OptionListener optionListener = new OptionListener(jbtnAdd,
				jbtnDel, jbtnEdit, jbtRefresh, jbtExit, jbtLock);
		jbtnAdd.addActionListener(optionListener);
		jbtnDel.addActionListener(optionListener);
		jbtnEdit.addActionListener(optionListener);
		jbtRefresh.addActionListener(optionListener);
		jbtExit.addActionListener(optionListener);
		jbtLock.addActionListener(optionListener);

		add(jbtnAdd);
		add(jbtnDel);
		add(jbtnEdit);
		add(jbtRefresh);
		add(jbtExit);
		add(jbtLock);
	}
}
