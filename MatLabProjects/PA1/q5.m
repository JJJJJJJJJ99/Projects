%{ 
Author:Jingjing Wang
Date: Feb 2
Describtion: A script prompts the user for the values of a and b. Since division by 0 is not possible, the script prints an
error message if the value of a is 0 (it ignores any other errors, however). If a is not 0, the
script calls a function to calculate and return the eccentricity, and then the script prints the
result. Write the script and the function.
*************
The function eccentricity is in another function script.
*************
%}

a = input('Enter semi-major axis a = : ');
b = input('Enter semi-minor axis b = : ');

if  a == 0
    error('Semi-major, a, cannot be 0')
else
    result = eccentricity(a, b);
end

X = sprintf('Result: %f', result);
disp(X);
