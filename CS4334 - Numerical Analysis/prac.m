format long e

clc
clear

w = 1;
h = 1/4;
t = 0;

while t<1
    w = w + (h/2)*((2*(t+1)*w) + 2*(t+h+1)*(w + h*(2*(t+1)*w)));
    t = t + h;
end
w1 = w;

w = 1;
t = 0;


while t<1
    w = w + (2*t*w - 2*w)*(h) + (2*w + 2*t - 2)*(2*t*w - 2*w)*(h^2/2);
    t = t + h;
end
w2 = 2;

error = abs(w1 - w2)