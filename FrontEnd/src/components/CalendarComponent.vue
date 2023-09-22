<script setup>
import { ref, computed, onMounted } from 'vue';

const escuelaSeleccionada = ref('');
const mesSeleccionado = ref('');
const meses = {
    enero: 31,
    febrero: 28,
    marzo: 31,
    abril: 30,
    mayo: 31,
    junio: 30,
    julio: 31,
    agosto: 31,
    septiembre: 30,
    octubre: 31,
    noviembre: 30,
    diciembre: 31
};
const diasDelMes = computed(() => {
    return Array.from({ length: meses[mesSeleccionado.value] }, (_, index) =>
        (index + 1).toString().padStart(2, '0')
    );
});

function actualizarCalendario() {
    //*************************************
    //  // Obtiene la lista de usuarios para el mes seleccionado (debes reemplazar esto con tu lógica real)
    //   const usuariosDelMes = obtenerUsuariosDelMes(mesSeleccionado.value); // Supongamos que tienes una función que obtiene los usuarios

    //   // Actualiza las celdas del calendario con los eventos de los usuarios
    //   usuariosDelMes.forEach((usuario) => {
    //     const filaUsuario = document.querySelector(`tr[title="${usuario.nombre}"]`);
    //     if (filaUsuario) {
    //       const diaEvento = usuario.diaEvento; // Reemplaza con el campo real que indica el día del evento
    //       const celda = filaUsuario.querySelector(`td:nth-child(${diaEvento + 1})`); // +1 porque el índice comienza en 0
    //       if (celda) {
    //         celda.textContent = 'X'; // Puedes mostrar un marcador o información adicional aquí
    //       }
    //     }
    //   });

    //*************************************************
    // Limpia las celdas de la tabla
    //    const filasUsuarios = document.querySelectorAll('tr[title^="User"]');
    //   filasUsuarios.forEach((filaUsuario) => {
    //     const celdas = filaUsuario.querySelectorAll('td');
    //     celdas.forEach((celda) => {
    //       celda.textContent = '';
    //     });
    //   });
}

onMounted(() => {
    // Llama a actualizarCalendario() para inicializar el calendario
    actualizarCalendario();
});
</script>

<template>
    <div class="calendar_container">
        <h2>Calendario</h2>
        <label for="escuela">Selecciona Escuela:</label>
        <select id="escuela" class="custom-select" v-model="escuelaSeleccionada" @change="actualizarCalendario">
            <option value="">Selecciona Escuela</option>
            <option value="asturias">Asturias</option>
            <option value="barcelona">Barcelona</option>
            <option value="madrid">Madrid</option>
            </select>
            <label for="mes">Selecciona Mes:</label>
            <select id="mes" class="custom-select" v-model="mesSeleccionado" @change="actualizarCalendario">
            <option value="">Selección mes</option>
            <option value="enero">Enero</option>
            <option value="febrero">Febrero</option>
            <option value="marzo">Marzo</option>
            <option value="abril">Abril</option>
            <option value="mayo">Mayo</option>
            <option value="junio">Junio</option>
            <option value="julio">Julio</option>
            <option value="agosto">Agosto</option>
            <option value="septiembre">Septiembre</option>
            <option value="octubre">Octubre</option>
            <option value="noviembre">Noviembre</option>
            <option value="diciembre">Diciembre</option>
        </select>

        <div v-if="mesSeleccionado" class="calendario_vista">
            <h4>{{ mesSeleccionado }}</h4>
            <div class="calendario_vista">
                <table class="calendar" cellspacing="0">
                    <tbody>
                        <tr title="daysNum">
                            <th></th>
                            <th v-for="dia in diasDelMes">{{ dia }}</th>
                        </tr>

                        <tr title="User1">
                            <th>user 1</th>
                            <td>x</td>
                            <td>x</td>
                            <td>x</td>
                            <td>x</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>

                        <tr title="User2">
                            <th>user 2</th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>x</td>
                            <td>x</td>
                            <td>x</td>
                            <td>x</td>
                            <td>x</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>

                        <tr title="user 3">
                            <th>user 3</th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>

                    <tr title="user 4">
                        <th>user 4</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>

                    <tr title="user 5">
                        <th>user 5</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>

                    <tr title="user 6">
                        <th>user 6</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
</template>

<style>.calendario-container {
    max-width: 100%;
    overflow: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
}

.custom-select {
    background-color: #FFA37F;
    padding: 5px;
    display: block;
    margin-bottom: 5px;
}

.calendario_vista {
    max-width: 100%;
    overflow: auto;
}

table.calendar {
    border-collapse: collapse;
    empty-cells: show;
    font-family: Arial, sans-serif;
}

table.calendar tr {
    vertical-align: bottom;
}

table.calendar th {
    border: 2px solid #f3f3f3;
    font-size: 14px;
    font-weight: bold;
    padding: 2px;
    vertical-align: middle;
    white-space: nowrap;
}

table.calendar td {
    border: 2px solid #f3f3f3;
    color: red;
    font-size: 14px;
    font-weight: bold;
    height: 14px;
    padding: 1px;
    text-align: center;
    text-transform: uppercase;
    vertical-align: middle;
    width: 14px;
}

.asturias .calendar th,
.asturias .calendar td {
    border-color: blue;
}

.madrid .calendar th,
.madrid .calendar td {
    border-color: red;
}

.barcelona .calendar th,
.barcelona .calendar td {
    border-color: yellow;
}


</style>
