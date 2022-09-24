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

function errorMessage() {
        var error = document.getElementById("error")
        error.textContent = "Please enter a correct valid"
        error.style.color = "red"
    }

function myFunction() {
        var x = document.getElementById("snackbar");
//        document.getElementById("snackbar").value = x;
        x.className = "show";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

function getUser() {
    document.getElementById("error").value = "";
    var emailId = document.getElementById("emailId").value;
    var password = document.getElementById("password").value;
    $.ajax({
    url: "login",
    method: "GET",
//    type: "JSONObject",
//    data: {"payload" : JSON.stringify({"email_id": emailId, "password": password})},
    type: "json",
    data: {"payload" : JSON.stringify({"email_id": emailId, "password": password})},
    cache: false,
    success: function(result){
        if(result)
            window.location.href = "/Eco/VendorHomePage.html";
        else if(result)
            window.location.href = "/Eco/Home.html";
        myFunction();
    },
    error: function(a, msg){
        errorMessage();
        document.getElementById("emailId").value = "";
        document.getElementById("password").value = "";
    }
    });
}

function vendorPage(event, view) {
  var i;
  var product = document.getElementsByClassName("tabcontent");
  for (i = 0; i < product.length; i++) {
    product[i].style.display = "none";
  }
  var tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(view).style.display = "block";
  event.currentTarget.className += " active";
}