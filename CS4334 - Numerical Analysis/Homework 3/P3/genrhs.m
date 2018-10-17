function [f] = genrhs(x)
%GENRHS Summary of this function goes here
%   Detailed explanation goes here
f = [-log(x(1)) - x(2)*1 + log(10-x(3)*1);
       -log(x(1)) - x(2)*2 + log(12-x(3)*2);
       -log(x(1)) - x(2)*3 + log(15-x(3)*3);];
end

