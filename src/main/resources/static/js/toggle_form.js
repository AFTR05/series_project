function toggleForm() {
    const formContainer = document.getElementById('formContainer');
    const button = document.getElementById('openModal');

    // Alternar la visibilidad del formulario
    if (formContainer.classList.contains('hidden')) {
        formContainer.classList.remove('hidden');
        button.classList.add('active'); // Cambia el estilo del botón
    } else {
        formContainer.classList.add('hidden');
        button.classList.remove('active'); // Quita el estilo del botón
    }
}

// Validación del formulario
(function () {
    'use strict'
    const forms = document.querySelectorAll('.needs-validation')
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }
                form.classList.add('was-validated')
            }, false)
        })
})()