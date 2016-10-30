
X = imread('coca1.jpg');

%BW = im2bw(X, map, level);

imwrite(X,'test1.pgm');
Y = imread('coca3.png');
%BW = im2bw(X, map, level);

imwrite(Y,'test.pgm');
Z = imread('test.pgm');
imwrite(imcomplement(Z), 'test2.pgm');
match('test1.pgm','test2.pgm');
