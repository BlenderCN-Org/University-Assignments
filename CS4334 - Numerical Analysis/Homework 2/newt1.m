format long e

% Tolerance Level
TOL = 10^(-6);

% Initial Guess
xp = 2;

iterates = [];

% While the relative error is less than the give tolerance, we will
% continue to use newton's method.
while 1

    xf = xp - fnewt(xp) / fpnewt(xp);
    iterates = [iterates, xf];
    
    if(abs((xf-xp)/(xf)) < TOL)
        break
    end
    
     xp = xf;
    
end

% Ouprinting the resulting iterates
clc
fprintf("Iterates::")
iterates'