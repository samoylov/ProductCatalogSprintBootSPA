// cart functions

function addToCart(id) {
    $.ajax({
        url: 'http://localhost:8080/addToCart?productId=' + id,
        method: 'GET',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function () {
            displayCartStatus();
        }
    });
}

function displayCartStatus() {
    $.ajax({
        url: 'http://localhost:8080/getCart',
        method: 'GET',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (res) {

            var cartStatus = document.getElementById('cartStatus');
            cartStatus.innerHTML = res.cartStatus;

        }
    });
}

