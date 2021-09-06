const CANDIDATE = "ROLE_CANDIDATE";
const EMPLOYER = "ROLE_EMPLOYER";

function showInputsByRole() {
  var role = document.getElementById("role");
  var candidate = document.getElementById("candidate-display");
  if (role.value == CANDIDATE) {
    candidate.style.display = "block";
  } else {
    candidate.style.display = "none";
  }
}


var loadFile = function(event) {
    var reader = new FileReader();
    reader.onload = function(){
      var output = document.getElementById('output');
      output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
  };