package com.bbva.simulador.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.bbva.simulador.domain.Contenido;
import com.bbva.simulador.domain.Fila;
import com.bbva.simulador.domain.Request;
import com.bbva.simulador.domain.Response;


  
@Controller
public class WebController {
	
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String home(){    	
        return "simulador";
    }
    


	@RequestMapping(value="/calcular",method = RequestMethod.GET)
    public @ResponseBody Contenido calcular(HttpServletRequest httpRequest, Locale locale){
		
		Contenido resp=new Contenido();
		resp.setData(new ArrayList<Fila>());
		
		if ((httpRequest.getParameter("filtroImporte") != null 
				&& httpRequest.getParameter("filtroImporte").length()>0) &&
				(httpRequest.getParameter("filtroTasa") != null 
				&& httpRequest.getParameter("filtroTasa").length()>0) &&
				(httpRequest.getParameter("filtroPlazo") != null 
				&& httpRequest.getParameter("filtroPlazo").length()>0)
				&&
				(httpRequest.getParameter("filtroFecha") != null 
				&& httpRequest.getParameter("filtroFecha").length()>0)) {
		   	 Request request2=new Request();
		     request2.setImporte(Integer.parseInt(httpRequest.getParameter("filtroImporte")));
		     request2.setTasa(Double.parseDouble(httpRequest.getParameter("filtroTasa")));
		     request2.setPlazo(Integer.parseInt(httpRequest.getParameter("filtroPlazo")));
		     request2.setFecha(httpRequest.getParameter("filtroFecha"));
			
		     RestTemplate restTemplate = new RestTemplate();
		     HttpEntity<Request> httpRequestRest = new HttpEntity<>(request2);
		     ResponseEntity<Response> response = restTemplate
		       .exchange("http://localhost:8080/simulador", HttpMethod.POST, httpRequestRest, Response.class);
		     resp.setData(response.getBody().getData());
		     resp.recordsTotal=resp.getData().size();
		}	    	
        return resp;
    }
}