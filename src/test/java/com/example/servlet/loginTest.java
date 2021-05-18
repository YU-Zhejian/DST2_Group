package com.example.servlet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.io.IOException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;

public class loginTest {

	private users servlet;

	private HttpServletRequest mockRequest;

	private HttpServletResponse mockResponse;


	@Before

	public void setUp() {

		servlet = new users();


		mockRequest = createMock(HttpServletRequest.class);                          //加载

		mockResponse = createMock(HttpServletResponse.class);

	}

	@After

	public void tearDown() {

		verify(mockRequest);                //验证

		verify(mockResponse);

	}

	@Transactional
	@Test

	public void test() {


		mockRequest.getParameter("username");

		expectLastCall().andReturn("alle");//传入参数


		mockRequest.getParameter("password");

		expectLastCall().andReturn("12345");//传入参数


		replay(mockRequest);                                           //回放

		replay(mockResponse);

		try {

			servlet.doPost(mockRequest, mockResponse);

		} catch (ServletException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}


		//调用

		assertTrue(true);


	}
}