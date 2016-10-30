%{ 
Author:Jingjing Wang
Date: Feb 2
Describtion: This script Create a sample data file and read this data from the file and determine whether blizzard
conditions were met during this day or not.
%}

%Create sample file in script, you can comment it out when testing
M = [10 1;
    20 0.3;
    25 0.3;
    30 0.4;
    31 0.5;
    32 0.2;
    33 0.3;
    35 0.7;
    20 0.7;
    20 0.3;
    20 0.3;
    20 0.3;
    20 0.3;
    20 0.3;
    20 0.3;
    16 0.3;
    17 0.3;
    18 0.3;
    19 0.3;
    20 0.8;
    21 0.9;
    22 1.0;
    23 1.1;
    24 1.2];
save('stormtrack.dat', 'M', '-ascii');
%Create sample file in script, you can comment it out when testing

% Load file in script
load stormtrack.dat;


count = 0;
flag = 1;
for i = 1:size(stormtrack, 1)
    if stormtrack(i,1) >= 30 && stormtrack(i,2) <= 0.5
        flag = 1;
        count = count + 1;
        if count == 4 && flag == 1
            break
        end
    else
        flag = 0;
    end
end
 
if flag == 1
    X = sprintf('Blizzard conditions were met today.');
else
     X = sprintf('There is no blizzard conditions today.');
end
     
disp(X);