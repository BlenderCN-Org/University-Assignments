function [w] = wtseries(t)
    n = length(t);
    h = 4/n;
    w = zeros(n,1);
    w(1) = 100;
    
    for i=2:n
        w(i) = w(i-1) + (h)*(200 + (w(i-1)/(t(i-1)-5))) + (h^2/2)*(200/(t(i-1)-5)) - (h^3/6)*(200/((t(i-1)-5)^2));
    end
    
end

    