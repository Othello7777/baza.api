package baza.api.sql;

public class Wypozyczenie
{
	private int idKsiazka;
	private int idCzyletnik;

	public int getIdKsiazka()
	{
		return idKsiazka;
	}

	public void setIdKsiazka(int idKsiazka)
	{
		this.idKsiazka = idKsiazka;
	}

	public int getIdCzyletnik()
	{
		return idCzyletnik;
	}

	public void setIdCzyletnik(int idCzyletnik)
	{
		this.idCzyletnik = idCzyletnik;
	}

	public Wypozyczenie()
	{
	}

	public Wypozyczenie(int idKsiazka, int idCzyletnik)
	{
		this.idKsiazka = idKsiazka;
		this.idCzyletnik = idCzyletnik;
	}

}
