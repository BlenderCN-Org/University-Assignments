% Program 5.1 Romberg integration
% Computes approximation to definite integral 
% Inputs: Matlab inline function specifying integrand f, 
%    a,b integration interval, n=number of rows
% Output: Romberg tableau r
function r=rombergmod(f,a,b,error)
n = 100;
h=(b-a)./(2.^(0:n-1));
fa = feval(f,a);
fb = feval(f,b);
r(1,1)=(b-a)*(fa+fb)/2;
fevals = 2; 
for j=2:n
  subtotal = 0;
  for i=1:2^(j-2)
    fmid = feval(f,a+(2*i-1)*h(j));
    fevals = fevals + 1;
    subtotal = subtotal + fmid;
  end
  r(j,1) = r(j-1,1)/2+h(j)*subtotal;
  for k=2:j
    r(j,k) = (4^(k-1)*r(j,k-1)-r(j-1,k-1))/(4^(k-1)-1);
  end
  if(abs(r(j,j) - r(j-1,j-1)) < error)
    break
  end
end
fevals