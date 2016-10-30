I = imread('lena.pgm');

corners = detectHarrisFeatures(I);
[features, valid_corners] = extractFeatures(I, corners);
figure, imshow(I);
figure, plot(valid_corners);
display(features);

points = detectSURFFeatures(I);
[features, valid_points] = extractFeatures(I, points);
figure, plot(valid_points.selectStrongest(10),'showOrientation',true);

I2 = imread('coca_cola.png');
corners = detectHarrisFeatures(I2);
[features, valid_corners] = extractFeatures(I2, corners);
figure, imshow(I2);
figure, plot(valid_corners);
display(features);