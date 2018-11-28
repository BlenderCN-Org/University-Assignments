function int = simpfun(m,a,b,func)
format long e
h = (b-a)/m;
x0 = feval(func,a);
xm = feval(func,b);
k = 2;
x = zeros(m,1);
for i = 1:m
    for x =  2*k-1
       x(1+i) = x;
    end 
end

y = sum(x)
r = zeros(m,1);
for i = 1: m-1
    for r = 2*k
    r(1+i) = r;
    end 
end
z = sum(r)
int = (h/3)*(x0 + xm) + (h/3)*4*y + (h/3)*z*2 

    
    
    
  











    
    



