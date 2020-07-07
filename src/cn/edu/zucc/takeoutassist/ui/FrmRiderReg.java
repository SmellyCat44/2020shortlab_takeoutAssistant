package cn.edu.zucc.takeoutassist.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.takeoutassist.control.RiderManager;
import cn.edu.zucc.takeoutassist.control.ShopManager;
import cn.edu.zucc.takeoutassist.model.BeanRider;
import cn.edu.zucc.takeoutassist.model.BeanShop;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;

public class FrmRiderReg extends JDialog implements ActionListener {
	private BeanRider rider=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelRiderid = new JLabel("账号：");
	private JLabel labelRiderpwd = new JLabel("密码：");
	private JLabel labelRidername = new JLabel("姓名：");
	
	private JTextField edtRiderid = new JTextField(20);
	private JTextField edtRiderpwd = new JTextField(20);
	private JTextField edtRidername = new JTextField(20);
	
	public FrmRiderReg(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelRiderid);
		workPane.add(edtRiderid);
		workPane.add(labelRiderpwd);
		workPane.add(edtRiderpwd);
		workPane.add(labelRidername);
		workPane.add(edtRidername);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			String rid=this.edtRiderid.getText();
			String rname=this.edtRiderpwd.getText();
			String rpwd=this.edtRidername.getText();
			rider=new BeanRider();
			rider.setR_id(rid);
			rider.setR_name(rname);
			rider.setR_pwd(rpwd);;
			try {
				(new RiderManager()).createRider(rider);
				this.setVisible(false);
			} catch (BaseException | BusinessException e1) {
				this.rider=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public BeanRider getRider() {
		return rider;
	}
}
