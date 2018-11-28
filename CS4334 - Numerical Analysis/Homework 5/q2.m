func = @(x) 20*exp((-(x-60)^2)/50)/power((2*pi),.5);

trapfun(10000, 0, 69, func)

simpfun(100, 0, 69, func)

sprintf('a) 96.41 students would fail this class based on trapezoid rule\nb) 96.41 students would fail based on simpsons rule\nNo, we definitely would not want to take this class')