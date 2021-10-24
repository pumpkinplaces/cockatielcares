const uploadButton = document.querySelector("#uploadButton");
const choosepfp = document.querySelector("#choosepfp");
const file = document.querySelector('#pfpfile');
const img = document.querySelector('#nopfppic');

choosepfp.addEventListener('mouseenter', function(){
	uploadButton.style.display = 'block';
});
choosepfp.addEventListener('mouseleave',function(){
	uploadButton.style.display = 'none';
});

file.addEventListener('change', function(){
	const selectedFile = this.files[0];
	const reader = new FileReader();
	reader.addEventListener('load', function(){
		img.setAttribute('src', reader.result);
	});
	reader.readAsDataURL(selectedFile);
	
});