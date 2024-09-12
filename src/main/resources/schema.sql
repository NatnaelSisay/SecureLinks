-- Create table for roles
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Create table for user roles mapping (use email to identify users)
CREATE TABLE user_roles (
    email VARCHAR(100) NOT NULL,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
