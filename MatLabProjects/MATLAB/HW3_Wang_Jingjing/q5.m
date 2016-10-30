clear;

file = fopen('xypts.dat');

if file == -1
    error('File xypts.dat is not opened')
end

line = fgetl(file);

X = [];
Y = [];
count = 0;
while ischar(line)
    count = count + 1;
    c = strsplit(line, ' ');
    X = [X str2double(c{2})];
    Y = [Y str2double(c{4})];
    line = fgetl(file);
end
s = sprintf('Plot of %d points', count);
scatter(X, Y,'filled');
title(s);

file = fclose('all');
if file == -1
    error('File xypts.dat is closed unsuccessfully.')
end
