var url = "/getCuisinesData";

function getCuisines() {
    var XHR = ("onload" in new XMLHttpRequest()) ? XMLHttpRequest : XDomainRequest;
    var xhr = new XHR();
    xhr.open('GET', url, true);
    xhr.onload = function() {
        json_data= JSON.parse(this.responseText);
        fillPageCuisines(json_data);
    };
    xhr.onerror = function() {
        console.log( 'Ошибка ' + this.status);
    };
    xhr.send();
}

function fillPageCuisines(data) {
    var ulCuisines = document.getElementById("left_menu");
    data.cuisines.forEach(function (cuisine) {
        var li = document.createElement("li");
        var aName = document.createElement("a");
        //aName.href = "/getRestaurantsShortData?searchCriterion=" + "cuisine" + "&value=" + cuisine.id;
        aName.href ="#";
        aName.innerHTML = cuisine.name;
        li.appendChild(aName);
        ulCuisines.appendChild(li);
    })
}

$(document).on('click', '#left_menu *', function(e) {
    getRestaurantsByCuisine(e.toElement.text);
});
