function eliminar(id, urleliminar, urlfinal, idtarea) {
	Swal.fire({
		title: '¿Estas seguro de eliminar?',
		text: "No se podra deshacer esta acción!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si, Eliminar!',
		cancelButtonText: 'Cancelar',
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: urleliminar + id,
				succes: function(res) {
					console.log(res);
				}
			});
			Swal.fire(
				'Eliminado!',
				'El registro ha sido eliminado.',
				'success'
			).then((ok) => {
				if (ok) {
				if(idtarea!=null){
					location.href=urlfinal+idtarea;
				}
				else{
					location.href = urlfinal;
				}
				
			}
		});
}
	})
}

// Add the following code if you want the name of the file appear on select
$(".custom-file-input").on("change", function() {
	var fileName = $(this).val().split("\\").pop();
	$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
//Funcion para modal de variables de formulario
function modalVariableO() {
	let eleccion = document.getElementById("variableSelect")	
	let boton = document.getElementById('GuardarVariable');
	let tabla = document.getElementById('tableVariable');
	tabla.style.display = "table";
	let nombreVar= document.getElementById("nombreVariable");
	let minvalue = document.getElementsByClassName("minval");
	let maxvalue = document.getElementsByClassName("maxval");
	let maxcar = document.getElementsByClassName("maxcar");
	let mincar = document.getElementsByClassName("mincar");
	let requerido = document.getElementsByClassName("required");
	let opciones = document.getElementById("displayOpciones");
	if (eleccion.value == "Input Text" || eleccion.value == "Text Area" || eleccion.value == "Email") {
		for (i = 0; i < 2; i++) {
			minvalue[i].style.display = "none";
			maxvalue[i].style.display = "none";
			maxcar[i].style.display = "table-cell";
			mincar[i].style.display = "table-cell";
			requerido[i].style.display = "table-cell";
		}
		opciones.style.display = "none";
	}
	if (eleccion.value == "CheckBox" || eleccion.value == "RadioCheck" || eleccion.value == "Select") {
		for (i = 0; i < 2; i++) {
			maxcar[i].style.display = "none";
			mincar[i].style.display = "none";
			minvalue[i].style.display = "none";
			maxvalue[i].style.display = "none";
			requerido[i].style.display = "table-cell";
		}
		opciones.style.display = "block";
	}
	if (eleccion.value == "Numero") {
		for (i = 0; i < 2; i++) {
			maxcar[i].style.display = "none";
			mincar[i].style.display = "none";
			requerido[i].style.display = "table-cell";
			minvalue[i].style.display = "table-cell";
			maxvalue[i].style.display = "table-cell";
		}
		opciones.style.display = "none";
	}	
	opciones.value=null;
	nombreVar.value=null;	
	boton.style.display  = "block";
}

//Funcion para editar variable
function modalVariable1(variable) {
	var url = '/formulario/findDetalle/' + variable;
	$.get(url, function(data ) {
		let eleccion = document.getElementById("variableSelect")
		eleccion.value= data.tipoVariable;
		modalVariableO();
		let idVariable= document.getElementById("idVariable");
		let nombreVar= document.getElementById("nombreVariable");		
		let minvalue = document.getElementById("minval");
		let maxvalue = document.getElementById("maxval");
		let maxcar = document.getElementById("maxcar");
		let mincar = document.getElementById("mincar");
		let requerido = document.getElementById("required");
		let opciones = document.getElementById("opcionesVariable");
		idVariable.value=data.id;
		nombreVar.value=data.nombreVariable;
		minvalue.value= data.minimo;
		maxvalue.value= data.maximo;
		maxcar.value= data.maxCaracter;
		mincar.value= data.minCaracter;
		if(data.requerido===true){
			requerido.checked=true;
		}else{
			requerido.checked=false;
		}
		var opcionesVariable="";
		data.opcionesVariables.forEach(element => opcionesVariable+= element.nombre+",");
		opciones.value= opcionesVariable;		
		
		eleccion.style.display= "none";
		//console.log(data);
		//console.log(idVariable.value);
		//console.log(data.opcionesVariables);
		//console.log(data.opcionesVariables[0].nombre);
	})
  .fail(function(data) {
    console.log(data);
    console.log(data.responseText.id);
  })
  
}

function abrirmodal(){
	let tabla = document.getElementById('tableVariable');
	tabla.style.display = "none";
	let eleccion = document.getElementById("variableSelect");
	eleccion.style.display= "block";
	let opciones = document.getElementById("displayOpciones");
	opciones.style.display="none";
	let idVariable= document.getElementById("idVariable");
	idVariable.value=null;
}

