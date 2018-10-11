/**
 * 
 */
function moveEditionTache() {

			let editionTache = $("#taches\\:editionTache");
            let eltCache=$(".cache");
			if (editionTache) {
				if(eltCache)editionTache.insertAfter(eltCache);
				else editionTache.remove();
			}
		}
		
$(document).ready(function() {
			$("#criteresTaches\\:categories_panel").mouseout(function(event) {
				let element = $("#taches\\:categories_panel");
				element.hide();

			});
			moveEditionTache();
		});
		

function activerDragDrop(e){
	e.draggable();
	$("#listeTaches").droppable({
		  drop: function() {
			    alert( "dropped" );
			  }
	});
}

		