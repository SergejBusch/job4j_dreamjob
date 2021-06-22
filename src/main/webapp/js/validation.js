const form = document.querySelector('.main-form');
const para = document.querySelector('.message');
const inputs = document.querySelectorAll('input');

form.addEventListener('submit', validate);

function validate(e) {
    e.preventDefault();
    let allValid = true;


    inputs.forEach((input) => {
        if (!input.value) {
            allValid = false;
        }
    });

    if (allValid) {
        form.submit();
    } else {
        para.textContent = 'Please write something in each input field';
    }

}


