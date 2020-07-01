package baza.api.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsunCzytelnikaServlet extends BazaServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String[] parametry =
		{"id"};
		if (!validate(parametry, request, response))
		{
			return;
		}
		int i = 0;
		int id = Integer.parseInt(request.getParameter(parametry[i++]));
		b.deleteCzytelnik(id);
	}
}
