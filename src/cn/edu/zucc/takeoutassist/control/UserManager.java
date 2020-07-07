package cn.edu.zucc.takeoutassist.control;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.edu.zucc.takeoutassist.model.BeanUser;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;
import cn.edu.zucc.takeoutassist.util.DBUtil;
import cn.edu.zucc.takeoutassist.util.DbException;

public class UserManager {
	public static BeanUser currentUser=null;

	public BeanUser loadUser(String id) throws BaseException, BusinessException {//
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_name,sex,user_tel,email,user_city,reg_time,is_vip,vip_due_date from users where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			BeanUser u=new BeanUser();
			u.setUser_id(rs.getString(1));
			u.setUser_name(rs.getString(2));
			u.setSex(rs.getString(3));
			u.setUser_tel(rs.getString(4));
			u.setEmail(rs.getString(5));
			u.setUser_city(rs.getString(6));
			u.setReg_time(rs.getDate(7));
			u.setIs_vip(rs.getString(8));
			u.setVip_due_date(rs.getDate(9));
			rs.close();
			pst.close();
			return u;
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

	public List<BeanUser> loadAllUsers(boolean withDeletedUser) throws BaseException{//
		// TODO Auto-generated method stub
		List<BeanUser> result=new ArrayList<BeanUser>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_pwd,user_name,sex,user_tel,email,user_city,reg_time,is_vip,vip_due_date from users";
			sql+=" order by user_id";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				BeanUser u=new BeanUser();
				u.setUser_id(rs.getString(1));
				u.setUser_pwd(rs.getString(2));
				u.setUser_name(rs.getString(3));
				u.setSex(rs.getString(4));
				u.setUser_tel(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setUser_city(rs.getString(7));
				u.setReg_time(rs.getDate(8));
				u.setIs_vip(rs.getString(9));
				u.setVip_due_date(rs.getDate(10));
				result.add(u);
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
	
	public void createUser(BeanUser user)throws BaseException, BusinessException{//
		if(user.getUser_id()==null || "".equals(user.getUser_id()) || user.getUser_id().length()>20){
			throw new BusinessException("登陆账号必须是1-20个字");
		}
		if(user.getUser_name()==null || "".equals(user.getUser_name()) || user.getUser_name().length()>50){
			throw new BusinessException("账号名称必须是1-50个字");
		}
		if(user.getUser_pwd()==null|| "".equals(user.getUser_pwd()) || user.getUser_id().length()>20) {
			throw new BusinessException("用户密码必须是1-20个字");
		}
		if(user.getEmail()==null||"".equals(user.getEmail())||user.getSex()==null||"".equals(user.getSex())||user.getUser_tel()==null||"".equals(user.getUser_tel())) {
			throw new BusinessException("用户信息不完整");
		}
		if(!"u".equals(user.getUser_id().substring(0, 1))) throw new BusinessException("用户id首字母必须为u");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from users where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			sql="insert into users(user_id,user_pwd,user_name,sex,user_tel,email,user_city,reg_time,is_vip,vip_due_date) values(?,?,?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_id());
			pst.setString(2, user.getUser_pwd());
			pst.setString(3,user.getUser_name());
			pst.setString(4, user.getSex());
			pst.setString(5, user.getUser_tel());
			pst.setString(6, user.getEmail());
			pst.setString(7, user.getUser_city());
			pst.setTimestamp(8,new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setString(9, user.getIs_vip());
			if(user.getIs_vip().equals("是")) {
				Timestamp ts0  = new Timestamp(System.currentTimeMillis());
				Calendar c = Calendar.getInstance();
				c.setTime(ts0);
				c.add(Calendar.MONTH, 1); //  加一个月
				Timestamp time1 = new Timestamp(c.getTimeInMillis());
				pst.setTimestamp(10,time1);
			}
			else pst.setTimestamp(10,null);
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
	
	public void changeUserPwd(String userid,String oldPwd,String newPwd)throws BaseException, BusinessException{
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>20) throw new BusinessException("必须为1-20个字符");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_pwd from users where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update users set user_pwd=? where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, userid);
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
	
	public void resetUserPwd(String userid)throws BaseException, BusinessException{//
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from users where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			rs.close();
			pst.close();
			sql="update users set user_pwd=? where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, userid);
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
	
	public void deleteUser(String userid)throws BaseException, BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from users where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			rs.close();
			pst.close();
			sql="delete from users where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
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
	
	//修改用户名
	
	
}
