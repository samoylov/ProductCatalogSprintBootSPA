var productCardTemplate =
    "<img src='{{photo}}'>" +
    "<h3>{{name}}</h3>" +
    "<div class='text-muted'>{{description}}</div>" +
    "<p class='text-danger'>$ {{price}}</p>" +
    "<button onclick='addToCart(id)' class='btn btn-default' id='{{id}}'>Add to Cart »</button>";

function displayProducts() {

    $.ajax({
        url: 'http://localhost:8080/Product',
        method: 'GET',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (res) {
            var products = res._embedded.products;

            for (var i = 0; i < products.length; i++) {
                displayProduct(products[i])
            }
        }
    });
}

function displayProduct(product) {
    var productList = document.getElementById('content');

    var div = document.createElement("div");
    div.className = "col-md-3";
    div.style = "height:420px";

    var html = Mustache.render(productCardTemplate, product);
    div.innerHTML = html;
    productList.appendChild(div);
}
