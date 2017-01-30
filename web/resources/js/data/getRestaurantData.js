var url = "/getRestaurantData";
var address = new Map();
var totalRouting;
var data;
function getRestaurant(routing) {
    if (routing != "") {
        totalRouting = routing;
    }
    var XHR = ("onload" in new XMLHttpRequest()) ? XMLHttpRequest : XDomainRequest;
    var xhr = new XHR();
    xhr.open('GET', url, true);
    xhr.onload = function() {
        json_data= JSON.parse(this.responseText);
        data = json_data;
        fillPageRestaurant();
    };
    xhr.onerror = function() {
        console.log( 'Ошибка ' + this.status);
    };
    xhr.send();
}

function fillPageRestaurant() {
    var restaurant = data.restaurant;
    if (totalRouting == "edit") {
        var restaurantName = document.getElementById("restaurantName");
        restaurantName.value = restaurant.name;
        var shortInformation = document.getElementById("shortInformation");
        shortInformation.value = restaurant.shortInformation;
        var information = document.getElementById("information");
        information.value = restaurant.information;
        var workingHours = document.getElementById("workingHours");
        workingHours.value = restaurant.workingHours;
        var phone = document.getElementById("phone");
        phone.value = restaurant.phone;
        var website = document.getElementById("website");
        website.value = restaurant.website;

        var countries = document.getElementById("countries");
        var cuisines = document.getElementById("cuisines");

        data.cuisines.forEach(function (cuisine) {
            var new_input = document.createElement("input");
            new_input.type = "checkBox";
            restaurant.cuisines.forEach(function (restCuisine) {
                if ( cuisine.name == restCuisine.name) {
                    new_input.checked = true;
                }
            });
            new_input.name = "cuisine";
            new_input.id = cuisine.name;
            new_input.value = cuisine.id;
            var new_label = document.createElement("label");
            new_label.from = cuisine.name;
            new_label.innerHTML = cuisine.name;
            cuisines.appendChild(new_input);
            cuisines.appendChild(new_label);
        });

        data.countries.forEach(function (country) {
            var option = document.createElement('OPTION');
            option.text = country.name;
            option.value = country.id;
            countries.add(option);
        })
        var addrStr = "";
        restaurant.addresses.forEach(function (t_address) {
            addrStr += t_address.name+ ", " + t_address.country.name + "; "
            address.set(t_address.country.id, t_address.id);
        });
        var addresses = document.getElementById("addresses");
        addresses.value = addrStr;
    } else {
        var headInformation = document.getElementById("headInformation");
        headInformation.innerHTML = restaurant.name;
        var download = document.getElementById("download");
        download.href = "/image?index=" + restaurant.id + "&download=download";
        download.innerHTML = "Скачать картинку";

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
        workingHoursValue.innerHTML = restaurant.workingHours;
        var phoneValue = document.getElementById("phoneValue");
        phoneValue.innerHTML = restaurant.phone;

        var addressesValue = document.getElementById("addressesValue");
        var addr = document.createElement("p");
        var addrStr = "";
        restaurant.addresses.forEach(function (address) {
            addrStr += address.name+ ", " + address.country.name + "; "
        });
        addr.innerHTML = addrStr;
        addressesValue.appendChild(addr);
        var websiteValue = document.getElementById("websiteValue");
        websiteValue.innerHTML = restaurant.website;

        var comments = document.getElementById("comments");
        data.comments.forEach(function (comment) {
            var divComment = document.createElement("div");
            divComment.id = "comment";

            var divUser = document.createElement("div");
            divUser.id = "username";
            var pUser = document.createElement("p");
            pUser.innerHTML = comment.user.username;
            divUser.appendChild(pUser);

            var divDate = document.createElement("div");
            divDate.id = "date";
            var pDate = document.createElement("p");
            pDate.innerHTML = comment.date;
            divDate.appendChild(pDate);

            var divText = document.createElement("div");
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


}

function addComment(user) {
    url = "/addComment";
    var comment = document.getElementById("newComment").value;
    var json ="{restaurant: {id: " + data.restaurant.id + "}, user: {id: " + user + " }, comment: \"" + comment.trim() + "\" }";
    var XHR = ("onload" in new XMLHttpRequest()) ? XMLHttpRequest : XDomainRequest;
    var xhr = new XHR();
    xhr.open('POST', url, true);
    xhr.onload = function() {
        location.reload();
    };
    xhr.onerror = function() {
        location.reload();
    };
    xhr.send(json);
}


function fillCities() {
    var countries = document.getElementById("countries");
    var cities = document.getElementById("cities");
    cities.options.length = 0;
    var selectedIndex = countries.options[countries.selectedIndex].value;
    var option = document.createElement('OPTION');
    option.text = "Ваберите город";
    option.value = 0;
    cities.add(option);
    if (selectedIndex > 0) {
        data.countries[selectedIndex - 1].cities.forEach(function (city) {
            option = document.createElement('OPTION');
            option.text = city.name;
            option.value = city.id;
            cities.add(option);
        })
    }
}

function addAddress() {
    var countries = document.getElementById("countries");
    var cities = document.getElementById("cities");

    var countrySelectedIndex = countries.options[countries.selectedIndex].value;
    var citySelectedIndex = cities.options[cities.selectedIndex].value;
    var countrySelected = countries.options[countries.selectedIndex].text;
    var citySelected = cities.options[cities.selectedIndex].text;
    if (countrySelectedIndex != 0 && citySelectedIndex != 0) {
        var addresses = document.getElementById("addresses");
        addresses.value += countrySelected + ", " + citySelected + "; ";
        address.set(countrySelectedIndex, citySelectedIndex);
    }
}

function editRestaurant() {
    var image = document.getElementById("image").files[0];
    var restaurantName = document.getElementById("restaurantName").value;
    var shortInformation = document.getElementById("shortInformation").value;
    var information = document.getElementById("information").value;
    var cuisines = document.getElementsByName("cuisine");
    var workingHours = document.getElementById("workingHours").value;
    var phone = document.getElementById("phone").value;
    var addresses = document.getElementById("addresses").value;
    var website = document.getElementById("website").value;
    if (image != "" &&
        restaurantName != "" &&
        shortInformation != "" &&
        information != "" &&
        cuisines.length != 0 &&
        workingHours != "" &&
        phone != "" &&
        addresses != "" &&
        website != "") {
        var json = "";
        json += "{ \"id\": \"" + data.restaurant.id + " \", ";
        json += "\"name\": \"" + restaurantName + " \", ";
        json += "\"website\": \"" + website + " \", ";
        json += "\"shortInformation\": \"" + shortInformation + " \", ";
        json += "\"phone\": \"" + phone + " \", ";
        json += "\"workingHours\": \"" + workingHours + " \", ";
        json += "\"addresses\": [";
        var cityList = "";
        var cityCount = 0;
        address.forEach(function (value) {
            if (cityCount == 0) {
                cityList += "{\"id\": " + value + "}";
            } else {
                cityList += ", {\"id\": " + value + "}";
            }
            cityCount ++;
        });
        json += cityList;
        json += "], ";
        json += "\"cuisines\": [";
        var cuisineList = "";
        var cuisineCount = 0;
        cuisines.forEach(function (cuisine) {
            if (cuisine.checked) {
                if (cuisineCount == 0) {
                    cuisineList += "{\"id\": " + cuisine.value + "}";
                } else {
                    cuisineList += ", {\"id\": " + cuisine.value + "}";
                }
                cuisineCount ++;
            }
        });
        json += cuisineList;
        json += "], ";
        json += "\"information\": \"" + information + " \"}";


        var formData = new FormData();
        formData.append("image", image);
        formData.append("json", json);
        var url = "/edit/restaurant";
        var XHR = ("onload" in new XMLHttpRequest()) ? XMLHttpRequest : XDomainRequest;
        var xhr = new XHR();
        xhr.open('POST', url, true);
        xhr.onload = function() {
            json_data= this.responseText;
        };
        xhr.onerror = function() {
            console.log( 'Ошибка ' + this.status);
        };
        xhr.send(formData);
    } else {
        alert("Заполните все поля");
    }
}
