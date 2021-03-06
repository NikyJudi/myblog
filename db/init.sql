use blog;

BEGIN;
INSERT INTO user (username, nickname, password, avatar) VALUES
  ('abc', 'ABC', '123', 'https://picsum.photos/id/1/200/200'),
  ('def', 'DEF', '456', 'https://picsum.photos/id/1/200/200'),
  ('ghi', 'GHI', '789', 'https://picsum.photos/id/1/200/200');

INSERT INTO category (user_id, name) VALUES
  (1, '默认'),
  (2, '编程'),
  (3, '生活');

INSERT INTO article (user_id, cover_image, category_id, title, content, created_at, updated_at) VALUES
  (1, 'https://picsum.photos/id/1/400/300', 1, '标题1', '随便写点什么1', '2019-12-28 21:00:00', '2019-12-30 21:00:00'),
  (1, 'https://picsum.photos/id/1/400/300', 2, '标题2', '随便写点什么2', '2019-12-27 21:00:00', '2019-12-30 21:00:00'),
  (1, 'https://picsum.photos/id/1/400/300', 3, '标题3', '随便写点什么3', '2019-12-24 21:00:00', '2019-12-30 21:00:00'),
  (2, 'https://picsum.photos/id/1/400/300', 1, '标题4', '随便写点什么4', '2019-12-14 21:00:00', '2019-12-30 21:00:00'),
  (2, 'https://picsum.photos/id/1/400/300', 2, '标题5', '随便写点什么5', '2019-12-03 21:00:00', '2019-12-30 21:00:00'),
  (3, 'https://picsum.photos/id/1/400/300', 3, '标题6', '随便写点什么6', '2019-12-30 21:00:00', '2019-12-30 21:00:00');

INSERT INTO comment (user_id, article_id, content, created_at) VALUES
  (3, 1, '写的挺好1', '2020-02-06 00:00:00'),
  (3, 1, '写的挺好2', '2020-02-04 00:00:00'),
  (3, 2, '写的挺好3', '2020-02-03 00:00:00'),
  (3, 3, '写的挺好4', '2020-02-07 00:00:00'),
  (2, 1, '同意', '2020-02-08 00:00:00'),
  (3, 3, '写的挺好', '2020-02-01 00:00:00'),
  (3, 2, '写的挺好', '2020-02-09 00:00:00'),
  (3, 1, '写的挺好', '2020-02-16 00:00:00'),
  (3, 1, '写的挺好', '2020-02-26 00:00:00'),
  (3, 1, '写的挺好', '2020-02-04 00:00:00');

COMMIT;


select * from article;