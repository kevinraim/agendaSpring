async function registrarUsuario(){
    
    let usuario = {};
    usuario.username = document.getElementById("inputUsername").value;
    usuario.password = document.getElementById("inputPassword").value;

    if(usuario.username == "" || usuario.password == ""){
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

    alert("¡Se registró exsitosamente!");
    window.location.href = "@{login}";
}