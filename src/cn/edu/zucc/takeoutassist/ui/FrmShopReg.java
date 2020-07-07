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

import cn.edu.zucc.takeoutassist.control.ShopManager;
import cn.edu.zucc.takeoutassist.model.BeanShop;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;

public class FrmShopReg extends JDialog implements ActionListener {
	private BeanShop shop=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelShopid = new JLabel("账号：");
	private JLabel labelShoppwd = new JLabel("密码：");
	private JLabel labelShopname = new JLabel("商家名：");
	
	private JTextField edtShopid = new JTextField(20);
	private JTextField edtShoppwd = new JTextField(20);
	private JTextField edtShopname = new JTextField(20);
	
	public FrmShopReg(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelShopid);
		workPane.add(edtShopid);
		workPane.add(labelShoppwd);
		workPane.add(edtShoppwd);
		workPane.add(labelShopname);
		workPane.add(edtShopname);
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
			String shopid=this.edtShopid.getText();
			String shopname=this.edtShopname.getText();
			String shoppwd=this.edtShoppwd.getText();
			shop=new BeanShop();
			shop.setShop_id(shopid);
			shop.setShop_pwd(shoppwd);
			shop.setShop_name(shopname);
			try {
				(new ShopManager()).createShop(shop);
				this.setVisible(false);
			} catch (BaseException | BusinessException e1) {
				this.shop=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public BeanShop getShop() {
		return shop;
	}
}
