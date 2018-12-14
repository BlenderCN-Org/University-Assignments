function [y] = actual(t)
    n = length(t);
    y = zeros(n,1);
    for i=1:n
        y(i) = 20 * (5 - t(i)) * (10 * log((5 / (5 - t(i)))) + 1);
    end
end

