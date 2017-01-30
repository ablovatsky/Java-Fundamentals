var json_data;
var address = new Map();
function get_data() {
    var url = "/administration/add-restaurants";
    var XHR = ("onload" in new XMLHttpRequest()) ? XMLHttpRequest : XDomainRequest;
    var xhr = new XHR();
    xhr.open('POST', url, true);
    xhr.onload = function() {
        json_data= JSON.parse(this.responseText);
        fillCountriesAndCuisines();
    };
    xhr.onerror = function() {
        console.log( 'Ошибка ' + this.status);
    };
    xhr.send();
}

function fillCountriesAndCuisines() {
    var countries = document.getElementById("countries");
    var cuisines = document.getElementById("cuisines");

    json_data.cuisines.forEach(function (cuisine) {
        var new_input = document.createElement("input");
        new_input.type = "checkBox";
        new_input.name = "cuisine";
        new_input.id = cuisine.name;
        new_input.value = cuisine.id;
        var new_label = document.createElement("label");
        new_label.from = cuisine.name;
        new_label.innerHTML = cuisine.name;
        cuisines.appendChild(new_input);
        cuisines.appendChild(new_label);
    });


    json_data.countries.forEach(function (country) {
        var option = document.createElement('OPTION');
        option.text = country.name;
        option.value = country.id;
        countries.add(option);
    })
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
        json_data.countries[selectedIndex - 1].cities.forEach(function (city) {
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

function addRestaurant() {
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

        json += "{ \"name\": \"" + restaurantName + " \", ";
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
        var url = "/loading/image";
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
