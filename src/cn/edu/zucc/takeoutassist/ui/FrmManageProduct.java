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

import cn.edu.zucc.takeoutassist.control.ProductManager;
import cn.edu.zucc.takeoutassist.model.BeanProduct;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;

public class FrmManageProduct extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加商品");
	private Button btnChg = new Button("修改商品");
	private Button btnDelete = new Button("删除商品");
	private Object tblTitle[]={"商品名","商品单价","优惠价格"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable userTable=new JTable(tablmod);
	private void reloadProductTable(){
		try {
			List<BeanProduct> pro=(new ProductManager()).loadAllProduct(false);
			tblData =new Object[pro.size()][3];
			for(int i=0;i<pro.size();i++){
				tblData[i][0]=pro.get(i).getP_name();
				tblData[i][1]=pro.get(i).getP_price();
				tblData[i][2]=pro.get(i).getP_dsc_price();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmManageProduct(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnChg);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadProductTable();
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(600, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnChg.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			FrmProductManager_Add dlg=new FrmProductManager_Add(this,"添加商品",true);
			dlg.setVisible(true);
			if(dlg.getUser()!=null){//刷新表格
				this.reloadProductTable();//
			}
		}
		else if(e.getSource()==this.btnChg){//修改商品
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定修改商品吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String userid=this.tblData[i][0].toString();//
				try {
					(new ProductManager()).resetUserPwd(userid);//
					JOptionPane.showMessageDialog(null,  "密码重置完成","提示",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException | BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(e.getSource()==this.btnDelete){//删除商品
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除商品吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String userid=this.tblData[i][0].toString();
				try {
					(new UserManager()).deleteUser(userid);//改
					this.reloadUserTable();//
				} catch (BaseException | BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
	
}
