function [x1, x2] = noncompquadroots(a, b, c)

x1 = (-b + sqrt(power(b,2) - 4*a*c)) / (2*a);
x2 = (-b - sqrt(power(b,2) - 4*a*c)) / (2*a);

end
