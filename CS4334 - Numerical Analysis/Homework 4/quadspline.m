%%%%%%%%%%%%%%%%%%%%%    compute spline coefficients here    %%%%%%%%%%%%%%%

format long e
 
clc

a = 0;
b = 2*pi;

% Num of nodes + array
n = 100;
y = (n);
x = (n);
 
% A matrix creation for 100 nodes
A = zeros(n-2, n-2);

A(1,1) = 1;
for i=2:n-1
    A(i, i) = 1;
    A(i, i-1) = 1;
end
A(n-2, n-2) = 1;
A(n-2, n-1) = 1;


% Spacing of nodes
iter = (abs(a) + abs(b)) / (n-1);
tmp = a;

% Setting up x and y values
x = linspace(0,2*pi);
y = cos(x);

% Setup a
a = zeros(n-1,1);
for i=1:n-1
    a(i) = y(i);
end

fprintf("Det(A) = 1\n")

% Setup Solution Vector b
b = zeros(n-2,1);
b(1) = 0;
for i = 2:n-1
   %b(i) = ((2/(x(i+1)-x(i)))*(y(i+1)-y(i))) - b(i-1);
    b(i) = ((2/(x(i)-x(i-1)))*(y(i)-y(i-1)));
end
b = forsub(A,b);
 
% Setup c
c = zeros(n-1,1);
for i=1:n-1
    c(i) = ((y(i+1) - y(i))/((x(i+1) - x(i))^2)) - (b(i)/(x(i+1) - x(i)));
end


%%%%%%%%%%%%%%%%%%  call sineval function here  %%%%%%%%%%%%%%%%%

X = 250;

cosine(X,a,b,c)

X = -100;

cosine(X,a,b,c)


%%%%%%%%%%%%%%%%%%%%%    plot spline    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%number of points on which to plot. n = number of nodes
nplot  = (n-1)*19+1;

xplot = zeros(nplot,1);
yplot = zeros(nplot,1);

%spacing between plot points
nspace = (x(n)-x(1))/(nplot-1);

k = 0;
for i = 1:n-1
    
    for j = 1:19
        
        k = k+1;
        xplot(k) = x(i) + (j-1)*nspace;
        yplot(k) = a(i) + b(i)*(xplot(k) - x(i)) + c(i)*(xplot(k) - x(i))^2;
        
    end
    
end

xplot(nplot) = x(n);
yplot(nplot) = a(i) + b(i)*(x(n) - x(n-1)) + c(i)*(x(n) - x(n-1))^2;

plot(xplot,yplot)

figure()

abserr = abs(yplot - cos(xplot));

plot(xplot,abserr)
