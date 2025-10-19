[//]: # (insert dữ liệu ban đầu)
docker exec -it study_management_db bash
psql -U postgres -d subjectdb
\dt
SELECT tablename FROM pg_tables WHERE schemaname = 'public';
SELECT * FROM users LIMIT 10;