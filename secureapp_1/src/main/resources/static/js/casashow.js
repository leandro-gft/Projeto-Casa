function enviarEvento(){

		  
		if(document.evento.nomeEvento.value=="")
		{
		bootbox.alert( "É necessário preencher o campo Evento." );
		document.evento.nomeEvento.focus();
		return false;
		}
		
		if(document.evento.data.value=="")
		{
		bootbox.alert( "É necessário preencher o campo Data." );
		document.evento.data.focus();
		return false;
		}
				
		  
		if(document.evento.valor.value=="")
		{
		bootbox.alert( "É necessário preencher o campo Preço do ingresso." );
		document.evento.valor.focus();
		return false;
		}
		
				
		if(document.evento.casa.value=="")
		{
		bootbox.alert( "Para cadastrar um evento você precisa ter pelo menos uma Casa de show cadastrada." );
		document.evento.casa.focus();
		return false;
		}
		
		
		
}

function enviarCasa(){
if(document.casa.nomeCasa.value=="")
{
bootbox.alert( "É necessário preencher  o campo Casa de show." );
document.casa.nomeCasa.focus();
return false;
}

if(document.casa.localCasa.value=="")
{
bootbox.alert( "É necessário preencher  o campo Endereço." );
document.casa.localCasa.focus();
return false;
}
}

function moeda(a, e, r, t) {
    let n = ""
      , h = j = 0
      , u = tamanho2 = 0
      , l = ajd2 = ""
      , o = window.Event ? t.which : t.keyCode;
    if (13 == o || 8 == o)
        return !0;
    if (n = String.fromCharCode(o),
    -1 == "0123456789".indexOf(n))
        return !1;
    for (u = a.value.length,
    h = 0; h < u && ("0" == a.value.charAt(h) || a.value.charAt(h) == r); h++)
        ;
    for (l = ""; h < u; h++)
        -1 != "0123456789".indexOf(a.value.charAt(h)) && (l += a.value.charAt(h));
    if (l += n,
    0 == (u = l.length) && (a.value = ""),
    1 == u && (a.value = "0" + r + "0" + l),
    2 == u && (a.value = "0" + r + l),
    u > 2) {
        for (ajd2 = "",
        j = 0,
        h = u - 3; h >= 0; h--)
            3 == j && (ajd2 += e,
            j = 0),
            ajd2 += l.charAt(h),
            j++;
        for (a.value = "",
        tamanho2 = ajd2.length,
        h = tamanho2 - 1; h >= 0; h--)
            a.value += ajd2.charAt(h);
        a.value += r + l.substr(u - 2, u)
    }
    return !1
}

$( function() {
    $( document ).tooltip();
  } );

$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})