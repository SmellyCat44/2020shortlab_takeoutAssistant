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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeoutassist.control.UserManager;
import cn.edu.zucc.takeoutassist.model.BeanUser;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;

public class FrmUserManager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("����û�");
	private Button btnResetPwd = new Button("��������");
	private Button btnDelete = new Button("ɾ���û�");
	private Object tblTitle[]={"�˺�","����","����","�Ա�","�绰","����","���ڳ���","ע��ʱ��","�Ƿ�Ϊ��Ա","��Ա����ʱ��"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable userTable=new JTable(tablmod);
	private void reloadUserTable(){
		try {
			List<BeanUser> users=(new UserManager()).loadAllUsers(false);
			tblData =new Object[users.size()][10];
			for(int i=0;i<users.size();i++){
				tblData[i][0]=users.get(i).getUser_id();
				tblData[i][1]=users.get(i).getUser_pwd();
				tblData[i][2]=users.get(i).getUser_name();
				tblData[i][3]=users.get(i).getSex();
				tblData[i][4]=users.get(i).getUser_tel();
				tblData[i][5]=users.get(i).getEmail();
				tblData[i][6]=users.get(i).getUser_city();
				tblData[i][7]=users.get(i).getReg_time();
				tblData[i][8]=users.get(i).getIs_vip();
				tblData[i][9]=users.get(i).getVip_due_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmUserManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnResetPwd);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		this.reloadUserTable();
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
		
		// ��Ļ������ʾ
		this.setSize(1000, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnResetPwd.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			FrmUserManager_AddUser dlg=new FrmUserManager_AddUser(this,"����˺�",true);
			dlg.setVisible(true);
			if(dlg.getUser()!=null){//ˢ�±��
				this.reloadUserTable();
			}
		}
		else if(e.getSource()==this.btnResetPwd){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���˺�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ������������","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String userid=this.tblData[i][0].toString();
				try {
					(new UserManager()).resetUserPwd(userid);
					JOptionPane.showMessageDialog(null,  "�����������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException | BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(e.getSource()==this.btnDelete){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���˺�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ���˺���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String userid=this.tblData[i][0].toString();
				try {
					(new UserManager()).deleteUser(userid);//��
					this.reloadUserTable();
				} catch (BaseException | BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
