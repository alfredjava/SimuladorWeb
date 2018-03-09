package com.bbva.simulador.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.simulador.domain.Contenido;
import com.bbva.simulador.domain.Fila;
import com.bbva.simulador.domain.MailObject;
import com.bbva.simulador.domain.Request;
import com.bbva.simulador.pdf.GeneratePdfReport;
import com.bbva.simulador.service.ConsumerService;
import com.bbva.simulador.service.EmailService;


@Scope  
@Controller
public class WebController {
	
	
    @Autowired
    public EmailService emailService;
    @Autowired
    public ConsumerService consumerService;

    @Value("${attachment.invoice}")
    private String attachmentPath;

    @Autowired
    public SimpleMailMessage template;
    private List<Fila> listaFila;
	



	public List<Fila> getListaFila() {
		return listaFila;
	}


	public void setListaFila(List<Fila> listaFila) {
		this.listaFila = listaFila;
	}


	@RequestMapping(value="/",method = RequestMethod.GET)
    public String home(){    	
        return "simulador";
    }
    

    @SuppressWarnings("static-access")
	@RequestMapping(value = "/enviar", method = RequestMethod.POST)
    public @ResponseBody Contenido createMailWithAttachment(Model model,
                            @RequestBody MailObject mailObject) {

    	Contenido c=new Contenido();
    	c.sEcho="false";
	     //Enviar Email
    	try {
   	     if(this.getListaFila()!=null && this.getListaFila().size()>0){
		     GeneratePdfReport generatePdfReport=new GeneratePdfReport();
		     ByteArrayInputStream byteArray=generatePdfReport.simulacionReport(this.getListaFila());
//		     MailObject mailObject =new MailObject();		     
//		     mailObject.setSubject("Simulacion");
//		     mailObject.setTo("alfredfis@gmail.com");
//		     mailObject.setText("Envio la Simulacion");
		  
		     
		        emailService.sendMessageWithAttachment(
		                mailObject.getTo(),
		                mailObject.getSubject(),
		                mailObject.getText(),
		                byteArray
		        );
	     }
   	     c.sEcho="true";
		} catch (Exception e) {
			c.sEcho="false";
		}
    	
    	

	    return c;

       
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
		     double tasa=Double.parseDouble(httpRequest.getParameter("filtroTasa"))/100;
		     request2.setTasa(tasa);
		     request2.setPlazo(Integer.parseInt(httpRequest.getParameter("filtroPlazo")));
		     request2.setFecha(httpRequest.getParameter("filtroFecha"));
			
		     
		     resp.setData(consumerService.obtenerSimulacion(request2));
		     resp.recordsTotal=(resp.getData()!=null)?resp.getData().size():0;
		     this.setListaFila(resp.getData());
		     
		}	    	
        return resp;
    }
}