const select = document.querySelector('select');

async function loadCities() {
    const response = await fetch('http://localhost:8081/job4j_dreamjob/city');
    const data =  await response.json();
    addToSelect(data);
}

function addToSelect(dataArray) {
    dataArray.forEach((c) => {
        let opt = document.createElement('option');
        opt.value = c.id;
        opt.innerHTML = c.city;
        select.appendChild(opt);
    });

    if (select.dataset.city_id !== '0') {
        select.value = select.dataset.city_id;
    }
}

loadCities();