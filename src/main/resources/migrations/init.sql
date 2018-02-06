-- CREATE DATABASE blockhain_archive;

CREATE TABLE IF NOT EXISTS file
(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  file_name TEXT NOT NULL,
  data BYTEA NOT NULL
);
CREATE INDEX IF NOT EXISTS file__file_name__ix ON file (file_name);
COMMENT ON COLUMN file.file_name IS 'Название файла';
COMMENT ON COLUMN file.data IS 'Данные файла в байтовом представлении';
COMMENT ON TABLE file IS 'Загруженные файлы';