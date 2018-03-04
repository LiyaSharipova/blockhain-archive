CREATE DATABASE blockhain_archive;
CREATE TABLE IF NOT EXISTS transaction
(
  id          BIGSERIAL PRIMARY KEY NOT NULL,
  file_hash   VARCHAR               NOT NULL,
  block_hash  VARCHAR               NOT NULL,
  user_id     VARCHAR               NOT NULL,
  create_time BIGINT                NOT NULL,
  file_name   TEXT                  NOT NULL,
  file_data   BYTEA                 NOT NULL
);

CREATE INDEX IF NOT EXISTS transaction__file_name__ix
  ON transaction (file_name);
COMMENT ON COLUMN transaction.file_name IS 'Название файла';
COMMENT ON COLUMN transaction.file_data IS 'Данные файла в байтовом представлении';
COMMENT ON COLUMN transaction.file_hash IS 'Хэш файла';
COMMENT ON COLUMN transaction.create_time IS 'Время загрузки файла в мсек после January 1, 1970, 00:00:00 GMT';
COMMENT ON TABLE transaction IS 'Транзакции загруженных файлов';


CREATE TABLE block
(
  hash          VARCHAR PRIMARY KEY,
  previous_hash VARCHAR NOT NULL
);
COMMENT ON COLUMN block.hash IS 'хеш блока';
COMMENT ON COLUMN block.previous_hash IS 'хеш предыдущего блока';