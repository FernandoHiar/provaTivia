package br.vaga.emprego.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;

import br.vaga.emprego.conexao.Conexao;
import br.vaga.emprego.dto.CampanhaDTO;

@Path("CampanhasDAO")
public class CampanhasDAO extends Conexao {
	
	private Connection conexao;
	
	@GET
	@Produces("text/plain")
	@Path("consultarCampanhas")
	public String consultarCampanhas() throws SQLException {
		List<CampanhaDTO> campanhas = new ArrayList<CampanhaDTO>();
		try {
			conexao = conectarBase();
			PreparedStatement statement =  conexao.prepareStatement("Select * from tbl_campanha");
			ResultSet resultado =  statement.executeQuery();
		
			if(resultado != null) {
				while (resultado.next()) {
					CampanhaDTO campanha = new CampanhaDTO();
					campanha.setIdentificador(resultado.getInt("identificador"));
					campanha.setDescricao(resultado.getString("descricao"));
					campanha.setDataInicial(resultado.getDate("dataInicial"));
					campanha.setDataFinal(resultado.getDate("dataFinal"));
					campanhas.add(campanha);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		}
		
		Gson json = new Gson();
		String response = json.toJson(campanhas);
		
		
		return response;
	}
	
	
	@POST
	@Produces("text/plain")
	//@Consumes("text/plain")
	@Path("gravarCampanha")
	public String gravarCampanha(String parametros) {
		
		try {
	
			conexao = conectarBase();
			
			PreparedStatement pstm2 = conexao
					.prepareStatement("select identificador from tbl_campanha order by identificador desc");
			
			ResultSet res = pstm2.executeQuery();
			int identificador= 1;
			if(res.next()) {
				identificador = res.getInt(1);
				identificador++;
			}
			
			PreparedStatement statement =  conexao.prepareStatement("insert into tbl_campanha(identificador,descricao,dataInicial,dataFinal)"
					+ " values(?,?,?,?)");
			 Gson gson = new Gson();
			 CampanhaDTO campanhaDTO = gson.fromJson(parametros,CampanhaDTO.class);
			 statement.setInt(1,identificador);
	         statement.setString(2, campanhaDTO.getDescricao());
	         statement.setDate(3, campanhaDTO.getDataInicial());
	         statement.setDate(4, campanhaDTO.getDataFinal());
			
			int retorno =  statement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "<status>ok</status>";
		
	}
	


}
