$( document ).ready(function() {
    
	$('#fecha').datepicker({
		dateFormat: 'mm/dd/yyyy',
	    minDate: 0,
	    monthNames: [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre" ]
	});
	 $("#fecha").datepicker("option", "minDate", new Date(2018, 1 - 1, 1));
	
	$('#calcular').click(function(){	
		console.log("calcular"); 
		table.ajax.reload();	 
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