document.querySelectorAll(".switch").forEach(function(item) {
   item.addEventListener("click", function(event){
       if(event.target.checked){
         var radioVal = event.target.value;
         $(".field_specific").each(function(){
            if($(this).attr("id") == radioVal){
               $(this).show();
            } else{
               $(this).hide();
            }
         });
       }
   }
)});