package baza.api.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DodajCzytelnikaServlet extends BazaServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String[] parametry =
		{ "imie", "nazwisko", "pesel" };
		if (!validate(parametry, request, response))
		{
			return;
		}
		int i = 0;
		String imie = request.getParameter(parametry[i++]);
		String nazwisko = request.getParameter(parametry[i++]);
		String pesel = request.getParameter(parametry[i++]);
		b.insertCzytelnik(imie, nazwisko, pesel);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}
}
