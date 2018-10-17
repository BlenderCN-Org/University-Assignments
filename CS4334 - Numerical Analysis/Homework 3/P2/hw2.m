format long e 

% Clear console for easy testing
clc

% Setup the Vandermonde matrix of size n, as well as the solution vector
n = 10;
A = zeros(n,n);
x = ones(n,1);

% Loop creating of the Vandermonde matrix
for i=1:n
    for j=1:n
        A(i,j) = i^(j-1);
    end
end

% Performing GEPP via MatLab built-in
A;
b = A*x;
xa = A\b;

% Calculating the errors based upon the actual solutiopn
% x, and the approx solution xa
relBkwdErr = (norm(A*x - A*xa))/norm(b)
condA = cond(A)
boundOnFwrdError = condA*relBkwdErr
relFwrdErr = (norm(x - xa))/norm(x)

fprintf("Since we are using double percision, our answer is only accurate to 16 digits\n")