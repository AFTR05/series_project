document.getElementById('submitForm').addEventListener('click', function() {
    const titulo = document.getElementById('titulo').value;
    const descripcion = document.getElementById('descripcion').value;
    const durationString = document.getElementById('duration').value;
    const fecha = new Date(document.getElementById('fecha').value).toISOString();

    // Convertir "HH:mm:ss" a formato "PT#H#M#S"
    const [hours, minutes, seconds] = durationString.split(':');
    const duration = `PT${hours}H${minutes}M${seconds}S`;

    const data = {
        title: titulo,
        description: descripcion,
        duration: duration, // Duración en formato ISO
        startDate: fecha,
        season_id: localStorage.getItem('selectedSeason'),
        data_state: true
    };

    fetch('http://localhost:8085/api/episode/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(err => { throw new Error(err.message || 'Error en la red'); });
            }
            return response.json();
        })
        .then(data => {
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: `Se ha creado exitosamente el capítulo: ${titulo}`,
                timer: 2000,
                showConfirmButton: false
            }).then(() => {
                location.reload();
            });
        })
        .catch((error) => {
            console.error('Error:', error);
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Hubo un problema al crear el capítulo. Por favor, intenta de nuevo.'
            });
        });
});

$(document).ready(function() {
    getEpisodes();
});

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };
}
async function getEpisodes() {
    try {
        const request = await fetch('http://localhost:8085/api/episode/get-all', {
            method: 'GET',
            headers: getHeaders()
        });

        if (!request.ok) {
            throw new Error('Network response was not ok: ' + request.statusText);
        }

        const response = await request.json();
        console.log('Episodes response:', response);

        // Accede a la propiedad 'episodes' del objeto
        const episodes = response.episodes;

        // Verifica si episodes es un array
        if (!Array.isArray(episodes)) {
            console.error('Expected an array but received:', episodes);
            throw new TypeError('Expected episodes to be an array');
        }

        // Obtén el número de temporada del localStorage
        const selectedSeason = localStorage.getItem('selectedSeason');
        document.querySelector('#titulo_temp').innerHTML = `<h2>Temporada ${selectedSeason}</h2>`;

        let episodesContentHtml = '';

        // Recorre cada episodio y crea una lista estilizada
        for (let episode of episodes) {
            episodesContentHtml += `
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">${episode.title}</h5>
                        <p class="card-text">Duración: <strong>${episode.duration}</strong></p>
                        <p class="card-text">Fecha de lanzamiento: <strong>${new Date(episode.startDate).toLocaleDateString()}</strong></p>
                    </div>
                </div>`;
        }

        // Actualiza el contenido de la lista de episodios
        document.querySelector('#episodes_content').innerHTML = episodesContentHtml;
    } catch (error) {
        console.error('Error fetching episodes:', error);
    }
}



