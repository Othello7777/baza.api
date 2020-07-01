package baza.api.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import baza.api.sql.Biblioteka;

public class JettyServer
{
	private Server server;

	public void start() throws Exception
	{
		server = new Server();
		ServerConnector connector = new ServerConnector(server);

		connector.setPort(8090); // ustawienie portu serwera

		server.setConnectors(new Connector[]
		{ connector });
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        // dodanie serwletów
        servletHandler.addServletWithMapping(WyswietlCzytelnikowServlet.class, "/wyswietlczytelnikow");
        servletHandler.addServletWithMapping(WyswietlCzytelnikowServlet.class, "/wc");
        servletHandler.addServletWithMapping(WyswietlKsiazkiServlet.class, "/wyswietlksiazki");
        servletHandler.addServletWithMapping(WyswietlKsiazkiServlet.class, "/wk");
        servletHandler.addServletWithMapping(DodajCzytelnikaServlet.class, "/dodajczytelnika");
        servletHandler.addServletWithMapping(DodajCzytelnikaServlet.class, "/dc");
        servletHandler.addServletWithMapping(DodajKsiazkeServlet.class, "/dodajksiazke");
        servletHandler.addServletWithMapping(DodajKsiazkeServlet.class, "/dk");
        servletHandler.addServletWithMapping(UsunCzytelnikaServlet.class, "/usunczytelnika");
        servletHandler.addServletWithMapping(UsunCzytelnikaServlet.class, "/uc");
        servletHandler.addServletWithMapping(UsunKsiazkeServlet.class, "/usunksiazke");
        servletHandler.addServletWithMapping(UsunKsiazkeServlet.class, "/uk");

        Biblioteka.getInstance().createTables(); // inicializacja tabel bazy danych

        server.start();
		server.join();
	}

	public static void main(String... args) throws Exception
	{
		new JettyServer().start();
	}
}
