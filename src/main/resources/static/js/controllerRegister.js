async function registrarUsuario(){
    
  let usuario = {};
  usuario.username = document.getElementById("inputUsername").value;
  usuario.password = document.getElementById("inputPassword").value;
  usuario.passwordRepeat = document.getElementById("inputPasswordRepeat").value;

  if(usuario.username == "" || usuario.password == "" || usuario.passwordRepeat == ""){
      return;
  }

  if(usuario.passwordRepeat != usuario.password){
    alert("Las contraseñas no coinciden");
    return;
  }

    const request = await fetch('/auth/register', {
        method: 'POST',
        headers: {
          'Accept' : 'application/json',
          'Content-Type' : 'application/json'
        },
        body: JSON.stringify(usuario)
      });

      if(request.status == 400){
        alert("Usuario ya existente");
        return;
      }

    alert("¡Se registró exsitosamente!");
    window.location.href = "@{login}";
}