package baza.api.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DodajKsiazkeServlet extends BazaServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String[] parametry =
		{ "nazwa", "autor" };
		if (!validate(parametry, request, response))
		{
			return;
		}
		int i = 0;
		String nazwa = request.getParameter(parametry[i++]);
		String autor = request.getParameter(parametry[i++]);
		b.insertKsiazka(nazwa, autor);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}
}
