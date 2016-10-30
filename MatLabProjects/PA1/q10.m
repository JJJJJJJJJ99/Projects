%{ 
Author:Jingjing Wang
Date: Feb 2
Describtion: This script prompts the user for N integers, and then write the positive
numbers (>= 0) to an ASCII file called pos.dat and the negative numbers to an ASCII
file called neg.dat. 
********************
When prompting input, please prompt your numbers in one line.
********************
%}

X = input('Enter N integers in one line:', 's');
[num, count, errmsg] = sscanf(X, '%d');
tf = isempty(errmsg);
if tf == 0
    msg = 'Error: The numbers you prompted are not all integers.';
    disp(msg);
else
    posM = num(num>=0);
    negM = num(num<0);
    save('pos.dat', 'posM', '-ascii');
    save('neg.dat', 'negM', '-ascii');
end

