-- CREATE DATABASE blockhain_archive;


CREATE SCHEMA blockchain_archive;
CREATE TABLE IF NOT EXISTS blockchain_archive.transaction
(
  id          BIGSERIAL PRIMARY KEY NOT NULL,
  file_hash   VARCHAR               NOT NULL,
  block_hash  VARCHAR               NOT NULL,
  user_id     VARCHAR               NOT NULL,
  create_time bigint                not null,
  file_name   TEXT                  NOT NULL,
  file_data   BYTEA                 NOT NULL
);

CREATE INDEX IF NOT EXISTS transaction__file_name__ix
  ON blockchain_archive.transaction (file_name);
COMMENT ON COLUMN blockchain_archive.transaction.file_name IS 'Название файла';
COMMENT ON COLUMN blockchain_archive.transaction.file_data IS 'Данные файла в байтовом представлении';
COMMENT ON COLUMN blockchain_archive.transaction.file_hash IS 'Хэш файла';
comment on column blockchain_archive.transaction.create_time is 'Время загрузки файла в мсек после January 1, 1970, 00:00:00 GMT';
COMMENT ON TABLE blockchain_archive.transaction IS 'Транзакции загруженных файлов';


CREATE TABLE blockchain_archive.block
(
  hash          VARCHAR PRIMARY KEY,
  previous_hash VARCHAR NOT NULL
);
COMMENT ON COLUMN blockchain_archive.block.hash IS 'хеш блока';
COMMENT ON COLUMN blockchain_archive.block.previous_hash IS 'хеш предыдущего блока';