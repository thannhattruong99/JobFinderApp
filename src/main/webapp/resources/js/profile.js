function confirmMsg() {
  return confirm('Are you sure?');
}


var loadFile = function(event) {
    var reader = new FileReader();
    reader.onload = function(){
      var output = document.getElementById('output');
      output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
};

var loadFile2 = function(event) {
    var reader = new FileReader();
    reader.onload = function(){
      var output = document.getElementById('output2');
      output.href = reader.result;
      output.style.display = 'block'
    };
    reader.readAsDataURL(event.target.files[0]);
};