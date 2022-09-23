var signincount = 0;
var signupcount = 0;
var br = document.createElement("br");

var form;
function commonAttribute() {
        var EMAIL_ID = document.createElement("input");
        EMAIL_ID.setAttribute("type","email");
        EMAIL_ID.setAttribute("name","emailId");
        EMAIL_ID.setAttribute("id","emailId");
        EMAIL_ID.setAttribute("placeholder", "E-mail ID");

        var PASSWORD = document.createElement("input");
        PASSWORD.setAttribute("type","password");
        PASSWORD.setAttribute("name","password");
        PASSWORD.setAttribute("placeholder","Password");

        form.appendChild(EMAIL_ID);
        form.appendChild(br.cloneNode());

        form.appendChild(PASSWORD);
        form.appendChild(br.cloneNode());
}

function signinform() {
    if(signincount != 1)
    {
        form = document.createElement("form");
        form.setAttribute("id","signinform");
        form.setAttribute("method", "get");
        form.setAttribute("autocomplete","off");

        commonAttribute();
        var SIGNIN = document.createElement("input");
       SIGNIN.setAttribute("type","submit");
       SIGNIN.setAttribute("value","Sign In");
       SIGNIN.setAttribute("onclick","getUser()");

       form.appendChild(SIGNIN);
       form.appendChild(br.cloneNode());

        if(signupcount == 1){
            document.getElementById("signupform").remove();
        }
        signincount = 1;
        signupcount = 0;
        document.getElementsByTagName("body")[0].appendChild(form);

    }
}

function signupform() {
    if(signupcount != 1){
           form = document.createElement("form");
           form.setAttribute("id","signupform");
           form.setAttribute("method", "post");
           form.setAttribute("action", "Home.html");

           if(signincount == 1){
                document.getElementById("signinform").remove();
                signincount = 0;
           }
           commonAttribute();

           var REPASSWORD = document.createElement("input");
           REPASSWORD.setAttribute("type","password");
           REPASSWORD.setAttribute("name","re_password");
           REPASSWORD.setAttribute("placeholder","Retype-Password");

           var ADDRESS = document.createElement("input");
           ADDRESS.setAttribute("type","text");
           ADDRESS.setAttribute("name","address");
           ADDRESS.setAttribute("placeholder","Address");

           var PHONE = document.createElement("input");
           PHONE.setAttribute("type","tel");
           PHONE.setAttribute("name","phone");
           PHONE.setAttribute("placeholder","Mobile No");

           var SIGNUP = document.createElement("input");
           SIGNUP.setAttribute("type","submit");
           SIGNUP.setAttribute("value","Sign Up");

           form.appendChild(REPASSWORD);
           form.appendChild(br.cloneNode());

           form.appendChild(ADDRESS);
           form.appendChild(br.cloneNode());
           form.appendChild(PHONE);
           form.appendChild(br.cloneNode());

           form.appendChild(SIGNUP);
           form.appendChild(br.cloneNode());
           signupcount = 1;

           document.getElementsByTagName("body")[0].appendChild(form);
       }
}

function getUser() {
//    var formName = form.getAttribute('id');
//    var queryString = $('#'+formName).serialize();
//    alert(formName+"  "+queryString);

    var emailId = document.getElementById("emailId").value;
    var password = document.getElementById("password").value;
    alert("Welcome to eco"+ emailId +" "+password);
    $.ajax({
    url: "login",
    type: "GET",
    datatype: "json",
    data: {"payload" : emailId},
    cache: false,
    success: function(a,b){
        console.log('error does not occured!!');
        window.location.href = "/Eco/Home.html";
        alert("Welcome " + a +b);
        },
    error: function(a,b){
    console.log('Error occured!!');
        alert("error on " + a+b)
    }
    });
}