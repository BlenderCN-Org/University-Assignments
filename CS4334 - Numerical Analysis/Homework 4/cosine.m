function [approx] = cosine(X, a, b, c)
%COSINE Summary of this function goes here
%   Detailed explanation goes here

X = mod(abs(X), 2*pi);

k = floor(X/(2*pi/99));

approx = a(k) + b(k)*(X - (k-1)*(2*pi/99)) + c(k)*(X - (k-1)*(2*pi/99))^2;

