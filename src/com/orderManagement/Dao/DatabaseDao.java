package com.orderManagement.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orderManagement.entity.Client;
import com.orderManagement.entity.Order;
import com.orderManagement.entity.OrderItem;
import com.orderManagement.entity.Payment;
import com.orderManagement.entity.Player;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;
import com.orderManagement.entity.Product;

public class DatabaseDao {

	public void loadDriver() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/ordermanager?useSSL=false";
			String user = "root";
			String pwd = "123456";
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	private void cleanup(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isUsernameExists(String username) {
		String sql = "select * from user where username = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.executeQuery();
			rs = ps.getResultSet();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cleanup(conn, ps, rs);
		}
		return false;
	}

	public void sign(User user) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		ResultSet rs = null;
		int id = -1;
		String sql1 = "INSERT INTO `ordermanager`.`user` (`username`, `password`, `email`, `client_id`) VALUES (?,?,?,?)";
		String sql2 = "INSERT INTO `ordermanager`.`client` (`name`, `phone`, `client_type_id`) VALUES (?, ?,?)";
		String sql3 = "select id from client where name=?";
		String sql4 = "INSERT INTO `ordermanager`.`account` (`client_id`, `balance`) VALUES (?, ?)";


		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			if(user.getClientId()==-1){
				ps1 = conn.prepareStatement(sql2);
				ps1.setString(1, user.getClient().getNickname());
				ps1.setString(2, user.getClient().getPhone());
				ps1.setInt(3, user.getClient().getClientType());
				ps1.execute();

				ps3 = conn.prepareStatement(sql3);
				ps3.setString(1, user.getUsername());
				ps3.executeQuery();
				rs = ps3.getResultSet();
				while (rs.next()) {
					id = rs.getInt("id");
				}
				
				ps4 = conn.prepareStatement(sql4);
				ps4.setInt(1, id);
				ps4.setInt(2, 0);
				ps4.execute();
			}
			
			

			ps2 = conn.prepareStatement(sql1);
			ps2.setString(1, user.getUsername());
			ps2.setString(2, user.getPassword());
			ps2.setString(3, user.getEmail());
			if (user.getClientId() != -1) {
				ps2.setInt(4, user.getClientId());
			} else {
				ps2.setInt(4, id);
			}
			ps2.execute();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (ps3 != null) {
				try {
					ps3.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps2 != null) {
				try {
					ps2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cleanup(conn, ps1, rs);
		}
	}

	public List<Client> getClient() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Client> lists = new ArrayList<>();
		Client client = null;
		String sql = "select * from client";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			rs = ps.getResultSet();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				int client_type_id = rs.getInt("client_type_id");
				int client_status_id = rs.getInt("client_status_id");
				client = new Client(id, name, phone, client_type_id, client_status_id);
				lists.add(client);
			}
			return lists;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cleanup(conn, ps, rs);
		}
		return lists;
	}

	@SuppressWarnings("resource")
	public User login(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		User user = null;
		Client client = null;
		int userId = -1;
		String name = null;
		String userpwd = null;
		String email = null;
		int client_id = -1;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from user where username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				userId = rs.getInt("id");
				name = rs.getString("username");
				userpwd = rs.getString("password");
				email = rs.getString("email");
				client_id = rs.getInt("client_id");
			}
			rs = null;
			ps2 = conn.prepareStatement("select * from client where id=?");
			ps2.setInt(1, client_id);
			ps2.executeQuery();
			rs = ps2.getResultSet();
			while (rs.next()) {
				String nickname = rs.getString("name");
				String phone = rs.getString("phone");
				int client_type_id = rs.getInt("client_type_id");
				int client_status_id = rs.getInt("client_status_id");
				client = new Client(client_id, nickname, phone, client_type_id, client_status_id);
			}
			user = new User(userId, name, userpwd, email, client);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (ps2 != null) {
				try {
					ps2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cleanup(conn, ps, rs);
		}

	}

	public List<Product> getProductList() {
		String sql = "select * from product,product_nuit,catalog where "
				+ "product.product_nuit_id=product_nuit.id and product.catalog_id=catalog.id";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;
		List<Product> products = new ArrayList<>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			rs = ps.getResultSet();
			while (rs.next()) {
				int id = rs.getInt("product.id");
				String code = rs.getString("product.code");
				String name = rs.getString("product.name");
				double price = rs.getDouble("product.price");
				String pUnit = rs.getString("product_nuit.name");
				String pCatalog = rs.getString("catalog.name");
				product = new Product(id, code, name, price, pUnit, pCatalog);
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cleanup(conn, ps, rs);
		}
		return products;
	}

	public void createOrder(Order order) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		ResultSet rs = null;
		int orderId = -1;
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = Date.valueOf(order.getCreateTime());
//		Console.println(date);
		String orderInsert = "INSERT INTO `ordermanager`.`order` (`create_datetime`, `client_id`) VALUES (?,?)";
		String queryOrder = "select * from ordermanager.order  where create_datetime=?;";
		String itemInsert = "INSERT INTO `ordermanager`.`order_item` (`amount`, `order_id`, `product_id`) VALUES (?,?,?)";
		String paymentInsert = "INSERT INTO `ordermanager`.`payment` (`order_id`, `payment_status_id`, `payment_method_id`) VALUES (?,?,?)";

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps1 = conn.prepareStatement(orderInsert);
			ps1.setString(1, order.getCreateTime());
			ps1.setInt(2, order.getClientId());
			ps1.execute();

			ps2 = conn.prepareStatement(queryOrder);
			ps2.setString(1, order.getCreateTime());
			ps2.executeQuery();
			rs = ps2.getResultSet();
			while (rs.next()) {
				orderId = rs.getInt("id");
			}

			ps3 = conn.prepareStatement(itemInsert);
			List<OrderItem> items = order.getItems();
			for(OrderItem o:items){
				ps3.setInt(1, o.getAmount());
				ps3.setInt(2, orderId);
				ps3.setInt(3, o.getProduct().getId());
				
				ps3.addBatch();
			}
			ps3.executeBatch();
			
			ps4 = conn.prepareStatement(paymentInsert);
			ps4.setInt(1, orderId);
			ps4.setInt(2, 1);
			ps4.setInt(3, order.getPayment().getpMethod().equals("现金")?1:2);
			ps4.execute();
			
			conn.commit();
			

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			cleanup(null, ps4, null);
			cleanup(null, ps3, null);
			cleanup(null, ps2, null);
			cleanup(conn, ps1, rs);
		}
	}

	public void recharge(User user,int num){
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		int userId = user.getClient().getId();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime =df.format(new Date());
		String value;
		String sql1 = "select * from account where client_id=?";
		String sql2 = "UPDATE `ordermanager`.`account` SET `balance`=? WHERE `client_id`=?";
		String sql3 = "INSERT INTO `ordermanager`.`transaction` "
				+ "(`value`, `create_datetime`, `account_id`, `transaction_type_id`, `account_client_id`) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, user.getClient().getId());
			ps1.executeQuery();
			rs=ps1.getResultSet();
			double balance = 0;
			while(rs.next()){
				balance = rs.getDouble("balance");
			}
			
			ps2 = conn.prepareStatement(sql2);
			ps2.setInt(2, userId);
			ps2.setDouble(1, balance+num);
			ps2.execute();
			
			value = "往账户中充值了"+num+"，当前账户余额为"+(balance+num);
			ps3 = conn.prepareStatement(sql3);
			ps3.setString(1, value);
			ps3.setString(2, createTime);
			ps3.setInt(3, user.getClient().getId());
			ps3.setInt(4, 1);
			ps3.setInt(5, user.getClient().getId());
			ps3.execute();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			cleanup(null, ps2, null);
			cleanup(conn, ps3, rs);
		}
	}

	public List<Order> getOrderList(User user){
		Connection conn =null;
		PreparedStatement ps =null;
		PreparedStatement ps2 =null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Order> list = new ArrayList<>();
		List<OrderItem> items = new ArrayList<>();
		OrderItem item=null;
		Order order = null;
		Product product = null;
		String sql1= "SELECT * FROM ordermanager.`order` o,order_status os,payment p,payment_method pm,payment_status ps "
				+ "where o.order_status_id=os.id and o.id=p.order_id "
				+ "and p.payment_status_id=ps.id and p.payment_method_id=pm.id  and o.client_id = ?";
		
//		String sql1= "SELECT * FROM ordermanager.`order`,ordermanager.order_status "
//				+ "where ordermanager.`order`.order_status_id=ordermanager.order_status.id and client_id = ?";
		String sql2 ="select oi.id,oi.amount,oi.memo,oi.order_id,p.id,p.code,p.name,p.price,pn.name,c.name "
				+ "from product_nuit pn,catalog c ,order_item oi,product p,ordermanager.`order` o "
				+ "where oi.product_id=p.id and oi.order_id=o.id and p.product_nuit_id=pn.id and p.catalog_id=c.id";
        

		
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql1);
			ps.setInt(1, user.getClient().getId());
			ps.executeQuery();
			rs=ps.getResultSet();
			
			while(rs.next()){
				int id = rs.getInt("o.id");
				String cTime = rs.getString("o.create_datetime");
				String status = rs.getString("os.name");
				String orderStatus = rs.getString("os.name");
				int clientId = rs.getInt("o.client_id");
				String pMedthod = rs.getString("pm.name");
				String pStatus = rs.getString("ps.name");
				Payment payment = new Payment(pMedthod, pStatus);
				order = new Order(id, cTime, null, orderStatus, clientId, payment);
				list.add(order);
				order=null;
			}
			
			
			ps2=conn.prepareStatement(sql2);
			ps2.executeQuery();
			rs2=ps2.getResultSet();
			while(rs2.next()){
				int orderId = rs2.getInt("oi.id");
				int amount = rs2.getInt("oi.amount");
				String memo = rs2.getString("oi.memo");
				int oId = rs2.getInt("oi.order_id");
				int pId = rs2.getInt("p.id");
				String pCode = rs2.getString("p.code");
				String pName = rs2.getString("p.name");
				double pPrice = rs2.getDouble("p.price");
				String pUnit = rs2.getString("pn.name");
				String pCatalog = rs2.getString("c.name");
				product = new Product(pId, pCode, pName, pPrice, pUnit, pCatalog);
				item = new OrderItem(orderId, amount, memo, oId, product);
				
				items.add(item);
			}
			for(Order o : list){
				for(OrderItem oi: items){
					if(o.getId()==oi.getUserId()){
						o.getItems().add(oi);
					}
				}
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(null, ps2, rs2);
			cleanup(conn, ps, rs);
		}
		return list;
	}

	public List<Order> getOrderList2(User user){
		Connection conn =null;
		PreparedStatement ps =null;
		PreparedStatement ps2 =null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Order> list = new ArrayList<>();
		List<OrderItem> items = new ArrayList<>();
		OrderItem item=null;
		Order order = null;
		Product product = null;
		String sql1= "SELECT * FROM ordermanager.`order` o,order_status os,payment p,payment_method pm,payment_status ps "
				+ "where o.order_status_id=os.id and o.id=p.order_id "
				+ "and p.payment_status_id=ps.id and p.payment_method_id=pm.id and os.id=1 and o.client_id = ?";
		
//		String sql1= "SELECT * FROM ordermanager.`order`,ordermanager.order_status "
//				+ "where ordermanager.`order`.order_status_id=ordermanager.order_status.id and client_id = ?";
		String sql2 ="select oi.id,oi.amount,oi.memo,oi.order_id,p.id,p.code,p.name,p.price,pn.name,c.name "
				+ "from product_nuit pn,catalog c ,order_item oi,product p,ordermanager.`order` o "
				+ "where oi.product_id=p.id and oi.order_id=o.id and p.product_nuit_id=pn.id and p.catalog_id=c.id";
        

		
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql1);
			ps.setInt(1, user.getClient().getId());
			ps.executeQuery();
			rs=ps.getResultSet();
			
			while(rs.next()){
				int id = rs.getInt("o.id");
				String cTime = rs.getString("o.create_datetime");
				String status = rs.getString("os.name");
				String orderStatus = rs.getString("os.name");
				int clientId = rs.getInt("o.client_id");
				String pMedthod = rs.getString("pm.name");
				String pStatus = rs.getString("ps.name");
				Payment payment = new Payment(pMedthod, pStatus);
				order = new Order(id, cTime, null, orderStatus, clientId, payment);
				list.add(order);
				order=null;
			}
			
			
			ps2=conn.prepareStatement(sql2);
			ps2.executeQuery();
			rs2=ps2.getResultSet();
			while(rs2.next()){
				int orderId = rs2.getInt("oi.id");
				int amount = rs2.getInt("oi.amount");
				String memo = rs2.getString("oi.memo");
				int oId = rs2.getInt("oi.order_id");
				int pId = rs2.getInt("p.id");
				String pCode = rs2.getString("p.code");
				String pName = rs2.getString("p.name");
				double pPrice = rs2.getDouble("p.price");
				String pUnit = rs2.getString("pn.name");
				String pCatalog = rs2.getString("c.name");
				product = new Product(pId, pCode, pName, pPrice, pUnit, pCatalog);
				item = new OrderItem(orderId, amount, memo, oId, product);
				
				items.add(item);
			}
			for(Order o : list){
				for(OrderItem oi: items){
					if(o.getId()==oi.getUserId()){
						o.getItems().add(oi);
					}
				}
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(null, ps2, rs2);
			cleanup(conn, ps, rs);
		}
		return list;
	}

	public void pay(User user, int code,Order order){
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		PreparedStatement ps5 = null;
		ResultSet rs = null;
		double totalprice = order.getTotalPrice();
		String updateOrderStatus = "UPDATE `ordermanager`.`order` SET `order_status_id`='2' WHERE `id`=?";
		String updatePaymentStatus = "UPDATE `ordermanager`.`payment` SET `payment_status_id`='2' WHERE `id`=?";
		String sql = "select * from `ordermanager`.`order` o,payment p,client c,account a ,transaction t "
				+ "where o.id=p.order_id and o.client_id=c.id and a.client_id=c.id and t.account_id=a.client_id";
		String updateAccount ="UPDATE `ordermanager`.`account` SET `balance`=? WHERE `client_id`=?";
		String insertSql ="INSERT INTO `ordermanager`.`transaction` "
				+ "(`value`, `create_datetime`, `account_id`, `transaction_type_id`, `account_client_id`) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps1 = conn.prepareStatement(updateOrderStatus);
			ps1.setInt(1, order.getId());
			ps1.execute();
			
			ps2 = conn.prepareStatement(sql);
			ps2.executeQuery();
			rs=ps2.getResultSet();
			int paymentId =0;
			int clientId = -1;
			double balance=0;
			while(rs.next()){
				paymentId = rs.getInt("p.id");
				clientId = rs.getInt("c.id");
				balance = rs.getDouble("a.balance");
			}
			
			ps3 = conn.prepareStatement(updatePaymentStatus);
			ps3.setInt(1, paymentId);
			ps3.execute();
			
			ps4 = conn.prepareStatement(updateAccount);
			ps4.setDouble(1, balance-totalprice);
			ps4.setInt(2, clientId);
			ps4.execute();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime =df.format(new Date());
			String value = "消费了"+totalprice+"，当前账户余额为"+(balance-totalprice);
			ps5 = conn.prepareStatement(insertSql);
			ps5.setString(1, value);
			ps5.setString(2, createTime);
			ps5.setInt(3, clientId);
			ps5.setInt(4, 2);
			ps5.setInt(5, clientId);
			ps5.execute();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			cleanup(null, ps5, null);
			cleanup(null, ps4, null);
			cleanup(null, ps3, null);
			cleanup(null, ps2, null);
			cleanup(conn, ps1, rs);
		}
		
	}

	public double showBalance(User user){
		double balance = 0;
		Connection conn=null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		String sql = "select balance from account where client_id=?";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getClient().getId());
			ps.executeQuery();
			rs = ps.getResultSet();
			while(rs.next()){
				balance = rs.getDouble("balance");
			}
			return balance;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return balance;
	}
	
}
