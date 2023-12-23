document.addEventListener('DOMContentLoaded', function () {
    // Get the modal
    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var lostSignButton = document.getElementById('lost-sign');

    // Get the <span> element that closes the modal
    var closeBtn = document.getElementsByClassName('close')[0];

    // When the user clicks the button, open the modal
    lostSignButton.onclick = function () {
        modal.style.display = 'block';
    };

    // When the user clicks on <span> (x), close the modal
    closeBtn.onclick = function () {
        modal.style.display = 'none';
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    };

    // Handle the "Xác nhận" button click event
    var confirmBtn = document.getElementById('confirmBtn');
    confirmBtn.onclick = function () {
        var emailValue = document.getElementById('email').value;
        var passwordValue = document.getElementById('password').value;

        // Do something with the email and password values, e.g., send them to a server
        console.log('Email:', emailValue);
        console.log('Password:', passwordValue);

        // Close the modal after processing
        modal.style.display = 'none';
    };
});


document.addEventListener('DOMContentLoaded', function () {
    // Get the modal
    var modal = document.getElementById('myModal_2');

    // Get the button that opens the modal
    var lostSignButton = document.getElementById('change-email');

    // Get the <span> element that closes the modal
    var closeBtn = document.getElementById('closeModal_2');

    // When the user clicks the button, open the modal
    lostSignButton.onclick = function () {
        modal.style.display = 'block';
    };

    // When the user clicks on <span> (x), close the modal
    closeBtn.onclick = function () {
        modal.style.display = 'none';
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    };

    // Handle the "Xác nhận" button click event
    // var confirmBtn = document.getElementById('confirmBtn_2');
    // confirmBtn.onclick = function () {
    //     modal.style.display = 'none';
    // };
});
