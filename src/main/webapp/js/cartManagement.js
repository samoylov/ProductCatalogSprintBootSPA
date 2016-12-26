// cart functions

function addToCart(id) {
    $.get(baseURL + '/addToCart?productId=' + id, function () {
        displayCart();
        displayCartStatus();
    });
}

function displayCartStatus() {
    $.get(baseURL + '/getCart', function (res) {
        var cartStatus = document.getElementById('cartStatus');
        cartStatus.innerHTML = "<span class='glyphicon glyphicon-shopping-cart'>&nbsp;</span>" + res.cartStatus;
    });
}

var cartItemsTableTemplate =
    "<h3>Shopping Cart</h3>" +
    "<table class='table table-condensed' id='cartEditor'><thead><tr>" +
    "<th>Product</th>" +
    "<th>Quantity</th>" +
    "<th></th>" +
    "<th>Amount</th>" +
    "<th></th>" +
    "</tr></thead><tbody>" +
    "{{#cartItems}}" +
    "<tr>" +

    "<td>{{product.name}}</td>" +

    "<td align='center'>{{cartItem.quantity}}" +
    "<td align='center' nowrap='nowrap'>" +

    "<div class='btn-group-sm'>" +
    "<button class='btn btn-default' id='{{cartItem.id}}' onclick='modifyCartItemQuantity(id, -1)'>-</button>" +
    "<button class='btn btn-default' id='{{cartItem.id}}' onclick='modifyCartItemQuantity(id, 1)'>+</button>" +
    "</div>" +

    "<td>{{amount}}</td>" +

    "<td><a href='#' name='deleteCartItem' id='{{cartItem.id}}' onclick='deleteCartItem(id)'>delete</a></td>" +
    "</tr>" +

    "{{/cartItems}}" +
    "</tbody></table>";

function displayCart() {
    $.get(baseURL + '/getCart', function (res) {
        var shoppingCart = document.getElementById('shoppingCart');
        if (res.cartItems.length != 0)
            shoppingCart.innerHTML = Mustache.render(cartItemsTableTemplate, res);
        else
            shoppingCart.innerHTML = "";
    });
}

function deleteCartItem(id) {
    $.get(baseURL + '/deleteCartItem?cartItemId=' + id, function (res) {
        displayCart();
        displayCartStatus();
    });
}

function modifyCartItemQuantity(cartItemId, delta) {
    $.get(baseURL + '/modifyCartItemQuantity?cartItemId=' + cartItemId + '&delta=' + delta, function (res) {
        displayCart();
        displayCartStatus();
    });
}

