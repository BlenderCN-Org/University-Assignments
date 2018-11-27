function int = trapfun(m,a,b,func)
    int = func(a) + func(b);
    x = linspace(a,b,m);
    h = (b-a)/m;
    
    for i=1:m-1
        int = int + 2*(func(x(i+1))); 
    end
    
    int = (h/2)*int;
    
end

