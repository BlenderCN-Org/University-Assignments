format long e

y = @(x) exp((x^2));
rombergmod(y, 0, 1, (0.5*10^-12))
