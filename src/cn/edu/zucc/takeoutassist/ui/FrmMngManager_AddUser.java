package cn.edu.zucc.takeoutassist.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeoutassist.control.MngManager;
import cn.edu.zucc.takeoutassist.control.RiderManager;
import cn.edu.zucc.takeoutassist.model.BeanManager;
import cn.edu.zucc.takeoutassist.model.BeanRider;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;

public class FrmMngManager_AddUser extends JDialog implements ActionListener {
	private BeanManager mng=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelMngid = new JLabel("账号：");
	private JLabel labelMngpwd = new JLabel("密码：");
	private JLabel labelMngname = new JLabel("姓名：");
	
	private JTextField edtMngid = new JTextField(20);
	private JTextField edtMngpwd = new JTextField(20);
	private JTextField edtMngname = new JTextField(20);
	
	public FrmMngManager_AddUser(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelMngid);
		workPane.add(edtMngid);
		workPane.add(labelMngpwd);
		workPane.add(edtMngpwd);
		workPane.add(labelMngname);
		workPane.add(edtMngname);
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
			String mid=this.edtMngid.getText();
			String mname=this.edtMngpwd.getText();
			String mpwd=this.edtMngname.getText();
			mng=new BeanManager();
			mng.setMng_id(mid);
			mng.setMng_name(mname);
			mng.setMng_pwd(mpwd);
			try {
				(new MngManager()).createManager(mng);
				this.setVisible(false);
			} catch (BaseException | BusinessException e1) {
				this.mng=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public BeanManager getManager() {
		return mng;
	}
}
