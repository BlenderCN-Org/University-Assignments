function [w] = wtseries(t)
    n = length(t);
    h = abs(t(2) - t(1));
    w = zeros(n,1);
    w(1) = 100;
    
    for i=2:n
        w(i) = w(i-1) + (h)*(200 + (w(i-1)/(t(i)-5))) + ((1/2) * h^2)*((1/(t(i)-5)) - (w(i-1)/((t(i)-5)^2))) + ((1/6) * h^3)*(((-2)/((t(i)-5)^2)) - ((2*w(i-1))/((t(i)-5)^3)));
    end
    
end

