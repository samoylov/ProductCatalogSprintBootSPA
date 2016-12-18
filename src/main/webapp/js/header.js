console.log("header");


$.ajax({
    url: 'http://localhost:8080/Product',
    method: 'GET',
    contentType: 'application/json; charset=UTF-8',
    dataType: 'json',
    success: function (res) {
        console.log(res);
        var products = res._embedded.products;

        for (var i = 0; i < products.length; i++) {
            addProductCard(products[i])
        }
    }
});

function addProductCard(product) {
    var productList = document.getElementById('content');
    var div = document.createElement("div");
    div.className = "col-md-2";
    div.style = "height:420px";
    // product name
    var pname = document.createElement('h3');
    pname.innerHTML = product.name;
    // product description
    var pdescription = document.createElement('div');
    pdescription.innerHTML = product.description;
    pdescription.className = "text-muted";
    // product price
    var pprice = document.createElement('p');
    pprice.innerHTML = "$ " + product.price;
    pprice.className = "text-danger";
    var pphoto = document.createElement('img');
    pphoto.src = product.photo;
    pphoto.className = 'img-rounded img-responsive';
    var buybutton = document.createElement('button');
    buybutton.className = 'btn btn-default';
    buybutton.innerHTML = 'Add to Cart »';
    buybutton.id = product.id;
    buybutton.onmouseover=function () {
        buybutton.className='btn btn-primary';
    }
    buybutton.onmouseout=function () {
        buybutton.className='btn btn-default';
    }

    div.appendChild(pphoto);
    div.appendChild(pname);
    div.appendChild(pdescription);
    div.appendChild(pprice);
    div.appendChild(buybutton);
    productList.appendChild(div);
}