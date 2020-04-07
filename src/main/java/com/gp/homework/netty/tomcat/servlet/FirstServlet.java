package com.gp.homework.netty.tomcat.servlet;

import com.gp.homework.netty.tomcat.http.CNHttpRequest;
import com.gp.homework.netty.tomcat.http.CNHttpResponse;
import com.gp.homework.netty.tomcat.http.CNHttpServlet;

public class FirstServlet extends CNHttpServlet {

	public void doGet(CNHttpRequest request, CNHttpResponse response) throws Exception {
		this.doPost(request, response);
	}

	public void doPost(CNHttpRequest request, CNHttpResponse response) throws Exception {
		response.write("This is First Serlvet");
	}

}
