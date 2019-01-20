
function buildContact(contact) {
    var temp = document.getElementById('template');
    var cont = document.createElement('div');
    cont.innerHTML = temp.innerHTML;

    cont.querySelector('.name').innerText = contact.name;
    cont.querySelector('.surname').innerText = contact.surname;
    cont.querySelector('.skype').innerText = contact.skype;
    cont.querySelector('.phone').innerText = contact.phone;
    cont.querySelector('.email').innerText = contact.email;
    cont.querySelector('.socialNetworks').innerText = contact.socialNetworks;

    document.querySelector('#out').appendChild(cont);
}

function load() {
    var req = new XMLHttpRequest();

    req.open('GET', "./controller");
    req.setRequestHeader("command", "list");

    req.setRequestHeader("args", "");

    req.onreadystatechange = function() {
        if(this.readyState !== 4) return;
        var json = this.getResponseHeader("contacts");
        var arr = JSON.parse(json);
        for(var i = 0; i < arr.length; i++)
            buildContact(arr[i]);
    };

    req.send();
}
