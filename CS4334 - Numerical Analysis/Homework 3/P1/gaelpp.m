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

%A

max = 0;
pos = 1;
for p = 1:n
    if abs(A(p,1)) > max
       max = abs(A(p,1));
       pos = p;
    end
end

%fprintf("Inital swap row[%i] with row[%i]\n", 1, p)
A([1 pos],:) = A([pos 1], :);
P([1 pos],:) = P([pos 1], :);

%Gaussian elimination
for j = 1:n-1 %j is pivot row
    
    if A(j,j) == 0 %avoid div. by 0
            break
    end
    
    if abs(A(j, j)) < abs(A(j+1, j))
        %fprintf("Swapped row[%i] with row[%i]\n", j, j+1)
        A([j j+1],:) = A([j+1 j], :);
        P([j j+1],:) = P([j+1 j], :);
        M([j j+1],:) = M([j+1 j], :);
     end 
    
    for i = j+1:n %elim. col. j from row i = j+1 to i = n       

        M(i,j) = A(i,j)/A(j,j); %multiplier to elim. A(i,j), store in matrix m
       % fprintf("row[%i], col[%i], mult = %i / %i: %f\n", i, j, A(i,j), A(j,j), M(i,j))
        
        for k = j+1:n % add mult of row j to row i
            
            A(i,k) = A(i,k) - M(i,j)*A(j,k);
                             
        end    
        
        %A
        
    end
         
end

if abs(A(n-1, n-1)) < abs(A(n, n-1))
       % fprintf("Swapped row[%i] with row[%i]\n", j, j+1)
        A([n-1 n],:) = A([n n-1], :);
        P([n-1 n],:) = P([n n-1], :);
        M([n-1 n],:) = M([n n-1], :);
end 

for y=2:n
    for x=1:y-1
        A(y,x) = M(y,x);
    end
end