/**
 * Handles AJAX requests in order to perform Autocomplete functionality
 */

window.onload=function(){
	var request=new XMLHttpRequest();
	var uploadData=new XMLHttpRequest();
	var getData=new XMLHttpRequest();
	document.getElementById("search").addEventListener("input",function(){
		var dropdown=document.getElementById("dropdown");
		for (var i=dropdown.length-1;i>=0;i--){
			dropdown.remove(dropdown[i]);
		}
		var entry=document.getElementById("search").value;
		request.open('GET','TrieDriver?'+entry);
		request.send();
		request.onload=function(){
			words=JSON.parse(request.responseText);
			entry.replace(/^\s+|\s+$/g, "");
			if (entry!=""){
				words.forEach(function(element){
					/*Finish drop down in here without select if possible*/
					var listItems=document.createElement("option");
					listItems.value=element.word;
					listItems.innerHTML=element.word;
					dropdown.append(listItems);
					console.log(listItems.innerHTML);
				});
			}
			console.log("\n");
		};
	});
}

