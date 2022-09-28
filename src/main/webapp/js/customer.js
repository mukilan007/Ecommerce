function CustomerPage() {
//    event.preventDefault();
	$.ajax({
		url : "/Eco/product",
		method : 'GET',
    headers : { Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"},
    success : function(result) {
        var page="";
        console.log(result);
        var product = $.parseJSON(result);
        for (let i = 0; i < product.length; i++) {
            page+=`
            <div class="outercard">
                <div class="innercard">
                    <a class="cardbtn" href="Home.html">`+product[i].cateory_name+`</a>
                </div>
            </div>`;
        }
        $('#itemData').html(page);
      }
    });
}

//type: "json",
//        data: {"payload" : JSON.stringify("a")},
//        cache: false,
//        success: function(a){
//        alert("ok "+a);
//        },
//        error: function(a){
//        alert("not okay "+a);
//        }
//        });