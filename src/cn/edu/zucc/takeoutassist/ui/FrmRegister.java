package cn.edu.zucc.takeoutassist.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrmRegister extends JFrame implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ע��");
	private Button btnCancel = new Button("ȡ��");
	
	private JLabel labelUser = new JLabel("�˺ţ�");
	private JLabel labelPwd = new JLabel("���룺");
	private JLabel labelName = new JLabel("������");
	private JLabel labelSex = new JLabel("�Ա�");
	private JLabel labelTel = new JLabel("�绰��");
	private JLabel labelEmail = new JLabel("���䣺");
	private JLabel labelCity = new JLabel("���ڳ��У�");
	private JLabel labelVip = new JLabel("�Ƿ�����Ա��");
	
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtSex = new JPasswordField(20);
	private JPasswordField edtName = new JPasswordField(20);
	private JPasswordField edtTel = new JPasswordField(20);
	private JPasswordField edtEmail = new JPasswordField(20);
	private JPasswordField edtCity = new JPasswordField(20);
	private JPasswordField edtVip = new JPasswordField(20);
	
	public FrmRegister(Frame f, String s, boolean b) {
//		super(f, s, b);
//		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		toolBar.add(this.btnOk);
//		toolBar.add(btnCancel);
//		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
//		workPane.add(labelUser);
//		workPane.add(edtUserId);
//		workPane.add(labelPwd);
//		workPane.add(edtPwd);
//		workPane.add(labelPwd2);
//		workPane.add(edtPwd2);
//		this.getContentPane().add(workPane, BorderLayout.CENTER);
//		this.setSize(300, 180);
//		this.btnCancel.addActionListener(this);
//		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
