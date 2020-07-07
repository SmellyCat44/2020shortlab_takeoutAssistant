package cn.edu.zucc.takeoutassist.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassist.model.BeanProduct;
import cn.edu.zucc.takeoutassist.util.BaseException;
import cn.edu.zucc.takeoutassist.util.DBUtil;
import cn.edu.zucc.takeoutassist.util.DbException;

public class ProductManager {
	public List<BeanProduct> loadAllProduct(boolean b) throws BaseException{
		// TODO Auto-generated method stub
		List<BeanProduct> result=new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select p_name,p_price,p_dsc_price from products";
			sql+=" order by p_id";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				BeanProduct p=new BeanProduct();
				p.setP_name(rs.getString(1));
				p.setP_price(rs.getDouble(2));
				p.setP_dsc_price(rs.getDouble(3));
				result.add(p);
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
