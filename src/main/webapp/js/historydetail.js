function getcart() {
    $.ajax({
            url: "cart",
            method: "GET",
            headers : { Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"},
            success : function(result) {
            var page="";
                    console.log(result);
                    var product = $.parseJSON(result);
                    for (let i = 0; i < product.length; i++) {
                        page+=`
                        <div class="innercard">
                            <p><b>product_id</b>: `+product[i].product_id+`</p>
                            <p><b>vendor_id</b>: `+product[i].vendor_id+`</p>
                            <p><b>quantity</b>: `+product[i].quantity+`</p>
                            <p><b>stage</b>: `+product[i].stage+`</p>
                        </div>`;
                    }
                    $('#itemData2').html(page);
              },
            error: function(){
                alert("error occurs");
            }
            });
}