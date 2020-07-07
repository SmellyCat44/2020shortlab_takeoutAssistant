package cn.edu.zucc.takeoutassist.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassist.model.BeanOrder;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.DBUtil;
import cn.edu.zucc.takeoutassist.util.DbException;

public class OrderManager {

	public List<BeanOrder> loadAllOrder(boolean b)throws BaseException{
		// TODO Auto-generated method stub
		List<BeanOrder> result=new ArrayList<BeanOrder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select o_id,user_id,r_id,addr_id,dsc_id,c_id,row,mon,o_time,rq_time,o_status from orders";
			sql+=" order by o_id";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				BeanOrder o=new BeanOrder();
				o.setO_id(rs.getInt(1));
				o.setUser_id(rs.getString(2));
				o.setR_id(rs.getString(3));
				o.setAddr_id(rs.getInt(4));
				o.setDsc_id(rs.getInt(5));
				o.setC_id(rs.getInt(6));
				o.setRow(rs.getDouble(7));
				o.setMon(rs.getDouble(8));
				o.setO_time(rs.getTimestamp(9));
				o.setRq_time(rs.getTimestamp(10));
				o.setO_status(rs.getString(11));
				result.add(o);
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

}
