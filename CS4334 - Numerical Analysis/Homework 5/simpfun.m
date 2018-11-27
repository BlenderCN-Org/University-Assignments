function int = simpfun(m,a,b,func)
    int = func(a) + func(b);
    x = linspace(a,b,2*m+1);
    h = (b-a)/(2*m);
    
    for i = 1:m %1 3 5 7 9 -> 2 4 6 8 10
        int = int + 4*func(x(2*(i)));
    end
    
    for i = 1:m-1 % 2 4 6 8 -> 3 5 7 9
        int = int + 2*func(x(2*(i)+1));
    end
    
    int = (h/3)*int;
end