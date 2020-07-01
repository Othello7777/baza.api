package baza.api.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Biblioteka
{
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:biblioteka.db";
	private static final Biblioteka INSTANCE = new Biblioteka();

	private Connection connection;
	private Statement statement;

	public static final Biblioteka getInstance()
	{
		return INSTANCE;
	}

	public Biblioteka()
	{
		try
		{
			Class.forName(Biblioteka.DRIVER);
		} catch (ClassNotFoundException e)
		{
			System.err.println("Brak sterownika JDBC");
			e.printStackTrace();
		}

		try
		{
			connection = DriverManager.getConnection(DB_URL);
			statement = connection.createStatement();
		} catch (SQLException e)
		{
			System.err.println("Problem z otwarciem polaczenia");
			e.printStackTrace();
		}
	}

	public void createTables()
	{
		String createCzytelnicy = "CREATE TABLE IF NOT EXISTS czytelnicy (id_czytelnika INTEGER PRIMARY KEY AUTOINCREMENT, imie varchar(255), nazwisko varchar(255), pesel int)";
		String createKsiazki = "CREATE TABLE IF NOT EXISTS ksiazki (id_ksiazki INTEGER PRIMARY KEY AUTOINCREMENT, tytul varchar(255), autor varchar(255))";
		String createWypozyczenia = "CREATE TABLE IF NOT EXISTS wypozyczenia (id_wypozycz INTEGER PRIMARY KEY AUTOINCREMENT, id_czytelnika int, id_ksiazki int)";
		try
		{
			statement.execute(createCzytelnicy);
			statement.execute(createKsiazki);
			statement.execute(createWypozyczenia);
		} catch (SQLException e)
		{
			System.err.println("Blad przy tworzeniu tabeli");
			e.printStackTrace();
		}
	}

	public boolean insertCzytelnik(String imie, String nazwisko, String pesel)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("insert into czytelnicy values (NULL, ?, ?, ?);");
			prepStmt.setString(1, imie);
			prepStmt.setString(2, nazwisko);
			prepStmt.setString(3, pesel);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy wstawianiu czytelnika");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertKsiazka(String tytul, String autor)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("insert into ksiazki values (NULL, ?, ?);");
			prepStmt.setString(1, tytul);
			prepStmt.setString(2, autor);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy wstawianiu ksiazki");
			return false;
		}
		return true;
	}

	public boolean insertWypozycz(int idCzytelnik, int idKsiazka)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("insert into wypozyczenia values (NULL, ?, ?);");
			prepStmt.setInt(1, idCzytelnik);
			prepStmt.setInt(2, idKsiazka);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy wypozyczaniu");
			return false;
		}
		return true;
	}

	public boolean deleteCzytelnik(int id)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("delete from czytelnicy where id_czytelnika = ?;");
			prepStmt.setInt(1, id);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy usuwaniu czytelnika");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteKsiazka(int id)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("delete from ksiazki where id_ksiazki = ?;");
			prepStmt.setInt(1, id);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy usuwaniu ksiazki");
			return false;
		}
		return true;
	}
	
	public boolean zmienCzytelnikaImie(int id, String imie)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("update czytelnicy set imie = ? where id_czytelnika = ?;");
			prepStmt.setString(1, imie);
			prepStmt.setInt(2, id);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy zmianie imienia czytelnika");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean zmienCzytelnikaNazwisko(int id, String nazwisko)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("update czytelnicy set nazwisko = ? where id_czytelnika = ?;");
			prepStmt.setString(1, nazwisko);
			prepStmt.setInt(2, id);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy zmianie nazwiska czytelnika");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean zmienCzytelnikaPesel(int id, String pesel)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("update czytelnicy set pesel = ? where id_czytelnika = ?;");
			prepStmt.setString(1, pesel);
			prepStmt.setInt(2, id);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy zmianie peselu czytelnika");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean zmienKsiazkeTytul(int id, String tytul)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("update ksiazki set tytul = ? where id_ksiazki = ?;");
			prepStmt.setString(1, tytul);
			prepStmt.setInt(2, id);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy zmianie tytulu ksiazki");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean zmienKsiazkeAutor(int id, String autor)
	{
		try
		{
			PreparedStatement prepStmt = connection.prepareStatement("update ksiazki set autor = ? where id_ksiazki = ?;");
			prepStmt.setString(1, autor);
			prepStmt.setInt(2, id);
			prepStmt.execute();
		} catch (SQLException e)
		{
			System.err.println("Blad przy zmianie autora ksiazki");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Czytelnik> selectCzytelnicy()
	{
		List<Czytelnik> czytelnicy = new LinkedList<Czytelnik>();
		try
		{
			ResultSet result = statement.executeQuery("select * from czytelnicy"); //executeQuery - wykonanie zapytania
			int id;
			String imie, nazwisko, pesel;
			while (result.next())
			{
				id = result.getInt("id_czytelnika");
				imie = result.getString("imie");
				nazwisko = result.getString("nazwisko");
				pesel = result.getString("pesel");
				czytelnicy.add(new Czytelnik(id, imie, nazwisko, pesel));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		return czytelnicy;
	}

	public List<Ksiazka> selectKsiazki()
	{
		List<Ksiazka> ksiazki = new LinkedList<Ksiazka>();
		try
		{
			ResultSet result = statement.executeQuery("select * from ksiazki");
			int id;
			String tytul, autor;
			while (result.next())
			{
				id = result.getInt("id_ksiazki");
				tytul = result.getString("tytul");
				autor = result.getString("autor");
				ksiazki.add(new Ksiazka(id, tytul, autor));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		return ksiazki;
	}

	public void closeConnection()
	{
		try
		{
			connection.close();
		} catch (SQLException e)
		{
			System.err.println("Problem z zamknieciem polaczenia");
			e.printStackTrace();
		}
	}
}
