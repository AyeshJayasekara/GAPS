DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;

CREATE TABLE users
(
    id       int(11) AUTO_INCREMENT,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled  TINYINT      NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

#ALL USERS WILL BY DEFAULT HAVE USER ROLE

CREATE TRIGGER `default_user_trigger`
    AFTER INSERT
    ON `users`
    FOR EACH ROW
BEGIN
    INSERT INTO `authorities` VALUE (NEW.username, 'ROLE_USER');
END;

