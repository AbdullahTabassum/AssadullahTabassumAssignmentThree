CREATE TABLE volumes( 
	id LONG PRIMARY KEY AUTO_INCREMENT, 
	width INT(255), 
	length INT(255),
	height INT(255) 
); 

CREATE TABLE rockets(
	id LONG PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(255),
	height INT(255),
	capacity INT(255),
	reuses INT(255)
); 

INSERT INTO rockets(name, height, capacity, reuses) VALUES 
('falcon', 70, 400, 1),
('eagle', 40, 300, 7),
('rhino', 30, 600, 4),
('viper', 70, 470, 2);

INSERT INTO volumes(width, length, height ) VALUES 
(43, 12, 34), 
(78, 54, 23), 
(21, 8, 76), 
(19, 67, 56); 

