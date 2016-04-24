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
                var playedGames = 0;
                var sessions = [];
                data.forEach(function(room) {
                    var polyline = L.polyline([]).addTo(map);
                    var from = [];
                    room.players.forEach(function(player) {
                        if (player.geo) {
                            var l = L.marker([player.geo.lat, player.geo.lon], {title: player.status}).addTo(map);
                            polyline.addLatLng(l.getLatLng());
                            from.push(player.geo.countryCode);
                        }
                    });
                    if (room.played && room.players && room.players.length == 2) {
                        playedGames++;
                        var sessionTime = (room.players[0].disconnectedAt + room.players[1].disconnectedAt) / 2.0 - room.startedAt;
                        sessions.push({
                            sessionTime: sessionTime / 1000,
                            from: from.join(' vs ')
                        });
                    }
                });
                var games = [];
                sessions.forEach(function (s) {
                    games.push("<tr><td>" + s.sessionTime + "</td><td>" + s.from + "</td></tr>")
                });
                var gamesTable = "<table><thead><tr><th>Session Time in sec.</th><th>Between</th></tr></thead><tbody>"+games.join('')+"</tbody></table>";
                document.getElementById('legend').innerHTML = "<p>Played " + playedGames + " games</p>" + gamesTable;
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
