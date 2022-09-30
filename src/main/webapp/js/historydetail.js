
function generateorder() {
    alert(" Pay a Amount ");
     $.ajax({
        url: "cart/order",
        method: "POST",
        cache: false,
        success: function(usertype, name){
            alert("ordered placed")
            refresh();
            window.location.href
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

function getorder() {
    $.ajax({
        url: "cart/order",
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