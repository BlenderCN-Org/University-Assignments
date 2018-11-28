format long e
f = @(x) cos(x);
%(f(x - 2h) - 2f(x) + f(x+2h) / (4h^2))
%h = linspace(10^-1, 10^-12, 12); % h = 10^-1, 10^-2,,....,10^-12
%function f(x) = cos(x)
%f = cos(x)
%f' = -sin(x) derivative
h = logspace(-1,-12,12);
length(h);

centerdiff = zeros(12,1);
absoluterror = zeros(12,1);
for z = 1:12 %  number of terms of h
    centerdiff(z) = ((cos(2 - 2*h(z)) - 2*cos(2) + cos(2 + 2*h(z))) / (4*h(z)^2)); 
end
centerdiff;
for z = 1:12
    absoluterror(z) = abs(-cos(2) - centerdiff(z));
end
absoluterror;
loglog(h,absoluterror)
xlim([10^-12 10^0])
ylim([10^-10 10^10])




