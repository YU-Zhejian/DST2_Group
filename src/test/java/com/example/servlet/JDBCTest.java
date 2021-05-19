package com.example.servlet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;

public class JDBCTest {

	private JDBC servlet;
	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;

	@Before
	public void setUp() {

		servlet = new JDBC();

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

		servlet.execute("COLLECT * FROM drug");
		servlet.result("COLLECT * FROM drug");
		assertTrue(true);
	}
}