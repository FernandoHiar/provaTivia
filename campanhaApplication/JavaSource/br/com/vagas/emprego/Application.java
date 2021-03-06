package br.com.vagas.emprego;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.vagas.emprego.service.Servico;
import br.com.vagas.emprego.utils.DateUtils;
import br.com.vagas.emprego.vo.CampanhaVO;

public class Application {
	
	public static void main(String[] args) {
		String json = "";
		try {
			json = new Servico().doGet("http://192.168.15.16:8080/campanhaServer/CampanhasDAO/consultarCampanhas");
			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<CampanhaVO>>() {
			}.getType();
			List<CampanhaVO> campanhas = gson.fromJson(json, listType);
			CampanhaVO campanha = new CampanhaVO();
			campanha.setDescricao("ttttt");
			campanha.setDataInicial(DateUtils.converterData(Calendar.getInstance().getTime()));
			campanha.setDataFinal(DateUtils.converterData(Calendar.getInstance().getTime()));
			CampanhaVO novaCampanha = regraCadastramento(campanhas, campanha);
			if (novaCampanha != null) {
				Gson parser = new Gson();
				String jasonFmr = parser.toJson(novaCampanha);
				Map<String, String> params = new HashMap<String, String>();
				params.put("", jasonFmr);
				json = new Servico().doPost("http://192.168.15.16:8080/campanhaServer/CampanhasDAO/gravarCampanha",
						params, "UTF-8");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static CampanhaVO regraCadastramento(List<CampanhaVO> campanhas,CampanhaVO novaCampanha) throws ParseException {

		if (campanhas != null && !campanhas.isEmpty()) {
			Calendar calendario = Calendar.getInstance();
			for (CampanhaVO campanha : campanhas) {
				if (getDate(novaCampanha.getDataFinal()).compareTo(getDate(campanha.getDataFinal())) == 0) {
					calendario.setTime(novaCampanha.getDataFinal());
					calendario.add(Calendar.DAY_OF_MONTH, 1);
					novaCampanha.setDataFinal(DateUtils.converterData(calendario.getTime()));
					regraCadastramento(campanhas, novaCampanha);
				}
			}
		}

		return novaCampanha;
	}
	
	private static Date getDate(Date data) throws ParseException {
		Calendar calenadario = Calendar.getInstance();
		calenadario.setTime(data);
		 String dataFormt = calenadario.get(Calendar.DAY_OF_MONTH) + "/" + calenadario.get(Calendar.MONTH) + "/" + calenadario.get(Calendar.YEAR);
		 Date date = DateUtils.converterDataFormato(dataFormt,"dd/MM/yyyy");
		 return date;
	}
	
}
