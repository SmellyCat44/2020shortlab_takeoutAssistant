package cn.edu.zucc.takeoutassist.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassist.model.BeanManager;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.BusinessException;
import cn.edu.zucc.takeoutassist.util.DBUtil;
import cn.edu.zucc.takeoutassist.util.DbException;

public class MngManager {
	public static BeanManager currentUser=null;

	public BeanManager loadUser(String id) throws BaseException, BusinessException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select mng_id,mng_pwd,mng_name from manager where mng_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("管理员不存在");
			BeanManager m=new BeanManager();
			m.setMng_id(rs.getString(1));
			m.setMng_pwd(rs.getString(2));
			m.setMng_name(rs.getString(3));
			rs.close();
			pst.close();
			return m;
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
	
	public List<BeanManager> loadAllManager(boolean b) throws BaseException{
		List<BeanManager> result=new ArrayList<BeanManager>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select mng_id,mng_pwd,mng_name from manager";
			sql+=" order by mng_id";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				BeanManager m=new BeanManager();
				m.setMng_id(rs.getString(1));
				m.setMng_pwd(rs.getString(2));
				m.setMng_name(rs.getString(3));
				result.add(m);
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
	
	public void createManager(BeanManager mng)throws BaseException, BusinessException{
		if(mng.getMng_id()==null || "".equals(mng.getMng_id()) || mng.getMng_id().length()>20){
			throw new BusinessException("管理员账号必须是1-20个字");
		}
		if(mng.getMng_name()==null || "".equals(mng.getMng_name()) || mng.getMng_name().length()>50){
			throw new BusinessException("姓名必须是1-50个字");
		}
		if(mng.getMng_pwd()==null|| "".equals(mng.getMng_pwd()) || mng.getMng_pwd().length()>20) {
			throw new BusinessException("商家密码必须是1-20个字");
		}
		if(!"m".equals(mng.getMng_id().substring(0, 1))) throw new BusinessException("管理员id首字母必须为m");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from manager where mng_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,mng.getMng_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("管理员账号已经存在");
			rs.close();
			pst.close();
			sql="insert into manager(mng_id,mng_pwd,mng_name) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, mng.getMng_id());
			pst.setString(2, mng.getMng_pwd());
			pst.setString(3,mng.getMng_name());
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
	
	public void changeManagerPwd(String mid,String oldPwd,String newPwd)throws BaseException, BusinessException{
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>20) throw new BusinessException("必须为1-20个字符");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select mng_pwd from manager where mng_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,mid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("管理员账号不存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update manager set mng_pwd=? where mng_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, mid);
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
	
	public void resetManagerPwd(String mid)throws BaseException, BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from manager where mng_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,mid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("管理员账号不存在");
			rs.close();
			pst.close();
			sql="update manager set mng_pwd=? where mng_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, mid);
			pst.setString(2, mid);
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
	
	public void deleteManager(String mid)throws BaseException, BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from manager where mng_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,mid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("管理员账号不存在");
			rs.close();
			pst.close();
			sql="delete from manager where mng_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, mid);
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
