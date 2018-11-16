format long e

clc

nnodes = 10;

a = -3;
b = 3;

nodesx = (nnodes);

% Creates evenly spaced nnodes
iter = (abs(a) + abs(b)) / (nnodes-1);
tmp = a;

% Fills out nodes
for i = 1:nnodes
    nodesx(i) = ((b+a)/2) + ((b-a)/2)*cos(((2*i - 1)*pi)/(2*nnodes));
end


nodesy = exp(-1*abs(nodesx));

% Evalute function at points
points = 600; % 0.01 spaced points

x = linspace(a,b, 600);
y = exp(-abs(x));

% Computing the coefs
coefs = newtdd(nodesx,nodesy,nnodes);

% Computing the polynomial
poly = nest(nnodes-1, coefs, x, nodesx);

% Error
abserr = max(abs(y - poly))

% Plotting
figure()
plot(x ,y)
hold on
plot(x, poly)
hold off 
s = sprintf("Actual vs Interpolated f(x) at %d nodes", nnodes);
title(s)
legend({'regular', 'interpolated'}, 'Location','southwest')





nnodes = 30;

a = -3;
b = 3;

nodesx = (nnodes);

% Creates evenly spaced nnodes
iter = (abs(a) + abs(b)) / (nnodes-1);
tmp = a;

% Fills out nodes
for i = 1:nnodes
    nodesx(i) = ((b+a)/2) + ((b-a)/2)*cos(((2*i - 1)*pi)/(2*nnodes));
end


nodesy = exp(-1*abs(nodesx));

% Evalute function at points
points = 600; % 0.01 spaced points

x = linspace(a,b, 600);
y = exp(-abs(x));

% Computing the coefs
coefs = newtdd(nodesx,nodesy,nnodes);

% Computing the polynomial
poly = nest(nnodes-1, coefs, x, nodesx);

% Error
abserr = max(abs(y - poly))

% Plotting
figure()
plot(x ,y)
hold on
plot(x, poly)
hold off 
s = sprintf("Actual vs Interpolated f(x) at %d nodes", nnodes);
title(s)
legend({'regular', 'interpolated'}, 'Location','southwest')


