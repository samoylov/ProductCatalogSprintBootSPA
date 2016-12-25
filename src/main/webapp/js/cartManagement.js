// cart functions

function addToCart(id) {
    $.get('http://localhost:8080/addToCart?productId=' + id, function () {
        displayCart();
        displayCartStatus();
    });
}

function displayCartStatus() {
    $.get('http://localhost:8080/getCart', function (res) {
        var cartStatus = document.getElementById('cartStatus');
        cartStatus.innerHTML = res.cartStatus;
    });
}

var cartItemsTableTemplate =
    "<table class='table table-condensed' id='cartEditor'><thead><tr>" +
    "<th>Product</th>" +
    "<th>Quantity</th>" +
    "<th>Price</th>" +
    "<th></th>" +
    "</tr></thead><tbody>" +
    "{{#cartItems}}" +
    "<tr>" +
    "<td>{{product.name}}</td>" +
    "<td align='center'>{{cartItem.quantity}}</td>" +
    "<td align='center'>{{product.price}}</td>" +
    "<td><a href='#' name='deleteCartItem' id='{{cartItem.id}}' onclick='deleteCartItem(id)'>delete</a></td>" +
    "</tr>" +
    "{{/cartItems}}" +
    "</tbody></table>";

function displayCart() {
    $.get('http://localhost:8080/getCart', function (res) {
        if (res.cartItems.length != 0)
            document.getElementById('sidebar').innerHTML = Mustache.render(cartItemsTableTemplate, res);
        else
            document.getElementById('sidebar').innerHTML = "";
    });
}

function deleteCartItem(id) {
    $.get('http://localhost:8080/deleteCartItem?cartItemId=' + id, function (res) {
        displayCart();
        displayCartStatus();
    });
}



