package com.danielcotter.officeprank.web.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1163127896685573599L;
	protected static final ObjectMapper mapper = new ObjectMapper();
	protected static List<String> alreadyHashed = new ArrayList<String>();
	protected static Boolean armed = false;
}
