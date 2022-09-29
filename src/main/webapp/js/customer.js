function findcateory(value){
console.log(value);
    var cateory_name = document.getElementById("cardbtn").value;
    var payload = {"cateory_name": value};
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
                                <button class ="btncart"><b>Add to Cart</b></button>
                            </div>
                        </div>
                    </div>`;
                }
                $('#itemData').html(page);
            },
            error: function(){
    //            errorMessage();
                    alert("error thrown");
            }
        });
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
                        <button id="cardbtn"  onclick="findcateory('`+product[i].cateory_name+`')" value=`+product[i].cateory_name+`>Goto</button>
                    </div>
                </div>`;
            }
            $('#itemData').html(page);
      }
    });
}
//`+product[i].cateory_id+`','`+product[i].cateory_name+`