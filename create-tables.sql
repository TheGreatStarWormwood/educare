
-- Create the 'educare' database
CREATE DATABASE IF NOT EXISTS educare;

-- Use the 'educare' database
USE educare;


-- Create tables
CREATE TABLE `assignments` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `course_id` INT,
  `title` VARCHAR(255),
  `description` TEXT,
  `file` VARCHAR(255)
);

CREATE TABLE `courses` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `lms_id` INT,
  `name` VARCHAR(255),
  `description` TEXT
);

CREATE TABLE `enrollments` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `lms_id` INT,
  `student_id` INT
);

CREATE TABLE `listings` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `tutor_id` INT,
  `keywords` TEXT
);

CREATE TABLE `lms` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `tutor_id` INT
);

CREATE TABLE `materials` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `course_id` INT,
  `title` VARCHAR(255),
  `file` VARCHAR(255)
);

CREATE TABLE `reviews` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `tutor_id` INT,
  `text` TEXT,
  `stars` INT
);

CREATE TABLE `students` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `submissions` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `assignment_id` INT,
  `student_id` INT,
  `file` VARCHAR(255)
);

CREATE TABLE `tutor_lms` (
  `tutor_id` INT NOT NULL,
  `lms_id` INT NOT NULL,
  PRIMARY KEY (`tutor_id`, `lms_id`)
);

CREATE TABLE `tutors` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT,
  `image` VARCHAR(255)
);

-- Insert sample data
INSERT INTO `students` (`name`) VALUES
  ('student1'),
  ('student2');

INSERT INTO `tutors` (`name`, `description`, `image`) VALUES
  ('tutor1', 'description', 'imgs/tutor-icon.png'),
  ('tutor2', 'description2', 'imgs/tutor-icon.png');

INSERT INTO `courses` (`lms_id`, `name`, `description`) VALUES
  (1, 'course1', 'course description'),
  (2, 'course2', 'course description2');

INSERT INTO `assignments` (`course_id`, `title`, `description`, `file`) VALUES
  (1, 'assignment1', 'description for assignment1', 'file1.pdf'),
  (2, 'assignment2', 'description for assignment2', 'file2.pdf');

INSERT INTO `enrollments` (`lms_id`, `student_id`) VALUES
  (1, 1),
  (2, 2);

INSERT INTO `listings` (`tutor_id`, `keywords`) VALUES
  (1, 'math, science'),
  (2, 'english, history');

INSERT INTO `materials` (`course_id`, `title`, `file`) VALUES
  (1, 'material1', 'material1.pdf'),
  (2, 'material2', 'material2.pdf');

INSERT INTO `reviews` (`tutor_id`, `text`, `stars`) VALUES
  (1, 'Great tutor!', 5),
  (2, 'Good tutor, but needs improvement.', 4);

INSERT INTO `submissions` (`assignment_id`, `student_id`, `file`) VALUES
  (1, 1, 'submission1.pdf'),
  (2, 2, 'submission2.pdf');

INSERT INTO `tutor_lms` (`tutor_id`, `lms_id`) VALUES
  (1, 1),
  (2, 2);

INSERT INTO `lms` (`tutor_id`) VALUES
  (1),
  (2);
