-- create the database
CREATE DATABASE IF NOT EXISTS autopark;

-- use autopark
USE autopark;

-- create the table
CREATE TABLE IF NOT EXISTS autopark (
    id INT AUTO_INCREMENT PRIMARY KEY,
    town VARCHAR(255) NOT NULL,
    autoparkName VARCHAR(255) NOT NULL,
    latitude DECIMAL(9,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL,
    occupiedParkingSpot INT NOT NULL,
    maxCapacity INT NOT NULL
);

-- insert the data
INSERT INTO autopark (town, autoparkName, latitude, longitude, occupiedParkingSpot, maxCapacity) VALUES
('Kadıköy', 'Kadıköy Çarşı Otoparkı', 40.9872, 29.0280, 5, 10),
('Kadıköy', 'Kadıköy Rıhtım Otoparkı', 40.9842, 29.0218, 4, 10),
('Kadıköy', 'Söğütlüçeşme Otoparkı', 40.9915, 29.0387, 4, 10),
('Kadıköy', 'Acıbadem Otoparkı', 40.9890, 29.0243, 6, 10),
('Kadıköy', 'Altıyol Otoparkı', 40.9814, 29.0263, 8, 10);
