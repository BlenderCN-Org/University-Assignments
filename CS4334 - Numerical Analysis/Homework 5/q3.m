format long e

func = @(x) exp((x^2));
rombergmod(func, 0, 1, (0.5*10^-12))

% order 16, since order is 2*colnum, 127 fevals?no
