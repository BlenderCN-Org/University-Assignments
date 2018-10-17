format long e

% Clear console for easy testing
clc

%relative error tolerance
TOL = .000001;

%initial guess; x is "current" iterate
x = [1; 1; 1;];

%store as previous iterate xp
xp = x;

%call function to generate right-hand-side, -f
rhs = genrhs(x);
    
%call function to generate matrix, DF
DF = genmatrix(x);

%Use MATLAB function to do PA=LU factorization
[L,U,P] = lu(DF);
        
%Use our functions in elearning, forsub and backsub, as appropriate
y = forsub(L, P*rhs);
s = backsub(U, y);

% Calculating the next iterate
x = xp + s;    
    
%x is the current iterate
%s is the difference between current and previous iterate
while norm(s)/norm(x) >= TOL  
 
    %store previous iterate
    xp = x;   
   
    %generate right-hand-side, -f
    rhs = genrhs(x);    
    
    %generate matrix, DF
    DF = genmatrix(x);

    %PA=LU factorization
    [L,U,P] = lu(DF);
        
    %Use our functions forsub and backsub as appropriate
    y = forsub(L, P*rhs);
    s = backsub(U, y);
    
    x = xp + s;   
     
end

%display last iterate and relerr estimate
x

norm(s)/norm(x)

