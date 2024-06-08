package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.UserManager;
import com.model.Users;

/**
 * Servlet implementation class LoginService
 */
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users U = new Users();
		U.setUsername(username);
		U.setPassword(password);
		UserManager UM = new UserManager();
		String ack = "";
		try
		{
			ack = UM.validateCredentials(U);
		}catch(Exception e)
		{
			ack = e.getMessage();
		}
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println(ack);
		//RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		//rd.include(request, response);
		
		//#Method-1
		//if(ack!="")
		//response.sendRedirect("home.jsp?user=" +ack);
		//else
			//response.sendRedirect("validationfailed.jsp");
		
		//#Method 2
		if(ack!="") {
			request.setAttribute("user",ack);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("validationfailed.jsp");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
