
function generateinvoice() {
    alert(" Pay a Amount ");
     $.ajax({
        url: "cart/order",
        method: "POST",
        cache: false,
        success: function(usertype, name){
            alert("ordered placed")
            location.reload();
        },
        error: function(){
            alert("error occurs")
        }
        });
}


function insertintotable(data) {
    console.log(data);
    for (let i = 0; i < data.length-1; i++) {
//        totalprice = totalprice + (data[i].quantity * data[i].price);
        var table = document.getElementById("cartlist").getElementsByTagName('tbody')[0];
        var newRow = table.insertRow(table.length);
        col0 = newRow.insertCell(0);
        col0.innerHTML = data[i].product_name;
        col1 = newRow.insertCell(1);
        col1.innerHTML = data[i].brand_name;
        col2 = newRow.insertCell(2);
        col2.innerHTML = data[i].color;
        col3 = newRow.insertCell(3);
        col3.innerHTML = data[i].size;
        col4 = newRow.insertCell(4);
        col4.innerHTML = data[i].quantity;
        col5 = newRow.insertCell(5);
        col5.innerHTML = data[i].price;
        col6 = newRow.insertCell(6);
        col6.innerHTML = data[i].totalprice;
   }
   var page =`<div class="dis_amount">
                <p><b>Total Amount: </b>`+data[data.length-1]+`</p>
          </div><br>`;
   $('#itemData2').html(page);
}

function getcart() {
    $.ajax({
            url: "cart",
            method: "GET",
            headers : { Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"},
            success : function(result) {
                var product = $.parseJSON(result);
                insertintotable(product);
             },
            error: function(){
                alert("error occurs");
            }
            });
}

//var page="";
//console.log(result);
//var product = $.parseJSON(result);
//for (let i = 0; i < product.length; i++) {
//    page+=`
//        <div class="imgcard">
//            <div class="innercard">
//                    <img src="image/image_photo.png" alt="photo" style="width:150px;height:150px;">
//                <div class="content">
//                    <h4><b>`+product[i].product_name+`</b></h4><br>
//                    <p><b>brand_name</b>: `+product[i].brand_name+`</p>
//                    <p><b>price</b>: `+product[i].price+`</p>
//                    <p><b>Detail</b>: `+product[i].detail+`</p>
//                    <p><b>color</b>: `+product[i].color+`</p>
//                    <p><b>size</b>: `+product[i].size+`</p>
//                    <p><b>quantity</b>: `+product[i].quantity+`</p>
//                    <button class ="btncart"
//                        onclick="addcart(`+product[i].product_id+','+product[i].vendor_id+`)"
//                        value=`+product[i].product_id+`><b>Add to Cart</b></button>
//                </div>
//            </div>
//        </div>`;
//}
//$('#itemData2').html(page);