package com.estsoft.emaillist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.DB.MySQLWebDBConnection;
import com.estsoft.emaillist.dao.EmailListDao;
import com.estsoft.emaillist.vo.EmailListVo;

/**
 * Servlet implementation class EmailListServlet
 */
@WebServlet("/el")
public class EmailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	// 이거있음 왜안댐?
	// public EmailListServlet() {
	// super();
	//
	// }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// utf-8
		response.setCharacterEncoding("UTF-8");

		// 요청분석

		String actionName = request.getParameter("a");

		if ("insert".equals(actionName)) {
			String firstName = request.getParameter("fn");
			String lastName = request.getParameter("ln");
			String email = request.getParameter("email");

			EmailListVo vo = new EmailListVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			
			EmailListDao dao = new EmailListDao(new MySQLWebDBConnection());
			dao.insert(vo);
			
			//insert를 하는 페이지에서 계속 F5를 누르면 계속 INSERT가 진행된다 -> 302 redirect를 이용해서 insert가 추가로 되지않도록
			response.sendRedirect("/MVC2/el");
		} else if ("form".equals(actionName)) {
			//servlet에게 jsp 주소를 써준다. webinfo는 보안때문에 접근이 안된다
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/form_emaillist.jsp");
			rd.forward(request, response);
		} else {
			
			// default action 404해버려ㅡㅡ; 나쁜 유저 하지만 우린 착하니깐 list를 여기다 하자!
			// : (list, index)
			EmailListDao dao = new EmailListDao(new MySQLWebDBConnection());
			List<EmailListVo> list = dao.getList();
			
			request.setAttribute("list", list );
			//servlet에게 jsp 주소를 써준다. webinfo는 보안때문에 접근이 안된다
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
			
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<h1>Email List!!! 한 글 !<h1>");
		}

	}
