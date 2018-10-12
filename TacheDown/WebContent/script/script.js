/**
 * 
 */
function moveEditionTache() {

			let editionTache = $("#taches\\:editionTache");
            let eltCache=$(".cache");
			if (editionTache) {
				if(eltCache)editionTache.insertAfter(eltCache);
				else {
					editionTache.remove();
					
				}
			}
		}
		
$(document).ready(function() {
			$("#criteresTaches\\:categories_panel").mouseout(function(event) {
				let element = $("#taches\\:categories_panel");
				element.hide();

			});
			
		});
		

function activerDeplacement(e){

	let parent=$("#taches\\:editionTache");
	console.log(parent);
	parent.draggable({
       helper:  function() {
    	   return $("<button  class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only '" +
    	   		"  style=' width:350px; background-color:"+parent[0].style.backgroundColor+";' type='submit'>" +
    	   				"<span class='ui-button-text ui-c'>"+$("#taches\\:texte").val()+"</span></button>");
       }, 
       scope: 'deplacementTache',
       zIndex: ++PrimeFaces.zindex
    });
	
	
    $('#taches\\:listeTaches button').droppable({
       activeClass: 'ui-state-active',
       hoverClass: 'ui-state-highlight',
       tolerance: 'pointer',
       scope: 'deplacementTache',
       drop: function(event, ui) {
    	  
    	  
    	   var regExp=new RegExp(/taches:listeTaches:(\d):tache/)
    	   var tab = regExp.exec($(this)[0].id);
    	   console.log(idTaches);
    	   console.log("drop sur id "+idTaches[tab[1]]);
    	   deplacementTache([
                {name: 'idTache', value: idTaches[tab[1]] }
           ]);
       }
    });
    }


function activerLiaison(e){

	let parent=$("#taches\\:editionTache");
	console.log(parent);
	parent.draggable({
       helper:  function() {
    	   return $("<button  class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only '" +
    	   		"  style=' width:350px; background-color:"+parent[0].style.backgroundColor+";' type='submit'>" +
    	   				"<span class='ui-button-text ui-c'>"+$("#taches\\:texte").val()+"</span></button>");
       }, 
       scope: 'dependanceTache',
       zIndex: ++PrimeFaces.zindex
    });
	
	
    $('#taches\\:listeTaches button').droppable({
       activeClass: 'ui-state-active',
       hoverClass: 'ui-state-highlight',
       tolerance: 'pointer',
       scope: 'dependanceTache',
       drop: function(event, ui) {
    	  
    	  
    	   var regExp=new RegExp(/taches:listeTaches:(\d):tache/)
    	   var tab = regExp.exec($(this)[0].id);
    	   console.log(idTaches);
    	   console.log("drop sur id "+idTaches[tab[1]]);
    	   dependanceTache([
                {name: 'dependantDe', value: idTaches[tab[1]] }
           ]);
       }
    });
    }

		