var url = "/getRestaurantsShortData";
var totalRouting;

function getRestaurants(routing) {
    if (routing) {
        totalRouting = routing;
    }
    var XHR = ("onload" in new XMLHttpRequest()) ? XMLHttpRequest : XDomainRequest;
    var xhr = new XHR();
    xhr.open('GET', url, true);
    xhr.onload = function() {
        json_data= JSON.parse(this.responseText);
        fillPageRestaurants(json_data);
    };
    xhr.onerror = function() {
        console.log( 'Ошибка ' + this.status);
    };
    xhr.send();
}

function fillPageRestaurants(data) {

    var divRestaurants = document.getElementById("restaurants");
    divRestaurants.innerHTML = '';
    data.restaurants.forEach(function (restaurant) {
        var br = document.createElement("br");
        var divRestaurant = document.createElement("div");
        divRestaurant.id = "restaurant";
        var divRestaurantImage = document.createElement("div");
        divRestaurantImage.id = "rest_img";
        var aImage = document.createElement("a");
        aImage.href = "/restaurant?id=" + restaurant.id;
        var image = document.createElement("img");
        image.src = "/image?index=" + restaurant.id;
        image.alt = restaurant.name;
        var divRestaurantInformation = document.createElement("div");
        divRestaurantInformation.id = "rest_inf";
        var aName = document.createElement("a");
        var deleteRestaurant = document.createElement("a");
        if (totalRouting == "edit") {
            deleteRestaurant.href = "/delete?id=" + restaurant.id;
            deleteRestaurant.innerHTML = "   Удалить";
            aName.href = "/restaurant?id=" + restaurant.id;
            aName.innerHTML = restaurant.name + "   \tРедактировать";
        } else {
            aName.href = "/restaurant?id=" + restaurant.id;
            aName.innerHTML = restaurant.name;
        }


        var aWebsite = document.createElement("a");
        aWebsite.href = restaurant.website;
        aWebsite.innerHTML = restaurant.website;

        var shortInformation = document.createElement("p");
        shortInformation.innerHTML = restaurant.shortInformation;

        divRestaurantInformation.appendChild(aName);
        if (totalRouting == "edit") {
            divRestaurantInformation.appendChild(deleteRestaurant);
        }
        divRestaurantInformation.appendChild(shortInformation);
        divRestaurantInformation.appendChild(aWebsite);

        aImage.appendChild(image);

        divRestaurantImage.appendChild(aImage);

        divRestaurant.appendChild(divRestaurantImage);
        divRestaurant.appendChild(divRestaurantInformation);

        divRestaurants.appendChild(divRestaurant);

    })
}

function getRestaurantsBy() {
    var divRestaurants = document.getElementById("restaurants");
    divRestaurants.innerHTML = '';
    var value = document.getElementById("search_value").value;
    var selected = document.getElementById("search_type");
    var searchCriterion = selected.options[selected.selectedIndex].value;
    url = "/getRestaurantsShortData?searchCriterion="+ searchCriterion + "&value=" + value;
    getRestaurants();
}

function getRestaurantsByCuisine(value) {
    var divRestaurants = document.getElementById("restaurants");
    divRestaurants.innerHTML = '';
    url = "/getRestaurantsShortData?searchCriterion="+ "cuisine" + "&value=" + value;
    getRestaurants();
}
