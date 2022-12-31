
var close = 0
var search = document.querySelector('.search-container')

function searchBox() {
    // alert("JS is LInked")
    if (close == 0) {
        search.style.display = "block"
        return close = 1;
    } else {
        search.style.display = "none"
        return close = 0;
    }
}

var tor_details = document.querySelectorAll('.tor-det');
var cards = document.querySelectorAll('.card');

for (let i = 0; i < cards.length; i++) {
    cards[i].addEventListener('mouseover', function handleMouseOver() {
        tor_details[i].style.visibility = "visible";
        cards[i].querySelector('img').style.opacity = "0.3";
    })
}

for (let i = 0; i < cards.length; i++) {
    cards[i].addEventListener('mouseout', function handleMouseOver() {
        tor_details[i].style.visibility = "hidden";
        cards[i].querySelector('img').style.opacity = '1'
        
    })
}
