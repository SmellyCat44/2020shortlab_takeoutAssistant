package cn.edu.zucc.takeoutassist.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.takeoutassist.control.MngManager;
import cn.edu.zucc.takeoutassist.control.RiderManager;
import cn.edu.zucc.takeoutassist.control.ShopManager;
import cn.edu.zucc.takeoutassist.control.UserManager;
import cn.edu.zucc.takeoutassist.model.BeanLoginUser;
import cn.edu.zucc.takeoutassist.model.BeanManager;
import cn.edu.zucc.takeoutassist.model.BeanRider;
import cn.edu.zucc.takeoutassist.model.BeanShop;
import cn.edu.zucc.takeoutassist.model.BeanUser;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;

public class FrmLogin extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnLogin = new Button("��½");
	private Button btnReg = new Button("ע��");//�¼�ע�ᰴť
	private Button btnCancel = new Button("�˳�");
	private JLabel labelUser = new JLabel("�û���");
	private JLabel labelPwd = new JLabel("���룺");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);

	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnLogin);
		toolBar.add(btnReg);//
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 140);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnReg.addActionListener(this);//
		btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == this.btnLogin) {
				String id=this.edtUserId.getText();//
				BeanLoginUser blu = new BeanLoginUser();
				blu.setId(id);
				String pwd=new String(this.edtPwd.getPassword());
				try {
					if("m".equals(id.substring(0, 1))) {//��֤����Ա
						MngManager mm=new MngManager();
						BeanManager mng = mm.loadUser(id);
						if(pwd.equals(mng.getMng_pwd())){
							MngManager.currentUser=mng;
							setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null,  "�������","������ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
					else if("u".equals(id.substring(0, 1))) {//��֤�û�
						UserManager um=new UserManager();
						BeanUser user = um.loadUser(id);
						if(pwd.equals(user.getUser_pwd())){
							UserManager.currentUser=user;
							setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null,  "�������","������ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
					else if("r".equals(id.substring(0, 1))) {//��֤����
						RiderManager rm = new RiderManager();
						BeanRider rdr = rm.loadRider(id);
						if(pwd.equals(rdr.getR_pwd())){
							RiderManager.currentUser=rdr;
							setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null,  "�������","������ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
					else if("s".equals(id.substring(0, 1))) {//��֤�̼�
						ShopManager sm=new ShopManager();
						BeanShop user = sm.loadShop(id);
						if(pwd.equals(user.getShop_pwd())){
							ShopManager.currentUser=user;
							setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null,  "�������","������ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "������ʾ",JOptionPane.ERROR_MESSAGE);
				} catch (BusinessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
//				setVisible(false);//���Խ��� ��ɾ��
			} else if (e.getSource() == this.btnCancel) {
				System.exit(0);
			} else if(e.getSource() == this.btnReg) {//ע��
				SelectUserType dlg = new SelectUserType(this,"ѡ���û�����",true);
				dlg.setVisible(true);
			}
		}
}
