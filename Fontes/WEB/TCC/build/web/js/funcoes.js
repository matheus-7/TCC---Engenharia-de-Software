
function adicionarItem(evt, campo) {
    var keyCode = evt.keyCode ? evt.keyCode : evt.charCode ? evt.charCode : evt.which ? evt.which : void 0

    if (keyCode == 13) {
        __doPostBack(campo, '')
    }
}

function atualizarDropDownList(campo, idComboBox) {
    $('#' + idComboBox).val(campo.value);
}

function atualizarLinhasSelecionadas(cb) {
    cbArray = cb.id.split('_');

    var numeroLinha = parseInt(cbArray[3]);

    if (numeroLinha % 2 == 0) OldColor = 'rgb(255, 255, 255)';
    else OldColor = 'rgb(235, 235, 235)'

    var linha = $(cb.parentElement.parentElement.parentElement);
    $(linha).trigger('click');
}

function buscar(palavraChave, tabelaId) {
    palavraChave = $('#' + palavraChave.id).val().toLowerCase();
    
    $('#' + tabelaId + ' tr').filter(function(){
        if (this.id !== 'header'){
            $(this).toggle($(this).text().toLowerCase().indexOf(palavraChave) > -1);
        }        
    });
}

function calendar(textBox) {

    var textBox = $("#" + textBox);
    var data = $(textBox).val();

    $(textBox).datepicker();
    $(textBox).datepicker("option", "showOn", "button");
    $(textBox).datepicker("option", "buttonImage", "App_Themes/Tema/Imagem/data.png");
    $(textBox).datepicker("option", "changeYear", true);
    $(textBox).datepicker("option", "showAnim", "slideDown");
    $(textBox).datepicker("option", "dateFormat", "dd/mm/yy");
    $(textBox).datepicker("option", "dayNames", ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Domingo']);
    $(textBox).datepicker("option", "dayNamesMin", ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D']);
    $(textBox).datepicker("option", "dayNamesShort", ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom']);
    $(textBox).datepicker("option", "monthNames", ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro']);
    $(textBox).datepicker("option", "monthNamesShort", ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']);

    $(textBox).val(data);
}

function collapsible(header) {
    $header = $(header);

    $content = $header.next();

    $content.stop().slideToggle(500);

    var imagem = $('#' + $content.context.id.replace('headerCollapsiblePanel', 'imgCollapsiblePanel'));

    var verificarImagem = imagem[0].attributes[1].value.split('/');

    if (verificarImagem[3] == undefined) {
        verificarImagem = imagem[0].src.split('/');

        if (verificarImagem[6] == 'expand.png') imagem[0].src = verificarImagem[0] + '//' + verificarImagem[2] + '/' + 'App_Themes/Tema/Imagem/collapse.png';
        else imagem[0].src = verificarImagem[0] + '//' + verificarImagem[2] + '/' + 'App_Themes/Tema/Imagem/expand.png';
    }
    else {
        if (verificarImagem[3] == 'expand.png') imagem[0].attributes[1].value = 'App_Themes/Tema/Imagem/collapse.png';
        else imagem[0].attributes[1].value = 'App_Themes/Tema/Imagem/expand.png';
    }



}

function convertToCurrency(num) {
    x = 0;

    if (num < 0) {
        num = Math.abs(num);
        x = 1;
    }

    if (isNaN(num)) num = "0";

    cents = Math.floor((num * 100 + 0.5) % 100);
    num = Math.floor((num * 100 + 0.5) / 100).toString();

    if (cents < 10) cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3) ; i++)
        num = num.substring(0, num.length - (4 * i + 3)) + '.'
              + num.substring(num.length - (4 * i + 3));
    ret = num + ',' + cents;

    if (x == 1) ret = ' - ' + ret;
    return ret;
}

function convertToDecimal(valor) {
    result = replaceAll(valor, '.', '');
    result = result.replace(',', '.');

    return parseFloat(result);
}

function bloquearEnter(campo, evt) {
    var keyCode = evt.keyCode ? evt.keyCode : evt.charCode ? evt.charCode : evt.which ? evt.which : void 0

    if (keyCode == 13) return false;
}

function bloquearEspaco(campo, evt) {
    var keyCode = evt.keyCode ? evt.keyCode : evt.charCode ? evt.charCode : evt.which ? evt.which : void 0

    if (keyCode == 32) {
        var checkBox = $(campo).children();

        if (checkBox[0].checked) checkBox[0].checked = false;
        else checkBox[0].checked = true;

        //$(checkBox[0].parentElement.parentElement.parentElement).trigger('click');
    }
}

function enterTab(field, evt) {
    var keyCode = evt.keyCode ? evt.keyCode : evt.charCode ? evt.charCode : evt.which ? evt.which : void 0

    if (keyCode == 13) {

        var i;

        for (i = 0; i < field.form.elements.length; i++) {
            if (field == field.form.elements[i]) break;
        }

        i = (i + 1);

        while (field.form.elements[i].tagName == 'BUTTON' || field.form.elements[i].disabled == true) {
            i = i + 1;
        }

        field.form.elements[i].focus();

        return false;
    }
}

function filtraCampo(campo) {
    var s = "";
    var cp = "";
    vr = campo.value;
    tam = vr.length;

    for (i = 0; i < tam; i++) {
        if (vr.substring(i, i + 1) != "/" && vr.substring(i, i + 1) != "-" && vr.substring(i, i + 1) != "." && vr.substring(i, i + 1) != "(" && vr.substring(i, i + 1) != ")" && vr.substring(i, i + 1) != ":" && vr.substring(i, i + 1) != ",") {
            s = s + vr.substring(i, i + 1);
        }
    }

    return s;
}

function filtraNumeros(campo) {
    var s = "";
    var cp = "";
    vr = campo; tam = vr.length;

    for (i = 0; i < tam; i++) {

        if (vr.substring(i, i + 1) == "0" || vr.substring(i, i + 1) == "1" || vr.substring(i, i + 1) == "2" || vr.substring(i, i + 1) == "3" || vr.substring(i, i + 1) == "4" || vr.substring(i, i + 1) == "5" || vr.substring(i, i + 1) == "6" || vr.substring(i, i + 1) == "7" || vr.substring(i, i + 1) == "8" || vr.substring(i, i + 1) == "9") {
            s = s + vr.substring(i, i + 1);
        }
    }

    return s;
}

function formataHora(campo, evt) {
    //HH:mm
    xPos = PosicaoCursor(campo);
    evt = getEvent(evt);
    var tecla = getKeyCode(evt);
    if (!teclaValida(tecla))
        return;
    vr = campo.value = filtraNumeros(filtraCampo(campo));
    if (tam == 2)
        campo.value = vr.substr(0, 2) + ':';
    if (tam > 2 && tam < 5)
        campo.value = vr.substr(0, 2) + ':' + vr.substr(2);
    if (xPos == 2)
        xPos = xPos + 1;
    MovimentaCursor(campo, xPos);
}

function getEvent(evt) {
    if (!evt)
        evt = window.event;
    //IE 
    return evt;
}

function getKeyCode(evt) {
    var code;
    if (typeof (evt.keyCode) == 'number')
        code = evt.keyCode;

    else if (typeof (evt.which) == 'number')
        code = evt.which;

    else if (typeof (evt.charCode) == 'number')
        code = evt.charCode;

    else
        return 0;

    return code;
}

function hexToRgb(hex) {
    var shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
    hex = hex.replace(shorthandRegex, function (m, r, g, b) {
        return r + r + g + g + b + b;
    });

    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16)
    } : null;
}

function mascaraBobina(evt, field) {
    var keyCode = evt.keyCode ? evt.keyCode : evt.charCode ? evt.charCode : evt.which ? evt.which : void 0
    var key = evt.key ? evt.key : evt.char ? evt.char : evt.which ? evt.which : void 0
    var campo = $('#' + field.id).val();
    var objCampo = $('#' + field.id);

    if (campo.length == 0 && keyCode == 47) return false;

    if (campo.length > 0 && keyCode == 47 && campo.indexOf('/') > 0) return false;

    if (keyCode > 31 && (keyCode < 47 || keyCode > 57)) {
        return false;
    }

    var string1;
    var string2;

    if (campo.indexOf('/') > 0) {
        var index = campo.indexOf('/') + 1;

        string1 = campo.substring(0, index);

        string2 = campo.substring(index, campo.length) + key;

        if (string2.length == 4) {
            objCampo.val(string1 + string2 + '-M');
            return false;
        }
    }

    if (campo.indexOf('M') > 0) {
        var index2 = campo.indexOf('M');

        if (campo.substring(index2, index2 + 2) != 'M') return false;
    }

    return true;
}

function mascaraCnpj(campo, event) {

    resultado = true;

    resultado = PermiteSomenteNumeros(event);

    if (resultado) {

        v = campo.value;

        v = v.replace(/\D/g, "")

        v = v.replace(/^(\d{2})(\d)/, "$1.$2")

        v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3")

        v = v.replace(/\.(\d{3})(\d)/, ".$1/$2")

        v = v.replace(/(\d{4})(\d)/, "$1-$2")

        campo.value = v;
    }
    else {
        return resultado;
    }
}

function mascaraCpf(campo, event) {

    resultado = true;

    resultado = PermiteSomenteNumeros(event);

    if (resultado) {

        v = campo.value;

        v = v.replace(/\D/g, "");

        v = v.replace(/(\d{3})(\d)/, "$1.$2");

        v = v.replace(/(\d{3})(\d)/, "$1.$2");

        v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

        campo.value = v;
    }
    else {
        return resultado;
    }
}

function mascaraData(campo, evt) {
    var xPos = PosicaoCursor(campo);

    evt = getEvent(evt);
    var tecla = getKeyCode(evt);

    if (!teclaValida(tecla))
        return;

    vr = campo.value = filtraNumeros(filtraCampo(campo));
    tam = vr.length;
    if (tam >= 2 && tam < 4)
        campo.value = vr.substr(0, 2) + '/' + vr.substr(2);

    if (tam == 4)
        campo.value = vr.substr(0, 2) + '/' + vr.substr(2, 2) + '/';

    if (tam > 4) campo.value = vr.substr(0, 2) + '/' + vr.substr(2, 2) + '/' + vr.substr(4);

    MovimentaCursor(campo, xPos);
}

function mascaraLinhaDigitavel(campo) {
    campo.value = replaceAll(campo.value, '.', '');
    campo.value = campo.value.substring(0, 5) + "." + campo.value.substr(5, 6);
}

function mascaraValor(valor) {

    valor = valor.toString();
    valor = valor.replace('.', ',');
    //remove todas as letras
    valor = valor.replace(/[A-Za-z]/g, '');
    //remove todos os espaços em branco
    valor = valor.replace(/[ \t]/g, '');

    if (valor != '') {

        if (valor.indexOf(',') < 0) {

            valor = valor.replace(/\D/g, "");

            if (valor != '') {

                valor = valor + ',00';
            }
        }

        var caracteresDepVirg = valor.split(",");

        if (caracteresDepVirg[1].length == 1) {
            valor += '0';
        }

        valor = valor.substring(0, (valor.indexOf(',') + 3));

        if (valor.length > 6) {

            valor = valor.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");
        }
        if (valor.length > 10) {
            valor = valor.replace(/([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, ".$1.$2,$3");
        }
        if (valor.length > 14) {
            valor = valor.replace(/([0-9]{3}).([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, ".$1.$2.$3,$4");
        }
    }

    return valor;
}

function mascData(campo) {

    valor = campo.value;

    valor = valor.replace(/\D/g, "");                    //Remove tudo o que não é dígito
    valor = valor.replace(/(\d{2})(\d)/, "$1/$2");
    valor = valor.replace(/(\d{2})(\d)/, "$1/$2");

    valor = valor.replace(/(\d{2})(\d{2})$/, "$1$2");

    campo.value = valor;
}

function mascHora(campo) {

    valor = campo.value;

    valor = valor.replace(/\D/g, "");                    //Remove tudo o que não é dígito
    valor = valor.replace(/(\d{2})(\d)/, "$1:$2");
    valor = valor.replace(/(\d{2})(\d)/, "$1:$2");

    valor = valor.replace(/(\d{2})(\d{2})$/, "$1$2");

    campo.value = valor;
}

function modal(divPopup, divContent, campoFocus) {

    $('#' + divPopup).toggleClass('divPopup1');
    $('#' + divContent).toggleClass('divPopupContend1');

    setTimeout(function () {
        $('#' + campoFocus).focus();
    }, 300);


    return false;
}

function MovimentaCursor(textarea, pos) {

    if (pos <= 0)
        return;

    //se a posição for 0 não reposiciona 
    if (typeof (document.selection) != 'undefined') {
        //IE 
        var oRange = textarea.createTextRange();
        var LENGTH = 1;
        var STARTINDEX = pos;
        oRange.moveStart("character", -textarea.value.length);
        oRange.moveEnd("character", -textarea.value.length);
        oRange.moveStart("character", pos);
        oRange.select();
        textarea.focus();
    }

    if (typeof (textarea.selectionStart) != 'undefined') {
        //FireFox 
        textarea.selectionStart = pos;
        textarea.selectionEnd = pos;
    }
}

var OldColor = '';
function passaMouse(linha, b) {
    if (b) {
        OldColor = $(linha).css('backgroundColor');
        $(linha).css('backgroundColor', '#C1E0FF');
    }
    else {
        $(linha).css('backgroundColor', OldColor);
    }
}

function PermiteSomenteLetras(event) {
    var charCode = (event.which) ? event.which : event.keyCode

    resultado = true;

    if (charCode < 96 || charCode > 122) {
        resultado = false;
    }

    if (charCode > 64 && charCode < 91) {
        resultado = true;
    }

    return resultado;
}

function PermiteSomenteNumeros(event) {
    var charCode = (event.which) ? event.which : event.keyCode
    
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }

    return true;
}

function PosicaoCursor(textarea) {
    var pos = 0;

    if (typeof (document.selection) != 'undefined') {
        //IE 
        var range = document.selection.createRange();
        var i = 0;

        for (i = textarea.value.length; i > 0; i--) {
            if (range.moveStart('character', 1) == 0)
                break;
        } pos = i;
    }

    if (typeof (textarea.selectionStart) != 'undefined') {
        //FireFox 
        pos = textarea.selectionStart;
    }

    if (pos == textarea.value.length)
        return 0;

        //retorna 0 quando não precisa posicionar o elemento 
    else
        return pos;
    //posição do cursor 
} //descobre qual a posição do cursor no campo 

function redirect(url) {
    window.location.href = url;
}

function removeFocus(campo) {
    $('#' + campo.id).blur();
}

function removePonto(campo) {
    campo.value = campo.value.replace('.', '');
}

function replaceAll(string, token, newtoken) {
    while (string.indexOf(token) != -1) {
        string = string.replace(token, newtoken);
    }
    return string;
}

function selecionarLinha(index, linha, tabela) {
    var hfLinhaSelecionada = '';

    hfLinhaSelecionada = $('#cphPrincipal_hfLinhaSelecionada');

    if (hfLinhaSelecionada[0] != null) {
        $('#cphPrincipal_hfLinhaSelecionada').val(index);
    }
    else {
        $('#ctl00_cphPrincipal_hfLinhaSelecionada').val(index);
    }

    $('#' + tabela + ' tr').each(function () {

        if ($(this).attr('tabindex') % 2 == 0) {
            $(this).css('background-color', '#fff');
        }
        else {
            $(this).css('background-color', 'rgb(239, 239, 239)');
        }
    })

    OldColor = "rgb(207, 207, 207)";
    $(linha).css('background-color', OldColor);

    __doPostBack('ctl00$cphPrincipal$hfLinhaSelecionada', '');

    return false;
}

function selectTexto(campo) {
    campo.select();
}

function setarValor(campo, valor) {
    document.getElementById(campo).value = valor;
}

function setSelectedValue(campo, idComboBox) {
    $('#' + idComboBox).val(campo.value);
}

function simularClick(campo) {
    $(campo).trigger('click');
}

function simularTab(campo, evt) {
    var charCode = (event.which) ? event.which : event.keyCode

    if (charCode != 9 && charCode != 16) {
        if ($(campo)[0].maxLength == campo.value.length) {
            $(campo).next().focus();
        }
    }
}

function somenteCopiar(campo, evt) {
    $(campo).bind('copy', function () {
        return true;
    });

    $(campo).bind('paste', function () {
        return false;
    });

    $(campo).bind('cut', function () {
        return false;
    });

    if (evt.ctrlKey == true || (evt.ctrlKey == true && evt.keyCode == 67) || evt.keyCode == 9) return true;
    else return false;
}

function somenteNumeroDecimal(campo, event) {
    var charCode = (event.which) ? event.which : event.keyCode

    if (charCode == 13) {
        return false;
    }

    if (charCode == 44) {

        if ($(campo).val().indexOf(',') >= 0) {

            return false;
        }

        return true;
    }
    else if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }

    return true;
}

function somenteNumerosEPontos(evt){
    var keyCode = evt.keyCode ? evt.keyCode : evt.charCode ? evt.charCode : evt.which ? evt.which : void 0

    if ((keyCode > 31 && (keyCode < 47 || keyCode > 57)) && keyCode != 46) {
        return false;
    }
    
    return true;
}

function teclaValida(tecla) {
    if (tecla == 8 //backspace 
        //Esta evitando o post, quando são pressionadas estas teclas. 
        //Foi comentado pois, se for utilizado o evento texchange, é necessario o post. 
        || tecla == 9 //TAB 
        || tecla == 27 //ESC 
        || tecla == 16 //Shif TAB 
        || tecla == 45 //insert 
        || tecla == 46 //delete 
        || tecla == 35 //home 
        || tecla == 36 //end 
        || tecla == 37 //esquerda 
        || tecla == 38 //cima 
        || tecla == 39 //direita 
        || tecla == 40)//baixo return false; else return true; 
    {
        return false;
    }
    else {
        return true;
    }
}

function toUpperCase(campo) {
    campo.value = campo.value.toUpperCase();
}

function validaCNPJ(strCNPJ) {

    document.getElementById('lblAlertaMasterMensagem').innerText = 'O CNPJ informado é inválido!';

    var cnpj = document.getElementById(strCNPJ.id).value;

    cnpj = replaceAll(cnpj, '.', '');
    cnpj = replaceAll(cnpj, '/', '');
    cnpj = replaceAll(cnpj, '-', '');

    if (cnpj == "00000000000000" || cnpj == "11111111111111" || cnpj == "22222222222222" || cnpj == "33333333333333" || cnpj == "44444444444444" ||
        cnpj == "55555555555555" || cnpj == "66666666666666" || cnpj == "77777777777777" || cnpj == "88888888888888" || cnpj == "99999999999999") {

        modal('pnlAlertaMaster', 'divContendAlerta', 'lbtnOkAlertaMasterMensagem');
        return false;
    }

    // Valida DVs
    tamanho = cnpj.length - 2
    numeros = cnpj.substring(0, tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;

    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }

    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

    if (resultado != digitos.charAt(0)) {

        modal('pnlAlertaMaster', 'divContendAlerta', 'lbtnOkAlertaMasterMensagem');
        return false;
    }

    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;

    for (i = tamanho; i >= 1; i--) {

        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }

    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

    if (resultado != digitos.charAt(1)) {

        modal('pnlAlertaMaster', 'divContendAlerta', 'lbtnOkAlertaMasterMensagem');
        return false;
    }

    return true;
}

function validaCPF(strCPF) {
    var Soma;
    var Resto;
    Soma = 0;

    document.getElementById('lblAlertaMasterMensagem').innerText = 'O CPF informado é inválido!';

    var cpf = document.getElementById(strCPF.id).value;

    cpf = replaceAll(cpf, '.', '');
    cpf = replaceAll(cpf, '-', '');

    if (cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" ||
        cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999") {

        modal('pnlAlertaMaster', 'divContendAlerta', 'lbtnOkAlertaMasterMensagem');
        return false;
    }

    for (i = 1; i <= 9; i++) {
        Soma = Soma + parseInt(cpf.substring(i - 1, i)) * (11 - i);
    }

    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) {
        Resto = 0;
    }

    if (Resto != parseInt(cpf.substring(9, 10))) {

        modal('pnlAlertaMaster', 'divContendAlerta', 'lbtnOkAlertaMasterMensagem');
        return false;
    }

    Soma = 0;

    for (i = 1; i <= 10; i++) {

        Soma = Soma + parseInt(cpf.substring(i - 1, i)) * (12 - i);
    }

    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) {

        Resto = 0;
    }

    if (Resto != parseInt(cpf.substring(10, 11))) {

        modal('pnlAlertaMaster', 'divContendAlerta', 'lbtnOkAlertaMasterMensagem');
        return false;
    }

    return true;
}

document.addEventListener('DOMContentLoaded', function () {

  // Get all "navbar-burger" elements
  var $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

  // Check if there are any navbar burgers
  if ($navbarBurgers.length > 0) {

    // Add a click event on each of them
    $navbarBurgers.forEach(function ($el) {
      $el.addEventListener('click', function () {

        // Get the target from the "data-target" attribute
        var target = $el.dataset.target;
        var $target = document.getElementById(target);

        // Toggle the class on both the "navbar-burger" and the "navbar-menu"
        $el.classList.toggle('is-active');
        $target.classList.toggle('is-active');

      });
    });
  }

});


function filtrarCidades() {
    var xhttp = null;
    if (window.XMLHttpRequest) {
        //code for modern browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code for old IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    xhttp.onreadystatechange = function() {
        if(this.readyState == 3) {
            console.log("Processando");
        } 
        if(this.readyState == 4 && this.status == 200) {
          document.getElementById("cidade").innerHTML = this.responseText;
          console.log("Pronto");
        }
    };
    
    xhttp.open("POST", "/TCC/UniversidadeServlet", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("acao=filtrarCidades&idEstado=" + document.getElementById("estado").value);
}



