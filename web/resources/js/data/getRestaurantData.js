var url = "/getRestaurantData";
var totalRouting;

function getRestaurant(routing) {
    if (routing) {
        totalRouting = routing;
    }
    var XHR = ("onload" in new XMLHttpRequest()) ? XMLHttpRequest : XDomainRequest;
    var xhr = new XHR();
    xhr.open('GET', url, true);
    xhr.onload = function() {
        json_data= JSON.parse(this.responseText);
        fillPageRestaurant(json_data);
    };
    xhr.onerror = function() {
        console.log( 'Ошибка ' + this.status);
    };
    xhr.send();
}

function fillPageRestaurant(data) {
    var restaurant = data.restaurant;
    var headInformation = document.getElementById("headInformation");
    headInformation.innerHTML = restaurant.name;
    var textInformation = document.getElementById("textInformation");
    var image = document.createElement("img");
    image.src = "/image?index=" + restaurant.id;
    image.alt = restaurant.name;
    var information = document.createElement("p");
    information.innerHTML = restaurant.information;
    textInformation.appendChild(image);
    textInformation.appendChild(information);

    var cuisinesValue = document.getElementById("cuisinesValue");
    restaurant.cuisines.forEach(function (cuisine) {
        var cuis = document.createElement("p");
        cuis.innerHTML = cuisine.name;
        cuisinesValue.appendChild(cuis);
    });

    var workingHoursValue = document.getElementById("workingHoursValue");
    headInformation.innerHTML = restaurant.workingHours;
    var phoneValue = document.getElementById("phoneValue");
    headInformation.innerHTML = restaurant.phone;

    var addressesValue = document.getElementById("addressesValue");
    restaurant.addresses.forEach(function (address) {
        var addr = document.createElement("p");
        addr.innerHTML = addr.name + addr.country.name;
        addressesValue.appendChild(addr);
    });

    var websiteValue = document.getElementById("websiteValue");
    websiteValue.innerHTML = restaurant.website;

    var comments = document.getElementById("comments");
    restaurant.comments.forEach(function (comment) {
        var divComment = document.createElement("div");
        divComment.id = "comments";

        var divUser = document.createElement("div");
        divUser.id = "username";
        var pUser = document.createElement("p");
        pUser.innerHTML = comment.username;
        divUser.appendChild(pUser);

        var divDate = document.createElement("div");
        divDate.id = "date";
        var pDate = document.createElement("p");
        pDate.innerHTML = comment.date;
        divDate.appendChild(pDate);

        var divText = document.createElement("date");
        divText.id = "textComment";
        var pText = document.createElement("p");
        pText.innerHTML = comment.comment;
        divText.appendChild(pText);

        divComment.appendChild(divUser);
        divComment.appendChild(divDate);
        divComment.appendChild(divText);

        comments.appendChild(divComment);

    });



}
