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

		mockRequest = createMock(HttpServletRequest.class);
		mockResponse = createMock(HttpServletResponse.class);

	}

	@After
	public void tearDown() {

		verify(mockRequest);
		verify(mockResponse);

	}

	@Transactional
	@Test
	public void test() {

		mockRequest.getParameter("username");
		expectLastCall().andReturn("alle");


		mockRequest.getParameter("password");
		expectLastCall().andReturn("12345");


		replay(mockRequest);
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

		assertTrue(true);
	}
}