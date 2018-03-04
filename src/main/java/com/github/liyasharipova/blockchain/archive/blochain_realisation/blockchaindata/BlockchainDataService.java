package com.github.liyasharipova.blockchain.archive.blochain_realisation.blockchaindata;

/**
 * Сервис для работы с данными в блокчейне
 */
public interface BlockchainDataService {

    /**
     * Помещаем файл в транзакцию, далее в блок, потом в блокчейн
     * @param fileData данные файла
     * @param userId идентификатор пользователя
     * @param uploadDateTime время загрузки файла в мсек since January 1, 1970, 00:00:00 GMT
     * @return boolean загружен ли файл в блокчейн успешно
     */
    boolean placeToBlockchain(byte[] fileData, String userId, long uploadDateTime);
}