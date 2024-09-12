-- Insert roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

-- Map users to roles by email (no password needed since you're using OAuth)
INSERT INTO user_roles (email, role_id) VALUES
    ('natyjava8@gmail.com', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
    ('user@example.com', (SELECT id FROM roles WHERE name = 'ROLE_USER'));
