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
	private JMenu menu_Manager=new JMenu("系统管理");
    private JMenu menu_search=new JMenu("查询信息");
    private JMenu menu_User=new JMenu("我是用户");
    private JMenu menu_Rider=new JMenu("我是骑手");
    private JMenu menu_Shop=new JMenu("我是商家");
    
    //系统管理
    private JMenuItem  menuItem_UserManager=new JMenuItem("用户管理");
    private JMenuItem  menuItem_ShopManager=new JMenuItem("商家管理");
    private JMenuItem  menuItem_RiderManager=new JMenuItem("骑手管理");
    private JMenuItem  menuItem_MngManager=new JMenuItem("管理员管理");
    
    //查询信息
    private JMenuItem  menuItem_UserConsumeSearch=new JMenuItem("用户消费情况查询");   
    private JMenuItem  menuItem_RiderInfoSearch=new JMenuItem("骑手信息查询");
    private JMenuItem  menuItem_ProductTypeSearch=new JMenuItem("分类商品情况查询");
    private JMenuItem  menuItem_ProductSearch=new JMenuItem("商品单品情况查询");
    
    //我是用户
    private JMenuItem  menuItem_UserOrder=new JMenuItem("点单");   
    private JMenuItem  menuItem_ProductComment=new JMenuItem("评价商品");
    private JMenuItem  menuItem_RiderComment=new JMenuItem("评价骑手");
    private JMenuItem  menuItem_UserChangePwd=new JMenuItem("修改密码");//
    
    //我是骑手
    private JMenuItem  menuItem_TakeOrder=new JMenuItem("接单");   
    private JMenuItem  menuItem_AccountSearch=new JMenuItem("查询账户");
    private JMenuItem  menuItem_RiderChangePwd=new JMenuItem("修改密码");//
    
    //我是商家
    
    
    private FrmLogin dlgLogin=null;//登陆界面
	private JPanel statusBar = new JPanel();
	
	public FrmMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖小助手");
		dlgLogin=new FrmLogin(this,"登陆",true);
		dlgLogin.setVisible(true);
		//if("m".equals(LoginUserManager.currentUser.getId().substring(0, 1))) {//管理员才能看到的系统管理选项*/
			menu_Manager.add(menuItem_UserManager);
	    	menuItem_UserManager.addActionListener(this);
	    	menu_Manager.add(menuItem_ShopManager);
	    	menuItem_ShopManager.addActionListener(this);
	    	menu_Manager.add(menuItem_RiderManager);
	    	menuItem_RiderManager.addActionListener(this);
	    	menu_Manager.add(menuItem_MngManager);
	    	menuItem_MngManager.addActionListener(this);
	    	menubar.add(menu_Manager);
		
	    //查询信息选项条
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
		
	    //我是商家
	    menubar.add(this.menu_Shop);//加！！！！！
	    
	    //用户点外卖
	    menu_User.add(this.menuItem_UserOrder);
		menuItem_UserOrder.addActionListener(this);
		menu_User.add(this.menuItem_ProductComment);
	    menuItem_ProductComment.addActionListener(this);
	    menu_User.add(this.menuItem_RiderComment);
	    menuItem_RiderComment.addActionListener(this);
	    menu_User.add(this.menuItem_UserChangePwd);
	    menuItem_UserChangePwd.addActionListener(this);
	    menubar.add(this.menu_User);
	    
	    //骑手接单
	    menu_Rider.add(this.menuItem_TakeOrder);
	    menuItem_TakeOrder.addActionListener(this);
	    menu_Rider.add(this.menuItem_AccountSearch);
	    menuItem_AccountSearch.addActionListener(this);
	    menu_Rider.add(this.menuItem_RiderChangePwd);
	    menuItem_RiderChangePwd.addActionListener(this);
	    menubar.add(this.menu_Rider);
	    
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("你肚子饿了吗？");
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
		//管理
		if(e.getSource()==this.menuItem_UserManager){
			FrmUserManager dlg=new FrmUserManager(this,"用户管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ShopManager){
			FrmShopManager dlg=new FrmShopManager(this,"商家管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_RiderManager){
			FrmRiderManager dlg=new FrmRiderManager(this,"骑手管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_MngManager){
			FrmMngManager dlg=new FrmMngManager(this,"管理员管理",true);
			dlg.setVisible(true);
		}
		
           //查询
		else if(e.getSource()==this.menuItem_UserConsumeSearch){
//			FrmUserConsumeSearch dlg=new FrmUserConsumeSearch(this,"用户消费情况查询",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_RiderInfoSearch){
//			FrmRiderInfoSearch dlg=new FrmRiderInfoSearch(this,"骑手信息查询",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductTypeSearch){
//			FrmProductTypeSearch dlg=new FrmProductTypeSearch(this,"分类商品情况查询",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductSearch){
//			FrmProductSearch dlg=new FrmProductSearch(this,"商品单品情况查询",true);
//			dlg.setVisible(true);
		}
		
		//我是用户
		else if(e.getSource()==this.menuItem_UserOrder) {
//			FrmUserOrder dlg=new FrmUserOrder(this,"点单",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductComment) {
//			FrmProductComment dlg=new FrmProductComment(this,"评价商品",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_RiderComment) {
//			FrmRiderComment dlg=new FrmRiderComment(this,"评价骑手",true);
//			dlg.setVisible(true);
		}
		
		//我是骑手
		else if(e.getSource()==this.menuItem_TakeOrder) {
//			FrmTakeOrder dlg=new FrmTakeOrder(this,"点单",true);
//			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_AccountSearch) {
//			FrmAccountSearch dlg=new FrmAccountSearch(this,"点单",true);
//			dlg.setVisible(true);
		}
		
		//我是商家
		

	} 
}
