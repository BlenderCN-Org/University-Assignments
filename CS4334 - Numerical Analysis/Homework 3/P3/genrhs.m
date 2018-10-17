function [f] = genrhs(x)
% Generates the right-hand-side of the
% log-transformed equation given in the HW.
% [Overview] Takes in a column vector X and returns a 
% column vector f (actually -f) of the original function 
% with the values of x plugged into it.
f = [-log(x(1)) - x(2)*1 + log(10-x(3)*1);
       -log(x(1)) - x(2)*2 + log(12-x(3)*2);
       -log(x(1)) - x(2)*3 + log(15-x(3)*3);];
end

