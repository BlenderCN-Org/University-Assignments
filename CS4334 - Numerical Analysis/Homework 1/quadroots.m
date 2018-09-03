function [x1, x2] = quadroots(a,b,c)

b_sq = power(b,2);
ac4  = 4*a*c;

if b > 0
    x1 = (-2*c) / (b + sqrt(b_sq - ac4));
    x2 = (-1) * (b + sqrt(b_sq - ac4)) / (2*a);
else
    x1 = (-b + sqrt(b_sq - ac4)) / (2*a);
    x2 = (2*c) / (-b + sqrt(b_sq - ac4));
end

end

