function onLoginResponse(){
    if(this.status === OK) {
        const user = JSON.parse(this.responseText);
        setAuthorization(user);
        onProfileLoad(user);
    }
    else {
        onOtherResponse(registerContentDivEl, this);
    }   
}
function onLoginButtonClicked(){
    const loginFormEl = document.forms['login-form'];
    const emailInputEl = loginFormEl.querySelector('input[name = "email"]');
    const passwordInputEl = loginFormEl.querySelector('input[name = "password"]');

    const email = emailInputEl.value;
    const password = passwordInputEl.value;

    const params = new URLSearchParams();
    params.append('email',email);
    params.append('password',password);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load',onLoginResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST','login');
    xhr.send(params);
}

function onRegisterButtonClicked(){
    const registerFormEl = document.forms['register-form'];

    const userNameInputEl = registerFormEl.querySelector('input[name ="name"]');
    const emailInputEl = registerFormEl.querySelector('input[name ="email"]');
    const passwordInputEl = registerFormEl.querySelector('input[name ="password"]');

    const userName = userNameInputEl.value;
    const email = emailInputEl.value;
    const password = passwordInputEl.value;

    const params = new URLSearchParams();
    params.append('name',userName);
    params.append('email',email);
    params.append('password',password);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load',onRegisterResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST','register')
    xhr.send(params);
}

function onRegisterResponse(){
    if(this.status === OK){
        const user = JSON.parse(this.responseText);
        setAuthorization(user);
        alert('Thanks for registering' + name);
    }
    else{
        onOtherResponse(document.getElementById('register-form'),this)
    }
}