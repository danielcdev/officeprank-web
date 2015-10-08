package com.danielcotter.officeprank.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danielcotter.officeprank.web.config.Config;
import com.danielcotter.officeprank.web.json.ClientRequest;
import com.danielcotter.officeprank.web.json.ClientResponse;
import com.danielcotter.officeprank.web.util.EncryptionUtil;

@WebServlet("/clientstatus")
public class ClientServlet extends BaseServlet {

	private static final long serialVersionUID = 3971145963722368761L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String tryHash = null;
		PrintWriter out = response.getWriter();

		ClientRequest clientRequest = mapper.readValue(
				request.getParameter("json"), ClientRequest.class);
		ClientResponse clientResponse = new ClientResponse(true);

		if (clientRequest == null
				|| (alreadyHashed.contains(clientRequest.getTimestamp())))
			return;

		try {
			tryHash = EncryptionUtil.shaHash(clientRequest.getTimestamp()
					+ Config.getSecret());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (tryHash == null || !tryHash.equals(clientRequest.getHash()))
			return;

		armed = clientRequest.getState();

		alreadyHashed.add(clientRequest.getTimestamp());
		response.setContentType("application/json");
		out.println(mapper.writeValueAsString(clientResponse));
		out.flush();
		out.close();
	}
}
