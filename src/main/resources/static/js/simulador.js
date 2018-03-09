$( document ).ready(function() {
    
	$('#fecha').datepicker({
		format: 'mm/dd/yyyy',
		minViewMode: '09/03/2018'
	   	});
	 $("#fecha").datepicker("option", "minDate", new Date());
	
	$('#calcular').click(function(){	
		console.log("calcular"); 
		table.ajax.reload();	 
	});
	$('#limpiar').click(function(){	
		console.log("limpiar"); 
		$('#importe').val("");
        $('#plazo').val("");
        $('#tasa').val("");
        $('#fecha').val("");	 
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
            { "data": "cuotaPago", render: $.fn.dataTable.render.number( ',', '.', 2, 'S/.' ) },
            { "data": "capitalPendiente", render: $.fn.dataTable.render.number( ',', '.', 2, 'S/.' ) }            
        ],
        "select": true
    } );
})