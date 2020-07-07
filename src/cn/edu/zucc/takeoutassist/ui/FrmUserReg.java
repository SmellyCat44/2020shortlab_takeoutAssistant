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

import cn.edu.zucc.takeoutassist.control.UserManager;
import cn.edu.zucc.takeoutassist.model.BeanUser;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;

public class FrmUserReg extends JDialog implements ActionListener {
	private BeanUser user=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelUserid = new JLabel("账号：");
	private JLabel labelUserpwd = new JLabel("密码：");//
	private JLabel labelUsername = new JLabel("姓名：");
	private JLabel labelUsersex = new JLabel("性别：");//
	private JLabel labelUsertel = new JLabel("电话：");//
	private JLabel labelUsercity = new JLabel("所在城市：");//
	private JLabel labelUseremail = new JLabel("邮箱：");//
	private JLabel labelUservip = new JLabel("是否充值会员：");//
	
	
	private JTextField edtUserid = new JTextField(20);
	private JTextField edtUserpwd = new JTextField(20);//
	private JTextField edtUsername = new JTextField(20);
	private JTextField edtUsersex = new JTextField(20);//
	private JTextField edtUsertel = new JTextField(20);//
	private JTextField edtUseremail = new JTextField(20);//
	private JTextField edtUservip = new JTextField(16);//
	private JTextField edtUsercity = new JTextField(18);//
	
	public FrmUserReg(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUserid);
		workPane.add(edtUserid);
		workPane.add(labelUserpwd);
		workPane.add(edtUserpwd);
		workPane.add(labelUsername);
		workPane.add(edtUsername);
		workPane.add(labelUsersex);
		workPane.add(edtUsersex);
		workPane.add(labelUsertel);
		workPane.add(edtUsertel);
		workPane.add(labelUseremail);
		workPane.add(edtUseremail);
		workPane.add(labelUservip);
		workPane.add(edtUservip);
		workPane.add(labelUsercity);
		workPane.add(edtUsercity);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 300);
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
			String userid=this.edtUserid.getText();
			String username=this.edtUsername.getText();
			String userpwd=this.edtUserpwd.getText();
			String usersex=this.edtUsersex.getText();
			String usertel=this.edtUsertel.getText();
			String usercity=this.edtUsercity.getText();
			String useremail=this.edtUseremail.getText();
			String uservip=this.edtUservip.getText();
			user=new BeanUser();
			user.setUser_id(userid);
			user.setUser_name(username);
			user.setUser_pwd(userpwd);
			user.setSex(usersex);
			user.setUser_tel(usertel);
			user.setUser_city(usercity);
			user.setEmail(useremail);
			user.setIs_vip(uservip);
			try {
				(new UserManager()).createUser(user);
				this.setVisible(false);
			} catch (BaseException | BusinessException e1) {
				this.user=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public BeanUser getUser() {
		return user;
	}
}
