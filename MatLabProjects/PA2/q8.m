%{
Author:Jingjing Wang
Date: Feb 11
This script asks for an integer n and then computes the following based on the
value of the integer:
a. While the value of n is greater than 1, replace the integer with half of its value
if the integer is even.
b. Otherwise, replace the integer with three times its value, plus 1.
%}


% Please enter 30 to meet the requirement of ploting a function of integers
% from 2 to 30.
% Enter other integers for finding pattern.

n = input('Please enter an integer n: ');

% This is to check if the number user entered is integer.
while rem(n,1) ~= 0
    X = 'Your input is not an integer.';
    disp(X);
    n = input('Please enter an integer n: ');
end

A = n; %This is to save the n in case it changes in future
i = 0;
while n > 1
    i = i +1;
    if rem(n, 2) == 0
        n = n/2;
        %M(:, i) = n;
        
    else
        n = 3*n + 1;
        %M(:, i) = n;
    end
end

msg = sprintf('The sequence results %d numbers of values', i);
disp(msg)


M = zeros(1, A-1);

total = A;
m = 2; % This to increment from 2 to n
j = 0; % This is to record the number of values for each m
while m <= total
    p = m;
    while p > 1
        j = j +1;
        if rem(p, 2) == 0
            p = p/2;
        else
            p = 3*p + 1;
        end
    end
    M(:, m-1) = j; % This is to record the number of values into M
    m = m + 1;
    j = 0;
end

xaxis = 2:A;

plot(xaxis, M,'ro');
title('2-n number of values');
xlabel('Input Numbers');
ylabel('Values');

disp('After I tried a big amount of large numbers, I see a visual pattern. Dots on the graph gradually line in several horizontal lines.');
disp('For all the testments I did there was no integer for which the sequence does not terminate.');
