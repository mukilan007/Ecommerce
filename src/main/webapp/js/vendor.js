function vendorPage(event, view) {
  var i;
  var product = document.getElementsByClassName("tabcontent");
  for (i = 0; i < product.length; i++) {
    product[i].style.display = "none";
  }
  var tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(view).style.display = "block";
  event.currentTarget.className += " active";
}

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
        }
        });
}
const form = document.getElementById("addform");
form.addEventListener('submit', function handleSubmit(event) {
    form.reset();
});