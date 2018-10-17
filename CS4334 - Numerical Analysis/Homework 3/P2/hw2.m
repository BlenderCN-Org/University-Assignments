format longG 

clc

n = 10;
A = zeros(n,n);
x = ones(n,1);

for i=1:n

    for j=1:n
        A(i,j) = i^(j-1);
    end

end

A
b = A*x

x
xa = A\b

relfwrderr = (norm(x - xa))/norm(x)
relbkwderr = (norm(A*x - A*xa))/norm(b)
condA = cond(A)
bound = cond(A)*relbkwderr

fprintf("Since we are using double percision, our answer is only accurate to 16 digits\n")