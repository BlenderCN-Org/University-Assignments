format long e

range = 12;

% h = linspace(10^-1, 10^-12, range);
h = [10^-1, 10^-2, 10^-3, 10^-4, 10^-5, 10^-6, 10^-7, 10^-8, 10^-9, 10^-10, 10^-11, 10^-12];
approx = zeros(range,1);
errors = zeros(range,1);

for i=1:range
    approx(i) = ((cos(2 - 2*h(i)) - 2*cos(2) + cos(2 + 2*h(i))) / (4*h(i)^2));
end

approx

for i=1:range
    errors(i) = abs(-cos(2) - approx(i));
end
    
[m, l] = min(errors);

h(l)
(6*(10^-16)/1)^.25

loglog(h, errors, '-s')
xlabel('h')
ylabel('errors')
grid on

% write more stuff rom q!