package cn.edu.zucc.takeoutassist.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import cn.edu.zucc.takeoutassist.control.LoginUserManager;
import cn.edu.zucc.takeoutassist.control.MngManager;
import cn.edu.zucc.takeoutassist.control.UserManager;
import cn.edu.zucc.takeoutassist.model.BeanLoginUser;

public class FrmMain extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar();
	private JMenu menu_Manager=new JMenu("ϵͳ����");
    private JMenu menu_search=new JMenu("��ѯ��Ϣ");
    private JMenu menu_User=new JMenu("�����û�");
    private JMenu menu_Rider=new JMenu("��������");
    private JMenu menu_Shop=new JMenu("�����̼�");
    
    //ϵͳ����
    private JMenuItem  menuItem_UserManager=new JMenuItem("�û�����");
    private JMenuItem  menuItem_ShopManager=new JMenuItem("�̼ҹ���");
    private JMenuItem  menuItem_RiderManager=new JMenuItem("���ֹ���");
    private JMenuItem  menuItem_MngManager=new JMenuItem("����Ա����");
    
    //��ѯ��Ϣ
    private JMenuItem  menuItem_UserConsumeSearch=new JMenuItem("�û����������ѯ");   
    private JMenuItem  menuItem_RiderInfoSearch=new JMenuItem("������Ϣ��ѯ");
    private JMenuItem  menuItem_ProductTypeSearch=new JMenuItem("������Ʒ�����ѯ");
    private JMenuItem  menuItem_ProductSearch=new JMenuItem("��Ʒ��Ʒ�����ѯ");
    
    //�����û�
    private JMenuItem  menuItem_UserOrder=new JMenuItem("�㵥");   
    private JMenuItem  menuItem_ProductComment=new JMenuItem("������Ʒ");
    private JMenuItem  menuItem_RiderComment=new JMenuItem("��������");
    private JMenuItem  menuItem_UserChangePwd=new JMenuItem("�޸�����");//
    
    //��������
    private JMenuItem  menuItem_TakeOrder=new JMenuItem("�ӵ�");   
    private JMenuItem  menuItem_AccountSearch=new JMenuItem("��ѯ�˻�");
    private JMenuItem  menuItem_RiderChangePwd=new JMenuItem("�޸�����");//
    
    //�����̼�
    
    
    private FrmLogin dlgLogin=null;//��½����
	private JPanel statusBar = new JPanel();
	
	public FrmMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("����С����");
		dlgLogin=new FrmLogin(this,"��½",true);
		dlgLogin.setVisible(true);
		//if("m".equals(LoginUserManager.currentUser.getId().substring(0, 1))) {//����Ա���ܿ�����ϵͳ����ѡ��*/
			menu_Manager.add(menuItem_UserManager);
	    	menuItem_UserManager.addActionListener(this);
	    	menu_Manager.add(menuItem_ShopManager);
	    	menuItem_ShopManager.addActionListener(this);
	    	menu_Manager.add(menuItem_RiderManager);
	    	menuItem_RiderManager.addActionListener(this);
	    	menu_Manager.add(menuItem_MngManager);
	    	menuItem_MngManager.addActionListener(this);
	    	menubar.add(menu_Manager);
		
	    //��ѯ��Ϣѡ����
	        menu_search.add(this.menuItem_UserConsumeSearch);
		    menuItem_UserConsumeSearch.addActionListener(this);
		    menu_search.add(this.menuItem_RiderInfoSearch);
		    menuItem_RiderInfoSearch.addActionListener(this);
	        menu_search.add(this.menuItem_ProductTypeSearch);
	        menuItem_ProductTypeSearch.addActionListener(this);
	        menu_search.add(this.menuItem_ProductSearch);
	        menuItem_ProductSearch.addActionListener(this);
	        menubar.add(this.menu_search);
	        //}
		
	    //�����̼�
	    menubar.add(this.menu_Shop);//�ӣ���������
	    
	    //�û�������
	    menu_User.add(this.menuItem_UserOrder);
		menuItem_UserOrder.addActionListener(this);
		menu_User.add(this.menuItem_ProductComment);
	    menuItem_ProductComment.addActionListener(this);
	    menu_User.add(this.menuItem_RiderComment);
	    menuItem_RiderComment.addActionListener(this);
	    menu_User.add(this.menuItem_UserChangePwd);
	    menuItem_UserChangePwd.addActionListener(this);
	    menubar.add(this.menu_User);
	    
	    //���ֽӵ�
	    menu_Rider.add(this.menuItem_TakeOrder);
	    menuItem_TakeOrder.addActionListener(this);
	    menu_Rider.add(this.menuItem_AccountSearch);
	    menuItem_AccountSearch.addActionListener(this);
	    menu_Rider.add(this.menuItem_RiderChangePwd);
	    menuItem_RiderChangePwd.addActionListener(this);
	    menubar.add(this.menu_Rider);
	    
	    //״̬��
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("����Ӷ�����");
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
		this.setJMenuBar(menubar);//end
	}
	
	public String getId() {
		
		return null;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//����
		if(e.getSource()==this.menuItem_UserManager){
			FrmUserManager dlg=new FrmUserManager(this,"�û�����",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ShopManager){
			FrmShopManager dlg=new FrmShopManager(this,"�̼ҹ���",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_RiderManager){
			FrmRiderManager dlg=new FrmRiderManager(this,"���ֹ���",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_MngManager){
			FrmMngManager dlg=new FrmMngManager(this,"����Ա����",true);
			dlg.setVisible(true);
		}
		
           //��ѯ
		else if(e.getSource()==this.menuItem_UserConsumeSearch){
//			FrmUserConsumeSearch dlg=new FrmUserConsumeSearch(this,"�û����������ѯ",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_RiderInfoSearch){
//			FrmRiderInfoSearch dlg=new FrmRiderInfoSearch(this,"������Ϣ��ѯ",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductTypeSearch){
//			FrmProductTypeSearch dlg=new FrmProductTypeSearch(this,"������Ʒ�����ѯ",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductSearch){
//			FrmProductSearch dlg=new FrmProductSearch(this,"��Ʒ��Ʒ�����ѯ",true);
//			dlg.setVisible(true);
		}
		
		//�����û�
		else if(e.getSource()==this.menuItem_UserOrder) {
//			FrmUserOrder dlg=new FrmUserOrder(this,"�㵥",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductComment) {
//			FrmProductComment dlg=new FrmProductComment(this,"������Ʒ",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_RiderComment) {
//			FrmRiderComment dlg=new FrmRiderComment(this,"��������",true);
//			dlg.setVisible(true);
		}
		
		//��������
		else if(e.getSource()==this.menuItem_TakeOrder) {
//			FrmTakeOrder dlg=new FrmTakeOrder(this,"�㵥",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_AccountSearch) {
//			FrmAccountSearch dlg=new FrmAccountSearch(this,"�㵥",true);
//			dlg.setVisible(true);
		}
		
		//�����̼�
		

	} 
}
