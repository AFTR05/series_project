$(document).ready(function() {
    getSeasons();
});

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };
}

async function getSeasons() {
    try {
        const request = await fetch('http://localhost:8085/api/season/get-all', {
            method: 'GET',
            headers: getHeaders()
        });

        if (!request.ok) {
            throw new Error('Network response was not ok: ' + request.statusText);
        }

        const response = await request.json();
        console.log('Seasons response:', response); // Depuración

        // Accede a la propiedad 'seasons' del objeto
        const seasons = response.seasons; // Aquí accedemos al array de temporadas

        // Verifica si seasons es un array
        if (!Array.isArray(seasons)) {
            console.error('Expected an array but received:', seasons);
            throw new TypeError('Expected seasons to be an array');
        }

        let listadoHtml = '';
        for (let season of seasons) {
            let seasonHtml = `
                <button class="btn btn-secondary season-button" onclick="handleSeasonClick(${season.seasonNumber})">
                    Temporada ${season.seasonNumber} (${new Date(season.startDate).toLocaleDateString()})
                </button>`;
            listadoHtml += seasonHtml;
        }

        // Actualiza el contenido del contenedor donde se mostrarán los botones
        document.querySelector('#season-buttons').innerHTML = listadoHtml;
    } catch (error) {
        console.error('Error fetching seasons:', error);
    }
}

// Manejo de clic en el botón de temporada
function handleSeasonClick(seasonNumber) {
    localStorage.setItem('selectedSeason', seasonNumber);
    window.location.href = 'episodes.html';
}
