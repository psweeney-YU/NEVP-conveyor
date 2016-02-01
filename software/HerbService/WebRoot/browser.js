
function Browser() 
{

  var userAgent, str, i;

  this.isIE    = false;
  this.isMoz    = false;
  
  userAgent = navigator.userAgent;

  str = "MSIE";
  if ((i = userAgent.indexOf(str)) >= 0) 
  {
    this.isIE = true;
    return;
  }
  
  str = "Opera";
  if ((i = userAgent.indexOf(str)) >= 0) 
  {
    this.isIE = true;
    return;
  }

  str = "Netscape6/";
  if ((i = userAgent.indexOf(str)) >= 0) 
  {
    this.isMoz = true;
    return;
  }

  str = "Gecko";
  if ((i = userAgent.indexOf(str)) >= 0) 
  {
    this.isMoz = true;
    return;
  }
  
  
}