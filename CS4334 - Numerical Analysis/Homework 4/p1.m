format long e

clc

nnodes = 10;

a = -3;
b = 3;

nodes = (nnodes);

% Creates evenly spaced nnodes
iter = (abs(a) + abs(b)) / (nnodes-1);
tmp = a;

% Fills out nodes
nodes(1) = a;
for i = 2:nnodes-1
tmp = tmp + iter;
nodes(i) = tmp;    
end
nodes(nnodes) = b;

% Evalute function at points
points = 601; % 0.01 spaced points
x = (points);
y = (points);

x(1) = a;
y(1) = exp(-1 * abs(a));
for i = 2:points-1
    x(i) = x(i-1) + 0.01;
    y(i) = exp(-1 * abs(x(i)));
end
x(points) = b;
y(points) = exp(-1 * abs(b));
x;
y;

coefs = newtdd(x,y,points);
coefs;

poly = (points);
for i = 1:points
    poly(i) = nest(nnodes-1, nodes, x(i));
end

% Plotting
figure()
plot(x ,y)
hold on
plot(x, poly)
hold off 
s = sprintf("Actual vs Interpolated f(x) at %d nodes", nnodes);
title(s)
legend({'regular', 'interpolated'}, 'Location','southwest')

format long e

clc

nnodes = 30;

a = -3;
b = 3;

nodes = (nnodes);

% Creates evenly spaced nnodes
iter = (abs(a) + abs(b)) / (nnodes-1);
tmp = a;

% Fills out nodes
nodes(1) = a;
for i = 2:nnodes-1
tmp = tmp + iter;
nodes(i) = tmp;    
end
nodes(nnodes) = b;

% Evalute function at points
points = 601; % 0.01 spaced points
x = (points);
y = (points);

x(1) = a;
y(1) = exp(-1 * abs(a));
for i = 2:points-1
    x(i) = x(i-1) + 0.01;
    y(i) = exp(-1 * abs(x(i)));
end
x(points) = b;
y(points) = exp(-1 * abs(b));
x;
y;

coefs = newtdd(x,y,points);
coefs;

poly = (points);
for i = 1:points
    poly(i) = nest(nnodes-1, nodes, x(i));
end

% Plotting
figure()
plot(x ,y)
hold on
plot(x, poly)
hold off 
s = sprintf("Actual vs Interpolated f(x) at %d nodes", nnodes);
title(s)
legend({'regular', 'interpolated'}, 'Location','southwest')

