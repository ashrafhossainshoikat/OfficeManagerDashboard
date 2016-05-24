Function.prototype.allowSleep = function(n) {
  var i, f;
  if (!/^[$\w]+$/.test(n)) return;
  i = 0;
  f = this.toString().replace(/sleep\((.*?)\);/g , function(f,t) {i++; return n+".f"+i+" = function() {"+n+".func"+i+"();};\nsleep("+t+","+n+".f"+i+");\n"+(i==1?"":"}\n")+n+".func" + i +" = function() {";});
  eval(n+'='+f+'\n}');
  }
sleep = function(t,f) {
  if (f === undefined) return;
  setTimeout(f,t);
}
 
afunc = function() {
  sleep(5000);
  document.form1.submit();
}
afunc.allowSleep('afunc');  //comment out to disable sleep
afunc();