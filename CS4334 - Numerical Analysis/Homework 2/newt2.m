format long e

clc

% Tolerance Level
TOL = 10^(-6);

% Initial Guess
xp = 2;

% Arrays to store the iterates and error values required
iterates = [];
relerr = [];
relerr2 = [];

% ep is 0 for the first iteration
ep = 1;

% While the relative error is less than the give t  olerance, we will
% continue to use newton's method.
while 1

    % Newton's Algorithm
    xf = xp - fnewt(xp) / fpnewt(xp);
    iterates = [iterates, xf];    
   
     % Error and Tolerance
    ef = abs((xf-xp)/(xf));
    fprintf("%f\n",ef)
    if(ef < TOL)
        break
    end    
   
    relerr = [relerr, ef/ep];
    relerr2 = [relerr2, ef/ep^2];
    
    xp = xf;
    ep = ef;
    
end

% Ouprinting the resulting iterates
clc
fprintf("Iterates::\n")
iterates'

fprintf("Error 1::\n")
relerr'

fprintf("Error 2::\n")
relerr2'

fprintf("Since the derivative of the function is non-zero, we can determine that there are multiple roots.  Also, we can determine that there is linear convergence since the error decreases at a somewhat linear rate.\n")

v = round(iterates(length(iterates)), 3);
mult = fnewt(v)/2*fpnewt(v);

rofc = (mult-1)/mult;

fprintf("Iterates:: %d\n", v)
fprintf("Multiplicity:: %d\n", mult)
fprintf("Rate of Convergence:: %d\n", rofc)

fprintf("We have determined that we have a rate of convergence of approx: 1, therefore we have linear convergence for this newton's method.\n")