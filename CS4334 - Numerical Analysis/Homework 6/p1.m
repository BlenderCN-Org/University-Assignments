format long e

clc
clear

n = 10000;
t = linspace(0,4,n+1)';

w = wtseries(t);    
y = actual(t);

plot(t, w, t, y)
title('approx v.s. actual')
legend("Approx", "Actual") 
xlabel("Input 't'")
ylabel("Output")
figure()

err = abs(w - y);

plot(t, err)
title('error v.s t')
legend("error")
xlabel("t")
ylabel("error")



fprintf('If we increase the number of points by a factor of 10, then our global error decreases by a factor of 10. Thus it has a linear relation, O(h), as we expect.\n');