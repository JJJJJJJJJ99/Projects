%{ 
Author:Jingjing Wang
Date: Feb 2
Describtion: This script prompts the user for letters of the alphabet and echo-print them
until the user enters a character that is not a letter of the alphabet. 
%}

a = input('Enter a letter: ', 's');
count = 0;
while isletter(a)
    X = sprintf('Thanks, you entered a %s', a);
    disp(X);
    a = input('Enter a letter: ', 's');
    count = count +1;
end

X = sprintf('%s is not a letter \nYou entered %d letters', a, count);
disp(X);