format long e

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

fprintf("")