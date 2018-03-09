$( document ).ready(function() {
	 $("#modalsend").prop('disabled', true);
	$('#fecha').datepicker({
		format: 'dd/mm/yyyy',
		minViewMode: '09/03/2018'
	   	});
	 $("#fecha").datepicker("option", "minDate", new Date());
	
	$('#calcular').click(function(){	
		console.log("calcular"); 
		
		
		setTimeout(function(){
			table.ajax.reload();
		  },30000)
		
		if(table.cells.length>0){
			$("#modalsend").prop('disabled', false);
		}
		
			 
	});
	$('#limpiar').click(function(){	
		console.log("limpiar"); 
		$('#importe').val("");
        $('#plazo').val("");
        $('#tasa').val("");
        $('#fecha').val("");
        $("#modalsend").prop('disabled', true);
	});
	$('#enviar').click(function(){	
		console.log("enviar"); 
		var correo=$('#correo').val();
		
		if(correo.length>0){
			var data={	"to":correo,
					"subject":"simulacion",
					"text":"Adjunto la simulacion realizada gracias saludos"
					};
			
			$.ajax({
				  type: "POST",
				  url: "./enviar",
				  data: JSON.stringify(data),
				  contentType: "application/json",
				  success: function(result) {					  
					  if(result.sEcho=="true"){
						  $("#myModal").modal('toggle');
					  }
				  },
				    error: function() {
				    	
				    }
				});
		}
		
		

		
	});
	
	var table= $('#tblcronograma').DataTable( {
    	"bJQueryUI":true,
        "processing": true,
        "responsive": true,
        "bSort":false,
        "bPaginate":true,
        "dom": '<"row" <"col-sm-12" t> ><"row" <"col-sm-3"l> <"col-sm-4"i> <"col-sm-5"p>>',
        "iDisplayLength": 10,
        "sPaginationType" : 'full_numbers',
        "ajax": {
            "url": './calcular',
            "type":'GET',
            "data": function (d) {
            	console.log("llamadaAjax"); 
                d.filtroImporte = $('#importe').val();
                d.filtroPlazo= $('#plazo').val();
                d.filtroTasa= $('#tasa').val();
                d.filtroFecha= $('#fecha').val();
            }
          },
        "serverSide": true,
        "columns": [
            { "data": "nroCouta" },
            { "data": "fecha" },
            { "data": "interes", render: $.fn.dataTable.render.number( ',', '.', 2, 'S/.' ) },
            { "data": "amortizacion", render: $.fn.dataTable.render.number( ',', '.', 2, 'S/.' ) },
            { "data": "totalAmortizado", render: $.fn.dataTable.render.number( ',', '.', 2, 'S/.' ) },            
            { "data": "capitalPendiente", render: $.fn.dataTable.render.number( ',', '.', 2, 'S/.' ) },
            { "data": "cuotaPago", render: $.fn.dataTable.render.number( ',', '.', 2, 'S/.' ) }
        ],
        "select": true
    } );
})