const divs = document.querySelectorAll('.city');

async function loadCities() {
    const response = await fetch('http://localhost:8080/job4j_dreamjob/city');
    const data =  await response.json();
    showCities(data);
}

function showCities(data) {
    divs.forEach((div) => {
        let id = div.dataset.city_id;
        if (id != 0) {
            for (const cityData of data) {
                if (id == cityData.id) {
                    div.innerHTML = cityData.city;
                    break;
                }
            }
        }
    });
}

loadCities();
