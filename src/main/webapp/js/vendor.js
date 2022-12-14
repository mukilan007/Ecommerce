function onDelivered(data) {
	var payload = {"_id": data.toString()};
    $.ajax({
            url : "vendor/edit",
            method: 'POST',
            type: "json",
            data: {"payload" : JSON.stringify(payload)},
            cache: false,
            success : function() {
                alert("success");
            },
            error: function(){
                alert("error occurs");
            }
        });
}

function insertintodeliverytable(data) {
    console.log(data);
    for (let i = 0; i < data.length; i++) {
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
        col6.innerHTML = `<button onClick="onDelivered(`+ data[i]._id +`)">Delivered</button>
        				<button id="cancel" onClick="onCancel(`+ data[i]._id +`)">Cancel</button>`;
   }
}
function insertintotable(data) {
    console.log(data);
    for (let i = 0; i < data.length; i++) {
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
        col6.innerHTML = `<button id="cancel" onClick="onDelete('`+ data[i].product_id +`')">Delete</button>`;
   }
}

function autoVendorPage() {
    $.ajax({
        url : "vendor/view",
        method : 'GET',
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

function getvendordeatil(stage) {
    var payload = {"stage": stage};
    $.ajax({
            url : "vendor/detail",
            method : 'GET',
            type: "json",
            data: {"payload" : JSON.stringify(payload)},
            cache: false,
            success : function(result) {
                var product = $.parseJSON(result);
                if(stage == "ordered") {
                    insertintodeliverytable(product);
                }
                else {
//                    insertintotable(product);
                    console.log(product);
                    for (let i = 0; i < product.length; i++) {
                        var table = document.getElementById("cartlist").getElementsByTagName('tbody')[0];
                        var newRow = table.insertRow(table.length);
                        col0 = newRow.insertCell(0);
                        col0.innerHTML = product[i].product_name;
                        col1 = newRow.insertCell(1);
                        col1.innerHTML = product[i].brand_name;
                        col2 = newRow.insertCell(2);
                        col2.innerHTML = product[i].color;
                        col3 = newRow.insertCell(3);
                        col3.innerHTML = product[i].size;
                        col4 = newRow.insertCell(4);
                        col4.innerHTML = product[i].quantity;
                        col5 = newRow.insertCell(5);
                        col5.innerHTML = product[i].price;
                    }
                }
            },
            error: function(){
                alert("error occurs");
            }
        });
}

//function vendorPage(event, type) {
//    var i;
//    var product = document.getElementsByClassName("tabcontent");
//    for (i = 0; i < product.length; i++) {
//    product[i].style.display = "none";
//    }
//    var tablinks = document.getElementsByClassName("tablinks");
//    for (i = 0; i < tablinks.length; i++) {
//    tablinks[i].className = tablinks[i].className.replace(" active", "");
//    }
//    document.getElementById(type).style.display = "block";
//    event.currentTarget.className += " active";
//}

function vendorAdd() {
    var categoryname = document.getElementById("categoryname").value;
    var type = document.getElementById("type").value;
    var brandname = document.getElementById("brandname").value;
    var productname = document.getElementById("productname").value;
    var detail = document.getElementById("detail").value;
    var size = document.getElementById("size").value;
    var color = document.getElementById("color").value;
    var price = document.getElementById("price").value;
    var quantity = document.getElementById("quantity").value;
    var payload = {"categoryname": categoryname,
                    "type": type,
                    "brandname": brandname,
                    "productname": productname,
                    "detail": detail,
                    "size": size,
                    "color": color,
                    "price": price,
                    "quantity": quantity
                    }
    alert("Confirm to add product " + productname);
    $.ajax({
        url: "vendor/add",
        method: "POST",
        type: "json",
        data: {"payload" : JSON.stringify(payload)},
        cache: false,
        success: function(){
            alert("success");
        },
        error: function(){
            alert("error occur");
        }
        });
}

function onDelete(data){
    var payload = {"product_id": data.toString()};
    $.ajax({
            url : "vendor/product/delete",
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
const form = document.getElementById("addform");
form.addEventListener('submit', function handleSubmit(event) {
    form.reset();
});