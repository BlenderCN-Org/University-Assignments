function [A,P] = gaelpp(A)

%Gaussian elimination
%inputs: 
%nXn matrix A, and and nX1 vector b
%outputs: 
%nXn matrix m of multipliers
%nXn upper triangular matrix A
%nX1 vector b

%get n
[n,n] = size(A);


% Create P
P = eye(n);

%set up matrix of zeros that will store multipliers
M = zeros(n,n);

% This initially swaps the top row by finding the maximum value'd
% row index inside this row and then swapping it with the initial row
max = 0;
pos = 1;
for p = 1:n
    if abs(A(p,1)) > max
       max = abs(A(p,1));
       pos = p;
    end
end


A([1 pos],:) = A([pos 1], :);
P([1 pos],:) = P([pos 1], :);

%Gaussian elimination
for j = 1:n-1 %j is pivot row
    
    if A(j,j) == 0 %avoid div. by 0
            break
    end
    
    % If the value at row j is less than the value
    % at row j+1, swap the rows and update A, P, and M
    if abs(A(j, j)) < abs(A(j+1, j))        
        A([j j+1],:) = A([j+1 j], :);
        P([j j+1],:) = P([j+1 j], :);
        M([j j+1],:) = M([j+1 j], :);
     end 
    
    for i = j+1:n %elim. col. j from row i = j+1 to i = n      

        M(i,j) = A(i,j)/A(j,j); %multiplier to elim. A(i,j), store in matrix m       
        
        for k = j+1:n % add mult of row j to row i            
            A(i,k) = A(i,k) - M(i,j)*A(j,k);                            
        end            
    end         
end

% Correctly sets the pivots in A to return properly (modified matrix)
for y=2:n
    for x=1:y-1
        A(y,x) = M(y,x);
    end
end