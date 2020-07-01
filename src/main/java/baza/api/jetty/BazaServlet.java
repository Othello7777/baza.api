package baza.api.jetty;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import baza.api.sql.Biblioteka;

public class BazaServlet extends HttpServlet
{
	protected final Biblioteka b = Biblioteka.getInstance();

	private final ObjectMapper mapper = new ObjectMapper();

	protected boolean validate(String[] parametry, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		for (String parametr : parametry)
		{
			if (request.getParameter(parametr) == null)
			{
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Brakuje parametru: " + parametr);
				return false;
			}
		}
		return true;
	}

	protected void setResponse(HttpServletResponse response, Object value) throws IOException
	{
		response.setContentType("application/json");
		response.getWriter().println(mapper.writeValueAsString(value));
	}
}
