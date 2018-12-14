format long e

clc
clear

n = 10000;
Apx = linspace(0,4,n+1)';
Apy = wtseries(Apx);

Acx = linspace(0,4,n+1)';
Acy = actual(Acx);

plot(Apx, Apy, Acx, Acy)
legend("Approx", "Actual") 
xlabel("Input 't'")
ylabel("Output")

fprintf('We are decresing our global error by a factor of 10 for each factor of 10 we increase n, thus it has a linear relation, O(h), as we expect.\n');