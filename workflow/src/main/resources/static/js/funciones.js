function eliminar(id, urleliminar, urlfinal){	
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
  		url: urleliminar +id,
  		succes: function(res){
  			console.log(res);  			
  		}
  	});
    Swal.fire(
      'Eliminado!',
      'El registro ha sido eliminado.',
      'success'
    ).then((ok)=>{
    	if(ok){
    		location.href=urlfinal;
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

