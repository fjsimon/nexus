INSERT INTO security_role (id, role_name, description) VALUES
(1, 'ROLE_ADMIN', 'Administrator');

-- non-encrypted password: letmein
INSERT INTO security_user (id, username, password, first_name, last_name) VALUES
(1,  'admin', '$2a$10$w08LwvSIZYAGzV3qyOOu9uyD35pmZLxDdHMppb2vS73Hm.y0Vh3OS', 'Admin', 'Admin');

INSERT INTO user_role(user_id, role_id) VALUES
(1, 1);

