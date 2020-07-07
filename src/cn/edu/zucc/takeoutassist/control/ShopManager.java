package cn.edu.zucc.takeoutassist.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.edu.zucc.takeoutassist.model.BeanShop;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;
import cn.edu.zucc.takeoutassist.util.DBUtil;
import cn.edu.zucc.takeoutassist.util.DbException;

public class ShopManager {
	public static BeanShop currentUser=null;

	public BeanShop loadShop(String id) throws BaseException, BusinessException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shop_id,shop_name,shop_pwd,shop_rank,avg_csm,sum_vlm from shop where shop_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�̼��˺Ų�����");
			BeanShop s=new BeanShop();
			s.setShop_id(rs.getString(1));
			s.setShop_name(rs.getString(2));
			s.setShop_pwd(rs.getString(3));
			s.setShop_rank(rs.getString(4));
			s.setAvg_csm(rs.getDouble(5));
			s.setSum_vlm(rs.getInt(6));
			rs.close();
			pst.close();
			return s;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public List<BeanShop> loadAllShop(boolean b) throws BaseException{
		// TODO Auto-generated method stub
		List<BeanShop> result=new ArrayList<BeanShop>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shop_id,shop_name,shop_pwd,shop_rank,avg_csm,sum_vlm from shop";
			sql+=" order by shop_id";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				BeanShop s=new BeanShop();
				s.setShop_id(rs.getString(1));
				s.setShop_name(rs.getString(2));
				s.setShop_pwd(rs.getString(3));
				s.setShop_rank(rs.getString(4));
				s.setAvg_csm(rs.getDouble(5));
				s.setSum_vlm(rs.getInt(6));;
				result.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	
	public void createShop(BeanShop shop)throws BaseException, BusinessException{
		if(shop.getShop_id()==null || "".equals(shop.getShop_id()) || shop.getShop_id().length()>20){
			throw new BusinessException("�̼��˺ű�����1-20����");
		}
		if(shop.getShop_name()==null || "".equals(shop.getShop_name()) || shop.getShop_name().length()>50){
			throw new BusinessException("�˺����Ʊ�����1-50����");
		}
		if(shop.getShop_pwd()==null|| "".equals(shop.getShop_pwd()) || shop.getShop_pwd().length()>20) {
			throw new BusinessException("�̼����������1-20����");
		}
		if(!"s".equals(shop.getShop_id().substring(0, 1))) throw new BusinessException("�̼�id����ĸ����Ϊs");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from shop where shop_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,shop.getShop_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("��½�˺��Ѿ�����");
			rs.close();
			pst.close();
			sql="insert into shop(shop_id,shop_name,shop_pwd,shop_rank,avg_csm,sum_vlm) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, shop.getShop_id());
			pst.setString(2, shop.getShop_name());
			pst.setString(3,shop.getShop_pwd());
			pst.setString(4, "һ��");
			pst.setDouble(5, 0);
			pst.setInt(6, 0);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void changeShopPwd(String shopid,String oldPwd,String newPwd)throws BaseException, BusinessException{
		if(oldPwd==null) throw new BusinessException("ԭʼ���벻��Ϊ��");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>20) throw new BusinessException("����Ϊ1-20���ַ�");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shop_pwd from shop where shop_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,shopid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�̼��˺Ų�����");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("ԭʼ�������");
			rs.close();
			pst.close();
			sql="update shop set shop_pwd=? where shop_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, shopid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void resetShopPwd(String shopid)throws BaseException, BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from shop where shop_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,shopid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�̼��˺Ų�����");
			rs.close();
			pst.close();
			sql="update users set shop_pwd=? where shop_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, shopid);
			pst.setString(2, shopid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void deleteShop(String shopid)throws BaseException, BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from shop where shop_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,shopid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�̼��˺Ų�����");
			rs.close();
			pst.close();
			sql="delete from shop where shop_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, shopid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	/*public void changeShopRank(String shopid,String newrank)throws BaseException, BusinessException{
		if(newrank==null) throw new BusinessException("�̼��Ǽ�����Ϊ��");
		if( !"һ��".equals(newrank) || !"����".equals(newrank)|| !"����".equals(newrank) || !"����".equals(newrank) || !"����".equals(newrank) ) throw new BusinessException("�̼ұ���Ϊһ��/����/����/����/����");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shop_rank from shop where shop_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,shopid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�̼��˺Ų�����");
			rs.close();
			pst.close();
			sql="update shop set shop_rank=? where shop_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newrank);
			pst.setString(2, shopid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}*/
}
