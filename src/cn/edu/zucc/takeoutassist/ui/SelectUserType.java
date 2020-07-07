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

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SelectUserType extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelTip = new JLabel("请选择你要注册的用户类型");
	private JComboBox cmbUsertype= new JComboBox(new String[] {"用户","商家","骑手"});
	public SelectUserType(FrmLogin frmLogin, String s, boolean b) {
		super(frmLogin, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelTip);
		workPane.add(cmbUsertype);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 140);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			if(this.cmbUsertype.getSelectedIndex()<0){
				JOptionPane.showMessageDialog(null,  "请选择注册账号类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(this.cmbUsertype.getSelectedIndex()==0) {//用户注册
				FrmUserReg dlg=new FrmUserReg(this,"用户注册",true);
				dlg.setVisible(true);
			}
            else if(this.cmbUsertype.getSelectedIndex()==1) {//商家注册
            	FrmShopReg dlg=new FrmShopReg(this,"商家注册",true);
            	dlg.setVisible(true);
			}
            else if(this.cmbUsertype.getSelectedIndex()==2) {//骑手注册
            	FrmRiderReg dlg=new FrmRiderReg(this,"骑手注册",true);
            	dlg.setVisible(true);
			}
		}
	}
}
