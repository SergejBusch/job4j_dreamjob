const select = document.querySelector('select');

async function loadCities() {
    const response = await fetch('http://localhost:8080/job4j_dreamjob/city');
    const data =  await response.json();
    addToSelect(data);
}

function addToSelect(dataArray) {
    dataArray.forEach((c) => {
        console.log(c);
        let opt = document.createElement('option');
        opt.value = c.id;
        opt.innerHTML = c.city;
        select.appendChild(opt);
    });
}

loadCities();