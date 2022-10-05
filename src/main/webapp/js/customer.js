function addcart(productid, vendorid){
    var payload = {"product_id": productid.toString(),
                "vendor_id": vendorid.toString()
                }
    $.ajax({
        url: "cart",
        method: "POST",
        type: "json",
        data: {"payload" : JSON.stringify(payload)},
        cache: false,
        success: function(){
            alert("added to cart");
        },
        error: function(sc, msg){
            alert(sc.status);
            switch(sc.status) {
                case "409":
                    alert("cart already exist");
                    break;
                default:
                    alert("not added to cart");
                    break;
            }
        }
        });
}
function findcateory(){
//    var cateory_name = document.getElementById("cardbtn").value;
    var cateory_name = sessionStorage.getItem("catname")
    var payload = {"cateory_name": cateory_name};
       $.ajax({
            url: "view/product",
            method: "GET",
            data: {"payload" : JSON.stringify(payload)},
            cache: false,
            headers : { Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"},
            success: function(result){
                var page="";
                var head="";
                console.log(result);
                var product = $.parseJSON(result);
                if(product) {
                    head += `<div>
                                <h1>`+product[0].category_name+`</h1>
                             </div>`;
                    $('#catname').html(head);
                }
                for (let i = 0; i < product.length; i++) {
                    page+=`
                    <div class="imgcard">
                        <div class="innercard">
                                <img src="image/image_photo.png" alt="photo" style="width:150px;height:150px;">
                            <div class="content">
                                <h4><b>`+product[i].product_name+`</b></h4><br>
                                <p><b>brand_name</b>: `+product[i].brand_name+`</p>
                                <p><b>price</b>: `+product[i].price+`</p>
                                <p><b>Detail</b>: `+product[i].detail+`</p>
                                <p><b>color</b>: `+product[i].color+`</p>
                                <p><b>size</b>: `+product[i].size+`</p>
                                <p><b>quantity</b>: `+product[i].quantity+`</p>
                                <button class ="btncart"
                                    onclick="addcart(`+product[i].product_id+','+product[i].vendor_id+`)"
                                    value=`+product[i].product_id+`><b>Add to Cart</b></button>
                            </div>
                        </div>
                    </div>`;
                }
                $('#itemData1').html(page);
            },
            error: function(){
    //            errorMessage();
                    alert("error thrown");
            }
        });
}
function redirectthis(value){
    sessionStorage.clear();
    sessionStorage.setItem("catname", value);
    window.location.href = "/Eco/ProductDetails.html";

}

function CustomerPage() {
//    event.preventDefault();
	$.ajax({
		url : "product",
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
                    <label><b>`+product[i].cateory_name+`</b></label><br>
                        <button id="cardbtn"  onclick="redirectthis('`+product[i].cateory_name+`')"
                            value=`+product[i].cateory_name+`>Goto</button>
                    </div>
                </div>`;
            }
            $('#itemData').html(page);
      }
    });
}
//`+product[i].cateory_id+','+product[i].cateory_name+`