INSERT INTO worker (name, birthday, level, salary) VALUES
('Олена Коваленко', '1990-03-15', 'Trainee', 300),
('Іван Петренко', '1985-07-22', 'Junior', 500),
('Марія Іванова', '1993-11-05', 'Middle', 1000),
('Дмитро Шевченко', '1988-01-30', 'Senior', 2000),
('Софія Бондаренко', '2000-06-18', 'Trainee', 300),
('Андрій Мельник', '1991-12-09', 'Junior', 500),
('Наталія Ткаченко', '1987-04-25', 'Middle', 2000),
('Вікторія Поліщук', '1996-08-14', 'Senior', 4000),
('Олександр Кравчук', '1992-02-28', 'Trainee', 300),
('Анна Сидоренко', '1989-09-11', 'Junior', 500),
('Руслан Лисенко', '1994-05-03', 'Middle', 3000),
('Євген Козак', '1997-10-20', 'Senior', 6000),
('Тетяна Грищенко', '1984-07-07', 'Trainee', 300),
('Михайло Зінченко', '1999-12-30', 'Junior', 500),
('Людмила Павленко', '1995-03-23', 'Middle', 6000);

INSERT INTO client (name) VALUES
('Alice Johnson'),
('Bob Smith'),
('Charlie Brown'),
('Diana Prince'),
('Edward Kenway'),
('Fiona Glenanne'),
('George Washington'),
('Hannah Baker'),
('Ian Shaw'),
('Jane Doe');


INSERT INTO project (client_id, name, START_DATE, FINISH_DATE)
SELECT client.id, 'Project A', '2025-08-01'::DATE, '2025-10-25'::DATE
FROM client WHERE client.name = 'Alice Johnson'

UNION ALL

SELECT client.id, 'Project B', '2025-08-10'::DATE, '2025-11-25'::DATE
FROM client WHERE client.name = 'Bob Smith'

UNION ALL

SELECT client.id, 'Project C', '2025-08-01'::DATE, '2025-12-25'::DATE
FROM client WHERE client.name = 'Charlie Brown'

UNION ALL

SELECT client.id, 'Project D', '2025-08-01'::DATE, '2026-02-25'::DATE
FROM client WHERE client.name = 'Diana Prince'

UNION ALL

SELECT client.id, 'Project E', '2025-08-01'::DATE, '2026-06-25'::DATE
FROM client WHERE client.name = 'Edward Kenway'

UNION ALL

SELECT client.id, 'Project F', '2025-08-01'::DATE, '2026-10-25'::DATE
FROM client WHERE client.name = 'Fiona Glenanne'

UNION ALL

SELECT client.id, 'Project G', '2025-08-01'::DATE, '2027-10-25'::DATE
FROM client WHERE client.name = 'George Washington'

UNION ALL

SELECT client.id, 'Project H', '2025-09-01'::DATE, '2028-10-25'::DATE
FROM client WHERE client.name = 'George Washington';



INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
SELECT p.id, w.id
FROM project p
JOIN client c ON p.client_id = c.id
JOIN worker w ON w.name IN (
    'Марія Іванова',
    'Тетяна Грищенко',
    'Людмила Павленко'
)
WHERE c.name = 'Alice Johnson'

UNION ALL

SELECT p.id, w.id
FROM project p
JOIN client c ON p.client_id = c.id
JOIN worker w ON w.name IN (
    'Євген Козак',
    'Андрій Мельник'
)
WHERE c.name = 'Bob Smith'

UNION ALL

SELECT p.id, w.id
FROM project p
JOIN client c ON p.client_id = c.id
JOIN worker w ON w.name IN (
    'Дмитро Шевченко',
    'Михайло Зінченко',
    'Євген Козак'
)
WHERE c.name = 'Edward Kenway';

