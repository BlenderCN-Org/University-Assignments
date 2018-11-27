func = @(x) 20*exp((-(x-60)^2)/50)/power((2*pi),.5);

trapfun(10000, 0, 69, func)

simpfun(100, 0, 69, func)

%Answer Qs