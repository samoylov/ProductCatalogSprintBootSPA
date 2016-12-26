var productCardTemplate =
    "<img class='img-responsive' src='{{photo}}'>" +
    "<h4>{{name}}</h4>" +
    "<div class='text-muted'>{{description}}</div>" +
    "<p class='text-danger'>$ {{price}}</p>" +
    "<button onclick='addToCart(id)' class='btn btn-default' id='{{id}}'>Add to Cart »</button>";

function displayProducts() {
    var pageNumber = getCookie('productListPageNumber');
    if (pageNumber != undefined && pageNumber != '')
        pageNumber = "?page=" + pageNumber;
    $.get(baseURL + '/Product' + pageNumber, function (res) {
        var products = res._embedded.products;
        document.getElementById('content').innerHTML = '';
        for (var i = 0; i < products.length; i++) {
            displayProduct(products[i])
        }
        displayPagination(res);
    });
}

function displayProduct(product) {
    var productList = document.getElementById('content');
    var div = document.createElement("div");
    div.className = "col-md-3";
    div.style = "height: 420px";
    div.innerHTML = Mustache.render(productCardTemplate, product);
    productList.appendChild(div);
}

function displayCatalogGenderFilters() {
    var element = document.getElementById('catalogGenderFilter');

    element.innerHTML = getCatalogGenderFilter();
}


// product catalogue navigation

function catalogGenderFilterExists() {
    return getCookie('catalogGenderFilter') != '';
}

function setCatalogGenderFilter(catalogGenderFilter) {
    setCookie('catalogGenderFilter', catalogGenderFilter);
}

function getCatalogGenderFilter() {
    if (catalogGenderFilterExists())
        return getCookie('catalogGenderFilter');
    else
        return 'All';
}

function catalogProductTypeFilterExists() {
    return getCookie('catalogProductTypeFilter') != null;
}

function setCatalogProductTypeFilter(catalogProductTypeFilter) {
    setCookie('catalogProductTypeFilter', catalogProductTypeFilter);
}

function getCatalogProductTypeFilter() {
    return getCookie('catalogProductTypeFilter');
}

// pagination

function switchProductListPage(pageNumber){
    setCookie('productListPageNumber', pageNumber);
    displayProducts();
}

function displayPagination(res) {
    var currentPage = res.page.number;
    var numberOfPages = res.page.totalPages;
    var div = document.createElement('div');
    div.style="text-align: right";

    // UL
    var ul = document.createElement('ul');
    ul.className = 'pagination';
    div.appendChild(ul);

    // LI
    var li, a;
    for (var i = 0; i < numberOfPages; i++) {

        li = document.createElement('li');
        a = document.createElement('a');
        a.innerHTML = i + 1;
        a.id = i;
        a.href = "#";
        a.setAttribute('onclick', 'switchProductListPage(id)');
        li.appendChild(a);
        if (i == currentPage) li.className = 'active';
        ul.appendChild(li);
    }

    // find how to delete content from DIV
    // document.getElementById('pagination').get
    document.getElementById('pagination').innerHTML = '';
    document.getElementById('pagination').appendChild(div);
}
