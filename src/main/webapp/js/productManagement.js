var productCardTemplate =
    "<img class='img-responsive' src='{{photo}}'>" +
    "<h4>{{name}}</h4>" +
    "<div class='text-muted'>{{description}}</div>" +
    "<p class='text-danger'>$ {{price}}</p>" +
    "<button onclick='{addToCart(id)}' class='btn btn-default' id='{{id}}'>Add to Cart »</button>";

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

function switchProductListPage(pageNumber) {
    setCookie('productListPageNumber', pageNumber);
    displayProducts();
}

function displayPagination(res) {
    var numberOfPages = res.page.totalPages;
    var pageLinks = res._links;

    var currentPage = res.page.number;
    var pageFirst = currentPage < 1 ? undefined : pageLinks.first.href.split("page=")[1].split("&")[0];
    var pagePrev = currentPage < 1 ? undefined : pageLinks.prev.href.split("page=")[1].split("&")[0];
    var pageNext = currentPage > numberOfPages - 2 ? undefined : pageLinks.next.href.split("page=")[1].split("&")[0];
    var pageLast = currentPage > numberOfPages - 2 ? undefined : pageLinks.last.href.split("page=")[1].split("&")[0];

    var pages = [];
    var p = 0;
    if (pageFirst != undefined)
        pages[p++] = {text: "<span class='glyphicon glyphicon-step-backward'></span>", id: parseInt(pageFirst), class: ""};
    else
        pages[p++] = {text: "<span class='glyphicon glyphicon-step-backward'></span>", id: 0, class: "disabled"};

    if (pagePrev != undefined)
        pages[p++] = {text: "<span class='glyphicon glyphicon-backward'> </span>", id: parseInt(pagePrev), class: ""};
    else
        pages[p++] = {text: "<span class='glyphicon glyphicon-backward'> </span>", id: 0, class: "disabled"};

    pages[p++] = {text: "Page " + (currentPage + 1) + "/" + numberOfPages, id: currentPage, class: "active"};

    if (pageNext != undefined)
        pages[p++] = {text: "<span class='glyphicon glyphicon-forward'></span> ", id: parseInt(pageNext), class: ""};
    else
        pages[p++] = {text: "<span class='glyphicon glyphicon-forward'></span>", id: 0, class: "disabled"};

    if (pageLast != undefined)
        pages[p++] = {text: "<span class='glyphicon glyphicon-step-forward'></span>", id: parseInt(pageLast), class: ""};
    else
        pages[p++] = {text: "<span class='glyphicon glyphicon-step-forward'></span>", id: 0, class: "disabled"};


    var div = document.createElement('div');
    div.style = "text-align: center;";

    // UL
    var ul = document.createElement('ul');
    ul.className = 'pagination';
    div.appendChild(ul);
    // LI
    var li, a;
    for (var i = 0; i < pages.length; i++) {

        li = document.createElement('li');
        a = document.createElement('a');
        a.innerHTML = pages[i].text;
        a.id = pages[i].id;
        a.href = "#";
        if (!(pages[i].id == currentPage || pages[i].class == "disabled"))
            a.setAttribute('onclick', '{switchProductListPage(id)}');
        li.appendChild(a);
        li.className = pages[i].class;
        ul.appendChild(li);
    }

    // find how to delete content from DIV
    // document.getElementById('pagination').get
    document.getElementById('pagination').innerHTML = '';
    document.getElementById('pagination').appendChild(div);
}