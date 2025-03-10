INSERT INTO users (username, email, password, roles, status, created_on, last_modified_on, created_by, modified_by)
VALUES
    ('blue1', 'blue1@harvest.com', '$2a$12$c6OJVB7jX8t2cyJWr2ji..KkiZDxBtnKu9HZLniltHalu6e19.IHy', 'ROLE_USER', 'ACTIVE', NOW(), NOW(), 'admin', 'admin'),

    ('blue2', 'blue2@harvest.com', '$2a$12$c6OJVB7jX8t2cyJWr2ji..KkiZDxBtnKu9HZLniltHalu6e19.IHy', 'ROLE_ADMIN', 'ACTIVE', NOW(), NOW(), 'admin', 'admin'),

    ('blue3', 'blue3@harvest.com', '$2a$12$c6OJVB7jX8t2cyJWr2ji..KkiZDxBtnKu9HZLniltHalu6e19.IHy', 'ROLE_USER', 'INACTIVE', NOW(), NOW(), 'admin', 'admin'),

    ('blue4', 'blue4r@harvest.com', '$2a$12$c6OJVB7jX8t2cyJWr2ji..KkiZDxBtnKu9HZLniltHalu6e19.IHy', 'ROLE_MANAGER', 'ACTIVE', NOW(), NOW(), 'system', 'system'),

    ('blue5', 'blue5@harvest.com', '$2a$12$c6OJVB7jX8t2cyJWr2ji..KkiZDxBtnKu9HZLniltHalu6e19.IHy', 'ROLE_USER', 'ACTIVE', NOW(), NOW(), 'system', 'admin');
