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

import cn.edu.zucc.takeoutassist.control.ShopManager;
import cn.edu.zucc.takeoutassist.control.UserManager;
import cn.edu.zucc.takeoutassist.model.BeanShop;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;

public class FrmShopManager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("����̼�");
	private Button btnResetPwd = new Button("��������");
	private Button btnResetRank = new Button("�޸��̼��Ǽ�");
	private Button btnDelete = new Button("ɾ���̼�");
	private Object tblTitle[]={"�˺�","�̼���","����","�̼��Ǽ�","�˾�����","������"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable userTable=new JTable(tablmod);
	private void reloadUserTable(){
		try {
			List<BeanShop> shop=(new ShopManager()).loadAllShop(false);
			tblData =new Object[shop.size()][6];
			for(int i=0;i<shop.size();i++){
				tblData[i][0]=shop.get(i).getShop_id();
				tblData[i][1]=shop.get(i).getShop_name();
				tblData[i][2]=shop.get(i).getShop_pwd();
				tblData[i][3]=shop.get(i).getShop_rank();
				tblData[i][4]=shop.get(i).getAvg_csm();
				tblData[i][5]=shop.get(i).getSum_vlm();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmShopManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnResetPwd);
		toolBar.add(btnResetRank);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		this.reloadUserTable();
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
		
		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnResetPwd.addActionListener(this);
		this.btnResetRank.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnAdd){
			FrmShopManager_AddUser dlg=new FrmShopManager_AddUser(this,"����˺�",true);
			dlg.setVisible(true);
			if(dlg.getShop()!=null){//ˢ�±��
				this.reloadUserTable();
			}
		}
		else if(e.getSource()==this.btnResetPwd){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ���̼�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ������������","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String shopid=this.tblData[i][0].toString();
				try {
					(new ShopManager()).resetShopPwd(shopid);
					JOptionPane.showMessageDialog(null,  "�����������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException | BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(e.getSource()==this.btnDelete){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���̼�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ���̼���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String shopid=this.tblData[i][0].toString();
				try {
					(new ShopManager()).deleteShop(shopid);//��
					this.reloadUserTable();
				} catch (BaseException | BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
