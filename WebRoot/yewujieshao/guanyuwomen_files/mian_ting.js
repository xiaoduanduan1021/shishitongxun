
marqueesHeight=25; 

stopscroll=false; 

with(icefable1) 
{ 
style.height=marqueesHeight; 
style.overflowX="visible"; 

style.overflowY="hidden"; 

noWrap=true; 

onmouseover=new Function("stopscroll=true"); 

onmouseout=new Function("stopscroll=false"); 

} 

preTop=0; 
currentTop=25; 
stoptime=0; 

icefable1.innerHTML += icefable1.innerHTML; 



function init_srolltext() 
{ 
icefable1.scrollTop=0; 
setInterval("scrollUp()",15); 

} 



function scrollUp()  
{ 

if(stopscroll==true) return; 

currentTop+=1; 

if(currentTop>25) 

    { 

    stoptime+=1; 

    currentTop-=1; 

        if(stoptime==300) 
     
        { 
         
        currentTop=0; 
  
        stoptime=0; 

        } 

    } 
  
    else 
    { 
  
    preTop=icefable1.scrollTop; 
    icefable1.scrollTop+=1; 
     

      if(preTop==icefable1.scrollTop) 
   
          { 
   
          icefable1.scrollTop=0; 

          icefable1.scrollTop+=1; 

        } 
  
    } 

} 

init_srolltext(); 
////////////

$(document).ready(function(){
$(".top_fh ul li").hover(function(){
$(this).find(".right_1").stop().animate({"width":"150px"},200).css({"opacity":"1","filter":"Alpha(opacity=100)","background":"#c1082b"})	
},function(){
$(this).find(".right_1").stop().animate({"width":"62px"},200).css({"opacity":"0.8","filter":"Alpha(opacity=80)","background":"#656565"})	
});
});
function goTop(){
	$('html,body').animate({'scrollTop':0},600);
}