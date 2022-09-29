function refresh() {
        window.location.reload();
    }

function logout(){
    $.ajax({
            url : "logout",
            method : 'GET',
            });
}