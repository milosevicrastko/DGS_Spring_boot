DROP TABLE IF EXISTS "user";

CREATE TABLE "user" (
    id int NOT NULL,
    name varchar(50),
    email varchar(100)
);

INSERT INTO "user" (id, name, email) VALUES
(1,'Alice Johnson', 'alice.johnson@example.com'),
(2,'Bob Smith', 'bob.smith@example.com'),
(3,'Charlie Brown', 'charlie.brown@example.com'),
(4,'Dana White', 'dana.white@example.com'),
(5,'Eva Green', 'eva.green@example.com'),
(6,'Frank Black', 'frank.black@example.com'),
(7,'Grace Lee', 'grace.lee@example.com'),
(8,'Henry Adams', 'henry.adams@example.com'),
(9,'Ivy Turner', 'ivy.turner@example.com'),
(10,'Jack Wilson', 'jack.wilson@example.com'),
(11,'Kelly Davis', 'kelly.davis@example.com'),
(12,'Liam Moore', 'liam.moore@example.com'),
(13,'Mia Clark', 'mia.clark@example.com'),
(14,'Nathan Hall', 'nathan.hall@example.com'),
(15,'Olivia Lewis', 'olivia.lewis@example.com'),
(16,'Paul Scott', 'paul.scott@example.com'),
(17,'Quinn Morgan', 'quinn.morgan@example.com'),
(18,'Rita Harris', 'rita.harris@example.com'),
(19,'Sam Carter', 'sam.carter@example.com'),
(20,'Tina Brown', 'tina.brown@example.com'),
(21,'Ursula King', 'ursula.king@example.com');
