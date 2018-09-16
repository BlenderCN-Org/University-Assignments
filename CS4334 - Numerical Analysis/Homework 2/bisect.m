%find root of f(x) = 0
%using Bisection Method

format long e

%chosen error tolerance (TOL)
TOL = .000001;

%choose max number of iterations
MAXIT = 50;

%initial bracket
a = ;
b = ;

%keep track of number of iterations
count = 0;

%record iterates - a col vector of MAXIT length
cits = zeros(MAXIT,1);

%evaluate func. at a and b
fa = fbisect(a);
fb = fbisect(b);

%stop if not appropriate interval
if sign(fa)*sign(fb) >= 0 
    
    return
    
end

%stop loop when error less than TOL or MAXIT reached
while abs(b-a)/2 >= TOL & count < MAXIT
   
    %get midpoint(root estimate)
    c = (a + b)/2;
    
    %eval. func at midpoint
    fc = fbisect(c);
    
    %stop if f(c)=0
    if fc == 0
        break
    end
    
    %update count
    count = count + 1;
    
    %add to list of iterates
    cits(count) = c;
   
    %if sign change between a and c make c the new right endpt
    if sign(fa)*sign(fc)<0    
        
        b = c;
        
    %if sign chg betw c and b make c the new left endpt    
    else

        a = c;
        
    end
    
end

%update count
    count = count + 1;

%get final midpoint(root estimate)
    c = (a+b)/2;
    
%add to vector of iterates
cits(count) = c;
    
%display error estimate
error = abs(b-a)/2       

%display vector of iterates
cits

%display number of iterates
count        

        
    
    