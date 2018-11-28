function int = trapfun(m,a,b,func)
format long e
h = (b-a)/m; %m is the number of panels, so distance
x = (a+h:h:b-h); %based on distance
%approximation trapezoid
int = (h/2)*(2*sum(feval(func,x))+feval(func,a)+feval(func,b));



 
 
 





