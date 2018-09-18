function c = ffalpos(a, b)
%FFALPOS Summary of this function goes here
%   Detailed explanation goes here
fa = (a-2)^2 + 1 - (1 - (a-2)^2 / 4)^0.5 - 1;
fb = (b-2)^2 + 1 - (1 - (b-2)^2 / 4)^0.5 - 1;
c = b - fb*((b-a)/(fb-fa));
end

