function [A] = genmatrix(x)
% Generates a matrix of the derivatives of the
% log-transformed equation given in the HW.
% [Overview] Takes in a column vector X and returns a matrix A
% (the jacobian / gradient) the original function
% with the values of x plugged into it.
A = [1/x(1) 1 1/(10-x(3)*1);
    1/x(1) 2 2/(12-x(3)*2);
    1/x(1) 3 3/(15-x(3)*3);];
end

