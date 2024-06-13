use dummy;
-- 1. Select All Authors
SELECT * FROM authors;

-- 2. Select All Posts
SELECT * FROM posts;

-- 3. Select Authors with Specific Conditions
SELECT * FROM authors WHERE first_name = 'Andy';

-- 4. Select Posts with Specific Conditions
SELECT * FROM posts WHERE title LIKE '%repudiandae%';

-- 5. Join Authors and Posts
SELECT a.first_name, a.last_name, p.title, p.content
FROM authors a
JOIN posts p ON a.id = p.author_id;

-- 6. Count Authors
SELECT COUNT(*) AS author_count FROM authors;

-- 7. Count Posts
SELECT COUNT(*) AS post_count FROM posts;

-- 8. Find Authors with No Posts
SELECT a.*
FROM authors a
LEFT JOIN posts p ON a.id = p.author_id
WHERE p.id =100;

-- 9. Update Author Email
UPDATE authors SET email = 'new.email@example.com' WHERE id = 1;

-- 10. Update Post Title
UPDATE posts SET title = 'Updated Title' WHERE id = 1;

-- 11. Delete an Author
DELETE FROM authors WHERE id = 10;

-- 12. Delete a Post
DELETE FROM posts WHERE id = 10;

-- 13. Select Authors Born After 2000
SELECT * FROM authors WHERE birthdate > '2000-01-01';

-- 14. Select Posts with Content Length > 100
SELECT * FROM posts WHERE CHAR_LENGTH(content) > 100;

-- 15. Aggregate Function - Average Author Age
SELECT AVG(YEAR(CURDATE()) - YEAR(birthdate)) AS average_age FROM authors;

-- 16. Group By Author Birth Year
SELECT YEAR(birthdate) AS birth_year, COUNT(*) AS count FROM authors GROUP BY birth_year;

-- 17. Select Authors with Most Recent Post
SELECT p.title, p.content, p.created_at
FROM authors a
JOIN posts p ON a.id = p.author_id
ORDER BY p.created_at DESC
LIMIT 1;

-- 18. Select Top 5 Oldest Authors
SELECT * FROM authors ORDER BY birthdate ASC LIMIT 5;

-- 19. Select Top 5 Recent Posts
SELECT * FROM posts ORDER BY created_at DESC LIMIT 5;

-- 20. Count Posts Per Author
SELECT a.first_name, a.last_name, COUNT(p.id) AS post_count
FROM authors a
LEFT JOIN posts p ON a.id = p.author_id
GROUP BY a.id;

-- 21. Select Authors with Multiple Posts
SELECT a.first_name, a.last_name
FROM authors a
JOIN posts p ON a.id = p.author_id
GROUP BY a.id
HAVING COUNT(p.id) > 1;

-- 22. Select Author Details with Post Titles
SELECT a.*, p.title
FROM authors a
JOIN posts p ON a.id = p.author_id;

-- 23. Find Posts by Author Email
SELECT p.*
FROM posts p
JOIN authors a ON p.author_id = a.id
WHERE a.email = 'jerde.carson@example.net';



-- 24. Select Authors by First Letter of Last Name
SELECT * FROM authors WHERE last_name LIKE 'S%';

-- 25. Select Posts with Specific Keywords
SELECT * FROM posts WHERE content LIKE '%Dolorem autem %';

select * from posts;

-- 26. Select Authors with Birthday in a Specific Month
SELECT * FROM authors WHERE MONTH(birthdate) = 1;

-- 27. Select Posts Created in the Last Year
SELECT * FROM posts WHERE created_at > DATE_SUB(CURDATE(), INTERVAL 1 YEAR);

-- 28. Find Authors with Gmail Accounts
SELECT * FROM authors WHERE email LIKE '%@example.org';

select * from authors;

-- 29. Select Authors with Birthdays This Month
SELECT * FROM authors WHERE MONTH(birthdate) = MONTH(CURDATE());

-- 30. Select Authors without Recent Posts
SELECT a.*
FROM authors a
LEFT JOIN posts p ON a.id = p.author_id
WHERE (p.date IS NULL OR p.date < DATE_SUB(CURDATE(), INTERVAL 1 YEAR));

select * from posts;
-- 31. Count Authors by Domain
SELECT SUBSTRING_INDEX(email, '@', -1) AS domain, COUNT(*) AS count
FROM authors
GROUP BY domain;

-- 32. Select Posts with Most Comments
SELECT * FROM posts ORDER BY content DESC LIMIT 5;


-- 33. Select Authors by Last Name Initial
SELECT * FROM authors WHERE LEFT(last_name, 1) = 'J';

-- 34. Find Posts with Specific Date Range
SELECT * FROM posts WHERE created_at BETWEEN '2023-01-01' AND '2023-12-31';

-- 35. Select Authors with Long Names
SELECT * FROM authors WHERE CHAR_LENGTH(first_name) + CHAR_LENGTH(last_name) > 15;

-- 36. Update Post Content
UPDATE posts SET content = 'Updated content' WHERE id = 2;

-- 37. Delete Posts by Author ID
DELETE FROM posts WHERE author_id = 3;

-- 38. Select Authors by Age Range
SELECT *, YEAR(CURDATE()) - YEAR(birthdate) AS age
FROM authors
HAVING age BETWEEN 20 AND 30;

-- 39. Select Posts by Word Count
SELECT * FROM posts WHERE CHAR_LENGTH(content) - CHAR_LENGTH(REPLACE(content, ' ', '')) + 1 < 20;

-- 40. Select Authors by Date Added
SELECT * FROM authors WHERE added > '2022-01-01';

-- 41. Count Posts by Year
SELECT YEAR(date) AS year, COUNT(*) AS count
FROM posts
GROUP BY year;


-- 42. Select Authors with Posts in the Last 5 Year
SELECT DISTINCT a.*
FROM authors a
JOIN posts p ON a.id = p.author_id
WHERE p.date > DATE_SUB(CURDATE(), INTERVAL 5 YEAR);



-- 43. Select Top 3 Authors by Post Count
SELECT a.first_name, a.last_name, COUNT(p.id) AS post_count
FROM authors a
JOIN posts p ON a.id = p.author_id
GROUP BY a.id
ORDER BY post_count DESC
LIMIT 3;

-- 44. Find Authors with No Email
SELECT * FROM authors WHERE email IS NULL;

-- 45. Select Posts with Specific Word in Title
SELECT * FROM posts WHERE title LIKE '%perspiciatis %';


-- 46. Select Authors by Specific Birth Year
SELECT * FROM authors WHERE YEAR(birthdate) = 1990;

-- 47. Select Recent Posts by Specific Author
SELECT p.*
FROM posts p
JOIN authors a ON p.author_id = a.id
WHERE a.first_name = 'Napoleon' AND a.last_name = 'Dibbert'
ORDER BY p.date DESC;




-- 48. Select Posts with Unique Titles
SELECT DISTINCT title FROM posts;

-- 49. Select Authors with Maximum Posts
SELECT a.first_name, a.last_name, COUNT(p.id) AS post_count
FROM authors a
JOIN posts p ON a.id = p.author_id
GROUP BY a.id
ORDER BY post_count DESC
LIMIT 1;

-- 50. Find Authors Added in the Last Week
SELECT * FROM authors WHERE added > DATE_SUB(CURDATE(), INTERVAL 1 WEEK);
