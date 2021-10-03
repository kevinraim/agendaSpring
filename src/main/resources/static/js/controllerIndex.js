// Call the dataTables jQuery plugin
$(document).ready(function() {

  cargarUsarios();

});

async function cargarUsarios(){

  const request = await fetch('/api/agenda/all/fecha', {
    method: 'GET',
    headers: {
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    },
  });

  const clientes = await request.json();

  clientesTabla(clientes);
}

async function agregarCliente(){

  let clienteDatos = {};
  clienteDatos.nombre = document.getElementById('inputNombre').value;
  clienteDatos.email = document.getElementById('inputEmail').value;
  clienteDatos.telefono = document.getElementById('inputTelefono').value;
  clienteDatos.precio = document.getElementById('inputPrecio').value;
  clienteDatos.turno = document.getElementById('inputFecha').value;

  if(clienteDatos.nombre == ""){
    alert("El nombre no puede estar vacio");
    return;
  }

  if(clienteDatos.turno == ""){
    alert("La fecha no puede estar vacía");
    return;
  }

  const request = await fetch('/api/agenda', {
    method: 'POST',
    headers: {
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    },
    body: JSON.stringify(clienteDatos)
  });

  location.reload();

}

async function borrarCliente(id){

  if(confirm("¿Está seguro que desea eliminar al cliente?"))
  {
    const request = await fetch('api/agenda/'+id, {
      method: 'DELETE',
      headers: {
        'Accept' : 'application/json',
        'Content-Type' : 'application/json'
      },
    });

    location.reload();
  }
}

async function actualizarCliente(id){

  let clienteDatos = {};
  clienteDatos.nombre = document.getElementById('inputActNombre').value;
  clienteDatos.email = document.getElementById('inputActEmail').value;
  clienteDatos.telefono = document.getElementById('inputActTelefono').value;
  clienteDatos.precio = document.getElementById('inputActPrecio').value;
  clienteDatos.turno = document.getElementById('inputActFecha').value;

  if(clienteDatos.turno == ""){
    alert("Debe indicar una fecha");
    return;
  }

  const request = await fetch('/api/agenda/'+id, {
    method: 'PUT',
    headers: {
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    },
    body: JSON.stringify(clienteDatos)
  });

  location.reload();
}

async function buscarPorNombre(){
  let clienteNombre = document.getElementById('inputBuscarNombre').value;

  const request = await fetch('/api/agenda/?nombre='+clienteNombre, {
    method: 'GET',
    headers: {
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    },
  });

  const clientes = await request.json();

  clientesTabla(clientes);
}

function clientesTabla(clientes){
  if(clientes == ""){
    document.getElementById("tbody").innerHTML = ""; 
    let sinClientesHTML = '<p class="text-center text-gray-800 description" id="pNoClientes">No se encontraron clientes</p>';
    document.getElementById("usersTable").insertAdjacentHTML("afterEnd", sinClientesHTML);
    return;
  }

    let allHtml = "";
  
    for(let cliente of clientes){
      let userHtml = '<tr id="tabla'+cliente.id+'"><td>'+cliente.nombre+'</td><td>'
      + (cliente.email == null ? "" : cliente.email )+'</td><td>'
      + (cliente.telefono == null ? "" : cliente.telefono) +'</td><td>'
      +  "$ " + cliente.precio +'</td><td>'
      + cliente.turno.slice(0,10)+'</td><td><a href = "#" onclick="borrarCliente(' 
      + cliente.id + ');" class="btn btn-danger btn-circle btn-sm"> <i class="fa fa-trash" aria-hidden="true"></i> </a><a href = "#" onclick="actualizarClienteHtml(' 
      + cliente.id + ');" class="ml-3 btn btn-primary btn-circle btn-sm"> <i class="fa fa-pencil-square-o" aria-hidden="true"></i> </a></td></tr>';
  
      allHtml += userHtml;
    }

    if(document.getElementById("pNoClientes") != null){
      document.getElementById("pNoClientes").parentElement.removeChild(document.getElementById("pNoClientes"));
    }
    
    document.getElementById("tbody").innerHTML = allHtml; 
}

function actualizarClienteHtml(id){

  if(document.getElementById("trad_"+id) != null){
    return;
  }

  let editarClienteForm = '<tr id="trad_'+id+'"><td><input id="inputActNombre" type="text" class="form-control" placeholder="Nombre"></td><td><input id="inputActEmail" type="text" class="form-control" placeholder="Email"></td><td><input id="inputActTelefono" type="text" class="form-control" placeholder="Telefono"></td><td><input id="inputActPrecio" type="text" class="form-control" placeholder="Precio"></td><td><input id="inputActFecha" type="date" class="form-control" placeholder="Fecha"></td><td><button class="btn btn-primary" onclick="actualizarCliente('+id+')">  Actualizar  </button></td></tr>'

  document.getElementById("tabla" + id).insertAdjacentHTML("afterEnd", editarClienteForm);
}