package com.danielcotter.officeprank.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danielcotter.officeprank.web.config.Config;
import com.danielcotter.officeprank.web.json.DaemonRequest;
import com.danielcotter.officeprank.web.json.DaemonResponse;
import com.danielcotter.officeprank.web.util.EncryptionUtil;

@WebServlet("/daemonstatus")
public class DaemonServlet extends BaseServlet {

	private static final long serialVersionUID = 1202515381953737113L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String tryHash = null;
		PrintWriter out = response.getWriter();

		DaemonRequest daemonRequest = mapper.readValue(
				request.getParameter("json"), DaemonRequest.class);
		DaemonResponse daemonResponse = new DaemonResponse(armed);

		if (daemonRequest == null
				|| alreadyHashed.contains(daemonRequest.getTimestamp()))
			return;

		try {
			tryHash = EncryptionUtil.shaHash(daemonRequest.getTimestamp()
					+ Config.getSecret());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (tryHash == null || !tryHash.equals(daemonRequest.getHash()))
			return;

		alreadyHashed.add(daemonRequest.getTimestamp());
		response.setContentType("application/json");
		out.println(mapper.writeValueAsString(daemonResponse));
		out.flush();
		out.close();
	}
}
