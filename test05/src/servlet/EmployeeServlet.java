package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.EmployeeDao;
import entity.Department;
import entity.Employee;

public class EmployeeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			if (type == null) {
				show(request, response);
			} else if ("showEmployee".equals(type)) {
				showEmployee(request, response);
			} else if ("add".equals(type)) {
				addEmployee(request, response);
			} else if ("showUpdate".equals(type)) {
				showUpdate(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			}else if ("delete".equals(type)) {
				delete(request, response);
			}else if ("deleteBatch".equals(type)) {
				deleteBatch(request, response);
			}else if ("showUpdateBatch1".equals(type)) {
				showUpdateBatch1(request, response);
			}else if ("updateBatch1".equals(type)) {
				updateBatch1(request, response);
			}else if ("showUpdateBatch2".equals(type)) {
				showUpdateBatch2(request, response);
			}else if ("updateBatch2".equals(type)) {
				updateBatch2(request, response);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void show(HttpServletRequest request, HttpServletResponse response) {
		try {int bianliang=1;
			if(request.getParameter("bianliang")!=null) {
				bianliang=(Integer.parseInt(request.getParameter("bianliang"))); 
			}
			int daitibl=(bianliang-1)*2;
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list = empDao.sousuo(daitibl);
			int count=empDao.hangshu();
			request.setAttribute("emps", list);
			request.setAttribute("count", count);
			request.setAttribute("jiluyeshu",bianliang);
			int xianshiyeshu=xianshiyeshu(bianliang, count);
			request.setAttribute("xianshiyeshu", xianshiyeshu);
			request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.getRequestDispatcher("add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			EmployeeDao ed = new EmployeeDao();
			Employee emp = new Employee();
			Department dep = new Department();
			
			emp.setName(request.getParameter("employeeName"));
			emp.setSex(request.getParameter("employeeSex"));
			emp.setAge(Integer.parseInt(request.getParameter("employeeAge")));
			dep.setId(Integer.parseInt(request.getParameter("employeeDep")));
			emp.setDep(dep);
			ed.add(emp);
			response.sendRedirect("Employee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDao empDao = new EmployeeDao();
			Employee emp = empDao.search(id);
			request.setAttribute("emp", emp);

			request.getRequestDispatcher("update.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {

		try {
			EmployeeDao ed = new EmployeeDao();
			int id = Integer.parseInt(request.getParameter("id"));
			Employee emp = new Employee();
			Department dep = new Department();
			emp.setName(request.getParameter("employeeName"));
			emp.setSex(request.getParameter("employeeSex"));
			emp.setAge(Integer.parseInt(request.getParameter("employeeAge")));
			emp.setId(id);
			dep.setId(Integer.parseInt(request.getParameter("employeeDep")));
			emp.setDep(dep);
			ed.update(emp);
			response.sendRedirect("Employee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {

		try {
			
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDao ed = new EmployeeDao();
			ed.delete(id);
			response.sendRedirect("Employee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void deleteBatch(HttpServletRequest request, HttpServletResponse response) {

		try {
			
			String  ids = request.getParameter("ids");
			EmployeeDao ed = new EmployeeDao();
			ed.deleteBatch(ids);
			response.sendRedirect("Employee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void showUpdateBatch1(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list  = empDao.search(ids);
			request.setAttribute("emp", list.get(0));
			request.setAttribute("ids", ids);

			request.getRequestDispatcher("WEB-INF/updateBatch1.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateBatch1(HttpServletRequest request, HttpServletResponse response) {

		try {
			EmployeeDao ed = new EmployeeDao();
			String ids = request.getParameter("ids");
			Employee emp = new Employee();
			Department dep = new Department();
			emp.setName(request.getParameter("employeeName"));
			emp.setSex(request.getParameter("employeeSex"));
			emp.setAge(Integer.parseInt(request.getParameter("employeeAge")));
			dep.setId(Integer.parseInt(request.getParameter("employeeDep")));
			emp.setDep(dep);
			ed.updateBatch1(ids,emp);
			response.sendRedirect("Employee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void showUpdateBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list  = empDao.search(ids);
			request.setAttribute("list", list);
			request.setAttribute("ids", ids);

			request.getRequestDispatcher("WEB-INF/updateBatch2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateBatch2(HttpServletRequest request, HttpServletResponse response) {

		try {
			Department dep = new Department();
			String emps= request.getParameter("emps");
			String[] array=emps.split(";");
			List<Employee> list = new ArrayList<>();
			for(int i=0;i<array.length;i++) {
				String[] temp=array[i].split(",");
				Employee emp = new Employee();
				emp.setId(Integer.parseInt(temp[0]));
				emp.setName(temp[1]);
				emp.setSex(temp[2]);
				emp.setAge(Integer.parseInt(temp[3]));
				dep.setId(Integer.parseInt(temp[4]));
				emp.setDep(dep);
				list.add(emp);
			}
			
			EmployeeDao ed = new EmployeeDao();
		
			ed.updateBatch2(list);
			response.sendRedirect("Employee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void Employee(HttpServletRequest request, HttpServletResponse response) {

		try {
			EmployeeDao ed = new EmployeeDao();
			Employee emp = new Employee();
			Department dep = new Department();
			emp.setName(request.getParameter("name"));
			emp.setSex(request.getParameter("sex"));
			emp.setAge(Integer.parseInt(request.getParameter("age")));
			dep.setId(Integer.parseInt(request.getParameter("dep")));
			emp.setDep(dep);
			ed.update(emp);
			response.sendRedirect("Employee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
//	public void showsousuo(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			EmployeeDao empDao = new EmployeeDao();
//			int bianliang=Integer.parseInt(request.getParameter("bianiang")); 
//			List<Employee> list = empDao.sousuo(bianliang);
//			request.setAttribute("emps", list);
//			request.setAttribute("jiluyeshu", bianliang);
//			request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public int xianshiyeshu(int jiluyeshu,int count) {
		int xianshiyeshu=jiluyeshu;
		if(xianshiyeshu<=3){
			xianshiyeshu=3;
		}else if(xianshiyeshu>=count-2){
			xianshiyeshu=count-2;
		}
		return xianshiyeshu;
	}
}
