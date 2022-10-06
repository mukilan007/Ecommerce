
function generateorder() {
    alert(" Pay a Amount ");
     $.ajax({
        url: "cart/order",
        method: "POST",
        cache: false,
        success: function(usertype, name){
            alert("ordered placed")
            refresh();
        },
        error: function(){
            alert("error occurs")
            refresh();
        }
        });
}
function insertintoordertable(data) {
    console.log(data);
    for (let i = 0; i < data.length; i++) {
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
        col6.innerHTML = data[i].price * data[i].quantity;
        col7 = newRow.insertCell(7);
        col7.innerHTML = `<button id="cancel" onClick="onCancel(`+ data[i]._id +`)">Cancel</button>`;
   }
   var page =`<div class="dis_amount">
                <p><b>Total Amount: </b>`+data[data.length-1]+`</p>
          </div><br>`;
   $('#itemData2').html(page);
}
function insertintocarttable(data) {
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
        col4.innerHTML = `<p id="tbodyquantity" onChange="" contenteditable="true">`+ data[i].quantity +`</p>`;
        col5 = newRow.insertCell(5);
        col5.innerHTML = data[i].price;
        col6 = newRow.insertCell(6);
        col6.innerHTML = data[i].totalprice;
        col7 = newRow.insertCell(7);
        col7.innerHTML = `<button onClick="quantity_save('`+ data[i].product_id +`')">Save</button>
                        <button id="cancel" onClick="Cancel(`+ data[i].product_id +`)">Cancel</button>`;
   }
   var page =`<div class="dis_amount">
                <p><b>Total Amount: </b>`+data[data.length-1]+`</p>
          </div><br>`;
   $('#itemData2').html(page);
}

function getcart(data) {
    $.ajax({
        url: "cart",
        method: "GET",
        headers : { Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"},
        success : function(result) {
            var product = $.parseJSON(result);
            insertintocarttable(product);
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
            insertintoordertable(product);
         },
        error: function(){
            alert("error occurs");
        }
        });
}

function Cancel(data) {
    var payload = {"product_id": data.toString()};
    $.ajax({
            url : "customer/delete",
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
function quantity_save(product_id) {
    var quantity = document.getElementById("tbodyquantity").innerHTML;
    var payload = {"product_id": product_id,
                    "quantity": quantity}
    alert(payload.product_id+" "+payload.quantity);
    $.ajax({
            url : "save/cart",
            method: 'GET',
            type: 'json',
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