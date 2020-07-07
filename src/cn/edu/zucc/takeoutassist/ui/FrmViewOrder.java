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

import cn.edu.zucc.takeoutassist.control.OrderManager;
import cn.edu.zucc.takeoutassist.model.BeanOrder;
import cn.edu.zucc.takeoutassist.util.BaseException;

public class FrmViewOrder extends JDialog{
	private JPanel toolBar = new JPanel();
	private Object tblTitle[]={"订单ID","用户ID","骑手ID","地址编号","满减编号","优惠券编号","原始金额","结算金额","下单时间","要求送达时间","订单状态"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable userTable=new JTable(tablmod);
	private void reloadOrderTable(){
		try {
			List<BeanOrder> odr=(new OrderManager()).loadAllOrder(false);
			tblData =new Object[odr.size()][11];
			for(int i=0;i<odr.size();i++){
				tblData[i][0]=odr.get(i).getO_id();
				tblData[i][1]=odr.get(i).getUser_id();
				tblData[i][2]=odr.get(i).getR_id();
				tblData[i][3]=odr.get(i).getAddr_id();
				tblData[i][4]=odr.get(i).getDsc_id();
				tblData[i][5]=odr.get(i).getC_id();
				tblData[i][6]=odr.get(i).getRow();
				tblData[i][7]=odr.get(i).getMon();
				tblData[i][8]=odr.get(i).getO_time();
				tblData[i][9]=odr.get(i).getRq_time();
				tblData[i][10]=odr.get(i).getO_status();
				
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmViewOrder(Frame f, String s, boolean b) {
		super(f, s, b);

		//提取现有数据
		this.reloadOrderTable();
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(1200, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}
	
	
}
