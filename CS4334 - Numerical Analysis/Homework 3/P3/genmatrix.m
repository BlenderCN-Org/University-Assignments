function [A] = genmatrix(x)
%GENMATRIX Summary of this function goes here
%   Detailed explanation goes here
A = [1/x(1) 1 1/(10-x(3)*1);
    1/x(1) 2 2/(12-x(3)*2);
    1/x(1) 3 3/(15-x(3)*3);];
end

