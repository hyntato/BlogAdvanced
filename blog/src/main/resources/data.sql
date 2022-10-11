insert into user (user_id, name, email) values (1, 'user1', 'user1@gmail.com');
insert into user (user_id, name, email) values (2, 'user2', 'user2@gmail.com');
insert into user (user_id, name, email) values (3, 'user3', 'user3@gmail.com');
insert into user (user_id, name, email) values (4, 'user4', 'user4@gmail.com');
insert into user (user_id, name, email) values (5, 'user5', 'user5@gmail.com');

insert into post (post_id, user_id, title, content) values (1, 1, 'post1', 'content1');
insert into post (post_id, user_id, title, content) values (2, 2, 'post2', 'content2');
insert into post (post_id, user_id, title, content) values (3, 3, 'post3', 'content3');
insert into post (post_id, user_id, title, content) values (4, 4, 'post4', 'content4');
insert into post (post_id, user_id, title, content) values (5, 5, 'post5', 'content5');

insert into comment (comment_id, user_id, post_id, parent_comment_id, content, is_deleted) values (1, 1, 1, 1, 'comment1', false);
insert into comment (comment_id, user_id, post_id, parent_comment_id, content, is_deleted) values (2, 2, 2, 1, 'comment2', false);
insert into comment (comment_id, user_id, post_id, parent_comment_id, content, is_deleted) values (3, 3, 3, 2, 'comment3', false);
insert into comment (comment_id, user_id, post_id, parent_comment_id, content, is_deleted) values (4, 4, 4, 3, 'comment4', false);
insert into comment (comment_id, user_id, post_id, parent_comment_id, content, is_deleted) values (5, 5, 5, 4, 'comment5', false);

insert into like_post (like_post_id, user_id, post_id) values (1, 1, 1);
insert into like_post (like_post_id, user_id, post_id) values (2, 2, 2);
insert into like_post (like_post_id, user_id, post_id) values (3, 3, 3);
insert into like_post (like_post_id, user_id, post_id) values (4, 4, 4);
insert into like_post (like_post_id, user_id, post_id) values (5, 5, 5);

insert into like_comment (like_comment_id, user_id, comment_id) values (1, 1, 1);
insert into like_comment (like_comment_id, user_id, comment_id) values (2, 2, 2);
insert into like_comment (like_comment_id, user_id, comment_id) values (3, 3, 3);
insert into like_comment (like_comment_id, user_id, comment_id) values (4, 4, 4);
insert into like_comment (like_comment_id, user_id, comment_id) values (5, 5, 5);

insert into hit (hit_id, post_id, count) values (1, 1, 1);
insert into hit (hit_id, post_id, count) values (2, 2, 2);
insert into hit (hit_id, post_id, count) values (3, 3, 3);
insert into hit (hit_id, post_id, count) values (4, 4, 4);
insert into hit (hit_id, post_id, count) values (5, 5, 5);