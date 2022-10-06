function refresh() {
        window.location.reload();
    }

function logout(){
    $.ajax({
            url : "logout",
            method : 'GET',
            success: function(name){
                window.location.href = "/Eco";
            }
            });
}


function onCancel(data) {
	    var payload = {"_id": data.toString()};
        $.ajax({
                url : "vendor/order/delete",
                method: 'GET',
                type: "json",
                data: {"payload" : JSON.stringify(payload)},
                cache: false,
                success : function() {
                    alert("success");
                    refresh();
                },
                error: function(){
                    alert("error occurs");
                }
            });
}