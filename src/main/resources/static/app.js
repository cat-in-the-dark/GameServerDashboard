(function() {
    'use strict';
    L.mapbox.accessToken = 'pk.eyJ1Ijoic2lnYW4iLCJhIjoiY2luZWVwN2RuMDA2Z3c3bHlnNjdrcGRwYyJ9.dG1BZ37cch38eZMYG_fyPw';
    
    ready(function() {
        var map = L.mapbox.map('map', 'mapbox.streets');
        
        var request = new XMLHttpRequest();
        request.open('GET', '/api/games.json', true);
        request.onload = function() {
            if (request.status >= 200 && request.status < 400) {
                var data = JSON.parse(request.responseText);
                data.forEach(function(room) {
                    var polyline = L.polyline([]).addTo(map);
                    room.players.forEach(function(player) {
                        if (player.geo) {
                            var l = L.marker([player.geo.lat, player.geo.lon], {title: player.status}).addTo(map);
                            polyline.addLatLng(l.getLatLng());
                        }
                    });
                });
            } else {
                console.error(request.status);
            }
        };
        request.send();
    });

    function ready(fn) {
        if (document.readyState != 'loading'){
            fn();
        } else {
            document.addEventListener('DOMContentLoaded', fn);
        }
    }
})();