package br.com.academia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;

public class ConexaoFB {
	
	
	private String url = "jdbc:mysql://localhost:3303/academia";
	private String user = "root";
	private String pass = "masteraoc";
	
	//private DataSource dataSource; // Interface responsável em criar o pool de conexões (fonte de dados)
	/*
	public ConexaoFB(){
		PGPoolingDataSource pool = new PGPoolingDataSource();
		pool.setUrl("jdbc:postgresql://127.0.0.1:5432/academia");
		pool.setUser("postgres");
		pool.setPassword("masteraoc");
		pool.setMaxConnections(50);
		this.dataSource = pool;
	}
	*/
	
	public Connection getConexao()throws SQLException, ClassNotFoundException{
		
		//Class.forName("org.postgresql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pass);
		//Connection con = dataSource.getConnection();
		System.out.println("Conectado ao bd academia com sucesso");
		return con;
	}

}
