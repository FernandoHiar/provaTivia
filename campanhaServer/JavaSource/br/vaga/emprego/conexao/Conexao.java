package br.vaga.emprego.conexao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.util.codec.binary.StringUtils;

import br.vaga.emprego.utils.ConexaoUtils;

public class Conexao {

	public  Connection conectarBase() throws IOException, ClassNotFoundException, SQLException {
		Connection conexao = criarConexao();
	
		return conexao;
	}
	
	private  Connection criarConexao() throws SQLException, ClassNotFoundException {
		Class.forName(ConexaoUtils.DRIVER);
		StringBuilder conexaoBase = new StringBuilder();
		conexaoBase.append(ConexaoUtils.PROTOCOLO).append(ConexaoUtils.DOIS_PONTOS)
				.append(ConexaoUtils.BASE).append(ConexaoUtils.DOIS_PONTOS)
				.append(ConexaoUtils.DUAS_BARRAS).append(ConexaoUtils.IP)
				.append(ConexaoUtils.PONTO_VIRGULA).append(ConexaoUtils.DATA_BASE_NAME)
				.append(ConexaoUtils.DATA_BASE).append(ConexaoUtils.PONTO_VIRGULA);
		return DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=netshoes;", "netshoes",
				"123456");
	}
	
	
}
