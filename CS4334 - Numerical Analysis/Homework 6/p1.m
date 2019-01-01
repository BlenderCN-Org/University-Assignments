format long e

clc
clear

for i=1:4
    n = power(10,i);
    t = linspace(0,4,n+1)';

    w = wtseries(t);    
    y = actual(t);

    plot(t, w, t, y)
    title(['approx v.s. actual @ n=' num2str(n)])
    legend("Approx", "Actual") 
    xlabel("Input 't'")
    ylabel("Output")
    figure() 
end

err = abs(w - y);
plot(t, err)
title('error v.s t @ n=10000')
legend("error")
xlabel("t")
ylabel("error")



fprintf('If we increase the number of points by a factor of 10, then our global error decreases by a factor of 10. Thus it has a linear relation, O(h), as we expect.\n');