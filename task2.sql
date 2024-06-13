use dummy;

select * from authors;

select * from posts;

select first_name from authors;

select title from posts;

select birthdate from authors where  year(birthdate)>1980;

select count(*) from authors;

select count(*) from posts;

select * from authors
join posts on authors.id=posts.author_id
where authors.first_name='Andy';


-- Intermediate

select * from authors
join posts on authors.id=posts.author_id;


select * from authors
join posts on authors.id=posts.author_id
where authors.id is not null;

select * from authors
join posts on authors.id=posts.author_id
where authors.id is null;



select authors.first_name as auth, count(*) as count from authors
join posts on authors.id=posts.author_id
group by auth;



select *  from authors
join posts on authors.id=posts.author_id
where posts.date>  DATE_SUB(CURDATE(), INTERVAL 1 YEAR);



select * from posts where title like "% voluptas %";


update authors set authors.email="rgleason@updated.net" where id=2; 


delete from posts where posts.id=1;

select *  from authors
join posts on authors.id=posts.author_id
where posts.date>  DATE_SUB(CURDATE(), INTERVAL 30 DAY);


select * from posts order by posts.date desc;


-- Addvance level
select avg(counts) as count_avg from(select count(authors.id) as counts  from authors
left join posts on authors.id=posts.author_id
group by authors.id) as postavg;




select authors.first_name,count(authors.id) as counts  from authors
join posts on authors.id=posts.author_id
group by authors.id
order by authors.id limit 5;


select *  from authors
join posts on authors.id=posts.author_id
where posts.date>  DATE_SUB(CURDATE(), INTERVAL 7 DAY);

select * from authors where  email like "%@example.org";





CREATE VIEW authors_post_count AS
SELECT a.id, a.name, a.email, COUNT(p.id) as post_count
FROM authors a
LEFT JOIN posts p ON a.id = p.author_id
GROUP BY a.id, a.name, a.email;



SELECT * FROM posts WHERE CHAR_LENGTH(content) - CHAR_LENGTH(REPLACE(content, ' ', '')) + 1 > 10000;


select *  from authors
join posts on authors.id=posts.author_id
where posts.date=(
select min(p2.date) from
 posts p2 
 where p2.author_id=authors.id
);


update posts set posts.description="JUST KIDDING" where posts.id is not null;

delete from posts where  posts.author_id is null;


-- EXPERT LEVEL
select *  from authors
join posts on authors.id=posts.author_id
order by authors.last_name;



SELECT id, author_id, title, content, date
FROM posts
WHERE DAYOFWEEK(date) IN (1, 7);

SELECT authors.first_name, YEAR(CURDATE()) - YEAR(birthdate) age FROM authors;




select * from authors where year(authors.birthdate) <1970
order by authors.birthdate;


select * from posts where char_length(posts.title)- char_length(REPLACE(posts.title, ' ', ''))+1>50;



DELIMITER //

CREATE PROCEDURE AddNewAuthor(IN author_name VARCHAR(255), IN author_email VARCHAR(255))
BEGIN
    INSERT INTO authors (name, email) VALUES (author_name, author_email);
END //

DELIMITER ;






CREATE TABLE post_deletions_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    author_id INT,
    title VARCHAR(255),
    content TEXT,
    deleted_at DATETIME
);

DELIMITER //

CREATE TRIGGER log_post_deletion
AFTER DELETE ON posts
FOR EACH ROW
BEGIN
    INSERT INTO post_deletions_log (post_id, author_id, title, content, deleted_at)
    VALUES (OLD.id, OLD.author_id, OLD.title, OLD.content, NOW());
END //

DELIMITER ;



select *  from authors
join posts on authors.id=posts.author_id
where posts.date<  DATE_SUB(CURDATE(), INTERVAL 1 YEAR);


SELECT p.*
FROM posts p
JOIN (
    SELECT title,
           MAX(CHAR_LENGTH(content) - CHAR_LENGTH(REPLACE(content, ' ', ''))) AS max_length_difference
    FROM posts
    GROUP BY title
    ORDER BY max_length_difference DESC
    LIMIT 1
) AS max_diff ON p.title = max_diff.title;


select *  from authors
join posts on authors.id=posts.author_id
where posts.date=  DATE_SUB(CURDATE(), INTERVAL 1 MONTH);



select *  from authors
join posts on authors.id=posts.author_id
where authors.first_name="Rene";


select posts.description, posts.date from posts
where year(posts.date)<2020
order by posts.date asc;

select count(*) as total_post from posts;

select * from posts order by posts.date desc limit 5;


-- 38. Select authors who have not written posts in the last year.
SELECT DISTINCT a.*
FROM authors a
LEFT JOIN posts p ON a.id = p.author_id
WHERE p.date IS NULL OR p.date < DATE_SUB(CURDATE(), INTERVAL 1 YEAR);

-- 39. Find the longest post content.
SELECT *
FROM posts
ORDER BY LENGTH(content) DESC
LIMIT 1;

-- 40. Select authors and the number of posts they wrote last month.
SELECT a.*, COUNT(p.id) AS post_count
FROM authors a
LEFT JOIN posts p ON a.id = p.author_id
WHERE p.date >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND p.date < CURDATE()
GROUP BY a.id;

-- 41. Find posts written by authors with "Smily" in their name.
SELECT p.*
FROM posts p
JOIN authors a ON p.author_id = a.id
WHERE a.name LIKE '%Smily%';

-- 42. Update the description of posts written before 2020.
UPDATE posts
SET description = 'Updated description'
WHERE date < '2020-01-01';

-- 43. Create a function to calculate the total number of posts.
-- CREATE FUNCTION total_posts() RETURNS INT
-- BEGIN
--     DECLARE total INT;
--     SELECT COUNT(*) INTO total FROM posts;
--     RETURN total;
-- END;

-- 44. Find authors who have written more than 10 posts.
SELECT a.*
FROM authors a
JOIN posts p ON a.id = p.author_id
GROUP BY a.id
HAVING COUNT(p.id) > 10;

-- 45. Select the top 5 most recent posts.
SELECT *
FROM posts
ORDER BY date DESC
LIMIT 5;

-- 46. Find authors who joined after writing their first post.
SELECT a.*
FROM authors a
JOIN posts p ON a.id = p.author_id
GROUP BY a.id
HAVING MIN(p.date) < a.join_date;

-- 47. Create a view showing authors and their latest post.
CREATE VIEW authors_latest_post AS
SELECT a.*, p.*
FROM authors a
LEFT JOIN posts p ON a.id = p.author_id
WHERE p.date = (SELECT MAX(date) FROM posts WHERE author_id = a.id);

-- 48. Update an author's birthdate.
UPDATE authors
SET birthdate = '1980-01-01'
WHERE id = 1; -- Change to the specific author's ID

-- 49. Select authors who have the same first and last name.
SELECT *
FROM authors
WHERE first_name = last_name;

-- 50. Find the total number of posts written on each day.
SELECT date, COUNT(*) AS post_count
FROM posts
GROUP BY date;

-- 51. Find posts written by authors who have "John" in their first name.
SELECT p.*
FROM posts p
JOIN authors a ON p.author_id = a.id
WHERE a.first_name LIKE '%John%';

-- 52. Update the email domain for all authors.
UPDATE authors
SET email = CONCAT(SUBSTRING_INDEX(email, '@', 1), '@newdomain.com');

-- 53. Create an index on the posts title.
CREATE INDEX idx_posts_title ON posts (title);

-- 54. Find the shortest post content.
SELECT *
FROM posts
ORDER BY LENGTH(content) ASC
LIMIT 1;

-- 55. Select authors who have not been updated recently.
SELECT *
FROM authors
WHERE last_updated < DATE_SUB(CURDATE(), INTERVAL 1 YEAR);

-- 56. Find posts written in the last 30 days.
SELECT *
FROM posts
WHERE date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY);

-- 57. Create a view showing posts and their author's email.
CREATE VIEW posts_with_author_email AS
SELECT p.*, a.email
FROM posts p
JOIN authors a ON p.author_id = a.id;

-- 58. Find posts that were updated after being created.
SELECT *
FROM posts
WHERE last_updated > date;

-- 59. Select authors and their posts, including those with no posts.
SELECT a.*, p.*
FROM authors a
LEFT JOIN posts p ON a.id = p.author_id;

-- 60. Update all authors to have a default birthdate if null.
UPDATE authors
SET birthdate = '2000-01-01'
WHERE birthdate IS NULL;

-- 61. Select authors with the most posts in the current year.
SELECT a.*, COUNT(p.id) AS post_count
FROM authors a
JOIN posts p ON a.id = p.author_id
WHERE YEAR(p.date) = YEAR(CURDATE())
GROUP BY a.id
ORDER BY post_count DESC
LIMIT 1;

-- 62. Find posts by authors with example.com email.
SELECT p.*
FROM posts p
JOIN authors a ON p.author_id = a.id
WHERE a.email LIKE '%@example.com';

-- 63. Create a stored procedure to delete an author.
-- CREATE PROCEDURE delete_author(IN author_id INT)
-- BEGIN
--     DELETE FROM authors WHERE id = author_id;
-- END;

-- 64. Find posts written on the same day by different authors.
SELECT p1.*, p2.*
FROM posts p1
JOIN posts p2 ON p1.date = p2.date AND p1.author_id <> p2.author_id;

-- 65. Update titles of posts to be upper case.
UPDATE posts
SET title = UPPER(title);

-- 66. Create a function to return the number of authors.
-- CREATE FUNCTION total_authors() RETURNS INT
-- BEGIN
--     DECLARE total INT;
--     SELECT COUNT(*) INTO total FROM authors;
--     RETURN total;
-- END;

-- 67. Find the longest time span between posts by the same author.
SELECT author_id, MAX(date) - MIN(date) AS time_span
FROM posts
GROUP BY author_id
ORDER BY time_span DESC
LIMIT 1;

-- 68. Select authors and their posts, filtered by post content.
SELECT a.*, p.*
FROM authors a
JOIN posts p ON a.id = p.author_id
WHERE p.content LIKE '%specific content%'; -- Change '%specific content%' to the desired filter

-- 69. Create a trigger to log author deletions.
-- CREATE TRIGGER log_author_deletion
-- AFTER DELETE ON authors
-- FOR EACH ROW
-- BEGIN
--     INSERT INTO author_deletion_log (author_id, deletion_date)
--     VALUES (OLD.id, NOW());
-- END;

-- 70. Find posts written by authors who have ever written before.
SELECT p.*
FROM posts p
JOIN authors a ON p.author_id = a.id
WHERE EXISTS (SELECT 1 FROM posts WHERE author_id = a.id AND date < p.date);


-- 71. Find posts having char length 100.
SELECT * FROM posts WHERE CHAR_LENGTH(posts.title) - CHAR_LENGTH(REPLACE(posts.title, ' ', ''))=100;

-- 71. Update posts content to time stamp.
update posts set posts.content=date() where posts.id is not null;


-- 71. author chaged email once.
SELECT id, COUNT(DISTINCT email) as email_changes
FROM authors
GROUP BY id
HAVING email_changes > 1;


-- 75. avg post length.
SELECT AVG(LENGTH(content)) AS average_post_length
FROM posts;

-- 76. Top 3 author by no posts.
SELECT posts.author_id, MAX(posts.title) AS latest_title, COUNT(posts.author_id) AS cnt
FROM posts
GROUP BY posts.author_id
ORDER BY cnt DESC;

-- 77.find the post written by author with same birthdate


create database jdbcdb;







