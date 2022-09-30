function refresh() {
        window.location.reload();
    }

function logout(){
    $.ajax({
            url : "logout",
            method : 'GET',
            success: function(){
                window.location.href = "/Eco";
            }
            });
}