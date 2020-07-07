package cn.edu.zucc.takeoutassist.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassist.model.BeanRider;
import cn.edu.zucc.takeoutassist.model.BeanShop;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;
import cn.edu.zucc.takeoutassist.util.DBUtil;
import cn.edu.zucc.takeoutassist.util.DbException;

public class RiderManager {

	public static BeanRider currentUser=null;

	public BeanRider loadRider(String id) throws BaseException, BusinessException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select r_id,r_pwd,r_name,r_rank,r_date from riders where r_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("骑手账号不存在");
			BeanRider r=new BeanRider();
			r.setR_id(rs.getString(1));
			r.setR_pwd(rs.getString(2));
			r.setR_name(rs.getString(3));
			r.setR_rank(rs.getString(4));
			r.setR_date(rs.getDate(5));
			rs.close();
			pst.close();
			return r;
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
	
	public List<BeanRider> loadAllRider(boolean b) throws BaseException{
		// TODO Auto-generated method stub
		List<BeanRider> result=new ArrayList<BeanRider>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select r_id,r_pwd,r_name,r_rank,r_date from riders";
			sql+=" order by r_id";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				BeanRider r=new BeanRider();
				r.setR_id(rs.getString(1));
				r.setR_pwd(rs.getString(2));
				r.setR_name(rs.getString(3));
				r.setR_rank(rs.getString(4));
				r.setR_date(rs.getDate(5));
				result.add(r);
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

	public void createRider(BeanRider rider)throws BaseException, BusinessException{
		if(rider.getR_id()==null || "".equals(rider.getR_id()) || rider.getR_id().length()>20){
			throw new BusinessException("商家账号必须是1-20个字");
		}
		if(rider.getR_name()==null || "".equals(rider.getR_name()) || rider.getR_name().length()>50){
			throw new BusinessException("账号名称必须是1-50个字");
		}
		if(rider.getR_pwd()==null|| "".equals(rider.getR_pwd()) || rider.getR_pwd().length()>20) {
			throw new BusinessException("商家密码必须是1-20个字");
		}
		if(!"r".equals(rider.getR_id().substring(0, 1))) throw new BusinessException("骑手id首字母必须为r");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from riders where r_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,rider.getR_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("骑手账号已经存在");
			rs.close();
			pst.close();
			sql="insert into riders(r_id,r_name,r_pwd,r_rank,r_date) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, rider.getR_id());
			pst.setString(2, rider.getR_name());
			pst.setString(3,rider.getR_pwd());
			pst.setString(4, "新人");
			pst.setTimestamp(5,new java.sql.Timestamp(System.currentTimeMillis()));
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
	
	public void changeRiderPwd(String rid,String oldPwd,String newPwd)throws BaseException, BusinessException{
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>20) throw new BusinessException("必须为1-20个字符");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select r_pwd from riders where r_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,rid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("骑手账号不存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update riders set r_pwd=? where r_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, rid);
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
	
	public void resetRidersPwd(String rid)throws BaseException, BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from riders where r_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,rid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("骑手账号不存在");
			rs.close();
			pst.close();
			sql="update riders set r_pwd=? where r_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, rid);
			pst.setString(2, rid);
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
	
	public void deleteRider(String rid)throws BaseException, BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from riders where r_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,rid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("骑手账号不存在");
			rs.close();
			pst.close();
			sql="delete from riders where r_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, rid);
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
}
