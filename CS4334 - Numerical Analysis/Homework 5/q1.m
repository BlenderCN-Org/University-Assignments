format long e

% How many h's we have
range = 12;

% Creates evenely spaced points for H
h = [10^-1, 10^-2, 10^-3, 10^-4, 10^-5, 10^-6, 10^-7, 10^-8, 10^-9, 10^-10, 10^-11, 10^-12];
approx = zeros(range,1);
errors = zeros(range,1);

% Calculates the approx values of h
for i=1:range
    approx(i) = ((cos(2 - 2*h(i)) - 2*cos(2) + cos(2 + 2*h(i))) / (4*h(i)^2));
end

% Calculates the errors for the approx values
for i=1:range
    errors(i) = abs(-cos(2) - approx(i));
end
    


% Testing our h values locally (they are close)
[m, l] = min(errors);
h(l);
(6*(10^-16)/1)^.25;

sprintf('We notice that as h gets smaller, the error decreases. However, due to a combonation of truncation, roundoff errors and loss of signifigance we see weird behavious from h as it gets smaller and smaller. Our h value that we got was close our expected h value form problem 2.')

loglog(h, errors, '-s')
xlabel('h')
ylabel('errors')
grid on