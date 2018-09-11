package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Employee;

public class EmployeeDao {

	public List<Employee> ctnnd() {
		List<Employee> list = new ArrayList<Employee>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 3建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5执行sql语句并得到结果
			String sql = "select e.*,d.name as dName from employee as e left join " + "department as d on e.d_id=d.id";
			ResultSet rs = stat.executeQuery(sql);
			// 对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));
				emp.setDep(dep);
				list.add(emp);
			}
			// 7关闭
			rs.close();
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public boolean add(Employee emp) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 3建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5执行sql语句并得到结果
			int rs = stat.executeUpdate("insert into employee(name,sex,age,d_id) values('" + emp.getName() + "','"
					+ emp.getSex() + "'," + emp.getAge() + "," + emp.getDep().getId() + ")");
			// 对结果集进行处理
			if (rs > 0) {
				flag = true;
			}

			// 7关闭

			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	public Employee search(int id) {
		Employee emp = new Employee();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");

			Statement stat = conn.createStatement();
			String sql = "select * from employee where id=" + id;
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				emp.setDep(dep);

			}
			rs.close();
			stat.close();
			conn.close();
		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emp;
	}

	public boolean update(Employee emp) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 3建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			// 4建立statement sql 语句执行器

			String sql = "update employee set name=?,sex=?,age=?,d_id=? where id =?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
			pstat.setInt(4, emp.getDep().getId());
			pstat.setInt(5, emp.getId());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
			pstat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(int id) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");

			Statement stat = conn.createStatement();

			int rs = stat.executeUpdate("delete from employee where id = " + id + "");
			if (rs > 0) {
				flag = true;
			}

			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean deleteBatch(String ids) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");

			Statement stat = conn.createStatement();

			int rs = stat.executeUpdate("delete from employee where id in (" + ids + ")");
			if (rs > 0) {
				flag = true;
			}

			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}

	public List<Employee> search(String ids) {
		List<Employee> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");

			Statement stat = conn.createStatement();
			String sql = "select * from employee where id in (" + ids + ")";
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				emp.setDep(dep);

			}
			rs.close();
			stat.close();
			conn.close();
		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public boolean updateBatch1(String ids, Employee emp) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 3建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			// 4建立statement sql 语句执行器

			String sql = "update employee set name=?,sex=?,age=?,d_id=? where id in(" + ids + ")";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
			pstat.setInt(4, emp.getDep().getId());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
			pstat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public boolean updateBatch2(List<Employee> list) {
		boolean flag = false;
		PreparedStatement pstat = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 3建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			// 4建立statement sql 语句执行器
			for (int i = 0; i < list.size(); i++) {
				Employee emp = list.get(i);
				String sql = "update employee set name=?,sex=?,age=?,d_id=? where id =?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, emp.getName());
				pstat.setString(2, emp.getSex());
				pstat.setInt(3, emp.getAge());
				pstat.setInt(4, emp.getDep().getId());
				pstat.setInt(5, emp.getId());
				int rs = pstat.executeUpdate();
				if (rs > 0) {
					flag = true;
				}
			}
			pstat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}
	public List<Employee> sousuo(int bianliang) {
		List<Employee> list = new ArrayList<Employee>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 3建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5执行sql语句并得到结果
			String sql = "select * from employee e left join department d on e.d_id=d.id  ORDER BY e.id ASC LIMIT "+bianliang+",2";
			ResultSet rs = stat.executeQuery(sql);
			// 对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("e.id"));
				emp.setName(rs.getString("e.name"));
				emp.setSex(rs.getString("e.sex"));
				emp.setAge(rs.getInt("e.age"));
				Department dep = new Department();
				dep.setId(rs.getInt("e.d_id"));
				dep.setName(rs.getString("d.name"));
				emp.setDep(dep);
				list.add(emp);
			}
			// 7关闭
			rs.close();
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public int hangshu() {
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 3建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5执行sql语句并得到结果
			String sql = "select count(id) from employee";
			ResultSet rs = stat.executeQuery(sql);
			// 对结果集进行处理
			while (rs.next()) {
				count=rs.getInt("count(id)");
			}
			// 7关闭
			rs.close();
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  count;
	}

}